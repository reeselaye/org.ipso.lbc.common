package org.ipso.lbc.common.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConfigurationIntegrationTest {

    @Test
    public void testGetConfiguration() throws Exception {
        Configuration cfg = Configuration.INSTANCE;

        String cfg1  =  cfg.getConfiguration("test.cfg1");
        String cfg2  =  cfg.getConfiguration("test.cfg2");
        String test  = cfg.getConfiguration("test");
        assertEquals("cfg1", cfg1);
        assertEquals("cfg2", cfg2);
        assertEquals("test",test);
    }
}