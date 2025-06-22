package com.dawn.patterns.behavioral.iterator.examples;

import com.dawn.patterns.behavioral.iterator.core.Aggregate;
import com.dawn.patterns.behavioral.iterator.core.Iterator;
import java.util.ArrayList;
import java.util.List;

/**
 * 简化的书籍集合类
 * 实现了Aggregate接口，专注于迭代器模式演示
 * 
 * @author Dawn
 * @version 1.0.0
 */
public class BookCollection implements Aggregate<Book> {
    
    private final String collectionName;
    private final List<Book> books;
    
    /**
     * 构造函数
     * 
     * @param collectionName 集合名称
     */
    public BookCollection(String collectionName) {
        this.collectionName = collectionName;
        this.books = new ArrayList<>();
    }
    
    /**
     * 添加书籍
     * 
     * @param book 要添加的书籍
     */
    public void addBook(Book book) {
        if (book != null) {
            books.add(book);
        }
    }
    
    /**
     * 移除书籍
     * 
     * @param book 要移除的书籍
     * @return 如果成功移除返回true，否则返回false
     */
    public boolean removeBook(Book book) {
        return books.remove(book);
    }
    
    /**
     * 根据索引获取书籍
     * 
     * @param index 书籍索引
     * @return 指定索引的书籍
     * @throws IndexOutOfBoundsException 如果索引越界
     */
    public Book getBook(int index) {
        return books.get(index);
    }
    
    /**
     * 获取内部书籍数量（仅供迭代器使用）
     * 
     * @return 书籍数量
     */
    int getSize() {
        return books.size();
    }
    
    /**
     * 创建迭代器
     * 
     * @return 用于遍历书籍集合的迭代器
     */
    @Override
    public Iterator<Book> createIterator() {
        return new BookIterator(this);
    }
    
    /**
     * 获取集合名称
     * 
     * @return 集合名称
     */
    public String getCollectionName() {
        return collectionName;
    }
    
    @Override
    public String toString() {
        return String.format("BookCollection{name='%s', size=%d}", collectionName, getSize());
    }
}
