package com.sdx.utils;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
/**
 * 直接操纵SQL类
 * @author QI
 *
 */
@Resource
public class JdbcTemplateHandler {
	
	 private DataSource dataSource;
	
	 public void setDataSource(DataSource dataSource) {
		        this.dataSource = dataSource;
	 }
	 
	 public  JdbcTemplate getJdbcTemplate(){
		 return  new JdbcTemplate(dataSource);
	 }
	 
}