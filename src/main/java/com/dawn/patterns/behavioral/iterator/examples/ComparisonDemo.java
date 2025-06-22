package com.dawn.patterns.behavioral.iterator.examples;

import com.dawn.patterns.behavioral.iterator.core.Aggregate;
import com.dawn.patterns.behavioral.iterator.core.Iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 对比演示类
 * 展示for循环vs迭代器模式的区别，以及统一接口的优势
 * 展示真正不同的数据结构：数组、链表、ArrayList
 *
 * @author Dawn
 * @version 1.0.0
 */
public class ComparisonDemo {

    public static void main(String[] args) {
        System.out.println("=== for循环 vs 迭代器模式对比演示 ===\n");

        // 创建真正不同数据结构的集合实现
        ArrayBookCollection arrayCollection = createArrayCollection();
        LinkedBookCollection linkedCollection = createLinkedCollection();
        BookCollection arrayListCollection = createArrayListCollection();

        System.out.println("1. 统一接口演示 - 不同数据结构，相同遍历代码：");
        demonstrateUnifiedInterface(arrayCollection, linkedCollection, arrayListCollection);

        System.out.println("\n2. 没有迭代器时：不同数据结构需要不同的遍历代码");
        demonstrateWithoutIterator(arrayCollection, linkedCollection, arrayListCollection);

        System.out.println("\n=== 对比演示结束 ===");
    }

    /**
     * 演示统一接口的优势
     */
    private static void demonstrateUnifiedInterface(Aggregate<Book> collection1, Aggregate<Book> collection2, Aggregate<Book> collection3) {
        System.out.println("  使用统一的processBooks方法处理不同数据结构的集合：");

        System.out.println("  处理基于数组的集合：");
        processBooks(collection1);
        System.out.println("    实现类型: " + collection1.getClass().getSimpleName());

        System.out.println("  处理基于链表的集合：");
        processBooks(collection2);
        System.out.println("    实现类型: " + collection2.getClass().getSimpleName());

        System.out.println("  处理基于ArrayList的集合：");
        processBooks(collection3);
        System.out.println("    实现类型: " + collection3.getClass().getSimpleName());

        System.out.println("  ✅ 相同的代码可以处理完全不同的数据结构！");
    }

    /**
     * 统一的处理方法 - 这就是迭代器模式的威力
     */
    private static void processBooks(Aggregate<Book> collection) {
        Iterator<Book> iterator = collection.createIterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            System.out.println("    - " + book.getTitle());
        }
    }

    /**
     * 演示没有迭代器时，不同数据结构需要不同的遍历代码
     */
    private static void demonstrateWithoutIterator(ArrayBookCollection arrayCollection,
                                                  LinkedBookCollection linkedCollection,
                                                  BookCollection arrayListCollection) {
        System.out.println("  假设没有迭代器模式，每种数据结构都需要不同的遍历代码：");
        
        System.out.println("  📊 遍历数组结构 (需要暴露内部数组和大小)：");
        for (int i = 0; i < arrayCollection.getSize(); i++) {
            Book book = arrayCollection.getBook(i);
            System.out.println("    - " + book.getTitle());
        }
        System.out.println("    代码特点: for (int i = 0; i < size; i++) { getBook(i) }");
        
        System.out.println("  🔗 遍历链表结构 (需要暴露Node结构)：");
        LinkedBookCollection.Node current = linkedCollection.getHead();
        while (current != null) {
            System.out.println("    - " + current.book.getTitle());
            current = current.next;
        }
        System.out.println("    代码特点: Node current = head; while (current != null) { current = current.next }");
        
        System.out.println("  📋 遍历ArrayList结构 (需要暴露内部ArrayList)：");
        List<Book> books = getInternalBooks(arrayListCollection);
        for (Book book : books) {
            System.out.println("    - " + book.getTitle());
        }
        System.out.println("    代码特点: for (int i = 0; i < list.size(); i++) { list.get(i) }");
        
        System.out.println("  ❌ 问题总结：");
        System.out.println("    - 每种数据结构都需要不同的遍历代码");
        System.out.println("    - 客户端必须知道内部实现细节");
        System.out.println("    - 如果要换数据结构，所有客户端代码都要改");
        System.out.println("    - 违反了封装原则");
    }


    /**
     * 创建基于数组的书籍集合
     */
    private static ArrayBookCollection createArrayCollection() {
        ArrayBookCollection collection = new ArrayBookCollection();
        collection.addBook(new Book("数据结构与算法", "严蔚敏"));
        collection.addBook(new Book("计算机网络", "谢希仁"));
        collection.addBook(new Book("操作系统概念", "Abraham Silberschatz"));
        return collection;
    }

    /**
     * 创建基于链表的书籍集合
     */
    private static LinkedBookCollection createLinkedCollection() {
        LinkedBookCollection collection = new LinkedBookCollection();
        collection.addBook(new Book("设计模式", "GoF"));
        collection.addBook(new Book("重构", "Martin Fowler"));
        collection.addBook(new Book("代码大全", "Steve McConnell"));
        return collection;
    }

    /**
     * 创建基于ArrayList的书籍集合
     */
    private static BookCollection createArrayListCollection() {
        BookCollection collection = new BookCollection("图书馆");
        collection.addBook(new Book("Java编程思想", "Bruce Eckel"));
        collection.addBook(new Book("Effective Java", "Joshua Bloch"));
        collection.addBook(new Book("Java核心技术", "Cay Horstmann"));
        return collection;
    }


    /**
     * 模拟获取内部结构的方法（不好的设计）
     */
    private static List<Book> getInternalBooks(BookCollection collection) {
        // 这里模拟暴露内部结构的情况
        List<Book> books = new ArrayList<>();
        Iterator<Book> iterator = collection.createIterator();
        while (iterator.hasNext()) {
            books.add(iterator.next());
        }
        return books;
    }
}
