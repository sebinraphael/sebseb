package com.flipkart.genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author daniel
 *
 */

public class PropertiesUtility {

	FileInputStream fis;
	
	/** 
	 * @param propertFilePath
	 * @param key
	 * @return
	 * @throws IOException
	 */
	
	public String getDataFromPropertyFile(String propertFilePath, String key) throws IOException 
	{

			fis = new FileInputStream(propertFilePath);
	
		Properties property = new Properties();
		
			property.load(fis);
		
		String value = property.getProperty(key);
		return value;
	}
	
	
	
	/**
	 * This method is used to close the file
	 * @throws IOException
	 */
	public void closePropertyFile() throws IOException
	{
	
			fis.close();

	
	}
}
