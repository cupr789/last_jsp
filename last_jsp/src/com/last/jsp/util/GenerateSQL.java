package com.last.jsp.util;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.last.jsp.common.MybatisSqlSessionFactory;

public class GenerateSQL {
	public static Logger log = Logger.getLogger(GenerateSQL.class);
	public static void main(String[] args) {
		
		try(SqlSession ss = MybatisSqlSessionFactory.getSS()){
			String tableName = "user_info";
			List<Map<String, String>> colList= ss.selectList("common.selectTable",tableName);
			String selectSql = "";
			String updateSql = "";
			String deleteSql = "";
			String insertSql = "";
			String updateColum = "";
			for(Map<String, String> m : colList) {
				selectSql += m.get("colName")+",";
				updateColum+=m.get("colName")+"=? ";
			}
			selectSql = "select "+ selectSql.substring(0,selectSql.length()-1)+" from "+tableName;
			updateSql = "update "+tableName+" set "+updateColum.substring(updateColum.indexOf("?")+2,updateColum.length()-1)+" where uino=1";
			deleteSql = "delete from "+tableName+" where uino=1";
			insertSql = "insert into "+tableName+"("+selectSql.substring(selectSql.indexOf(",")+1,selectSql.indexOf("from")-1)+") values(?,?,?,?,?,?,?)";
			log.debug(selectSql);
			log.debug(updateSql);
			log.debug(deleteSql);
			log.debug(insertSql);
		}
	}
	
}
