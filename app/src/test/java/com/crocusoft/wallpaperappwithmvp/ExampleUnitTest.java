package com.crocusoft.wallpaperappwithmvp;

import com.crocusoft.wallpaperappwithmvp.util.Util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void stringLengthFunctionTest() {
        assertEquals(1, Util.stringLength("1"));
        assertEquals(3, Util.stringLength("123"));
        assertEquals(0, Util.stringLength(""));
        assertEquals(0, Util.stringLength(null));
    }

}