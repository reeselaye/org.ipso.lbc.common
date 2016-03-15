/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.math.interp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LagrangeTest {

     @Test
    public void testAddPoints() throws Exception {
        Lagrange lagrange = new Lagrange(96);
        lagrange.addPoints(new Double[]{0.25,0.30,0.39,0.45,0.53},new Double[]{0.50,0.5477,0.6245,0.6708,0.7280});
        assertEquals(0.6557,lagrange.calc(0.43),0.001);

        lagrange.addPoints(new Double[]{0.60,0.65},new Double[]{0.7512,0.7980});
        assertEquals(0.7370,lagrange.calc(0.55),0.001);


    }
    @Test
    public void testAddPoints1() throws Exception {
        Lagrange lagrange = new Lagrange(96);
        lagrange.addPoints(new Double[]{0.25,0.30,0.39},new Double[]{0.50,0.5477,0.6245});
        assertEquals(0.5918,lagrange.calc(0.35),0.001);

    }

}