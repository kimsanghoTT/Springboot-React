package com.kh.common.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/config.properties")
public class DBConfig {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	
	@Bean 
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig(); 
	}
	
	
	@Bean 
	public DataSource dataSource(HikariConfig config) {
	    return new HikariDataSource(config);
	}
	
	
	@Bean
	public SqlSessionFactory sessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
		sfb.setDataSource(dataSource); //HikariConfig에서 받은 정보로 연결한 DataBase 연결 경로를 가져와서 사용
		sfb.setMapperLocations(applicationContext.getResources("classpath:/mappers/**.xml"));
		sfb.setTypeAliasesPackage("com.kh.dto"); 
		sfb.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
		return sfb.getObject();
	}
	
	
	@Bean 
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sf) {
		return new SqlSessionTemplate(sf);
	}
	
	@Bean 
	public DataSourceTransactionManager dataSourceTransactionManager(DataSource ds) {
		return new DataSourceTransactionManager(ds);
	}
}