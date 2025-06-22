package com.dawn.patterns.behavioral.iterator.examples;

import com.dawn.patterns.behavioral.iterator.core.Aggregate;
import com.dawn.patterns.behavioral.iterator.core.Iterator;

/**
 * 基于数组的书籍集合实现
 * 展示不同数据结构的统一遍历接口
 * 
 * @author Dawn
 * @version 1.0.0
 */
public class ArrayBookCollection implements Aggregate<Book> {
    
    private Book[] books;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    
    /**
     * 构造函数
     */
    public ArrayBookCollection() {
        this.books = new Book[DEFAULT_CAPACITY];
        this.size = 0;
    }
    
    /**
     * 添加书籍
     * 
     * @param book 要添加的书籍
     */
    public void addBook(Book book) {
        if (book == null) return;
        
        if (size >= books.length) {
            // 扩容
            Book[] newBooks = new Book[books.length * 2];
            System.arraycopy(books, 0, newBooks, 0, size);
            books = newBooks;
        }
        books[size++] = book;
    }
    
    /**
     * 根据索引获取书籍
     * 
     * @param index 索引
     * @return 书籍
     */
    public Book getBook(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("索引越界: " + index);
        }
        return books[index];
    }
    
    /**
     * 获取书籍数量
     * 
     * @return 书籍数量
     */
    public int getSize() {
        return size;
    }
    
    /**
     * 创建迭代器
     * 
     * @return 用于遍历数组的迭代器
     */
    @Override
    public Iterator<Book> createIterator() {
        return new ArrayBookIterator(this);
    }
    
    @Override
    public String toString() {
        return String.format("ArrayBookCollection{size=%d, capacity=%d}", size, books.length);
    }
    
    /**
     * 数组书籍迭代器
     */
    private static class ArrayBookIterator implements Iterator<Book> {
        private final ArrayBookCollection collection;
        private int currentIndex;
        
        public ArrayBookIterator(ArrayBookCollection collection) {
            this.collection = collection;
            this.currentIndex = 0;
        }
        
        @Override
        public boolean hasNext() {
            return currentIndex < collection.getSize();
        }
        
        @Override
        public Book next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException("没有更多元素");
            }
            return collection.getBook(currentIndex++);
        }
    }
}
