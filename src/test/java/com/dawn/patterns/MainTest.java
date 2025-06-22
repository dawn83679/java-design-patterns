package com.dawn.patterns;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Main类的单元测试
 * 
 * @author Dawn
 * @version 1.0.0
 */
class MainTest {
    
    @Test
    @DisplayName("测试Main类是否能正常实例化")
    void testMainClassInstantiation() {
        // 测试Main类能否正常实例化
        assertDoesNotThrow(() -> new Main());
    }
    
    @Test
    @DisplayName("测试main方法是否能正常执行")
    void testMainMethod() {
        // 测试main方法能否正常执行而不抛出异常
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }
}
