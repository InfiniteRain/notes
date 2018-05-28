package me.infiniterain.notes;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainStageController implements Initializable {
	public Button btnNew;
	public Button btnDelete;
	public Button btnEdit;
	public ListView<Note> lstNotes;
	public TextArea txtView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lstNotes.setItems(NotesApp.notes);

		lstNotes.setOnMouseClicked((MouseEvent click) -> {
			if (click.getClickCount() == 2) {
				try {
					onBtnEdit();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				Note selectedNote = lstNotes.getSelectionModel().getSelectedItem();
				if (selectedNote != null) {
					txtView.setText(selectedNote.getContent());
				}
			}
		});
	}

	public void onBtnNew() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/update.fxml"));
		Stage updateStage = new Stage(StageStyle.DECORATED);
		updateStage.setScene(new Scene(loader.load()));
		updateStage.setTitle("New note");
		updateStage.setResizable(false);
		updateStage.show();
	}

	public void onBtnEdit() throws IOException {
		Note selectedNote = lstNotes.getSelectionModel().getSelectedItem();
		if (selectedNote != null) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/update.fxml"));
			Stage updateStage = new Stage(StageStyle.DECORATED);
			updateStage.setScene(new Scene(loader.load()));
			UpdateStageController controller = loader.getController();
			controller.editNote(selectedNote, lstNotes);
			updateStage.setTitle("Edit note");
			updateStage.setResizable(false);
			updateStage.show();
		}
	}

	public void onBtnDelete(ActionEvent actionEvent) {
		Note selectedNote = lstNotes.getSelectionModel().getSelectedItem();
		Alert alert = new Alert(
				Alert.AlertType.WARNING,
				"Are you sure, that you want to delete the note titled \"" +
						selectedNote.getTitle() + "\"?",
				ButtonType.YES,
				ButtonType.NO
		);
		alert.setTitle("Are you sure?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.isPresent() && result.get() == ButtonType.YES) {
			lstNotes.getItems().remove(lstNotes.getSelectionModel().getSelectedIndex());
			txtView.setText("");
		}
	}
}
