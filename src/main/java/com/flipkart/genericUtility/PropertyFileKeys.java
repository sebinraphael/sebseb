package com.flipkart.genericUtility;

/**
 * 
 * @author daniel
 *
 */

public enum PropertyFileKeys {
	BROWSER("browser"), URL("url");

private String keys;

//setters
PropertyFileKeys(String keys) 
{
this.keys=keys;	
}

//getter
public String convertToString()
{
	return keys.toString();
}
}
