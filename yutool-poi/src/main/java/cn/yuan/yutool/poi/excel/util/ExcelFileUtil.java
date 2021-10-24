package cn.yuan.yutool.poi.excel.util;

import org.apache.poi.poifs.filesystem.FileMagic;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

/**
 * Excel文件工具类
 * 
 * @author PanFowa
 * @since 1.0-jdk1.6
 */
public class ExcelFileUtil {
	/**
	 * 是否为XLS格式的Excel文件（HSSF）<br>
	 * XLS文件主要用于Excel 97~2003创建
	 * 
	 * @param in excel输入流
	 * @return 是否为XLS格式的Excel文件（HSSF）
	 */
	public static boolean isXls(InputStream in) {
		final PushbackInputStream pin = (in instanceof PushbackInputStream) ? (PushbackInputStream) in : new PushbackInputStream(in, 8);
		try {
			return FileMagic.valueOf(pin) == FileMagic.OLE2;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 是否为XLSX格式的Excel文件（XSSF）<br>
	 * XLSX文件主要用于Excel 2007+创建
	 * 
	 * @param in excel输入流
	 * @return 是否为XLSX格式的Excel文件（XSSF）
	 */
	public static boolean isXlsx(InputStream in) {
		if (false == in.markSupported()) {
			in = new BufferedInputStream(in);
		}
		try {
			return FileMagic.valueOf(in) == FileMagic.OOXML;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
