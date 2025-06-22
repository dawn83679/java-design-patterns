package com.dawn.patterns.behavioral.iterator.examples;

import java.util.Objects;

/**
 * 
 * @author Dawn
 * @version 1.0.0
 */
public class Book {
    
    private final String title;
    private final String author;
    
    /**
     * 构造函数
     * 
     * @param title 书名
     * @param author 作者
     */
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
    
    // Getters
    public String getTitle() { 
        return title; 
    }
    
    public String getAuthor() { 
        return author; 
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) && Objects.equals(author, book.author);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }
    
    @Override
    public String toString() {
        return String.format("Book{title='%s', author='%s'}", title, author);
    }
}
