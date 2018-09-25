package com.likang.myutil.util.excel.export;

import java.util.List;

public class ExportData {
	//文件名
	private String fileName;
	//数据标题
	private List<ExportCell> dataTitle;
	//数据
	private List<?> data;
	//表格宽度
	private List<Integer> width;

    //数据标题
    private List<ExportCell> totalTitle;
    //数据
    private List<?> totalData;
    //表格宽度
    private List<Integer> totalWidth;
	
	public ExportData(String fileName , ExportType type , List<?> data){
		this.fileName = fileName;
		this.dataTitle = type.getDataTitle();
		this.width = type.getWidth();
		this.data = data;
	}

    public ExportData(String fileName , ExportType type , List<?> data, List<?> totalData){
        this.fileName = fileName;
        this.dataTitle = type.getDataTitle();
        this.width = type.getWidth();
        this.data = data;
        this.totalTitle = type.getTotalTitle();
        this.totalData = totalData;
        this.totalWidth = type.getTotalWidth();
    }
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<ExportCell> getDataTitle() {
		return dataTitle;
	}

	public void setDataTitle(List<ExportCell> dataTitle) {
		this.dataTitle = dataTitle;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

	public List<Integer> getWidth() {
		return width;
	}

	public void setWidth(List<Integer> width) {
		this.width = width;
	}

    public List<ExportCell> getTotalTitle() {
        return totalTitle;
    }

    public void setTotalTitle(List<ExportCell> totalTitle) {
        this.totalTitle = totalTitle;
    }

    public List<?> getTotalData() {
        return totalData;
    }

    public void setTotalData(List<?> totalData) {
        this.totalData = totalData;
    }

    public List<Integer> getTotalWidth() {
        return totalWidth;
    }

    public void setTotalWidth(List<Integer> totalWidth) {
        this.totalWidth = totalWidth;
    }
}
