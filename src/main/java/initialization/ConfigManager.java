package initialization;

import java.io.FileInputStream;
import java.util.Properties;

import utilities.ExcelUtil;

public class ConfigManager {
	private static Properties properties;
	private static final String CONFIG_FILE = "src\\main\\resources\\config.properties";
	
	private static final String EXCEL_FILE_PATH = "C:\\Users\\kruthivk\\Desktop\\ATLAS 25\\configData.xlsx";   
    private static final String EXCEL_SHEET = "Sheet1";
    
    private static Object[][] excelData;
	
	static {
		properties = new Properties();
		try {
			FileInputStream input = new FileInputStream(CONFIG_FILE);
			properties.load(input);
			input.close();
			excelData = ExcelUtil.loadExcelData(EXCEL_FILE_PATH, EXCEL_SHEET);
		} catch(Exception e) {
			throw new RuntimeException("Failed to read config properties file");
		}
	}
	
	

	public static String getProperty(String property) {
		return properties.getProperty(property);
	}
	
	public static String getBaseUrl() {
		if (excelData.length > 0 && excelData[0].length > 1) {
            return excelData[0][1].toString();
        }
        return null;
	}
	
	public static String getBrowser(int i, int j) {
		if (excelData.length > 0 && excelData[0].length > 0) {
            return excelData[i][j].toString();
        }
        return null;
	}
	
	public static String getValidUsername() {
		if (excelData.length > 0 && excelData[0].length > 2) {
			return excelData[0][2].toString();
		}
		return null;
	}
	
	public static String getValidPassword() {
        if (excelData.length > 0 && excelData[0].length > 3) {
            return excelData[0][3].toString();
        }
        return null;
    }
	
	public static int getImplicitWait() {
		return Integer.parseInt(properties.getProperty("implicitwait"));
	}
	
	public static int getExplicitWait() {
		return Integer.parseInt(properties.getProperty("explicitwait"));
	}
	
	public static String getTimeout() {
		return properties.getProperty("timeout");
	}
	
	public static String getTimeUnit() {
		return properties.getProperty("timeunit");
	}
}
