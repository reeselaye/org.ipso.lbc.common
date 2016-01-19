/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.utils.color;


import java.awt.*;
import java.awt.color.ColorSpace;
import java.util.Date;
import java.util.Random;

/**
 * 李倍存 创建于 2015-04-08 11:01。电邮 1174751315@qq.com。
 */
public class MyColor extends Color {
    public MyColor(int r, int g, int b) {
        super(r, g, b);
    }

    public MyColor(int r, int g, int b, int a) {
        super(r, g, b, a);
    }

    public MyColor(int rgb) {
        super(rgb);
    }

    public MyColor(int rgba, boolean hasalpha) {
        super(rgba, hasalpha);
    }

    public MyColor(float r, float g, float b) {
        super(r, g, b);
    }

    public MyColor(float r, float g, float b, float a) {
        super(r, g, b, a);
    }

    public MyColor(ColorSpace cspace, float[] components, float alpha) {
        super(cspace, components, alpha);
    }


    public static final Color c1 = new Color(210, 222, 239);
    public static final Color c2 = new Color(118, 183, 247);
    public static final Color c3 = new Color(124, 187, 0);
    public static final Color c4 = new Color(0, 114, 51);
    public static final Color c5 = new Color(0, 24, 143);
    public static final Color c6 = new Color(0, 160, 233);
    public static final Color c7 = new Color(0, 114, 198);
    public static final Color c8 = new Color(131, 204, 254);
    public static final Color c9 = new Color(188, 178, 167);
    public static final Color c10 = new Color(200, 228, 155);
    public static final Color c11 = new Color(217, 217, 217);
    public static final Color c12 = new Color(198, 224, 180);
    public static final Color c13 = new Color(89, 89, 89);
    public static final Color c14 = new Color(127, 127, 127);
    public static final Color c15 = new Color(221, 235, 247);
    public static final Color COMMON_BACKGROUND = c15;
    public static final Color COMMON_FOREGROUND = c13;
    public static final Color COMMON_GRID_LINE = c14;
    public static final Color COMMON_SERIES_1 = Color.red;
    public static final Color COMMON_SERIES_2 = Color.darkGray;
    public static final Color COMMON_SERIES_3 = Color.magenta;
    public static final Color COMMON_SERIES_4 = Color.blue;
    public static final Color COMMON_SERIES_5 = Color.magenta;
    public static final Color COMMON_SERIES_6 = c4;

    public static Color getRandomGray(Color color) {
        Date date = new Date();
        Double r = new Random(color.hashCode()).nextDouble();
        Double d = (r * 255.);
        Integer n = d.intValue();
        return new Color(n, n, n);
    }

    public static Color getRandomColor() {
        Date date = new Date();
        Double d = (new Random(date.getTime()).nextDouble()) * 255.;
        Integer r = d.intValue();

        date = new Date();
        d = (new Random(date.hashCode()).nextDouble()) * 255.;
        Integer g = d.intValue();

        date = new Date();
        d = (new Random(date.hashCode()).nextDouble()) * 255.;
        Integer b = d.intValue();

        return new Color(r, g, b);
    }

    public static Color getColor(String rStr, String gStr, String bStr, Integer inc) {
        Integer rInt = Integer.parseInt(rStr, inc);
        Integer gInt = Integer.parseInt(gStr, inc);
        Integer bInt = Integer.parseInt(bStr, inc);
        return new Color(rInt, gInt, bInt);
    }


}
