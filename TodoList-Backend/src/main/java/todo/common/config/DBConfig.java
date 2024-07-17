package todo.common.config;

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
/*
@PropertySource : 깃허브에 올리지 않고, 이메일 이름, 비밀번호 처럼 암호화해서 사용해야하는 설정을 가져옴
	Property : 개발자가 사용자에게 인증번호를 보낼 이메일, 이메일 비밀번호
				또는 DB 아이디, 비밀번호, 주소처럼 회사에서 비공개적으로 보호해야하는 자산을 작성하는 공간	
*/
public class DBConfig {

	@Autowired
	private ApplicationContext applicationContext;
	//현재 만든 TodoList-Backend라는 폴더 흐름
	//TodoList-Backend라는 폴더 = Application = 나중에 폴더 안에 작성한 파일이 하나의 어플이나 웹에서 작동하는 파일이 됨
	//추후 자바나 JS코드로 작성한 파일을 exe와 같은 확장자로 만들어 소비자들이 다운, 실행이 가능한 프로그램으로 만들 수 있음
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		//hikari = DB를 연결하기 위해 사용하는 기능
		//hikari와 같은 외부기능을 쓰지 않으면 코드가 많이 늘어남
		return new HikariConfig();
	}
	
	//연결된 DB를 스프링에서 인지하고 관리할 것을 표기
	@Bean
	public DataSource dataSource(HikariConfig config) {
		DataSource dataSource = new HikariDataSource(config);
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		
		sessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/**.xml"));
		sessionFactoryBean.setTypeAliasesPackage("todo.dto");
		sessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
	
		return sessionFactoryBean.getObject();
	}
	
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sessionFactory) {
		return new SqlSessionTemplate(sessionFactory);
	}
	
	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
}
