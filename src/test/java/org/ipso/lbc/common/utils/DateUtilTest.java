package org.ipso.lbc.common.utils;

import org.ipso.lbc.common.exception.AppUnCheckException;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class DateUtilTest {

    @Test
    public void testGetDateStringsBetween() throws Exception {
        List<String> dates = DateUtil.getDateStringsBetween("2010-01-07", "2010-01-07");
        assertEquals(dates.size(), 1);
        assertEquals(dates.get(0), "2010-01-07");

        dates = DateUtil.getDateStringsBetween("2015-01-03", "2015-01-06");
        assertEquals(dates.size(), 4);
        assertEquals(dates.get(0), "2015-01-03");
        assertEquals(dates.get(1), "2015-01-04");
        assertEquals(dates.get(2), "2015-01-05");
        assertEquals(dates.get(3), "2015-01-06");
    }

    @Test(expected = AppUnCheckException.class)
    public void testGetDate(){
        Date date = DateUtil.getDate("2016-01-01");
        assertTrue(date.after(DateUtil.getDate("2015-12-31")));
        assertTrue(date.before(DateUtil.getDate("2016-01-12")));

        date = DateUtil.getDate("20100101");
    }
}