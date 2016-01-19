/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package org.ipso.lbc.common.ado;

import java.util.LinkedList;

/**
 * Created by LBC on 2015/2/15.
 */
public class MaxMinableLinkedList<T extends Comparable<T>> extends LinkedList<T> implements IListMaxMinable<T> {
    private Boolean isMinMaxed = Boolean.FALSE;
    private Integer maxIndex = 0, minIndex = 0;
    private T max, min;

    private void update() {
        if (isMinMaxed)
            return;
        Integer size = this.size();
        max = this.get(0);
        min = this.get(0);
        T v = this.get(0);
        for (int i = 0; i < size; i++) {
            if (v.compareTo(max) > 0) {
                max = v;
                maxIndex = i;
            }
            if (v.compareTo(min) < 0) {
                min = v;
                minIndex = i;
            }
        }
        isMinMaxed = Boolean.TRUE;
    }

    public T getMinValue() {
        update();
        return min;
    }

    public T getMaxValue() {
        update();
        return max;
    }

    public Integer getMinValueIndex() {
        update();
        return minIndex;
    }

    public Integer getMaxValueIndex() {
        update();
        return maxIndex;
    }
}
