/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.frameworks.poi;

import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ipso.lbc.common.exception.ExcelOpenException;
import org.ipso.lbc.common.exception.ExcelWriteException;
import org.ipso.lbc.common.exception.FileAccessException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 李倍存 创建于 2015-04-16 15:53。电邮 1174751315@qq.com。
 */
public class AbstractXLAccessor {
    public AbstractXLAccessor() {
    }

    public AbstractXLAccessor(String wbPath) {
        this.setWorkbook(openWorkbook(wbPath));path=wbPath;
    }
    private String path;
    public String getWorkbookPath(){
        return path;
    }
    public Workbook getWorkbook() {
        return workbook;
    }


    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
        this.evaluator = workbook.getCreationHelper().createFormulaEvaluator();
    }

    protected Workbook workbook;
    protected FormulaEvaluator evaluator;

    protected void forceCalcAllFormulas() {
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            workbook.getSheetAt(i).setForceFormulaRecalculation(true);
        }
    }


    public static  void writeAndCloseWorkbook(Workbook workbook, String newPath) {
        try {
            workbook.write(new FileOutputStream(newPath));
            workbook.close();
        } catch (FileNotFoundException e) {
            throw new ExcelWriteException("保存并关闭工作簿时发生异�?", e);
        } catch (IOException e) {
            throw new ExcelWriteException("保存并关闭工作簿时发生异�?", e);
        }
    }
    public static  void writeWorkbook(Workbook workbook,String newPath) {
            try {
                workbook.write(new FileOutputStream(newPath));
            } catch (IOException e) {
                throw new ExcelWriteException(e);
            }
        }
    public static void closeWorkbook(Workbook w) {
        try {
            w.close();
        } catch (IOException e) {
            throw new FileAccessException(e);
        }
    }
    public static Workbook openWorkbook(String path) {
        try {
            openedWorkbook = new XSSFWorkbook(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            throw new ExcelOpenException(e);
        } catch (IOException e) {
            throw new ExcelOpenException(e);
        }
        return openedWorkbook;
    }

    public static Workbook openedWorkbook = null;

    public static Workbook getOpenedWorkbook() {
        return openedWorkbook;
    }
}
