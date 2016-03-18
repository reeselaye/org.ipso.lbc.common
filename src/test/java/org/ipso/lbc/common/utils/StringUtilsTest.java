package org.ipso.lbc.common.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilsTest {

    @Test
    public void testContainSum() throws Exception {
        Integer t1 = StringUtils.existCount("123513465741236541236", "123");
        assertEquals(3,t1.intValue());

        Integer t2 = StringUtils.existCount("file:/e:/test/", "/");
        assertEquals(3, t2.intValue());
    }

    @Test
    public void testRemoveFromTail() throws Exception {
        String s1;

        s1 = StringUtils.removeFromTail("file:/e:/test/test/test","/",1);
        assertEquals("file:/e:/test/test/",s1);

        s1 = StringUtils.removeFromTail("file:/e:/test/test/test/","/",1);
        assertEquals("file:/e:/test/test/test/",s1);

        s1 = StringUtils.removeFromTail("file:/e:/test/test/test/","/",1,true);
        assertEquals("file:/e:/test/test/test",s1);

        s1 = StringUtils.removeFromTail("file:/e:/test/test/test/","/",2);
        assertEquals("file:/e:/test/test/",s1);

        s1 = StringUtils.removeFromTail("file:/e:/test/test/test/","/",2,true);
        assertEquals("file:/e:/test/test",s1);

        s1 = StringUtils.removeFromTail("file:/e:/test/test/test/","/",5);
        assertEquals("file:/",s1);

        s1 = StringUtils.removeFromTail("file:/e:/test/test/test/","/",6);
        assertEquals("file:/",s1);

        s1 = StringUtils.removeFromTail("file:/e:/test/test/test/","/",7);
        assertEquals("file:/",s1);

        s1 = StringUtils.removeFromTail("file:/e:/test/test/test/","/",7, true);
        assertEquals("file:",s1);

    }

    @Test
    public void testRemoveFromHead() throws Exception {
        String s1;


        s1 = StringUtils.removeFromHead("file:/e:/test/test/test/", "/", 2);
        assertEquals("/test/test/test/",s1);

        s1 = StringUtils.removeFromHead("file:/e:/test/test/test/", "/", 2, true);
        assertEquals("test/test/test/",s1);

        s1 = StringUtils.removeFromHead("file:/e:/test/test/test/", "/", 5);
        assertEquals("/",s1);

        s1 = StringUtils.removeFromHead("file:/e:/test/test/test/", "/", 6);
        assertEquals("/",s1);

        s1 = StringUtils.removeFromHead("file:/e:/test/test/test/", "/", 7);
        assertEquals("/",s1);

        s1 = StringUtils.removeFromHead("file:/e:/test/test/test/", "/", 7, true);
        assertEquals("",s1);

        s1 = StringUtils.removeFromHead("/file:/e:/test/test/test", "/", 7, true);
        assertEquals("test",s1);

        s1 = StringUtils.removeFromHead("/file:/e:/test/test/test", "/", 7);
        assertEquals("/test",s1);
    }
}