package me.infiniterain.notes;

public class Note {
	private int id;
	private String title;
	private String content;

	Note(int id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}

	int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return title;
	}

}
