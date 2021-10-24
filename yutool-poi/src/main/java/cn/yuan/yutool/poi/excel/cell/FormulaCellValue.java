package cn.yuan.yutool.poi.excel.cell;

/**
 * 公式类型的值
 * 
 * @author PanFowa
 * @since 1.0-jdk1.6
 */
public class FormulaCellValue implements CellValue<String> {

	/** 公式 */
	String formula;

	public FormulaCellValue(String formula) {
		this.formula = formula;
	}

	@Override
	public String getValue() {
		return this.formula;
	}

}
