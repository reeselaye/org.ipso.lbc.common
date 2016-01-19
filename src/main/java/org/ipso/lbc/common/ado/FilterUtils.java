/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.ado;

import java.util.LinkedList;
import java.util.List;

/**
 * 李倍存 创建于 2015-04-09 10:02。电邮 1174751315@qq.com。
 */
public class FilterUtils {
    public static List<Double> filter(Double[] in, Integer wndWidth) {

        Integer i, x, y;
        Double t;
        Integer wndWidth_2 = wndWidth / 2;
        Integer size = in.length;
        Double[] num = new Double[wndWidth];
        for (i = wndWidth_2; i < size - wndWidth_2; i++) {

            for (int j = 0; j < wndWidth; j++) {
                num[j] = in[i + j - wndWidth_2];
            }
            for (x = 1; x < wndWidth; x++) {
                for (y = 0; y < wndWidth - x; y++) {
                    if (num[y] > num[y + 1]) {
                        t = num[y];
                        num[y] = num[y + 1];
                        num[y + 1] = t;
                    }
                }
            }
            in[i] = num[wndWidth_2];
        }
        List<Double> list = new LinkedList<Double>();
        for (int j = 0; j < in.length; j++) {
            list.add(in[j]);
        }
        return list;
    }

    public static List<Double> medFilter(Double[] in, Integer wndWidth, Double threshold) {

        Integer i, x, y;
        Double t;
        Integer wndWidth_2 = wndWidth / 2;
        Integer size = in.length;
        Double[] num = new Double[wndWidth];
        for (i = wndWidth_2; i < size - wndWidth_2; i++) {

            if (in[i] - in[i - wndWidth_2] > threshold)
                in[i] = (in[i - wndWidth_2] + in[i + wndWidth_2]) / 2.;
        }
        List<Double> list = new LinkedList<Double>();
        for (int j = 0; j < in.length; j++) {
            list.add(in[j]);
        }
        return list;
    }
}
