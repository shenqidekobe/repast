package repast.yiyou.common.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据列表
 * */
public class DataGrid<T> implements Serializable {
	
	private static final long serialVersionUID = 5822312643642086443L;
	
	private Integer pageCount = 0;/**总页数*/
	private Long rowCount = 0L;/** 总记录数 */
	private List<T> records = new ArrayList<T>();
	
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Long getRowCount() {
		return rowCount;
	}
	public void setRowCount(Long rowCount) {
		this.rowCount = rowCount;
	}
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}
	
}
