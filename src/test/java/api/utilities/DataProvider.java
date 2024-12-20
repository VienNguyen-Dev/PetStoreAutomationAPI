package api.utilities;

import java.io.IOException;
import java.util.Iterator;

public class DataProvider {

	@org.testng.annotations.DataProvider(name = "Data")
	public String[][] getAllData() throws IOException {

		
		String path = System.getProperty("user.dir") + "\\testData\\UserData.xlsx";
		
		XLUtility xlUtility = new XLUtility(path);
		int rows = xlUtility.getCountRows("Sheet1");
		int cols = xlUtility.getCountCols("Sheet1", 1);
		String[][] data = new String[rows][cols]; 
		for (int i = 1; i <= rows; i++) {
			for (int j = 0; j < cols; j++) {
				data[i-1][j] = xlUtility.getCellData("Sheet1", i, j); 
			}
		}
		return data;
	}
	
	@org.testng.annotations.DataProvider(name = "UserNames")
	public String[] getUsername() throws IOException {
String path = System.getProperty("user.dir") + "\\testData\\UserData.xlsx";

		XLUtility xl = new XLUtility(path);
		int rows = xl.getCountRows("Sheet1");
		
		String[] username = new String[rows];
		
		for (int i = 1; i <= rows; i++) {
			
			username[i-1] = xl.getCellData("Sheet1",i, 1);
			
		}
		return username;
}
}
