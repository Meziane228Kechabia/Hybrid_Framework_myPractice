
package com.tutorialsNinja.qa.Utilities;

import java.util.Date;

public class Utils {
	
public static String emailWithDateTimeStamp() {
	Date date = new Date();
	String emailTimeStamp = date.toString().replace(" ", "_").replace(":", "_");
	return  "meziane" + emailTimeStamp + "@gmail.com";
	}
public static final int Implicit_Wait = 10;
public static final int Page_Load_Timeout = 10;
public static final int Script_Timeout = 200;
}		
	
	

