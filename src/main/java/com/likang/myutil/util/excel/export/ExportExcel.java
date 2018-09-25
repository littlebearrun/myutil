package com.likang.myutil.util.excel.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;


/**
 *
 *@Author likang  2017年8月23日 下午3:36:39
 *
 */
public class ExportExcel {


	private static Logger logger = LoggerFactory.getLogger(ExportExcel.class);

    private ExportData exportData;

	// Workbook对象
	private SXSSFWorkbook sxssfWorkbook;

	// Sheet对象
	private Sheet sheet;

	// 生成的EXCEL文件对象
	private File excelFile;

	public void exportExcel(ExportData exportData, Integer index, boolean isEnd, String excelPath, String sheetName) {
		try {
			this.exportData = exportData;
			excelFile = new File(excelPath);
			if (!excelFile.getParentFile().exists()) {
				excelFile.getParentFile().mkdirs();
			}
			if (!excelFile.exists()) {
				excelFile.createNewFile();
			}
			if (index == 0) {
				// 创建EXCEL
				sxssfWorkbook = new SXSSFWorkbook(100);
			}
			// 创建Excel
			initExcel(sheetName);
			logger.info("=============== 1/3 初始化Excel ========:{}", exportData.getFileName());
			int rowNum = 0;
			//统计相关-总金额、总笔数等
            logger.debug("类型》》》》》》》"+ JSONObject.toJSONString(exportData));
			if(null!=exportData.getTotalTitle()&&null!=exportData.getTotalData()){
			    logger.info("添加统计表头");
                // 添加表头
                addTitle(exportData.getTotalTitle(),rowNum);
                logger.info("=============== 2/3 初始化表头 ========:{}", exportData.getFileName());
                // 添加数据
                addData(exportData.getTotalTitle(), exportData.getTotalData(),rowNum);
                rowNum = rowNum+exportData.getTotalData().size()+1;
            }

			// 添加表头
			addTitle(exportData.getDataTitle(),rowNum);
			logger.info("=============== 2/3 初始化表头 ========:{}", exportData.getFileName());
			// 添加数据
			addData(exportData.getDataTitle(), exportData.getData(),rowNum);
			logger.info("=============== 3/3 添加数据表头 ========:{}", exportData.getFileName());
			if (isEnd) {
				// 写入数据
				writeData();
			}
		} catch (Exception e) {
			logger.error("EXCEL数据写入异常", e);
		}

	}

	// 写入数据
	private void writeData() throws Exception {
		OutputStream os = new FileOutputStream(excelFile);
		sxssfWorkbook.write(os);
		sxssfWorkbook.dispose();
		os.close();
	}

	// 初始化EXCEL
	private void initExcel(String sheetName) throws Exception {

		// 创建Sheet
		sheet = sxssfWorkbook.createSheet(sheetName);
		// 设置宽度
		List<Integer> width = exportData.getWidth();
		for (int i = 0; i < width.size(); i++) {
			sheet.setColumnWidth(i, 256 * width.get(i) + 184);
		}
	}

	// 设置标题
	private void addTitle(List<ExportCell> dataTitle,int row) throws Exception {
		// 添加dataTitle
		Row dataHeadRow = sheet.createRow(row);
		for (int i = 0; i < dataTitle.size(); i++) {
			ExportCell title = dataTitle.get(i);
			Cell cell = dataHeadRow.createCell(i);
			addCell(cell, title.getTitle());
		}
	}

	// 设置cell值
	private void addCell(Cell cell, String val) throws Exception {
		cell.setCellValue(val);
	}

	// 设置cell值
	private void addCell(Cell cell, Object obj, ExportCell exportCell) throws Exception {
		String field = exportCell.getFieldName();
		Object val = PropertyUtils.getProperty(obj, field);
		cell.setCellValue(val + "");
	}

	private void addData(List<ExportCell> dataTitle, List<?> dataVal,int rowNum) throws Exception {
		if (CollectionUtils.isEmpty(dataTitle) || CollectionUtils.isEmpty(dataVal)) {
			return;
		}
		// 添加dataVal
		for (int i = 0; i < dataVal.size(); i++) {
			Row row = sheet.createRow(rowNum +i + 1);
			Object object = dataVal.get(i);
			for (int j = 0; j < dataTitle.size(); j++) {
				Cell cell = row.createCell(j);
				ExportCell title = dataTitle.get(j);
				addCell(cell, object, title);
			}
		}
	}

}
