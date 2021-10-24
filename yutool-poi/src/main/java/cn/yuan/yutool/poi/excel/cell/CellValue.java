package cn.yuan.yutool.poi.excel.cell;

/**
 * 抽象的单元格值接口，用于判断不同类型的单元格值
 * 
 * @param <T> 值得类型
 * @author PanFowa
 * @since 1.0-jdk1.6
 */
public interface CellValue<T> {
	/**
	 * 获取单元格值
	 * 
	 * @return 值
	 */
	T getValue();
}
