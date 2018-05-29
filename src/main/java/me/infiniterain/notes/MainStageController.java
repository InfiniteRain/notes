package me.infiniterain.notes;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Controller for the main stage.
 *
 * @author David Lõssenko
 */
public class MainStageController implements Initializable {
    /**
     * The "New" button.
     */
    public Button btnNew;

    /**
     * The "Delete Selected" button.
     */
    public Button btnDelete;

    /**
     * The "Edit Selected" button.
     */
    public Button btnEdit;

    /**
     * List view of all the notes.
     */
    public ListView<Note> lstNotes;

    /**
     * Text area that displays notes' content.
     */
    public TextArea txtView;

    /**
     * On controller initialisation.
     *
     * @param location Location
     * @param resources Resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Setting items of the list view to the currently loaded notes.
        lstNotes.setItems(NotesApp.notes);

        // On mouse click event.
        lstNotes.setOnMouseClicked((MouseEvent click) -> {
            if (click.getClickCount() == 2) {
                // On double click, edit mode.
                try {
                    onBtnEdit();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // On single click, display mode.
                Note selectedNote = lstNotes.getSelectionModel().getSelectedItem();
                if (selectedNote != null) {
                    txtView.setText(selectedNote.getContent());
                }
            }
        });
    }

    /**
     * When the "New" button is clicked.
     *
     * @throws IOException If scene FXML has failed to load.
     */
    public void onBtnNew() throws IOException {
        // Opening the update stage in "new" mode.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/update.fxml"));
        Stage updateStage = new Stage(StageStyle.DECORATED);
        updateStage.setScene(new Scene(loader.load()));
        updateStage.setTitle("New note");
        updateStage.setResizable(false);
        updateStage.show();
    }

    /**
     * When the "Edit Selected" button is clicked.
     *
     * @throws IOException If scene FXML has failed to load.
     */
    public void onBtnEdit() throws IOException {
        // Opening the update stage in "edit" mode.
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

    /**
     * When the "Delete Selected" button is clicked.
     */
    public void onBtnDelete() {
        // Deleting the currently selected item.
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
