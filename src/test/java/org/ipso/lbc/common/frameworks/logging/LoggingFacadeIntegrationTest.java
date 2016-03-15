/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.frameworks.logging;

import org.junit.Test;

import static org.ipso.lbc.common.frameworks.logging.LoggingFacade.*;
public class LoggingFacadeIntegrationTest {
    public LoggingFacadeIntegrationTest() {
        LoggerFactory.ConfigureLog4j();

        LoggerFactory.getLogger("fuck").error("TEST" + this.hashCode());
        LoggerFactory.getLogger("org.lf").info("TEST");
    }

    @Test
    public void testInfo() throws Exception {
        info("testInfo");
    }

    @Test
    public void testInfoGeneral() throws Exception {
        infoGeneral("testInfoGeneral");
    }

    @Test
    public void testInfoImportant() throws Exception {
        infoImportant("testInfoImportant");
    }

    @Test
    public void testInfoVital() throws Exception {
        infoVital("testInfoVital");
    }

//    @Test
//    public void testErrorUser() throws Exception {
//        errorUser("testErrorUser");
//    }

    @Test
    public void testError() throws Exception {
        error("testError");
    }
    @Test
    public void testErrorUser() throws Exception {
        errorUser("testErrorUser");
    }
    @Test
    public void testError1() throws Exception {
        try {
            throw new Exception("TEST");
        }catch (Exception e){
            error("testError1", e);
        }
    }

    @Test
    public void testWarn() throws Exception {
        warn("testWarn");
    }

    @Test
    public void testFatal() throws Exception {
        fatal("testFatal");
    }

    @Test
    public void testTrace() throws Exception {
        trace("testTrace");
    }

    @Test
    public void testDebug() throws Exception {
        debug("testDebug");
    }
}