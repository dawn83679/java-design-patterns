package com.dawn.patterns.behavioral.iterator.core;

/**
 * 聚合接口
 * 定义了创建迭代器的统一接口
 * 
 * @param <T> 元素类型
 * @author Dawn
 * @version 1.0.0
 */
public interface Aggregate<T> {
    
    /**
     * 创建一个迭代器
     * 
     * @return 新的迭代器实例
     */
    Iterator<T> createIterator();
}
