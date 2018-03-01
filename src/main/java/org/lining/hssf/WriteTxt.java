package org.lining.hssf;

import java.io.*;
import java.util.*;

/**
 * description:
 * date 2018-02-27
 *
 * @author lining1
 * @version 1.0.0
 */
public class WriteTxt {

    public static Set readTxtFile2(String filePath) {
        Set<String> ss = new HashSet<>();
        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file));//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    ss.add(lineTxt);
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return ss;
    }

    public static Map writeTxt(String filePath) {
        Set<String> ss = readTxtFile2(filePath);
        Map<String, String> map = new HashMap<>();
        for (String s : ss) {
            String[] split = s.split("\t");
            String key = split[0] + "\t" + split[1];
            String value = split[2] + "\t" + split[3] + "\t";
            if (map.containsKey(key)) {
                String value2 = map.get(key) + value;
                map.put(key, value2);
            } else {
                map.put(key, value);
            }
        }
        return map;
    }

    public static void main(String[] args) throws Exception {
        Map<String, String> map = writeTxt("D:\\统计数据\\2.txt");
        String pathname = "D:\\统计数据\\33.txt";
        File writename = new File(pathname);
        writename.createNewFile();
        BufferedWriter out = new BufferedWriter(new FileWriter(writename));
        for (Map.Entry<String, String> entry : map.entrySet()) {
            out.write(entry.getKey() + "\t" + entry.getValue());
            out.write("\n");
        }
        out.flush();
        out.close();
    }

}
