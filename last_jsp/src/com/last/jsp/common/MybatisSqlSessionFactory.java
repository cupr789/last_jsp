package com.last.jsp.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.sound.midi.Synthesizer;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.log4j2.Log4j2Impl;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

public class MybatisSqlSessionFactory {
	private static SqlSessionFactory ssf; 
	private static Logger log = null;
	static {
		try(InputStream is = Resources.getResourceAsStream("conf/mybatis-config.xml")){
			ssf= new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSession getSS() {
		return ssf.openSession();
	}
	public static SqlSession getSS(boolean commit) {
		return ssf.openSession(commit);
	}
	
}
