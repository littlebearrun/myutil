package com.likang.myutil.util.excel.export;

import java.util.Arrays;
import java.util.List;


public enum ExportType {

	TEST(1,
		Arrays.asList(40,40,40),
		Arrays.asList(
			new ExportCell("姓名", "name"),
			new ExportCell("描述", "description"),
			new ExportCell("时间", "createTime")
		)
	)
    		 
     ;
	
	
	
	ExportType(int type, List<Integer> width, List<ExportCell> dataTitle){
		this.type = type;
		this.width = width;
		this.dataTitle = dataTitle;
	}

    ExportType(int type, List<Integer> width, List<ExportCell> dataTitle,List<Integer> totalWidth, List<ExportCell> totalTitle){
        this.type = type;
        this.width = width;
        this.dataTitle = dataTitle;
        this.totalTitle = totalTitle;
        this.totalWidth = totalWidth;
    }
	
	private int type;
	//宽度
	private List<Integer> width;
	//数据表头
	private List<ExportCell> dataTitle;
    //统计数据表头宽度
    private List<Integer> totalWidth;
	//统计数据表头
    private List<ExportCell> totalTitle;
	
	public List<ExportCell> getDataTitle() {
		return dataTitle;
	}
	public void setDataTitle(List<ExportCell> dataTitle) {
		this.dataTitle = dataTitle;
	}
	public List<Integer> getWidth() {
		return width;
	}
	public void setWidth(List<Integer> width) {
		this.width = width;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public static ExportType getExprotType(int type){
		ExportType enums [] = ExportType.values();
		for(ExportType e : enums){
			if(e.getType() == type){
				return e;
			}
		}
		return null;
	}


    public List<ExportCell> getTotalTitle() {
        return totalTitle;
    }

    public void setTotalTitle(List<ExportCell> totalTitle) {
        this.totalTitle = totalTitle;
    }

    public List<Integer> getTotalWidth() {
        return totalWidth;
    }

    public void setTotalWidth(List<Integer> totalWidth) {
        this.totalWidth = totalWidth;
    }
}
