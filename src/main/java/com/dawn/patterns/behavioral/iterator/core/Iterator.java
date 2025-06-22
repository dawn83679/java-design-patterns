package com.dawn.patterns.behavioral.iterator.core;

/**
 * 迭代器接口
 * 定义了遍历聚合对象的统一接口
 * 
 * @param <T> 元素类型
 * @author Dawn
 * @version 1.0.0
 */
public interface Iterator<T> {
    
    /**
     * 检查是否还有下一个元素
     * 
     * @return 如果还有下一个元素返回true，否则返回false
     */
    boolean hasNext();
    
    /**
     * 获取下一个元素并移动到下一个位置
     * 
     * @return 下一个元素
     */
    T next();
}
