package me.infiniterain.notes;

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

    public static void main(String[] args) {
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
