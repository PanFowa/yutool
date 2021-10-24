package cn.yuan.yutool.poi.excel.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel图片工具类
 * 
 * @author PanFowa
 * @since 1.0-jdk1.6
 */
public class ExcelPicUtil {
	/**
	 * 获取工作簿指定sheet中图片列表
	 * 
	 * @param workbook 工作簿{@link Workbook}
	 * @param sheetIndex sheet的索引
	 * @return 图片映射，键格式：行_列，值：{@link PictureData}
	 */
	public static Map<String, PictureData> getPicMap(Workbook workbook, int sheetIndex) {
		if (sheetIndex < 0) {
			sheetIndex = 0;
		}

		if (workbook instanceof HSSFWorkbook) {
			return getPicMapXls((HSSFWorkbook) workbook, sheetIndex);
		} else if (workbook instanceof XSSFWorkbook) {
			return getPicMapXlsx((XSSFWorkbook) workbook, sheetIndex);
		} else {
			throw new IllegalArgumentException("Workbook type "+workbook.getClass()+" is not supported!");
		}
	}

	/**
	 * 获取XLS工作簿指定sheet中图片列表
	 * 
	 * @param workbook 工作簿{@link Workbook}
	 * @param sheetIndex sheet的索引
	 * @return 图片映射，键格式：行_列，值：{@link PictureData}
	 */
	private static Map<String, PictureData> getPicMapXls(HSSFWorkbook workbook, int sheetIndex) {
		final Map<String, PictureData> picMap = new HashMap();
		final List<HSSFPictureData> pictures = workbook.getAllPictures();
		if (CollectionUtils.isNotEmpty(pictures)) {
			final HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
			HSSFClientAnchor anchor;
			int pictureIndex;
			for (HSSFShape shape : sheet.getDrawingPatriarch().getChildren()) {
				if (shape instanceof HSSFPicture) {
					pictureIndex = ((HSSFPicture) shape).getPictureIndex() - 1;
					anchor = (HSSFClientAnchor) shape.getAnchor();
					picMap.put(anchor.getRow1()+"_"+ anchor.getCol1(), pictures.get(pictureIndex));
				}
			}
		}
		return picMap;
	}

	/**
	 * 获取XLSX工作簿指定sheet中图片列表
	 * 
	 * @param workbook 工作簿{@link Workbook}
	 * @param sheetIndex sheet的索引
	 * @return 图片映射，键格式：行_列，值：{@link PictureData}
	 */
	private static Map<String, PictureData> getPicMapXlsx(XSSFWorkbook workbook, int sheetIndex) {
		final Map<String, PictureData> sheetIndexPicMap = new HashMap<String, PictureData>();
		final XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
		XSSFDrawing drawing;
		for (POIXMLDocumentPart dr : sheet.getRelations()) {
			if (dr instanceof XSSFDrawing) {
				drawing = (XSSFDrawing) dr;
				final List<XSSFShape> shapes = drawing.getShapes();
				XSSFPicture pic;
				CTMarker ctMarker;
				for (XSSFShape shape : shapes) {
					pic = (XSSFPicture) shape;
					ctMarker = pic.getPreferredSize().getFrom();
					sheetIndexPicMap.put(ctMarker.getRow()+"_"+ctMarker.getCol(), pic.getPictureData());
				}
			}
		}
		return sheetIndexPicMap;
	}
}
