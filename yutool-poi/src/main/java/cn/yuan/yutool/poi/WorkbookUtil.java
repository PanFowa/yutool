
package cn.yuan.yutool.poi;


import cn.yuan.yutool.poi.excel.exceptions.POIException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 *
 */
public class WorkbookUtil {

    /**
     * 创建或加载工作簿
     *
     * @param excelFilePath Excel文件路径，绝对路径或相对于ClassPath路径
     * @return {@link Workbook}
     * @since 1.0-jdk1.6
     */
    public static Workbook createBook(String excelFilePath) {
        return createBook(new File(excelFilePath), null);
    }

    /**
     * 创建或加载工作簿
     *
     * @param excelFile Excel文件
     * @return {@link Workbook}
     */
    public static Workbook createBook(File excelFile) {
        return createBook(excelFile, null);
    }

    /**
     * 创建工作簿，用于Excel写出
     *
     * <pre>
     * 1. excelFile为null时直接返回一个空的工作簿，默认xlsx格式
     * 2. 文件已存在则通过流的方式读取到这个工作簿
     * 3. 文件不存在则检查传入文件路径是否以xlsx为扩展名，是则创建xlsx工作簿，否则创建xls工作簿
     * </pre>
     *
     * @param excelFile Excel文件
     * @return {@link Workbook}
     * @since 1.0-jdk1.6
     */
    public static Workbook createBookForWriter(File excelFile) throws Exception {
        if (null == excelFile) {
            return createBook(true);
        }

        if (excelFile.exists()) {
            return createBook(new FileInputStream(excelFile), true);
        }
        return createBook("xlsx".equals(FilenameUtils.getExtension(excelFile.getName()).toLowerCase()));
    }

    /**
     * 创建或加载工作簿，只读模式
     *
     * @param excelFile Excel文件
     * @param password  Excel工作簿密码，如果无密码传{@code null}
     * @return {@link Workbook}
     */
    public static Workbook createBook(File excelFile, String password) {
        try {
            return WorkbookFactory.create(excelFile, password);
        } catch (Exception e) {
            throw new POIException(e);
        }
    }

    /**
     * 创建或加载工作簿
     *
     * @param in             Excel输入流
     * @param closeAfterRead 读取结束是否关闭流
     * @return {@link Workbook}
     */
    public static Workbook createBook(InputStream in, boolean closeAfterRead) {
        return createBook(in, null, closeAfterRead);
    }

    /**
     * 创建或加载工作簿
     *
     * @param in             Excel输入流
     * @param password       密码
     * @param closeAfterRead 读取结束是否关闭流
     * @return {@link Workbook}
     * @since 1.0-jdk1.6
     */
    public static Workbook createBook(InputStream in, String password, boolean closeAfterRead) {
        try {
            return WorkbookFactory.create(new BufferedInputStream(in), password);
        } catch (Exception e) {
            throw new POIException(e);
        } finally {
            if (closeAfterRead) {
                IOUtils.closeQuietly(in);
            }
        }
    }

    /**
     * 根据文件类型创建新的工作簿，文件路径
     *
     * @param isXlsx 是否为xlsx格式的Excel
     * @return {@link Workbook}
     * @since 1.0-jdk1.6
     */
    public static Workbook createBook(boolean isXlsx) {
        Workbook workbook;
        if (isXlsx) {
            workbook = new XSSFWorkbook();
        } else {
            workbook = new org.apache.poi.hssf.usermodel.HSSFWorkbook();
        }
        return workbook;
    }

    /**
     * 创建或加载SXSSFWorkbook工作簿
     *
     * @param excelFilePath Excel文件路径，绝对路径或相对于ClassPath路径
     * @return {@link SXSSFWorkbook}
     * @since 1.0-jdk1.6
     */
    public static SXSSFWorkbook createSXSSFBook(String excelFilePath) {
        return createSXSSFBook(new File(excelFilePath), null);
    }

    /**
     * 创建或加载SXSSFWorkbook工作簿
     *
     * @param excelFile Excel文件
     * @return {@link SXSSFWorkbook}
     * @since 1.0-jdk1.6
     */
    public static SXSSFWorkbook createSXSSFBook(File excelFile) {
        return createSXSSFBook(excelFile, null);
    }

