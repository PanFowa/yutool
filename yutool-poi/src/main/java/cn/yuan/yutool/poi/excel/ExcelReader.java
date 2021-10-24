package cn.yuan.yutool.poi.excel;


import cn.yuan.yutool.poi.WorkbookUtil;
import cn.yuan.yutool.poi.annaction.HeadAlias;
import cn.yuan.yutool.poi.excel.cell.CellEditor;
import cn.yuan.yutool.poi.excel.cell.CellUtil;
import cn.yuan.yutool.poi.excel.util.ExcelUtil;
import cn.yuan.yutool.poi.excel.util.RowUtil;
import cn.yuan.yutool.util.IterUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.extractor.ExcelExtractor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel读取器<br>
 * 读取Excel工作簿
 *
 * @author PanFowa
 * @since 1.0-jdk1.6
 */
public class ExcelReader extends ExcelBase<ExcelReader> {

    /**
     * 是否忽略空行
     */
    private boolean ignoreEmptyRow = true;
    /**
     * 单元格值处理接口
     */
    private CellEditor cellEditor;
    /**
     * 表头与属性映射表
     */
    private Map<String, String> headerAlias = new HashMap();

    /**
     * @param excelFilePath Excel文件路径，绝对路径或相对于ClassPath路径
     * @param sheetIndex    sheet序号，0表示第一个sheet
     */
    public ExcelReader(String excelFilePath, int sheetIndex) {
        this(new File(excelFilePath), sheetIndex);
    }

    /**
     * @param bookFile   Excel文件
     * @param sheetIndex sheet序号，0表示第一个sheet
     */
    public ExcelReader(File bookFile, int sheetIndex) {
        this(WorkbookUtil.createBook(bookFile), sheetIndex);
    }

    /**
     * 构造
     *
     * @param bookFile  Excel文件
     * @param sheetName sheet名，第一个默认是sheet1
     */
    public ExcelReader(File bookFile, String sheetName) {
        this(WorkbookUtil.createBook(bookFile), sheetName);
    }

    /**
     * 构造
     *
     * @param bookStream     Excel文件的流
     * @param sheetIndex     sheet序号，0表示第一个sheet
     * @param closeAfterRead 读取结束是否关闭流
     */
    public ExcelReader(InputStream bookStream, int sheetIndex, boolean closeAfterRead) {
        this(WorkbookUtil.createBook(bookStream, closeAfterRead), sheetIndex);
    }

    /**
     * 构造
     *
     * @param bookStream     Excel文件的流
     * @param sheetName      sheet名，第一个默认是sheet1
     * @param closeAfterRead 读取结束是否关闭流
     */
    public ExcelReader(InputStream bookStream, String sheetName, boolean closeAfterRead) {
        this(WorkbookUtil.createBook(bookStream, closeAfterRead), sheetName);
    }

    /**
     * 构造
     *
     * @param book       {@link Workbook} 表示一个Excel文件
     * @param sheetIndex sheet序号，0表示第一个sheet
     */
    public ExcelReader(Workbook book, int sheetIndex) {
        this(book.getSheetAt(sheetIndex));
    }

    /**
     * 构造
     *
     * @param book      {@link Workbook} 表示一个Excel文件
     * @param sheetName sheet名，第一个默认是sheet1
     */
    public ExcelReader(Workbook book, String sheetName) {
        this(book.getSheet(sheetName));
    }

    /**
     * 构造
     *
     * @param sheet Excel中的sheet
     */
    public ExcelReader(Sheet sheet) {
        super(sheet);
    }

    /**
     * 是否忽略空行
     *
     * @return 是否忽略空行
     */
    public boolean isIgnoreEmptyRow() {
        return ignoreEmptyRow;
    }

    /**
     * 设置是否忽略空行
     *
     * @param ignoreEmptyRow 是否忽略空行
     * @return this
     */
    public ExcelReader setIgnoreEmptyRow(boolean ignoreEmptyRow) {
        this.ignoreEmptyRow = ignoreEmptyRow;
        return this;
    }

