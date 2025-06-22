package com.dawn.patterns.behavioral.iterator.examples;

import com.dawn.patterns.behavioral.iterator.core.Iterator;

/**
 * 书籍迭代器类
 * 实现了Iterator接口，提供了遍历BookCollection的具体实现
 * 
 * @author Dawn
 * @version 1.0.0
 */
public class BookIterator implements Iterator<Book> {
    
    private final BookCollection bookCollection;
    private int currentIndex;
    
    /**
     * 构造函数
     * 
     * @param bookCollection 要遍历的书籍集合
     */
    public BookIterator(BookCollection bookCollection) {
        this.bookCollection = bookCollection;
        this.currentIndex = 0;
    }
    
    /**
     * 检查是否还有下一个书籍
     * 
     * @return 如果还有下一本书返回true，否则返回false
     */
    @Override
    public boolean hasNext() {
        return currentIndex < bookCollection.getSize();
    }
    
    /**
     * 获取下一本书籍
     * 
     * @return 下一本书籍
     * @throws IndexOutOfBoundsException 如果没有更多书籍时抛出异常
     */
    @Override
    public Book next() {
        if (!hasNext()) {
            throw new IndexOutOfBoundsException("没有更多书籍可以迭代");
        }
        return bookCollection.getBook(currentIndex++);
    }
    
    
    @Override
    public String toString() {
        return String.format("BookIterator{currentIndex=%d, hasNext=%s}", 
                           currentIndex, hasNext());
    }
}