    /**
     * 创建或加载SXSSFWorkbook工作簿，只读模式
     *
     * @param excelFile Excel文件
     * @param password  Excel工作簿密码，如果无密码传{@code null}
     * @return {@link SXSSFWorkbook}
     * @since 1.0-jdk1.6
     */
    public static SXSSFWorkbook createSXSSFBook(File excelFile, String password) {
        return toSXSSFBook(createBook(excelFile, password));
    }

    /**
     * 创建或加载SXSSFWorkbook工作簿
     *
     * @param in             Excel输入流
     * @param closeAfterRead 读取结束是否关闭流
     * @return {@link SXSSFWorkbook}
     * @since 1.0-jdk1.6
     */
    public static SXSSFWorkbook createSXSSFBook(InputStream in, boolean closeAfterRead) {
        return createSXSSFBook(in, null, closeAfterRead);
    }

    /**
     * 创建或加载SXSSFWorkbook工作簿
     *
     * @param in             Excel输入流
     * @param password       密码
     * @param closeAfterRead 读取结束是否关闭流
     * @return {@link SXSSFWorkbook}
     * @since 1.0-jdk1.6
     */
    public static SXSSFWorkbook createSXSSFBook(InputStream in, String password, boolean closeAfterRead) {
        return toSXSSFBook(createBook(in, password, closeAfterRead));
    }

    /**
     * 创建SXSSFWorkbook，用于大批量数据写出
     *
     * @return {@link SXSSFWorkbook}
     * @since 1.0-jdk1.6
     */
    public static SXSSFWorkbook createSXSSFBook() {
        return new SXSSFWorkbook();
    }

    /**
     * 创建SXSSFWorkbook，用于大批量数据写出
     *
     * @param rowAccessWindowSize 在内存中的行数
     * @return {@link Workbook}
     * @since 1.0-jdk1.6
     */
    public static SXSSFWorkbook createSXSSFBook(int rowAccessWindowSize) {
        return new SXSSFWorkbook(rowAccessWindowSize);
    }

    /**
     * 将Excel Workbook刷出到输出流，不关闭流
     *
     * @param book {@link Workbook}
     * @param out  输出流
     * @throws RuntimeException IO异常
     * @since 1.0-jdk1.6
     */
    public static void writeBook(Workbook book, OutputStream out) throws RuntimeException {
        try {
            book.write(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取或者创建sheet表<br>
     * 如果sheet表在Workbook中已经存在，则获取之，否则创建之
     *
     * @param book      工作簿{@link Workbook}
     * @param sheetName 工作表名
     * @return 工作表{@link Sheet}
     * @since 1.0-jdk1.6
     */
    public static Sheet getOrCreateSheet(Workbook book, String sheetName) {
        if (null == book) {
            return null;
        }
        sheetName = StringUtils.isBlank(sheetName) ? "sheet1" : sheetName;
        Sheet sheet = book.getSheet(sheetName);
        if (null == sheet) {
            sheet = book.createSheet(sheetName);
        }
        return sheet;
    }

    /**
     * sheet是否为空
     *
     * @param sheet {@link Sheet}
     * @return sheet是否为空
     * @since 1.0-jdk1.6
     */
    public static boolean isEmpty(Sheet sheet) {
        return null == sheet || (sheet.getLastRowNum() == 0 && sheet.getPhysicalNumberOfRows() == 0);
    }

    /**
     * 将普通工作簿转换为SXSSFWorkbook
     *
     * @param book 工作簿
     * @return SXSSFWorkbook
     * @since 1.0-jdk1.6
     */
    private static SXSSFWorkbook toSXSSFBook(Workbook book) {
        if (book instanceof SXSSFWorkbook) {
            return (SXSSFWorkbook) book;
        }
        if (book instanceof XSSFWorkbook) {
            return new SXSSFWorkbook((XSSFWorkbook) book);
        }
        throw new POIException("The input is not a [xlsx] format.");
    }
}
