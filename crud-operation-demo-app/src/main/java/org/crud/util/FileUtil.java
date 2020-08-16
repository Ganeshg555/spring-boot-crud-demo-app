package org.crud.util;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class FileUtil {

	@Autowired
	private ResourceLoader resourceLoader;
	
	@Value("${users.file}")
	private String filePath;
	
	public List<User> loadUsersData() {
		// TODO Auto-generated method stub
		List<User> users = new LinkedList<User>();
		Resource resource = resourceLoader.getResource(filePath);
		try(InputStream inputStream = resource.getInputStream();
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);) {
		 int noOfSheets = workbook.getNumberOfSheets();
		 XSSFSheet worksheet = null;
		 XSSFRow row = null;
		 User user = null;
		 for(int sheetNumber = 0; sheetNumber<noOfSheets; sheetNumber++) {
			 worksheet = workbook.getSheetAt(sheetNumber);
			 for(int rowNumber = 0; rowNumber<worksheet.getPhysicalNumberOfRows(); rowNumber++) {
				 row = worksheet.getRow(rowNumber);
				 user = new User();
				 user.setUsername(row.getCell(0).getStringCellValue());
				 user.setPassword(row.getCell(1).getStringCellValue());
				 user.setSource("Excel");
				 users.add(user);
			 }
		 }
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return users;
	}

}
