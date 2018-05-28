package me.infiniterain.notes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class NotesApp extends Application {

    public static ObservableList<Note> notes = FXCollections.observableList(new ArrayList<Note>());

    @Override
    public void start(Stage mainStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("scenes/main.fxml"));
        mainStage.setTitle("Notes by David Lõssenko");
        mainStage.setScene(new Scene(root));
        mainStage.setResizable(false);
        mainStage.show();
    }

    public static void main(String[] args) throws IOException {
        File file = new File("notes.json");
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    System.out.println("Unable to create \"notes.json\".");
                } else {
                    PrintWriter p = new PrintWriter(new FileOutputStream(file, false));
                    p.print("[]");
                    p.close();
                }
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
