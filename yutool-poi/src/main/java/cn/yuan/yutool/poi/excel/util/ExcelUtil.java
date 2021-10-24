package cn.yuan.yutool.poi.excel.util;


import cn.yuan.yutool.poi.excel.BigExcelWriter;
import cn.yuan.yutool.poi.excel.ExcelReader;
import cn.yuan.yutool.poi.excel.ExcelWriter;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Excel工具类
 *
 * @author PanFowa
 */
public class ExcelUtil {


    /**
     * 获取Excel读取器，通过调用{@link ExcelReader}的read或readXXX方法读取Excel内容<br>
     * 默认调用第一个sheet
     *
     * @param bookFilePath Excel文件路径，绝对路径或相对于ClassPath路径
     * @return {@link ExcelReader}
     * @since 1.0-jdk1.6
     */
    public static ExcelReader getReader(String bookFilePath) {
        return getReader(bookFilePath, 0);
    }

    /**
     * 获取Excel读取器，通过调用{@link ExcelReader}的read或readXXX方法读取Excel内容<br>
     * 默认调用第一个sheet
     *
     * @param bookFile Excel文件
     * @return {@link ExcelReader}
     */
    public static ExcelReader getReader(File bookFile) {
        return getReader(bookFile, 0);
    }

    /**
     * 获取Excel读取器，通过调用{@link ExcelReader}的read或readXXX方法读取Excel内容
     *
     * @param bookFilePath Excel文件路径，绝对路径或相对于ClassPath路径
     * @param sheetIndex   sheet序号，0表示第一个sheet
     * @return {@link ExcelReader}
     * @since 1.0-jdk1.6
     */
    public static ExcelReader getReader(String bookFilePath, int sheetIndex) {
        return new ExcelReader(bookFilePath, sheetIndex);
    }

    /**
     * 获取Excel读取器，通过调用{@link ExcelReader}的read或readXXX方法读取Excel内容
     *
     * @param bookFile   Excel文件
     * @param sheetIndex sheet序号，0表示第一个sheet
     * @return {@link ExcelReader}
     */
    public static ExcelReader getReader(File bookFile, int sheetIndex) {
        return new ExcelReader(bookFile, sheetIndex);
    }

    /**
     * 获取Excel读取器，通过调用{@link ExcelReader}的read或readXXX方法读取Excel内容
     *
     * @param bookFile  Excel文件
     * @param sheetName sheet名，第一个默认是sheet1
     * @return {@link ExcelReader}
     */
    public static ExcelReader getReader(File bookFile, String sheetName) {
        return new ExcelReader(bookFile, sheetName);
    }

    /**
     * 获取Excel读取器，通过调用{@link ExcelReader}的read或readXXX方法读取Excel内容<br>
     * 默认调用第一个sheet，读取结束自动关闭流
     *
     * @param bookStream Excel文件的流
     * @return {@link ExcelReader}
     */
    public static ExcelReader getReader(InputStream bookStream) {
        return getReader(bookStream, 0, true);
    }

    /**
     * 获取Excel读取器，通过调用{@link ExcelReader}的read或readXXX方法读取Excel内容<br>
     * 默认调用第一个sheet
     *
     * @param bookStream     Excel文件的流
     * @param closeAfterRead 读取结束是否关闭流
     * @return {@link ExcelReader}
     * @since 1.0-jdk1.6
     */
    public static ExcelReader getReader(InputStream bookStream, boolean closeAfterRead) {
        try {
            return getReader(bookStream, 0, closeAfterRead);
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError("");
        }
    }

    /**
     * 获取Excel读取器，通过调用{@link ExcelReader}的read或readXXX方法读取Excel内容<br>
     * 读取结束自动关闭流
     *
     * @param bookStream Excel文件的流
     * @param sheetIndex sheet序号，0表示第一个sheet
     * @return {@link ExcelReader}
     */
    public static ExcelReader getReader(InputStream bookStream, int sheetIndex) {
        return new ExcelReader(bookStream, sheetIndex, true);
    }

