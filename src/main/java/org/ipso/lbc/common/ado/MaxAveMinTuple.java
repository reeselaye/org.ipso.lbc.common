/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package org.ipso.lbc.common.ado;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LBC on 2015/2/10.
 */
public class MaxAveMinTuple<T> implements IPrintable, IMapable<String, T> {
    private String name;
    public T max;

    public MaxAveMinTuple() {
    }

    public MaxAveMinTuple(T max, T ave, T min) {
        this.max = max;
        this.min = min;
        this.ave = ave;
    }

    public MaxAveMinTuple(String name) {
        this.name = name;
    }

    public T getAve() {
        return ave;
    }

    public void setAve(T ave) {
        this.ave = ave;
    }

    public T getMax() {
        return max;
    }

    public void setMax(T max) {
        this.max = max;
    }

    public T getMin() {
        return min;
    }

    public void setMin(T min) {
        this.min = min;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T min;
    public T ave;

    @Override
    public void print(PrintStream ps) {
        ps.println(name + " : " + "[最大值 " + max + " ], " + "[平均值 " + ave + " ], " + "[最小值 " + min + " ] ");
    }

    private Map<String, T> map;

    @Override
    public Map<String, T> toMap() {
        if (map != null)
            return map;
        map = new HashMap<String, T>();
        map.put("max", max);
        map.put("min", min);
        map.put("ave", ave);
        return map;
    }
}
