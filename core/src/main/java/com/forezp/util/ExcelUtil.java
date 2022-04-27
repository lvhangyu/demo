package com.forezp.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
* <p>Title: ExcelUtil</p>  
* <p>Description: </p>  
* @author guozc  
* @date 2019/05/16
 */
public class ExcelUtil {
	
	private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);
    
    public static List<List<String>> readExcel(InputStream is,String fileType) {
        // return a list contains many list
        List<List<String>> lists = new ArrayList<List<String>>();
        //读取excel文件
        try {
            ;
            //获取工作薄
            Workbook wb = null;
            if ("xls".equals(fileType)) {
                wb = new HSSFWorkbook(is);
            } else if ("xlsx".equals(fileType)) {
                wb = new XSSFWorkbook(is);
            } else {
                return null;
            }

            //读取第一个工作页sheet
            Sheet sheet = wb.getSheetAt(0);
            //第一行为标题
            for (Row row : sheet) {
                ArrayList<String> list = new ArrayList<String>();
                for (Cell cell : row) {
                    //根据不同类型转化成字符串
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    list.add(cell.getStringCellValue());
                }
                lists.add(list);
            }
        } catch (IOException e) {
        	logger.info("*** IOException:{}",e);
//            e.printStackTrace();
        } 
        return lists;
    }
}