    /**
     * 获取Excel读取器，通过调用{@link ExcelReader}的read或readXXX方法读取Excel内容
     *
     * @param bookStream     Excel文件的流
     * @param sheetIndex     sheet序号，0表示第一个sheet
     * @param closeAfterRead 读取结束是否关闭流
     * @return {@link ExcelReader}
     * @since 1.0-jdk1.6
     */
    public static ExcelReader getReader(InputStream bookStream, int sheetIndex, boolean closeAfterRead) {

        return new ExcelReader(bookStream, sheetIndex, closeAfterRead);

    }

    /**
     * 获取Excel读取器，通过调用{@link ExcelReader}的read或readXXX方法读取Excel内容<br>
     * 读取结束自动关闭流
     *
     * @param bookStream Excel文件的流
     * @param sheetName  sheet名，第一个默认是sheet1
     * @return {@link ExcelReader}
     */
    public static ExcelReader getReader(InputStream bookStream, String sheetName) {

        return new ExcelReader(bookStream, sheetName, true);

    }

    /**
     * 获取Excel读取器，通过调用{@link ExcelReader}的read或readXXX方法读取Excel内容
     *
     * @param bookStream     Excel文件的流
     * @param sheetName      sheet名，第一个默认是sheet1
     * @param closeAfterRead 读取结束是否关闭流
     * @return {@link ExcelReader}
     */
    public static ExcelReader getReader(InputStream bookStream, String sheetName, boolean closeAfterRead) {

        return new ExcelReader(bookStream, sheetName, closeAfterRead);

    }

    /**
     * 获得{@link ExcelWriter}，默认写出到第一个sheet<br>
     * 不传入写出的Excel文件路径，只能调用{@link ExcelWriter#flush(OutputStream)}方法写出到流<br>
     * 若写出到文件，还需调用{@link ExcelWriter#setDestFile(File)}方法自定义写出的文件，然后调用{@link ExcelWriter#flush()}方法写出到文件
     *
     * @return {@link ExcelWriter}
     * @since 1.0-jdk1.6
     */
    public static ExcelWriter getWriter() {

        return new ExcelWriter();

    }

    /**
     * 获得{@link ExcelWriter}，默认写出到第一个sheet<br>
     * 不传入写出的Excel文件路径，只能调用{@link ExcelWriter#flush(OutputStream)}方法写出到流<br>
     * 若写出到文件，还需调用{@link ExcelWriter#setDestFile(File)}方法自定义写出的文件，然后调用{@link ExcelWriter#flush()}方法写出到文件
     *
     * @param isXlsx 是否为xlsx格式
     * @return {@link ExcelWriter}
     * @since 1.0-jdk1.6
     */
    public static ExcelWriter getWriter(boolean isXlsx) {

        return new ExcelWriter(isXlsx);

    }

    /**
     * 获得{@link ExcelWriter}，默认写出到第一个sheet
     *
     * @param destFilePath 目标文件路径
     * @return {@link ExcelWriter}
     */
    public static ExcelWriter getWriter(String destFilePath) throws Exception {

        return new ExcelWriter(destFilePath);

    }

    /**
     * 获得{@link ExcelWriter}，默认写出到第一个sheet
     *
     * @param sheetName Sheet名
     * @return {@link ExcelWriter}
     * @since 1.0-jdk1.6
     */
    public static ExcelWriter getWriterWithSheet(String sheetName) throws Exception {

        return new ExcelWriter((File) null, sheetName);

    }

    /**
     * 获得{@link ExcelWriter}，默认写出到第一个sheet，名字为sheet1
     *
     * @param destFile 目标文件
     * @return {@link ExcelWriter}
     */
    public static ExcelWriter getWriter(File destFile) throws Exception {
        return new ExcelWriter(destFile);
    }

    /**
     * 获得{@link ExcelWriter}
     *
     * @param destFilePath 目标文件路径
     * @param sheetName    sheet表名
     * @return {@link ExcelWriter}
     */
    public static ExcelWriter getWriter(String destFilePath, String sheetName) throws Exception {
        return new ExcelWriter(destFilePath, sheetName);
    }

