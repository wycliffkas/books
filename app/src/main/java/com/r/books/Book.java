package com.r.books;

public class Book {
    private String id;
    private String title;
    private String subTitle;
    private String[] authors;
    private String publisher;
    private String publishedDate;

    public Book(String id, String title, String subTitle, String[] authors,
                String publisher, String publishedDate) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.authors = authors;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String[] getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }
}
