package me.infiniterain.notes;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateStageController implements Initializable {
	public TextField txtTitle;
	public TextArea txtContent;
	public Button btnConfirm;
	public Button btnCancel;

	private Note editingNote;
	private ListView<Note> listView;

	@Override
	public void initialize(URL location, ResourceBundle resources) { }

	public void editNote(Note note, ListView<Note> listView) {
		this.editingNote = note;
		this.listView = listView;

		txtTitle.setText(note.getTitle());
		txtContent.setText(note.getContent());
	}

	public void onBtnConfirm(ActionEvent actionEvent) {
		Node source = (Node) actionEvent.getSource();
		Stage updateWindow = (Stage) source.getScene().getWindow();

		if (editingNote == null) {
			int id = 0;
			while (NotesApp.noteIdExists(id)) {
				id++;
			}

			NotesApp.notes.add(id, new Note(id, txtTitle.getText(), txtContent.getText()));

			updateWindow.close();
		} else {
			editingNote.setTitle(txtTitle.getText());
			editingNote.setContent(txtContent.getText());
			listView.getItems().set(editingNote.getId(), editingNote);

			updateWindow.close();
		}
	}

	public void onBtnCancel(ActionEvent actionEvent) {
		Node source = (Node) actionEvent.getSource();
		Stage updateWindow = (Stage) source.getScene().getWindow();
		updateWindow.close();
	}
}