    /**
     * 获得{@link ExcelWriter}
     *
     * @param destFile  目标文件
     * @param sheetName sheet表名
     * @return {@link ExcelWriter}
     */
    public static ExcelWriter getWriter(File destFile, String sheetName) throws Exception {
        return new ExcelWriter(destFile, sheetName);
    }

    /**
     * 获得{@link BigExcelWriter}，默认写出到第一个sheet<br>
     * 不传入写出的Excel文件路径，只能调用{@link BigExcelWriter#flush(OutputStream)}方法写出到流<br>
     * 若写出到文件，还需调用{@link BigExcelWriter#setDestFile(File)}方法自定义写出的文件，然后调用{@link BigExcelWriter#flush()}方法写出到文件
     *
     * @return {@link BigExcelWriter}
     * @since 1.0-jdk1.6
     */
    public static ExcelWriter getBigWriter() {
        return new BigExcelWriter();
    }

    /**
     * 获得{@link BigExcelWriter}，默认写出到第一个sheet<br>
     * 不传入写出的Excel文件路径，只能调用{@link BigExcelWriter#flush(OutputStream)}方法写出到流<br>
     * 若写出到文件，还需调用{@link BigExcelWriter#setDestFile(File)}方法自定义写出的文件，然后调用{@link BigExcelWriter#flush()}方法写出到文件
     *
     * @param rowAccessWindowSize 在内存中的行数
     * @return {@link BigExcelWriter}
     * @since 1.0-jdk1.6
     */
    public static ExcelWriter getBigWriter(int rowAccessWindowSize) {
        return new BigExcelWriter(rowAccessWindowSize);
    }

    /**
     * 获得{@link BigExcelWriter}，默认写出到第一个sheet
     *
     * @param destFilePath 目标文件路径
     * @return {@link BigExcelWriter}
     */
    public static BigExcelWriter getBigWriter(String destFilePath) {
        return new BigExcelWriter(destFilePath);
    }

    /**
     * 获得{@link BigExcelWriter}，默认写出到第一个sheet，名字为sheet1
     *
     * @param destFile 目标文件
     * @return {@link BigExcelWriter}
     */
    public static BigExcelWriter getBigWriter(File destFile) {
        return new BigExcelWriter(destFile);
    }

    /**
     * 获得{@link BigExcelWriter}
     *
     * @param destFilePath 目标文件路径
     * @param sheetName    sheet表名
     * @return {@link BigExcelWriter}
     */
    public static BigExcelWriter getBigWriter(String destFilePath, String sheetName) {
        return new BigExcelWriter(destFilePath, sheetName);
    }

    /**
     * 获得{@link BigExcelWriter}
     *
     * @param destFile  目标文件
     * @param sheetName sheet表名
     * @return {@link BigExcelWriter}
     */
    public static BigExcelWriter getBigWriter(File destFile, String sheetName) {
        return new BigExcelWriter(destFile, sheetName);
    }

    /**
     * 将Sheet列号变为列名
     *
     * @param index 列号, 从0开始
     * @return 0-》A; 1-》B...26-》AA
     * @since 1.0-jdk1.6
     */
    public static String indexToColName(int index) {
        if (index < 0) {
            return null;
        }
        final StringBuilder colName = new StringBuilder();
        do {
            if (colName.length() > 0) {
                index--;
            }
            int remainder = index % 26;
            colName.append((char) (remainder + 'A'));
            index = (int) ((index - remainder) / 26);
        } while (index > 0);
        return colName.reverse().toString();
    }

    /**
     * 根据表元的列名转换为列号
     *
     * @param colName 列名, 从A开始
     * @return A1-》0; B1-》1...AA1-》26
     * @since 1.0-jdk1.6
     */
    public static int colNameToIndex(String colName) {
        int length = colName.length();
        char c;
        int index = -1;
        for (int i = 0; i < length; i++) {
            c = Character.toUpperCase(colName.charAt(i));
            if (Character.isDigit(c)) {
                break;// 确定指定的char值是否为数字
            }
            index = (index + 1) * 26 + (int) c - 'A';
        }
        return index;
    }
}
