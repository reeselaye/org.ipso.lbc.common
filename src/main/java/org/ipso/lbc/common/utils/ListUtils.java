/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.utils;

import java.util.LinkedList;
import java.util.List;

/**
 * 李倍存 创建于 2015-04-11 21:34。电邮 1174751315@qq.com。
 */
public class ListUtils {
    public static <T> List<T> unnamed(T t1, T t2) {
        List<T> list = new LinkedList<T>();
        list.add(t1);
        list.add(t2);
        return list;
    }

    public static String toCsv(List<Double> list){
        String csv = "";
        Integer size = list.size();
        for (int i = 0; i < size; i++) {
            csv += list.get(i).toString() + ",";
        }
        return csv.substring(0,csv.length()-1);
    }
}
