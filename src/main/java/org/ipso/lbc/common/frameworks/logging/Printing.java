/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.frameworks.logging;

import org.ipso.lbc.common.ado.ElementPrintableLinkedList;
import org.ipso.lbc.common.ado.IPrintable;
import org.ipso.lbc.common.ado.PrintableLinkedList;

import java.io.PrintStream;

/**
 * 信息：李倍存 创建于 2015-05-05 9:19。电邮 1174751315@qq.com。
 * 说明：该类为单例，负责所有的Print信息输出。不应越过该类直接调用PrintStream的函数。
 *      因为该类可以统一关闭或打开所有的消息输出，使程序的行为易于管理。
 */

public class Printing {

    public static Printing INSTANCE = new Printing();



    /**
     * 该单例包装的PrintStream对象，所有的print和println函数调用均转发给该对象。
     */
    private PrintStream ps = System.out;
    /**
     * 是否允许信息输出。
     */
    private Boolean on = false;


    private Printing() {
    }
    public void setPs(PrintStream ps) {
        this.ps = ps;
    }
    /**
     * 启动Print功能，从而允许Print信息的输出。
     */
    public void on() {
        on = true;
    }

    /**
     * 关闭Print信息的输出，所有对该类的print以及println函数的调用均直接返回。
     */
    public void off() {
        on = false;
    }




    public <T extends IPrintable> void print(ElementPrintableLinkedList<T> list) {
        if (on) {
            list.print(ps);
        }
    }

    public <T> void print(PrintableLinkedList<T> list) {
        if (on) {
            list.print(ps);
        }
    }

    public void print(Object objects) {
        if (on) {
            ps.print(objects);
        }
    }

    public void println(Object object) {
        if (on) {
            ps.println(object);
        }
    }
}
