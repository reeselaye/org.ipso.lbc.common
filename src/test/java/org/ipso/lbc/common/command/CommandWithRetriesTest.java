package org.ipso.lbc.common.command;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommandWithRetriesTest {

    @Test
    public void testExec() throws Exception {
        CommandWithRetries commandWithRetries = new CommandWithRetries(new IReceiver(){
            @Override
            public Object action(Object params) {
                return null;
            }

            @Override
            public Object info() {
                return "YEAH";
            }

            @Override
            public Object[] getParams() {
                return new Object[0];
            }
        }, 2, "TEST");
        commandWithRetries.exec(null);
        assertTrue(!commandWithRetries.isFinallyFailed());
        assertEquals(0, commandWithRetries.getFailTimes().intValue());
    }
}