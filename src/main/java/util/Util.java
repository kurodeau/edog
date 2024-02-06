package util;

public class Util {
	
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	
	// MySQL 8.0.13�H��u�ݫO�dserverTimezone�]�w�Y�i
	public static final String URL = 
			"jdbc:mysql://localhost:3306/edog?"
//			+ "useSSL=false&"                   
//			+ "rewriteBatchedStatements=true"
			+ "serverTimezone=Asia/Taipei";     
//			+ "allowPublicKeyRetrieval=true&"   
//			+ "useUnicode=true&"                
//			+ "characterEncoding=utf-8";      
	
	public static final String USER = "root";
	
	public static final String PASSWORD = "123456";
	
	 // DATASOURCE
	public static final String  DS_NAME= "java:comp/env/jdbc/TestDB2";

	
	 // SELLER CONSTANTS
	
	public static final Integer SELLERLV_LIMIT = 5;
}
