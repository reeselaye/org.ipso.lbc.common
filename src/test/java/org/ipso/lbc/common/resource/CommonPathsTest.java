package org.ipso.lbc.common.resource;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CommonPathsTest {

    @Test
    public void testGetContextRoot() throws Exception {
        assertTrue(!CommonPaths.getContextRoot().isEmpty());
    }
}