package com.dawn.patterns.behavioral.iterator.advanced;

import com.dawn.patterns.behavioral.iterator.core.Iterator;
import java.util.function.Predicate;

/**
 * 过滤迭代器
 * 对原始迭代器进行过滤，只返回满足条件的元素
 * 
 * @param <T> 元素类型
 * @author Dawn
 * @version 1.0.0
 */
public class FilterIterator<T> implements Iterator<T> {
    
    private final Iterator<T> originalIterator;
    private Predicate<T> filter;
    private T nextElement;
    private boolean hasNextElement;
    
    /**
     * 构造函数
     * 
     * @param originalIterator 原始迭代器
     * @param filter 过滤条件
     */
    public FilterIterator(Iterator<T> originalIterator, Predicate<T> filter) {
        this.originalIterator = originalIterator;
        this.filter = filter;
        this.hasNextElement = false;
        findNextElement();
    }
    
    /**
     * 查找下一个满足条件的元素
     */
    private void findNextElement() {
        hasNextElement = false;
        while (originalIterator.hasNext()) {
            T element = originalIterator.next();
            if (filter.test(element)) {
                nextElement = element;
                hasNextElement = true;
                break;
            }
        }
    }
    
    /**
     * 检查是否还有下一个满足条件的元素
     * 
     * @return 如果还有下一个满足条件的元素返回true，否则返回false
     */
    @Override
    public boolean hasNext() {
        return hasNextElement;
    }
    
    /**
     * 获取下一个满足条件的元素
     * 
     * @return 下一个满足条件的元素
     * @throws IndexOutOfBoundsException 如果没有更多满足条件的元素时抛出异常
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new IndexOutOfBoundsException("没有更多满足条件的元素");
        }
        
        T current = nextElement;
        findNextElement(); // 预先查找下一个元素
        return current;
    }


    
    @Override
    public String toString() {
        return String.format("FilterIterator{hasNext=%s, filter=%s}", 
                           hasNext(), filter.toString());
    }
}
