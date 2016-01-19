/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.frameworks.poi;

import org.apache.poi.ss.util.CellReference;

/**
 * 李倍存 创建于 2015-03-24 20:46。电邮 1174751315@qq.com。
 */
public class CellPosition {
    /**
     * @param cellRef   符合MS EXCEL相关规范的形如“A1”的单元格坐标。
     * @param sheetName EXCEL工作簿的工作表名字。
     */
    public CellPosition(String cellRef, String sheetName) {
        cellReference = new CellReference(cellRef);
        this.sheetName = sheetName;
        ref=cellRef;
    }

    public CellPosition(Integer col, Integer row, String sheetName) {
        cellReference = new CellReference(row, col);
        this.sheetName = sheetName;
    }

    private String sheetName;
    private CellReference cellReference;


    private String ref;
    public String getRef(){
        return ref;
    }

    public String getSheetName() {
        return this.sheetName;
    }


    /**
     * @return 单元格的行索引（特定于POI和JXL）。
     */
    public Integer getRow() {
        return cellReference.getRow();
    }

    /**
     * @return 单元格的列索引（特定于POI和JXL）。
     */
    public Short getCol() {
        return cellReference.getCol();

    }


    public CellPosition ofColAfter(Integer offset) {
        return this.ofAfter(offset, 0);
    }

    public CellPosition ofRowAfter(Integer offset) {
        return this.ofAfter(0, offset);
    }

    public CellPosition ofAfter(Integer colOffset, Integer rowOffset) {
        return new CellPosition(Integer.valueOf(this.cellReference.getCol() + colOffset), this.cellReference.getRow() + rowOffset, this.getSheetName());

    }
}
