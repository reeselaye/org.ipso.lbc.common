/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package org.ipso.lbc.common.ado;

import java.util.List;

/**
 * Created by LBC on 2015/2/13.
 */
public class ConvertUtils {
    public static <T> PrintableLinkedList<T> toPrintableLinkedList(List<T> list, String name) {
        PrintableLinkedList<T> dest = new PrintableLinkedList<T>(name);
        for (int i = 0; i < list.size(); i++) {
            dest.add(i, list.get(i));
        }
        return dest;
    }

    public static <T extends IPrintable> ElementPrintableLinkedList<T> toElementPrintableLinkedList(List<T> list, String name) {
        ElementPrintableLinkedList<T> dest = new ElementPrintableLinkedList<T>(name);
        for (int i = 0; i < list.size(); i++) {
            dest.add(list.get(i));
        }
        return dest;
    }

    public static <T extends IPrintable> ElementPrintableLinkedList<T> toElementPrintableLinkedList1(PrintableLinkedList<T> list, String name) {
        ElementPrintableLinkedList<T> dest = new ElementPrintableLinkedList<T>(name);
        for (int i = 0; i < list.size(); i++) {
            dest.add(list.get(i));
        }
        return dest;
    }
}