    /**
     * 设置单元格值处理逻辑<br>
     * 当Excel中的值并不能满足我们的读取要求时，通过传入一个编辑接口，可以对单元格值自定义，例如对数字和日期类型值转换为字符串等
     *
     * @param cellEditor 单元格值处理接口
     * @return this
     */
    public ExcelReader setCellEditor(CellEditor cellEditor) {
        this.cellEditor = cellEditor;
        return this;
    }

    /**
     * 获得标题行的别名Map
     *
     * @return 别名Map
     */
    public Map<String, String> getHeaderAlias() {
        return headerAlias;
    }

    /**
     * 设置标题行的别名Map
     *
     * @param headerAlias 别名Map
     * @return this
     */
    public ExcelReader setHeaderAlias(Map<String, String> headerAlias) {
        this.headerAlias = headerAlias;
        return this;
    }

    /**
     * 增加标题别名
     *
     * @param header 标题
     * @param alias  别名
     * @return this
     */
    public ExcelReader addHeaderAlias(String header, String alias) {
        this.headerAlias.put(header, alias);
        return this;
    }

    /**
     * 去除标题别名
     *
     * @param header 标题
     * @return this
     */
    public ExcelReader removeHeaderAlias(String header) {
        this.headerAlias.remove(header);
        return this;
    }

    /**
     * 读取工作簿中指定的Sheet的所有行列数据
     *
     * @return 行的集合，一行使用List表示
     */
    public List<List<Object>> read() {
        return read(0);
    }

    /**
     * 读取工作簿中指定的Sheet
     *
     * @param startRowIndex 起始行（包含，从0开始计数）
     * @return 行的集合，一行使用List表示
     * @since 1.0-jdk1.6
     */
    public List<List<Object>> read(int startRowIndex) {
        return read(startRowIndex, Integer.MAX_VALUE);
    }

    /**
     * 读取工作簿中指定的Sheet
     *
     * @param startRowIndex 起始行（包含，从0开始计数）
     * @param endRowIndex   结束行（包含，从0开始计数）
     * @return 行的集合，一行使用List表示
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<List<Object>> read(int startRowIndex, int endRowIndex) {
        checkNotClosed();
        List<List<Object>> resultList = new ArrayList();

        startRowIndex = Math.max(startRowIndex, this.sheet.getFirstRowNum());// 读取起始行（包含）
        endRowIndex = Math.min(endRowIndex, this.sheet.getLastRowNum());// 读取结束行（包含）
        boolean isFirstLine = true;
        List rowList;
        for (int i = startRowIndex; i <= endRowIndex; i++) {
            rowList = readRow(i);
            if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(rowList) || false == ignoreEmptyRow) {
                if (null == rowList) {
                    rowList = new ArrayList(0);
                }
                if (isFirstLine) {
                    isFirstLine = false;
                    if (!this.headerAlias.isEmpty()) {
                        rowList = aliasHeader(rowList);
                    }
                }
                resultList.add(rowList);
            }
        }
        return resultList;
    }

    /**
     * 读取Excel为Map的列表，读取所有行，默认第一行做为标题，数据从第二行开始<br>
     * Map表示一行，标题为key，单元格内容为value
     *
     * @return Map的列表
     */
    public List<Map<String, Object>> readAll() {
        return read(0, 1, Integer.MAX_VALUE);
    }

