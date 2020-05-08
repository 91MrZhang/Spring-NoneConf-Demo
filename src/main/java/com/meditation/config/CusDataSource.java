package com.meditation.config;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * <b>数据源</b>
 * 
 * @author: zhangyuting <br>
 * @date: 2019-11-27 <br>
 */
public class CusDataSource extends BasicDataSource {

	public CusDataSource() {
		// 数据库连接信息在这里复写一下即可
		super();
	}
}
