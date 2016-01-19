/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */
package org.ipso.lbc.common.frameworks.poi;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 李倍存 创建于 2015-05-04 21:42。电邮 1174751315@qq.com。
 */
public class PoiUtils {
    /**
     * 功能：拷贝sheet
     * 实际调用     copySheet(targetSheet, sourceSheet, targetWork, sourceWork, true)
     * @param targetSheet
     * @param sourceSheet
     * @param targetWork
     * @param sourceWork
     */
    public static void copySheet(XSSFSheet targetSheet, XSSFSheet sourceSheet,
                                 XSSFWorkbook targetWork, XSSFWorkbook sourceWork) {
        if(targetSheet == null || sourceSheet == null || targetWork == null || sourceWork == null){
            throw new IllegalArgumentException("调用PoiUtil.copySheet()方法时，targetSheet、sourceSheet、targetWork、sourceWork都不能为空，故抛出该异常！");
        }
        copySheet(targetSheet, sourceSheet, targetWork, sourceWork, true);
    }

    public static XSSFWorkbook cloneWorkbook(XSSFWorkbook src) {
        XSSFWorkbook target=new XSSFWorkbook();
        Integer nbrOfSheets=src.getNumberOfSheets();
        for (int i = 0; i <nbrOfSheets; i++) {
            XSSFSheet sheet=src.getSheetAt(i);
            copySheet(target.createSheet(sheet.getSheetName()),sheet,target,src);
        }
        return target;
    }
    
    /**
     * 功能：拷贝sheet
     * @param targetSheet
     * @param sourceSheet
     * @param targetWork
     * @param sourceWork
     * @param copyStyle                 boolean 是否拷贝样式
     */
    public static void copySheet(XSSFSheet targetSheet, XSSFSheet sourceSheet,
                                 XSSFWorkbook targetWork, XSSFWorkbook sourceWork, boolean copyStyle) {

        if(targetSheet == null || sourceSheet == null || targetWork == null || sourceWork == null){
            throw new IllegalArgumentException("调用PoiUtil.copySheet()方法时，targetSheet、sourceSheet、targetWork、sourceWork都不能为空，故抛出该异常！");
        }

        //复制源表中的行
        int maxColumnNum = 0;

        Map styleMap = (copyStyle) ? new HashMap() : null;

       for (int i = sourceSheet.getFirstRowNum(); i <= sourceSheet.getLastRowNum(); i++) {
            XSSFRow sourceRow = sourceSheet.getRow(i);
            XSSFRow targetRow = targetSheet.createRow(i);

            if (sourceRow != null) {
                copyRow(targetRow, sourceRow,
                        targetWork, sourceWork, styleMap);
                if (sourceRow.getLastCellNum() > maxColumnNum) {
                    maxColumnNum = sourceRow.getLastCellNum();
                }
            }
        }

        //复制源表中的合并单元格
        mergerRegion(targetSheet, sourceSheet);

        //设置目标sheet的列宽
        for (int i = 0; i <= maxColumnNum; i++) {
            targetSheet.setColumnWidth(i, sourceSheet.getColumnWidth(i));
        }
    }

    /**
     * 功能：拷贝row
     * @param targetRow
     * @param sourceRow
     * @param styleMap
     * @param targetWork
     * @param sourceWork
     */
    public static void copyRow(XSSFRow targetRow, XSSFRow sourceRow,
                               XSSFWorkbook targetWork, XSSFWorkbook sourceWork,Map styleMap){
        if(targetRow == null || sourceRow == null || targetWork == null || sourceWork == null){
            throw new IllegalArgumentException("调用PoiUtil.copyRow()方法时，targetRow、sourceRow、targetWork、sourceWork、targetPatriarch都不能为空，故抛出该异常！");
        }

        //设置行高
        targetRow.setHeight(sourceRow.getHeight());

        for (int i = sourceRow.getFirstCellNum();i>=0 &&i <= sourceRow.getLastCellNum(); i++) {

            XSSFCell sourceCell = null;
            XSSFCell targetCell = null;
            sourceCell = sourceRow.getCell(i);
            targetCell = targetRow.getCell(i);

            if (sourceCell != null) {
                if (targetCell == null) {
                    targetCell = targetRow.createCell(i);
                }

                //拷贝单元格，包括内容和样式
                copyCell(targetCell, sourceCell, targetWork, sourceWork, styleMap);

                //拷贝单元格注释
                copyComment(targetCell,sourceCell);
            }
        }
    }

