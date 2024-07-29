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
@PropertySource("classpath:/config.properties") // = src/main/resources
public class DBConfig {

	@Autowired
	private ApplicationContext applicationContext; //연결되는 주소 관리자. xml과 같은 경로를 보유하고 관리
	
	//객체 생성. 히카리 사용을 선언
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		/*
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://localhost:3306/KH_WORKBOOK");
		config.setUsername("root");
		config.setPassword("kh1234");
		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
		*/
		return new HikariConfig(); //hikari : DataBase 연결을 도와주는 라이브러리
	}
	
	@Bean
	public DataSource dataSource(HikariConfig config) {
		DataSource dataSource = new HikariDataSource(config);
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
		sfb.setDataSource(dataSource);
		sfb.setMapperLocations(applicationContext.getResources("classpath:/mappers/**.xml"));
		sfb.setTypeAliasesPackage("com.kh.dto"); // 추후 본인의 dto 패키지 명으로 변경. database에 작성한 컬럼명과 dto에 작성한 변수명 대조
		sfb.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml")); // 추후 컬럼명을 카멜케이스 형식으로 들여옴을 설정
		return sfb.getObject();
	}
	
	//sql에 작성한 CRUD 작업을 관리
	@Bean 
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sf) {
		return new SqlSessionTemplate(sf);
	}
	
	//커밋, 롤백을 통해 db에 완전히 저장하거나 되돌릴 수 있도록 도와줌
	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
		//insert, delete, update는 commit을 하지 않으면 완벽히 저장되지 않은 상태에서 select를 진행
	}
}
