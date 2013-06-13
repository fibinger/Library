package pl.fibinger.model;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;
import org.hibernate.validator.constraints.NotEmpty;

public class Book {

    @Id
    @ObjectId
	private String id;

    @NotEmpty
	private String title;

    @NotEmpty
	private String isbn;

    @NotEmpty
	private String author;
	
	Book() { }
	
	public Book(String title, String author, String isbn) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
	}
	
	public Book(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Override
	public String toString() {
		return String.format("book id: %s, title: %s, isbn: %s", getId(), getTitle(), getIsbn());
	}
	

}
