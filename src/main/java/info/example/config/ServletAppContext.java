package info.example.config;

import info.example.interceptor.TopMenuInterceptor;
import info.example.mapper.ClassInfoMapper;
import info.example.mapper.ClassMenuMapper;
import info.example.mapper.TopMenuMapper;
import info.example.service.TopMenuService;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
@ComponentScan("info.example.controller")
@ComponentScan("info.example.dao")
@ComponentScan("info.example.service")
@PropertySource("/WEB-INF/properties/db.properties")
public class ServletAppContext implements WebMvcConfigurer {

    @Value("${database.classname}")
    private String db_classname;

    @Value("${database.url}")
    private String db_url;

    @Value("${database.username")
    private String db_username;

    @Value("${database.password}")
    private String db_password;

    @Autowired
    private TopMenuService topMenuService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        TopMenuInterceptor topMenuInterceptor = new TopMenuInterceptor(topMenuService);
        InterceptorRegistration reg1 = registry.addInterceptor(topMenuInterceptor);
        reg1.addPathPatterns("/**");

    }

    @Bean
    public BasicDataSource dataSource(){
        BasicDataSource source = new BasicDataSource();
        source.setDriverClassName(db_classname);
        source.setUrl(db_url);
        source.setUsername(db_username);
        source.setPassword(db_password);

        return source;
    }

    @Bean
    public SqlSessionFactory factory(DataSource source) throws Exception{
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(source);
        SqlSessionFactory factory = factoryBean.getObject();
        return factory;
    }


    @Bean
    public MapperFactoryBean<ClassInfoMapper> getBoardMapper(SqlSessionFactory factory) throws Exception {
        MapperFactoryBean<ClassInfoMapper> factoryBean =
                new MapperFactoryBean<ClassInfoMapper>(ClassInfoMapper.class);
        factoryBean.setSqlSessionFactory(factory);
        return factoryBean;
    }

    @Bean
    public MapperFactoryBean<TopMenuMapper> getTopMenuMapper(SqlSessionFactory factory) throws Exception {
        MapperFactoryBean<TopMenuMapper> factoryBean =
                new MapperFactoryBean<TopMenuMapper>(TopMenuMapper.class);
        factoryBean.setSqlSessionFactory(factory);
        return factoryBean;
    }

    @Bean
    public MapperFactoryBean<ClassMenuMapper> getClassMenuMapper(SqlSessionFactory factory) throws Exception {
        MapperFactoryBean<ClassMenuMapper> factoryBean =
                new MapperFactoryBean<ClassMenuMapper>(ClassMenuMapper.class);
        factoryBean.setSqlSessionFactory(factory);
        return factoryBean;
    }

}
