package com.meditation.config;

import org.mybatis.spring.SqlSessionFactoryBean;

/**
 * <b>Mybatis的SqlSessionFactory</b>
 * 
 * @author: zhangyuting <br>
 * @date: 2019-11-27 <br>
 */
public class CusSqlSessionFactory extends SqlSessionFactoryBean {

	public CusSqlSessionFactory() {
		// 映射的xml文件在这里复写指定
		super();
	}
}
