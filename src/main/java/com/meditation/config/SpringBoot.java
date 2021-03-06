package com.meditation.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.cfg.ImprovedNamingStrategy;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * Spring服务层
 * 实际就是spring.xml，之前注册Bean的地方在这里写即可
 *
 * @author zhangyuting
 */
@Configuration
@ComponentScan("com.meditation.*")
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class SpringBoot {

    /**
     * DataSource
     *
     * @return DataSource
     */
    @Bean("dataSource")
    public DataSource dataSource() {
        return new CusDataSource();
    }

    @Bean("sessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setPackagesToScan("com.meditation.*");
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        localSessionFactoryBean.setHibernateProperties(hibernateProperties);
        ImprovedNamingStrategy improvedNamingStrategy = new ImprovedNamingStrategy();
        localSessionFactoryBean.setNamingStrategy(improvedNamingStrategy);
        return localSessionFactoryBean;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory() {
        CusSqlSessionFactory cnkiSqlSessionFactory = new CusSqlSessionFactory();
        cnkiSqlSessionFactory.setDataSource(dataSource());
        return cnkiSqlSessionFactory;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.meditation.*");
        final Properties mybatisProperties = new Properties();
        mybatisProperties.setProperty("mappers", "tk.mybatis.mapper.common.Mapper");
        mapperScannerConfigurer.setProperties(mybatisProperties);
        return mapperScannerConfigurer;
    }

    /**
     * 配置事务
     *
     * @return DataSourceTransactionManager
     */
    @Bean("transactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
