# 迭代器模式实现 - Java 设计模式学习项目

## 📋 概述

本项目完整实现了**迭代器模式（Iterator Pattern）**，这是一个属于**行为型设计模式**的经典模式。迭代器模式提供了一种方法来顺序访问聚合对象中的各个元素，而不需要暴露其内部表示。

## 🎯 迭代器模式的核心概念

### 问题
- 如何在不暴露内部结构的情况下遍历集合对象？
- 如何为不同的数据结构提供统一的遍历接口？
- 如何支持多种遍历方式（正向、反向、过滤等）？

### 解决方案
迭代器模式通过将遍历逻辑封装在独立的迭代器对象中，实现了集合与遍历算法的分离。

## 🏗️ 项目结构

```
src/main/java/com/dawn/patterns/behavioral/iterator/
├── core/                           # 核心接口定义
│   ├── Iterator.java               # 迭代器接口（核心）
│   └── Aggregate.java              # 聚合接口  
├── examples/                       # 具体示例实现
│   ├── Book.java                   # 书籍实体类
│   ├── BookCollection.java         # 基于ArrayList的集合（实现Aggregate）
│   ├── BookIterator.java           # ArrayList迭代器（实现Iterator）
│   ├── ArrayBookCollection.java    # 基于数组的集合（实现Aggregate）
│   ├── LinkedBookCollection.java   # 基于链表的集合（实现Aggregate）
│   ├── LibraryDemo.java            # 基础演示
│   └── ComparisonDemo.java         # 不同数据结构对比演示
└── advanced/                       # 高级特性
    ├── FilterIterator.java         # 过滤迭代器
    ├── ReverseIterator.java        # 反向迭代器
    └── AdvancedIteratorDemo.java   # 高级特性演示
```

## 🔑 核心组件

### 1. 迭代器接口

```java
public interface Iterator<T> {
    boolean hasNext();    // 检查是否还有下一个元素
    T next();            // 获取下一个元素并前进
}
```

### 2. 聚合接口

```java
public interface Aggregate<T> {
    Iterator<T> createIterator();  // 创建迭代器
}
```

### 3. 具体实现

- **BookCollection**: 书籍集合的具体实现，实现了Aggregate接口
- **BookIterator**: 书籍专用迭代器，实现了Iterator接口
- **Book**: 书籍实体类

## 🤔 为什么不直接用for循环？

### 1. **封装性：隐藏内部实现**

**不好的设计（暴露内部结构）：**
```java
// 假设BookCollection暴露了内部ArrayList
List<Book> books = bookCollection.getBooks(); // 暴露了内部实现
for (int i = 0; i < books.size(); i++) {
    Book book = books.get(i);
    // 处理书籍...
}
```

**问题**：
- 客户端知道了内部用ArrayList存储
- 如果改成LinkedList，所有客户端代码都要修改
- 违反了封装原则

**好的设计（迭代器模式）：**
```java
Iterator<Book> iterator = bookCollection.createIterator();
while (iterator.hasNext()) {
    Book book = iterator.next();
    // 处理书籍... 完全不知道内部如何存储
}
```


## 🛠️ 高级特性

### 1. 过滤迭代器 (FilterIterator)

支持条件过滤的迭代器：

```java
// 只遍历标题包含"Java"的书籍
Predicate<Book> filter = book -> book.getTitle().contains("Java");
FilterIterator<Book> filteredIterator = new FilterIterator<>(
    collection.createIterator(), 
    filter
);

while (filteredIterator.hasNext()) {
    Book book = filteredIterator.next();  // 只返回满足条件的书籍
    System.out.println(book);
}
```

### 2. 反向迭代器 (ReverseIterator)

支持反向遍历：

```java
// 从最后一本书开始遍历
ReverseIterator<Book> reverseIterator = new ReverseIterator<>(
    collection.createIterator()
);

while (reverseIterator.hasNext()) {
    Book book = reverseIterator.next();  // 反向获取书籍
    System.out.println(book);
}
```

## 🎨 设计模式特点

### 优点
1. **封装性**: 隐藏聚合对象的内部结构
2. **统一性**: 为不同数据结构提供统一的遍历接口
3. **扩展性**: 易于添加新的遍历方式（过滤、反向等）

### 使用场景
- 需要遍历聚合对象而不暴露其内部结构
- 需要为不同的数据结构提供统一的遍历接口
- 需要支持多种遍历方式

### 与for循环的对比

| 特性 | for循环 | 迭代器模式 |
|------|---------|------------|
| 简单性 | ✅ 简单直接 | ❌ 需要额外设计 |
| 封装性 | ❌ 可能暴露内部结构 | ✅ 完全封装 |
| 统一性 | ❌ 不同结构需要不同代码 | ✅ 统一接口 |
| 扩展性 | ❌ 难以扩展 | ✅ 易于扩展 |

## 📝 学习要点

1. **理解核心思想**: 将遍历逻辑从聚合对象中分离
2. **接口设计**: Iterator接口只需要hasNext()和next()两个核心方法
3. **扩展能力**: 学习如何实现过滤、反向等高级特性
4. **权衡取舍**: 知道何时使用迭代器，何时简单for循环就够了

## 💡 关键洞察

迭代器模式的真正价值不在于替代所有的for循环，而在于：

1. **当你需要隐藏集合的内部实现时**
2. **当你需要为不同数据结构提供统一接口时**
3. **当你需要支持多种遍历方式时（过滤、反向等）**

简单的本地数组遍历，for循环就够了。但当涉及到封装、统一性、扩展性时，迭代器模式就显示出了它的价值。

---

**作者**: Dawn  
**版本**: 1.0.0  
**创建时间**: 2025年6月22日

