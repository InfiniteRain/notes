package me.infiniterain.notes;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.json.JSONArray;
import org.json.JSONObject;

public class NotesApp extends Application {

	public static ObservableList<Note> notes = FXCollections.observableList(new ArrayList<Note>());

	@Override
	public void start(Stage mainStage) throws Exception {
		System.out.println("Working Directory = " +
				System.getProperty("user.dir"));
		Parent root = FXMLLoader.load(getClass().getResource("/scenes/main.fxml"));
		mainStage.setTitle("Notes by David Lõssenko");
		mainStage.setScene(new Scene(root));
		mainStage.setResizable(false);
		mainStage.setOnHiding((WindowEvent event) -> Platform.runLater(() -> {
			JSONObject json = new JSONObject();
			ArrayList<JSONObject> jsonNotes = new ArrayList<>();
			for (Note note : notes) {
				JSONObject jsonNote = new JSONObject();
				jsonNote.put("id", note.getId());
				jsonNote.put("title", note.getTitle());
				jsonNote.put("content", note.getContent());
				jsonNotes.add(jsonNote);
			}
			json.put("notes", jsonNotes);

			try {
				File file = new File("notes.json");
				if (!file.exists()) {
					if (!file.createNewFile()) {
						System.out.println("Unable to create \"notes.json\".");
					}
				}

				PrintWriter p = new PrintWriter(new FileOutputStream(file, false));
				p.println(json);
				p.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}));
		mainStage.show();
	}

	public static void main(String[] args) throws IOException {
		try {
			File file = new File("notes.json");
			if (!file.exists()) {
				if (!file.createNewFile()) {
					System.out.println("Unable to create \"notes.json\".");
				} else {
					PrintWriter p = new PrintWriter(new FileOutputStream(file, false));
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("notes", new ArrayList<String>());
					p.println(jsonObject);
					System.out.println(jsonObject.toString());
					p.close();
				}
			}

			JSONObject jsonObject = new JSONObject(new String(Files.readAllBytes(Paths.get("notes.json"))));
			JSONArray notes = jsonObject.getJSONArray("notes");
			for (int i = 0; i < notes.length(); i++) {
				JSONObject note = notes.getJSONObject(i);
				NotesApp.notes.add(
						new Note(note.getInt("id"), note.getString("title"), note.getString("content"))
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		launch(args);
	}

	public static boolean noteIdExists(int id) {
		for (Note note : NotesApp.notes) {
			if (note.getId() == id) {
				return true;
			}
		}

		return false;
	}

}
