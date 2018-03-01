package org.lining.hssf;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * description:
 * date 2017/12/21
 *
 * @author lining1
 * @version 1.0.0
 */
public class ReadSN {

    private static final String fileName = "d:\\FTP\\PON.xls";

    private static HSSFWorkbook readFile(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        try {
            return new HSSFWorkbook(fis);
        } finally {
            fis.close();
        }
    }

    public static Set<String> readSN(String path, int i) {
        try {
            Set<String> result = new HashSet<>();
            HSSFWorkbook wb = ReadSN.readFile(path);
            HSSFSheet sheet = wb.getSheetAt(i);
            int rows = sheet.getPhysicalNumberOfRows();
            for (int r = 1; r < rows; r++) {
                HSSFRow row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                HSSFCell cell0 = row.getCell(0);
                String value0 = getStringFromHssf(cell0);
                result.add(value0);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
      return null;
    }

    public static String getStringFromHssf(HSSFCell cell) {
        String value = null;
        if(cell != null) {
            switch (cell.getCellTypeEnum()) {
                case STRING:
                    value = cell.getStringCellValue();
                    break;

                case NUMERIC:
                    value = String.valueOf((long) cell.getNumericCellValue());
                    break;

                default:
                    value = "Error value of type " + cell.getCellTypeEnum() + " , Please convert it as String.";
            }
            System.out.println("CELL col=" + cell.getColumnIndex() + " VALUE=" + value);
        }
        return value;
    }

    public static void main(String[] args) throws IOException {
        Set<String> result = new HashSet<>();


        Set<String> r1 = readSN(fileName, 0);
        Set<String> r2 = readSN(fileName, 1);
        Set<String> r3 = readSN(fileName, 2);
        Set<String> r4 = readSN(fileName, 3);
        Set<String> r5 = readSN(fileName, 4);
        Set<String> r6 = readSN(fileName, 5);
        Set<String> r7 = readSN(fileName, 6);

        result.addAll(r1);
        result.addAll(r2);
        result.addAll(r3);
        result.addAll(r4);
        result.addAll(r5);
        result.addAll(r6);
        result.addAll(r7);

        BufferedWriter bw = new BufferedWriter(new FileWriter("d:\\FTP\\result.txt"));
        for(String sn : result){
            bw.write(sn);
            bw.write("\n");
        }
        bw.close();

    }
}
