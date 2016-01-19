/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package org.ipso.lbc.common.ado;

import java.io.PrintStream;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by LBC on 2015/2/11.
 */
public class ElementPrintableLinkedList<T extends IPrintable> extends LinkedList<T> implements IPrintable {
    public String getName() {
        return name;
    }
    public void setName(String name){this.name = name;}
    private String name;

    public ElementPrintableLinkedList(String name) {
        super();
        this.name = name;
    }
    public ElementPrintableLinkedList() {
        this("");
    }
    public ElementPrintableLinkedList(String name, Collection<? extends T> var1) {
        super(var1);
        this.name = name;
    }

    public ElementPrintableLinkedList(T ... datas){
        Integer size = datas.length;
        for (int i = 0; i < size; i++) {
            super.add(datas[i]);
        }
    }

    public void print(PrintStream ps) {
        int size = size();
        ps.println("List  [  " + name + "  ]    Size  [  " + size + "  ]  \n");
        for (int i = 0; i < size; i++) {
            T t=get(i);
            if (t!=null){
                get(i).print(ps);
            }

        }
    }
    public ElementPrintableLinkedList<T> clearNull(){
        ElementPrintableLinkedList<T> r=new ElementPrintableLinkedList<T>("CLEAR_NULL");
        for (int i = 0; i < size(); i++) {
            T t=get(i);
            if (t!=null){
                r.add(t);
            }
        }
        return r;
    }
    public Boolean hasNull() {
        for (int i = 0; i < size(); i++) {
            if (get(i) == null)
                return true;
        }
        return false;
    }

//    @Override
//    public ElementPrintableLinkedList<T> clone(){
//        ElementPrintableLinkedList<T> l=new ElementPrintableLinkedList<T>(this.name);
//        for (int i = 0; i <this.size() ; i++) {
//            T  temp=this.get(i);
//            l.add(temp.clone());
//        }
//    }
}
