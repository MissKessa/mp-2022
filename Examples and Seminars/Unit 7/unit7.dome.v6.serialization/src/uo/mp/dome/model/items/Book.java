package uo.mp.dome.model.items;

import uo.mp.dome.model.Borrowable;

@SuppressWarnings("serial")
public class Book extends Item implements Borrowable {
	private boolean available;
	private String author;
	private String ISBN;
	private String publisher;


	public Book(String title, String isbn) {
		super(title);
		this.ISBN = isbn;
		this.available = false;
	}

	@Override
	public boolean isAvailable() {
		return this.getOwn() && available;
	}

	@Override
	public void borrow() {
		this.available = false;
	}

	@Override
	public void giveBack() {
		this.available = true;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getISBN() {
		return ISBN;
	}

	@Override
	public String toString() {
		return "Book [available=" + available 
				+ ", author=" + author 
				+ ", ISBN=" + ISBN 
				+ ", publisher=" + publisher
				+ ", getComment()=" + getComment() 
				+ ", getOwn()=" + getOwn() 
				+ ", getTitle()=" + getTitle() 
			+ "]";
	}

	
}
