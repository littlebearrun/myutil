package com.likang.myutil.util.excel.export;

public class ExportCell {
	//标题
	private String title;
	//属性
	private String fieldName;
	
	public ExportCell() {
		super();
	}
	public ExportCell(String title, String fieldName ) {
		super();
		this.title = title;
		this.fieldName = fieldName;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}
