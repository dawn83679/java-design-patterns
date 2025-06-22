package com.dawn.patterns.behavioral.iterator.advanced;

import com.dawn.patterns.behavioral.iterator.core.Iterator;
import java.util.ArrayList;
import java.util.List;

/**
 * 反向迭代器
 * 以相反的顺序遍历集合元素
 * 
 * @param <T> 元素类型
 * @author Dawn
 * @version 1.0.0
 */
public class ReverseIterator<T> implements Iterator<T> {
    
    private final List<T> elements;
    private int currentIndex;
    
    /**
     * 构造函数
     * 
     * @param originalIterator 原始迭代器
     */
    public ReverseIterator(Iterator<T> originalIterator) {
        this.elements = new ArrayList<>();
        
        // 先将所有元素收集到列表中
        while (originalIterator.hasNext()) {
            elements.add(originalIterator.next());
        }
        
        // 从最后一个元素开始
        this.currentIndex = elements.size() - 1;
    }
    
    /**
     * 基于现有列表创建反向迭代器
     * 
     * @param elements 元素列表
     */
    public ReverseIterator(List<T> elements) {
        this.elements = new ArrayList<>(elements);
        this.currentIndex = this.elements.size() - 1;
    }
    
    /**
     * 检查是否还有上一个元素（反向遍历）
     * 
     * @return 如果还有上一个元素返回true，否则返回false
     */
    @Override
    public boolean hasNext() {
        return currentIndex >= 0;
    }
    
    /**
     * 获取上一个元素（反向遍历）
     * 
     * @return 上一个元素
     * @throws IndexOutOfBoundsException 如果没有更多元素时抛出异常
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new IndexOutOfBoundsException("没有更多元素可以反向迭代");
        }
        return elements.get(currentIndex--);
    }
    
    
    @Override
    public String toString() {
        return String.format("ReverseIterator{currentIndex=%d, hasNext=%s}", 
                           currentIndex, hasNext());
    }
}
