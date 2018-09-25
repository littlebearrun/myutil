package com.likang.myutil.util.excel;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.likang.myutil.bean.Product;
import com.likang.myutil.util.excel.export.ExportData;
import com.likang.myutil.util.excel.export.ExportExcel;
import com.likang.myutil.util.excel.export.ExportType;



public class ExportExcelUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ExportExcelUtil.class);

	
	
	/**
	 * 
	 * @param list   导出数据对象数组
	 * @param downloadType
	 * @param outFileName
	 * @param outPath
	 * @throws Exception
	 */
	public static void export(List<?> list, int downloadType, String outFileName, String outPath) throws Exception{
        
        String outPathFile = outPath + outFileName;
		try {
			File file = new File(outPath);
			if(!file.exists()){//文件夹不存在,创建
				file.mkdirs();
			}
			ExportExcel excel = new ExportExcel();
			ExportData exportData = new ExportData(outFileName, ExportType.getExprotType(downloadType), list);
			excel.exportExcel(exportData, 0, true, outPathFile, "sheetName");

		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	
	
	
	
	public static void main(String[] args) throws Exception{
		List<Product> list = new ArrayList<>();
		Product p = new Product("手机", "智能手机", new Date());
		list.add(p);
		
		export(list, 1, "product", "d://");
	}
}
