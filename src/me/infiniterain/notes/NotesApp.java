package me.infiniterain.notes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NotesApp extends Application {

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

}
