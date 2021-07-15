package com.terraformcreatives.markdownhelper

import org.junit.Assert
import org.junit.Test


class MyClassUtilTest {
    @Test
    fun sampleTest() {
        val target = "target"
        val expected = "target formatted"
        val result = MyClassUtil.formatText(target)
        Assert.assertEquals(expected, result)
    }
}