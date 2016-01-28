package org.ipso.lbc.common.config;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigurationIntegrationTest {

    @Test
    public void testGetConfiguration() throws Exception {
        Configuration cfg = Configuration.INSTANCE;

        String cfg1  =  cfg.getConfiguration("cfg1");
        String cfg2  =  cfg.getConfiguration("cfg2");
        String cfg3  =  cfg.getConfiguration("cfg3");

        assertEquals("damn", cfg1);
        assertEquals("shift", cfg2);
        assertEquals("jerk", cfg3);
    }
}