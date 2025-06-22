package com.dawn.patterns.behavioral.iterator.advanced;

import com.dawn.patterns.behavioral.iterator.examples.Book;
import com.dawn.patterns.behavioral.iterator.examples.BookCollection;

import java.util.function.Predicate;

/**
 * 简化的高级迭代器演示程序
 * 专注于迭代器模式的高级特性演示
 * 
 * @author Dawn
 * @version 1.0.0
 */
public class AdvancedIteratorDemo {
    
    public static void main(String[] args) {
        System.out.println("=== 高级迭代器模式演示 ===\n");
        
        // 创建书籍集合
        BookCollection library = createSampleLibrary();
        System.out.println("图书馆信息: " + library);
        System.out.println();
        
        // 1. 演示过滤迭代器
        demonstrateFilterIterator(library);
        
        // 2. 演示反向迭代器
        demonstrateReverseIterator(library);

        System.out.println("\n=== 高级迭代器模式演示结束 ===");
    }
    
    /**
     * 创建示例图书馆
     */
    private static BookCollection createSampleLibrary() {
        BookCollection library = new BookCollection("高级模式演示图书馆");
        
        library.addBook(new Book("Effective Java", "Joshua Bloch"));
        library.addBook(new Book("Design Patterns", "GoF"));
        library.addBook(new Book("Clean Code", "Robert Martin"));
        library.addBook(new Book("Java核心技术", "Cay Horstmann"));
        library.addBook(new Book("算法导论", "Thomas Cormen"));
        library.addBook(new Book("数据结构", "Mark Weiss"));
        library.addBook(new Book("操作系统", "Abraham Silberschatz"));
        library.addBook(new Book("计算机网络", "Andrew Tanenbaum"));
        
        return library;
    }
    
    /**
     * 演示过滤迭代器
     */
    private static void demonstrateFilterIterator(BookCollection library) {
        System.out.println("1. 过滤迭代器演示:");
        
        // 1.1 过滤包含"Java"的书籍
        System.out.println("  1.1 只显示标题包含'Java'的书籍:");
        Predicate<Book> javaFilter = book -> book.getTitle().contains("Java");
        FilterIterator<Book> javaIterator = new FilterIterator<>(library.createIterator(), javaFilter);
        
        int count = 1;
        while (javaIterator.hasNext()) {
            Book book = javaIterator.next();
            System.out.printf("    %d. %s\n", count++, book);
        }
        System.out.println();
        
        // 1.2 过滤作者包含"Martin"的书籍
        System.out.println("  1.2 只显示作者包含'Martin'的书籍:");
        Predicate<Book> martinFilter = book -> book.getAuthor().contains("Martin");
        FilterIterator<Book> martinIterator = new FilterIterator<>(library.createIterator(), martinFilter);
        
        count = 1;
        while (martinIterator.hasNext()) {
            Book book = martinIterator.next();
            System.out.printf("    %d. %s\n", count++, book);
        }
        System.out.println();
    }
    
    /**
     * 演示反向迭代器
     */
    private static void demonstrateReverseIterator(BookCollection library) {
        System.out.println("2. 反向迭代器演示:");
        
        // 2.1 基本反向遍历
        System.out.println("  2.1 反向遍历所有书籍:");
        ReverseIterator<Book> reverseIterator = new ReverseIterator<>(library.createIterator());
        System.out.println("    反向迭代器状态: " + reverseIterator);
        
        int count = 1;
        while (reverseIterator.hasNext()) {
            Book book = reverseIterator.next();
            System.out.printf("    %d. %s\n", count++, book);
        }
        System.out.println();
    }
    

}
