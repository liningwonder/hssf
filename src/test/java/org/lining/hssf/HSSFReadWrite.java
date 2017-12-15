

package org.lining.hssf;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * File for HSSF testing/examples
 *
 */
public final class HSSFReadWrite {

    private static List<Map<String, String>> DATALIST = new ArrayList<>();
    private static final String fileName = "d:\\FTP\\new2.xls";
    private static int ROWS = 0;
    private static int COLUMNS = 0;

    static {
        init();
    }

    /**
     * creates an {@link HSSFWorkbook} with the specified OS filename.
     */
    private static HSSFWorkbook readFile(String filename) throws IOException {
        FileInputStream fis = new FileInputStream(filename);
        try {
            return new HSSFWorkbook(fis);		// NOSONAR - should not be closed here
        } finally {
            fis.close();
        }
    }


    /**
     * Method init()
     *
     */
    public static void init() {
        try {
            HSSFWorkbook wb = HSSFReadWrite.readFile(fileName);
            HSSFSheet sheet = wb.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();
            int column = sheet.getRow(1).getLastCellNum();
            ROWS = rows;
            COLUMNS = column;
            Map<String, String> map = new HashMap<>();
            for (int r = 1; r < rows; r++) {
                HSSFRow row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                HSSFCell cell0 = row.getCell(0);
                HSSFCell cell1 = row.getCell(1);
                String value0 = getStringFromHssf(cell0);
                String value1 = getStringFromHssf(cell1);
                map.put(value0, value1);
                DATALIST.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getStringFromHssf(HSSFCell cell) {
        String value = null;
        if(cell != null) {
            switch (cell.getCellTypeEnum()) {
                case FORMULA:
                    value = "FORMULA value=" + cell.getCellFormula();
                    break;

                case NUMERIC:
                    value = "NUMERIC value=" + cell.getNumericCellValue();
                    break;

                case STRING:
                    value = "STRING value=" + cell.getStringCellValue();
                    break;

                case BLANK:
                    value = "<BLANK>";
                    break;

                case BOOLEAN:
                    value = "BOOLEAN value-" + cell.getBooleanCellValue();
                    break;

                case ERROR:
                    value = "ERROR value=" + cell.getErrorCellValue();
                    break;

                default:
                    value = "UNKNOWN value of type " + cell.getCellTypeEnum();
            }
            System.out.println("CELL col=" + cell.getColumnIndex() + " VALUE="
                    + value);
        }
        return value;
    }

    public static void main(String[] args) {
        Map<String, String> map = DATALIST.get(0);
        System.out.println(map.toString());
    }

}
