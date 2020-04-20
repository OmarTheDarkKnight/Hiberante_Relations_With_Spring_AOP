package com.bat.alfred;

import org.springframework.stereotype.Component;

@Component
public class Helper {
	/*
	 * @param String folderName
	 * @param String viewName
	 * @return String folderName/viewName
	 * */
	public String buildViewName(String folderName, String viewName) {
		return folderName + "/" +viewName;
	}
	
	/* 
	 * @param String fields
	 * @param Stirng operator
	 * @param Stirng value
	 * @return String
	 * Builds query using fields name, value and operator
	 * OR format and return it
	 * example : field1 operator value OR field2 operator value
	 * */ 
	private String buildWhereQueryWithOperator(String[] fields, String operator, String value) {
		String query = "";
		int length = fields.length - 1;
		int i = 0;
		for(String field: fields) {
			query += field + " " + operator + " " + value;
			
			// If there are multiple fields then adds OR at the end of the query
			if(i != length) {
		        query += " OR ";
		    }
			i++;
		}
		return query;
	}
	
	/* 
	 * @param String fields
	 * @param Stirng regex
	 * @return String
	 * Builds query using fields name and value
	 * by calling the buildWhereQueryWithOperator method
	 * "=" is sent as operator to the method
	 * example : (field1 = regex OR field2 = regex)
	 * */ 
	public String where(String[] fields, String value) {
		return "(" + this.buildWhereQueryWithOperator(fields, "=", value) + ")";
	}
	
	/* 
	 * @param String fields
	 * @param Stirng operator
	 * @param Stirng value
	 * @return String
	 * Builds query using fields name, operator, value
	 * by calling the buildWhereQueryWithOperator method
	 * example : (field1 operator value OR field2 operator value)
	 * */ 
	public String where(String[] fields, String operator, String value) {
		return "(" + this.buildWhereQueryWithOperator(fields, operator, value) + ")";
	}
	
	/* 
	 * @param String fields
	 * @param Stirng regex
	 * @return String
	 * Builds query using fields name and value
	 * by calling the buildWhereQueryWithOperator method
	 * "LIKE" is sent as operator to the method
	 * example : (field1 LIKE regex OR field2 LIKE regex)
	 * */ 
	public String whereLike(String[] fields, String regex) {
		return "(" + this.buildWhereQueryWithOperator(fields, "LIKE", regex) + ")";
	}
}
