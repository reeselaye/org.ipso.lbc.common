/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.math;

import org.ipso.lbc.common.ado.MaxAveMinTuple;

import java.util.LinkedList;
import java.util.List;

/**
 * 信息：李倍存 创建于 2015-07-20 20:23。电邮 1174751315@qq.com。<br>
 * 说明：
 */
public class Comparator {

    List<List<Double>> list = new LinkedList<List<Double>>();


    public void addList(List<Double> list){
        this.list.add(list);
    }
    public void addList(List<Double> ... list){
        Integer size =list.length;
        for (int s = 0; s < size; s++) {
            addList(list[s]);
        }
    }
    public void clear(){
        list.clear();
    }

    public MaxAveMinTuple<Double> getResult(){
        return getMaxMin(list);
    }


    private MaxAveMinTuple<Double> getMaxMin(List<List<Double>> datas){
        MaxAveMinTuple<Double> t = new MaxAveMinTuple<Double>(Double.MIN_VALUE,0.,Double.MAX_VALUE);
        Integer size = datas.size();
        for (int i = 0; i < size; i++) {
            if (t.max < unnamed(datas.get(i)).max){
                t.max = unnamed(datas.get(i)).max;
            }
            if (t.min > unnamed(datas.get(i)).min){
                t.min = unnamed(datas.get(i)).min;
            }
        }
        return t;
    }

    private MaxAveMinTuple<Double> unnamed(List<Double> datas){
        MaxAveMinTuple<Double> t = new MaxAveMinTuple<Double>(Double.MIN_VALUE,0.,Double.MAX_VALUE);
        Integer size = datas.size();
        for (int i = 0; i < size; i++) {
            if (t.max < datas.get(i)){
                t.max = datas.get(i);
            }
            if (t.min > datas.get(i)){
                t.min = datas.get(i);
            }

        }
        return t;
    }
}