    /**
     * 功能：拷贝cell，依据styleMap是否为空判断是否拷贝单元格样式
     * @param targetCell            不能为空
     * @param sourceCell            不能为空
     * @param targetWork            不能为空
     * @param sourceWork            不能为空
     * @param styleMap              可以为空
     */
    public static void copyCell(XSSFCell targetCell, XSSFCell sourceCell, XSSFWorkbook targetWork, XSSFWorkbook sourceWork,Map styleMap) {
        if(targetCell == null || sourceCell == null || targetWork == null || sourceWork == null ){
            throw new IllegalArgumentException("调用PoiUtil.copyCell()方法时，targetCell、sourceCell、targetWork、sourceWork都不能为空，故抛出该异常！");
        }

        //处理单元格样式
        if(styleMap != null){
            if (targetWork == sourceWork) {
                targetCell.setCellStyle(sourceCell.getCellStyle());
            } else {
                String stHashCode = "" + sourceCell.getCellStyle().hashCode();
                XSSFCellStyle targetCellStyle = (XSSFCellStyle) styleMap
                        .get(stHashCode);
                if (targetCellStyle == null) {
                    targetCellStyle = targetWork.createCellStyle();
                    targetCellStyle.cloneStyleFrom(sourceCell.getCellStyle());
                    styleMap.put(stHashCode, targetCellStyle);
                }

                targetCell.setCellStyle(targetCellStyle);
            }
        }

        //处理单元格内容
        switch (sourceCell.getCellType()) {
            case XSSFCell.CELL_TYPE_STRING:
                targetCell.setCellValue(sourceCell.getRichStringCellValue());
                break;
            case XSSFCell.CELL_TYPE_NUMERIC:
                targetCell.setCellValue(sourceCell.getNumericCellValue());
                break;
            case XSSFCell.CELL_TYPE_BLANK:
                targetCell.setCellType(XSSFCell.CELL_TYPE_BLANK);
                break;
            case XSSFCell.CELL_TYPE_BOOLEAN:
                targetCell.setCellValue(sourceCell.getBooleanCellValue());
                break;
            case XSSFCell.CELL_TYPE_ERROR:
                targetCell.setCellErrorValue(sourceCell.getErrorCellValue());
                break;
            case XSSFCell.CELL_TYPE_FORMULA:
                targetCell.setCellFormula(sourceCell.getCellFormula());
                break;
            default:
                break;
        }
    }

    /**
     * 功能：拷贝comment
     */
    public static void copyComment(XSSFCell targetCell,XSSFCell sourceCell){

    }

    /**
     * 功能：复制原有sheet的合并单元格到新创建的sheet
     *
     * @param targetSheet
     * @param sourceSheet
     */
    public static void mergerRegion(XSSFSheet targetSheet, XSSFSheet sourceSheet){
        if(targetSheet == null || sourceSheet == null){
            throw new IllegalArgumentException("调用PoiUtil.mergerRegion()方法时，targetSheet或者sourceSheet不能为空，故抛出该异常！");
        }
        for (int i = 0; i < sourceSheet.getNumMergedRegions(); i++) {
            CellRangeAddress oldRange = sourceSheet.getMergedRegion(i);
            CellRangeAddress newRange = new CellRangeAddress(
                    oldRange.getFirstRow(), oldRange.getLastRow(),
                    oldRange.getFirstColumn(), oldRange.getLastColumn());
            targetSheet.addMergedRegion(newRange);
        }
    }

    /**
     * 功能：重新定义XSSFColor.YELLOW的色值
     *
     * @param workbook
     * @return
     */
    public static XSSFColor setMForeColor(XSSFWorkbook workbook) {
        return null;
    }
        /**
         * 功能：重新定义XSSFColor.PINK的色值
         *
         * @param workbook
         * @return
         */
    public static XSSFColor setMBorderColor(XSSFWorkbook workbook) {
        return null;
    }
}