    /**
     * 读取Excel为Map的列表<br>
     * Map表示一行，标题为key，单元格内容为value
     *
     * @param headerRowIndex 标题所在行，如果标题行在读取的内容行中间，这行做为数据将忽略
     * @param startRowIndex  起始行（包含，从0开始计数）
     * @param endRowIndex    读取结束行（包含，从0开始计数）
     * @return Map的列表
     */
    public List<Map<String, Object>> read(int headerRowIndex, int startRowIndex, int endRowIndex) {
        checkNotClosed();
        // 边界判断
        final int firstRowNum = sheet.getFirstRowNum();
        final int lastRowNum = sheet.getLastRowNum();
        if (headerRowIndex < firstRowNum) {
            throw new IndexOutOfBoundsException("Header row index " + headerRowIndex + " is lower than first row index " + firstRowNum + ".");
        } else if (headerRowIndex > lastRowNum) {
            throw new IndexOutOfBoundsException("Header row index " + headerRowIndex + " is greater than last row index " + firstRowNum + ".");
        }
        startRowIndex = Math.max(startRowIndex, firstRowNum);// 读取起始行（包含）
        endRowIndex = Math.min(endRowIndex, lastRowNum);// 读取结束行（包含）

        // 读取header
        List<Object> headerList = readRow(sheet.getRow(headerRowIndex));

        final List<Map<String, Object>> result = new ArrayList(endRowIndex - startRowIndex + 1);
        List<Object> rowList;
        for (int i = startRowIndex; i <= endRowIndex; i++) {
            if (i != headerRowIndex) {
                // 跳过标题行
                rowList = readRow(sheet.getRow(i));
                if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(rowList) || false == ignoreEmptyRow) {
                    if (null == rowList) {
                        rowList = new ArrayList(0);
                    }
                    result.add(IterUtil.toMap(aliasHeader(headerList), rowList));
                }
            }
        }
        return result;
    }

    /**
     * 读取Excel为Bean的列表，读取所有行，默认第一行做为标题，数据从第二行开始
     *
     * @param <T>      Bean类型
     * @param beanClass 每行对应Bean的类型
     * @return Map的列表
     */
    public <T> List<T> readAll(Class<T> beanClass) throws Exception {
        return read(0, 1, Integer.MAX_VALUE, beanClass);
    }

    /**
     * 读取Excel为Bean的列表
     *
     * @param <T>            Bean类型
     * @param headerRowIndex 标题所在行，如果标题行在读取的内容行中间，这行做为数据将忽略，，从0开始计数
     * @param startRowIndex  起始行（包含，从0开始计数）
     * @param beanClass       每行对应Bean的类型
     * @return Map的列表
     * @since 1.0-jdk1.6
     */
    public <T> List<T> read(int headerRowIndex, int startRowIndex, Class<T> beanClass) throws Exception {
        return read(headerRowIndex, startRowIndex, Integer.MAX_VALUE, beanClass);
    }

    /**
     * 读取Excel为Bean的列表
     *
     * @param <T>            Bean类型
     * @param headerRowIndex 标题所在行，如果标题行在读取的内容行中间，这行做为数据将忽略，，从0开始计数
     * @param startRowIndex  起始行（包含，从0开始计数）
     * @param endRowIndex    读取结束行（包含，从0开始计数）
     * @param beanClass       每行对应Bean的类型
     * @return Map的列表
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> read(int headerRowIndex, int startRowIndex, int endRowIndex, Class<T> beanClass) throws Exception {
        //从标注中读取excel中属性与表头映射关系
        if (this.headerAlias.isEmpty()) {
            getAllHeadRelect(beanClass);
        }
        checkNotClosed();
        final List<Map<String, Object>> mapList = read(headerRowIndex, startRowIndex, endRowIndex);
        if (Map.class.isAssignableFrom(beanClass)) {
            return (List<T>) mapList;
        }

        final List<T> beanList = new ArrayList(mapList.size());
        for (Map<String, Object> map : mapList) {
            Object o = beanClass.newInstance();
            BeanUtils.populate(o, map);
            beanList.add((T) o);
        }
        return beanList;
    }

    /**
     * 解析给定类及其所有父类指定的表头与类属性的对应关系
     * @param clazz
     * @throws NoSuchFieldException
     */
    private <T> void getAllHeadRelect(Class<T> clazz) throws NoSuchFieldException {
        if (clazz.getSuperclass() == null) {
            return;
        } else {
            getAllHeadRelect(clazz.getSuperclass());
            getHeadRelect(clazz);
        }

    }

    /**
     * 给定类时，解析excel表头与类属性的对应关系
     * @param beanClass
     * @param <T>
     * @throws NoSuchFieldException
     */
    private <T> void getHeadRelect(Class<T> beanClass) throws NoSuchFieldException {
        String rels = beanClass.getAnnotation(HeadAlias.class).rels();
        //解析表头与属性映射关系
        String[] relsArr = rels.split(":");
        if (relsArr.length > 1) {
            String fieds = relsArr[0];
            String heads = relsArr[1];
            String[] prosArr = fieds.split(",");
            String[] headsArr = heads.split(",");
            if (prosArr.length == headsArr.length) { //属性与表头映射关系匹配正确，使用指定的映射关系。如映射不正确，则
                for (int i = 0; i < headsArr.length; i++) {
                    String fieldStr = prosArr[i];
                    Field field = beanClass.getDeclaredField(fieldStr);
                    field.setAccessible(true);//将属性设置为可访问。避免私有属性不可访问，赋值失败
                    this.addHeaderAlias(headsArr[i], fieldStr);
                }
            } else {
                throw new IndexOutOfBoundsException("bean property with excel head length relect is Illegal ");
            }
        }


    }

    /**
     * 读取为文本格式<br>
     * 使用{@link ExcelExtractor} 提取Excel内容
     *
     * @param withSheetName 是否附带sheet名
     * @return Excel文本
     * @since 1.0-jdk1.6
     */
    public String readAsText(boolean withSheetName) {
        final ExcelExtractor extractor = getExtractor();
        extractor.setIncludeSheetNames(withSheetName);
        return extractor.getText();
    }

    /**
     * 获取 {@link ExcelExtractor} 对象
     *
     * @return {@link ExcelExtractor}
     * @since 1.0-jdk1.6
     */
    public ExcelExtractor getExtractor() {
        ExcelExtractor extractor;
        Workbook wb = this.workbook;
        if (wb instanceof HSSFWorkbook) {
            extractor = new org.apache.poi.hssf.extractor.ExcelExtractor((HSSFWorkbook) wb);
        } else {
            extractor = new XSSFExcelExtractor((XSSFWorkbook) wb);
        }
        return extractor;
    }

    /**
     * 读取某一行数据
     *
     * @param rowIndex 行号，从0开始
     * @return 一行数据
     * @since 1.0-jdk1.6
     */
    public List<Object> readRow(int rowIndex) {
        return readRow(this.sheet.getRow(rowIndex));
    }

    /**
     * 读取某个单元格的值
     *
     * @param x X坐标，从0计数，即列号
     * @param y Y坐标，从0计数，即行号
     * @return 值，如果单元格无值返回null
     * @since 1.0-jdk1.6
     */
    public Object readCellValue(int x, int y) {
        return CellUtil.getCellValue(getCell(x, y), this.cellEditor);
    }

    /**
     * 获取Excel写出器<br>
     * 在读取Excel并做一定编辑后，获取写出器写出
     *
     * @return {@link ExcelWriter}
     * @since 1.0-jdk1.6
     */
    public ExcelWriter getWriter() {
        return new ExcelWriter(this.sheet);
    }


    /**
     * 读取一行
     *
     * @param row 行
     * @return 单元格值列表
     */
    private List<Object> readRow(Row row) {
        return RowUtil.readRow(row, this.cellEditor);
    }

    /**
     * 转换标题别名，如果没有别名则使用原标题，当标题为空时，列号对应的字母便是header
     *
     * @param headerList 原标题列表
     * @return 转换别名列表
     */
    private List<String> aliasHeader(List<Object> headerList) {
        final int size = headerList.size();
        final ArrayList<String> result = new ArrayList(size);
        if (CollectionUtils.isEmpty(headerList)) {
            return result;
        }

        for (int i = 0; i < size; i++) {
            result.add(aliasHeader(headerList.get(i), i));
        }
        return result;
    }

    /**
     * 转换标题别名，如果没有别名则使用原标题，当标题为空时，列号对应的字母便是header
     *
     * @param headerObj 原标题
     * @param index     标题所在列号，当标题为空时，列号对应的字母便是header
     * @return 转换别名列表
     * @since 4.3.2
     */
    private String aliasHeader(Object headerObj, int index) {
        if (null == headerObj) {
            return ExcelUtil.indexToColName(index);
        }

        final String header = headerObj.toString();
        return this.headerAlias.get(header);
    }

    /**
     * 检查是否未关闭状态
     */
    private void checkNotClosed() {
        Assert.assertFalse("ExcelReader has been closed!", this.isClosed);
    }
}
