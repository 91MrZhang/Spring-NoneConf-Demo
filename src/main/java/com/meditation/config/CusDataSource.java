package com.meditation.config;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * 数据源
 * 目前使用的是DBCP连接池
 * 要使用其他的连接池,继承其他的连接池即可
 *
 * @author zhangyuting
 */
public class CusDataSource extends BasicDataSource {

    public CusDataSource() {
        // 数据库连接信息在这里复写一下即可
        super();
    }
}
