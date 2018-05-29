package me.infiniterain.notes;

/**
 * Note class.
 *
 * @author David Lõsssenko
 */
public class Note {
    /**
     * Id of the note.
     */
    private int id;

    /**
     * Title of the note.
     */
    private String title;

    /**
     * Content of the note.
     */
    private String content;

    /**
     * Constructor.
     *
     * @param id Id of the note.
     * @param title Title of the note.
     * @param content Content of the note.
     */
    public Note(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    /**
     * Gets the note ID.
     *
     * @return Note ID.
     */
    int getId() {
        return id;
    }

    /**
     * Gets the note title.
     *
     * @return Note title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the note title.
     *
     * @param title New title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the note content.
     *
     * @return Note content.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the note content.
     *
     * @param content New content.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Overriding the toString method.
     *
     * @return The title of the note.
     */
    @Override
    public String toString() {
        return title;
    }
}
