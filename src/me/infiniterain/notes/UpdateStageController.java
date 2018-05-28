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
	public TextArea txtId;
	public Button btnConfirm;
	public Button btnCancel;

	@Override
	public void initialize(URL location, ResourceBundle resources) { }

	public void onBtnConfirm(ActionEvent actionEvent) {
	}

	public void onBtnCancel(ActionEvent actionEvent) {
		Node source = (Node) actionEvent.getSource();
		Stage updateWindow = (Stage) source.getScene().getWindow();
		updateWindow.close();
	}
}
