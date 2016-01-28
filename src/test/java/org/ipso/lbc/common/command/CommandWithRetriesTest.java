package org.ipso.lbc.common.command;

import org.ipso.lbc.common.exception.AppUnCheckException;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class CommandWithRetriesTest {

    @Test
    public void testExec() throws Exception {
        CommandWithRetries commandWithRetries = new CommandWithRetries(new IReceiver() {
            @Override
            public Object action(Object params) {
                throw new AppUnCheckException("TEST");
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
        assertTrue(commandWithRetries.isFinallyFailed());
        assertEquals(commandWithRetries.getFailTimes().intValue(),3);
    }
}