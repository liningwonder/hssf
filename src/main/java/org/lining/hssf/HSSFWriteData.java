package org.lining.hssf;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * description:
 * date 2018-02-27
 *
 * @author lining1
 * @version 1.0.0
 */
public class HSSFWriteData {

    private static void testCreateSampleSheet(String outputFilename) throws IOException {
        try (HSSFWorkbook wb = new HSSFWorkbook()) {
            HSSFSheet s = wb.createSheet();
            HSSFCellStyle cs = wb.createCellStyle();
            HSSFCellStyle cs2 = wb.createCellStyle();
            HSSFCellStyle cs3 = wb.createCellStyle();
            HSSFFont f = wb.createFont();
            HSSFFont f2 = wb.createFont();

            f.setFontHeightInPoints((short) 12);
            f.setColor((short) 0xA);
            f.setBold(true);
            f2.setFontHeightInPoints((short) 10);
            f2.setColor((short) 0xf);
            f2.setBold(true);
            cs.setFont(f);
            cs.setDataFormat(HSSFDataFormat.getBuiltinFormat("($#,##0_);[Red]($#,##0)"));
            cs2.setBorderBottom(BorderStyle.THIN);
            cs2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cs2.setFillForegroundColor((short) 0xA);
            cs2.setFont(f2);
            wb.setSheetName(0, "HSSF Test");
            int rownum;
            for (rownum = 0; rownum < 300; rownum++) {
                HSSFRow r = s.createRow(rownum);
                if ((rownum % 2) == 0) {
                    r.setHeight((short) 0x249);
                }

                for (int cellnum = 0; cellnum < 50; cellnum += 2) {
                    HSSFCell c = r.createCell(cellnum);
                    c.setCellValue(rownum * 10000 + cellnum
                            + (((double) rownum / 1000) + ((double) cellnum / 10000)));
                    if ((rownum % 2) == 0) {
                        c.setCellStyle(cs);
                    }
                    c = r.createCell(cellnum + 1);
                    c.setCellValue(new HSSFRichTextString("TEST"));
                    // 50 characters divided by 1/20th of a point
                    s.setColumnWidth(cellnum + 1, (int) (50 * 8 / 0.05));
                    if ((rownum % 2) == 0) {
                        c.setCellStyle(cs2);
                    }
                }
            }

            // draw a thick black border on the row at the bottom using BLANKS
            rownum++;
            rownum++;
            HSSFRow r = s.createRow(rownum);
            cs3.setBorderBottom(BorderStyle.THICK);
            for (int cellnum = 0; cellnum < 50; cellnum++) {
                HSSFCell c = r.createCell(cellnum);
                c.setCellStyle(cs3);
            }
            s.addMergedRegion(new CellRangeAddress(0, 3, 0, 3));
            s.addMergedRegion(new CellRangeAddress(100, 110, 100, 110));

            // end draw thick black border
            // create a sheet, set its title then delete it
            wb.createSheet();
            wb.setSheetName(1, "DeletedSheet");
            wb.removeSheetAt(1);

            // end deleted sheet
            try (FileOutputStream out = new FileOutputStream(outputFilename)) {
                wb.write(out);
            }
        }
    }
}
