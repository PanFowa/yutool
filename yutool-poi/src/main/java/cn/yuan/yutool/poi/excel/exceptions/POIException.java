package cn.yuan.yutool.poi.excel.exceptions;

/**
 * POI异常
 * @author PanFowa
 */
public class POIException extends RuntimeException {
	private static final long serialVersionUID = 2711633732613506552L;

	public POIException(Throwable e) {
		super(e.getMessage(), e);
	}
	
	public POIException(String message) {
		super(message);
	}
	

	
	public POIException(String message, Throwable throwable) {
		super(message, throwable);
	}
	

}
