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

/**
 * Controller for the update stage.
 *
 * @author David Lõssenko
 */
public class UpdateStageController implements Initializable {
    /**
     * The title text field.
     */
    public TextField txtTitle;

    /**
     * The content text area.
     */
    public TextArea txtContent;

    /**
     * The "Confirm" button.
     */
    public Button btnConfirm;

    /**
     * The "Cancel" button.
     */
    public Button btnCancel;

    /**
     * Note that is currently being edited. The value is null if the stage is opened in the "add new" mode.
     */
    private Note editingNote;

    /**
     * List view from the main stage controller. The value is null if the stage is opened in the "add new" mode.
     */
    private ListView<Note> listView;

    /**
     * On controller initialization.
     *
     * @param location Location
     * @param resources Resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Nothing to do here.
    }

    /**
     * Sets the current mode of the stage to "edit note" mode.
     *
     * @param note Note that is being edited.
     * @param listView List view of the notes from the main stage controller.
     */
    public void editNote(Note note, ListView<Note> listView) {
        // Setting all the values.
        this.editingNote = note;
        this.listView = listView;

        txtTitle.setText(note.getTitle());
        txtContent.setText(note.getContent());
    }

    /**
     * When the "Confirm" button is clicked.
     *
     * @param actionEvent Event data.
     */
    public void onBtnConfirm(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage updateWindow = (Stage) source.getScene().getWindow();

        // If the current mode is "add note".
        if (editingNote == null) {
            // Generating a new ID.
            int id = 0;
            while (NotesApp.noteIdExists(id)) {
                id++;
            }

            // Adding the note.
            NotesApp.notes.add(new Note(id, txtTitle.getText(), txtContent.getText()));

            updateWindow.close();
        // Otherwise, if the mode is "edit note".
        } else {
            // Changing the value of the note.
            editingNote.setTitle(txtTitle.getText());
            editingNote.setContent(txtContent.getText());

            // Updating the list view.
            listView.getItems().set(listView.getSelectionModel().getSelectedIndex(), editingNote);

            updateWindow.close();
        }
    }

    /**
     * When the "Cancel" button is clicked.
     *
     * @param actionEvent Event data.
     */
    public void onBtnCancel(ActionEvent actionEvent) {
        // Closing the window.
        Node source = (Node) actionEvent.getSource();
        Stage updateWindow = (Stage) source.getScene().getWindow();
        updateWindow.close();
    }
}
