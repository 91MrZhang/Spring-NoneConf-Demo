package com.meditation.config;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

/**
 * Mybatis的SqlSessionFactory
 *
 * @author zhangyuting
 */
public class CusSqlSessionFactory extends SqlSessionFactoryBean {

    public CusSqlSessionFactory() {
        super();
        // 映射的xml文件在这里复写指定
//        String mapperLocation = "classpath:/mapper/oracle/*.xml";
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        Configuration cfg = new Configuration();
//        try {
//            super.setMapperLocations(resolver.getResources(mapperLocation));
//            cfg.setMapUnderscoreToCamelCase(true);
//            super.setConfiguration(cfg);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
