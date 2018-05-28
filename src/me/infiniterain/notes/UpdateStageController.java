package me.infiniterain.notes;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class UpdateStageController implements Initializable {
	public TextArea txtContent;
	public Button btnConfirm;
	public Button btnCancel;

	@Override
	public void initialize(URL location, ResourceBundle resources) { }

	public void onBtnConfirm(ActionEvent actionEvent) {
		int id = 0;
		while (NotesApp.noteIdExists(id)) {
			id++;
		}

		NotesApp.notes.add(new Note(id, txtContent.getText()));

		Node source = (Node) actionEvent.getSource();
		Stage updateWindow = (Stage) source.getScene().getWindow();
		updateWindow.close();
	}

	public void onBtnCancel(ActionEvent actionEvent) {
		Node source = (Node) actionEvent.getSource();
		Stage updateWindow = (Stage) source.getScene().getWindow();
		updateWindow.close();
	}
}
