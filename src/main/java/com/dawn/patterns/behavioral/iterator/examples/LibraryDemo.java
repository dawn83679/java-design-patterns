package com.dawn.patterns.behavioral.iterator.examples;

import com.dawn.patterns.behavioral.iterator.core.Iterator;

/**
 * 简化的书籍集合演示程序
 * 专注于迭代器模式演示，去除复杂业务逻辑
 * 
 * @author Dawn
 * @version 1.0.0
 */
public class LibraryDemo {
    
    public static void main(String[] args) {
        System.out.println("=== 迭代器模式 - 书籍集合演示 ===\n");
        
        // 创建书籍集合
        BookCollection library = new BookCollection("学习图书馆");
        
        // 添加一些书籍
        addSampleBooks(library);
        
        System.out.println("图书馆信息: " + library);
        System.out.println();
        
        //  使用迭代器遍历所有书籍
        demonstrateBasicIteration(library);
        
        System.out.println("\n=== 迭代器模式演示结束 ===");
    }
    
    /**
     * 添加示例书籍
     */
    private static void addSampleBooks(BookCollection library) {
        library.addBook(new Book("Effective Java", "Joshua Bloch"));
        library.addBook(new Book("Design Patterns", "GoF"));
        library.addBook(new Book("Clean Code", "Robert Martin"));
        library.addBook(new Book("Java核心技术", "Cay Horstmann"));
        library.addBook(new Book("算法导论", "Thomas Cormen"));
        library.addBook(new Book("数据结构", "Mark Weiss"));
        library.addBook(new Book("操作系统", "Abraham Silberschatz"));
    }
    
    /**
     * 演示基本的迭代功能
     */
    private static void demonstrateBasicIteration(BookCollection library) {
        System.out.println("1. 使用迭代器遍历所有书籍:");
        Iterator<Book> iterator = library.createIterator();
        int count = 1;
        while (iterator.hasNext()) {
            Book book = iterator.next();
            System.out.printf("  %d. %s\n", count++, book);
        }
        System.out.println();
    }

}
