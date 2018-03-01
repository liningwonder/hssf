Java操作Excel、txt/csv文件
1. Excel
（1）write
public class ExcelUtils {
    public static void writeExcel(String filePath) {
        try {
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet s = wb.createSheet();
            HSSFCellStyle cs = wb.createCellStyle();

            HSSFFont f = wb.createFont();
            f.setColor((short) 0xA);
            cs.setFont(f);

            wb.setSheetName(0, "HSSF Test");

            int rownum;
            for (rownum = 0; rownum < 300; rownum++) {
                HSSFRow r = s.createRow(rownum);
                for (int cellnum = 0; cellnum < 50; cellnum ++) {
                    HSSFCell c = r.createCell(cellnum);
                    c.setCellValue(1);
                }
            }

            try (FileOutputStream out = new FileOutputStream(filePath)) {
                wb.write(out);
            }
        }
    }
}


FTP文件服务器 vsftpd

HTTP文件服务器 apache httpd