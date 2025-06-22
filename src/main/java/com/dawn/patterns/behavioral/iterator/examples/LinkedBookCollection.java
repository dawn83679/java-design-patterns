package com.dawn.patterns.behavioral.iterator.examples;

import com.dawn.patterns.behavioral.iterator.core.Aggregate;
import com.dawn.patterns.behavioral.iterator.core.Iterator;

/**
 * 基于链表的书籍集合实现
 * 展示不同数据结构的统一遍历接口
 * 
 * @author Dawn
 * @version 1.0.0
 */
public class LinkedBookCollection implements Aggregate<Book> {
    
    private Node head;
    private int size;
    
    /**
     * 构造函数
     */
    public LinkedBookCollection() {
        this.head = null;
        this.size = 0;
    }
    
    /**
     * 添加书籍
     * 
     * @param book 要添加的书籍
     */
    public void addBook(Book book) {
        if (book == null) return;
        
        Node newNode = new Node(book);
        if (head == null) {
            head = newNode;
        } else {
            // 添加到链表尾部
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
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
     * 获取头节点（仅供迭代器使用）
     * 
     * @return 头节点
     */
    Node getHead() {
        return head;
    }
    
    /**
     * 创建迭代器
     * 
     * @return 用于遍历链表的迭代器
     */
    @Override
    public Iterator<Book> createIterator() {
        return new LinkedBookIterator(this);
    }
    
    @Override
    public String toString() {
        return String.format("LinkedBookCollection{size=%d}", size);
    }
    
    /**
     * 链表节点
     */
    static class Node {
        Book book;
        Node next;
        
        Node(Book book) {
            this.book = book;
            this.next = null;
        }
    }
    
    /**
     * 链表书籍迭代器
     */
    private static class LinkedBookIterator implements Iterator<Book> {
        private Node current;
        
        public LinkedBookIterator(LinkedBookCollection collection) {
            this.current = collection.getHead();
        }
        
        @Override
        public boolean hasNext() {
            return current != null;
        }
        
        @Override
        public Book next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException("没有更多元素");
            }
            Book book = current.book;
            current = current.next;
            return book;
        }
    }
}
