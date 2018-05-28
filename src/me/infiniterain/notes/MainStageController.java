package me.infiniterain.notes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainStageController implements Initializable {
	public Button btnNew;
	public Button btnDelete;
	public Button btnEdit;
	public ListView<Note> lstNotes;
	public TextArea txtView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lstNotes.setItems(NotesApp.notes);
	}

	public void onBtnNew(ActionEvent actionEvent) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("scenes/update.fxml"));
		Stage updateStage = new Stage();
		updateStage.initModality(Modality.APPLICATION_MODAL);
		updateStage.setTitle("New note");
		updateStage.setScene(new Scene(root));
		updateStage.setResizable(false);
		updateStage.show();
	}
}
