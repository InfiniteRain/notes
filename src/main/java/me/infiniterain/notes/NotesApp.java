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

/**
 * Main class for this application.
 *
 * @author David Lõssenko
 */
public class NotesApp extends Application {
    /**
     * Observable list of notes.
     */
    public static ObservableList<Note> notes = FXCollections.observableList(new ArrayList<Note>());

    /**
     * On application start.
     *
     * @param mainStage The main stage of the application.
     * @throws IOException If scene FXML has failed to load.
     */
    @Override
    public void start(Stage mainStage) throws IOException {
        // Loading main stage.
        Parent root = FXMLLoader.load(getClass().getResource("/scenes/main.fxml"));
        mainStage.setTitle("Notes by David Lõssenko");
        mainStage.setScene(new Scene(root));
        mainStage.setResizable(false);

        // On close, saving the notes to the JSON file.
        mainStage.setOnHiding((WindowEvent event) -> Platform.runLater(() -> {
            // Generating JSON.
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

            // Outputting JSON to the "note.json" file.
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

        // Displaying the stage.
        mainStage.show();
    }

    /**
     * Main method.
     *
     * @param args Passed arguments.
     */
    public static void main(String[] args) {
        try {
            // Loading notes from the "notes.json" file.
            File file = new File("notes.json");

            // If the file doesn't exist, creating it.
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

            // Reading the JSON file, and loading the notes.
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

        // Launching the application.
        launch(args);
    }

    /**
     * Checks whether or not a note with provided ID exists.
     *
     * @param id Id to check against.
     * @return True/false depending on whether or not the ID exists.
     */
    public static boolean noteIdExists(int id) {
        for (Note note : NotesApp.notes) {
            if (note.getId() == id) {
                return true;
            }
        }

        return false;
    }
}
