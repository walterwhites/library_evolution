package com.walterwhites.library.consumer.dao.impl;

import javax.activation.DataSource;

public class AbstractDaoImpl {
    private static DataSource dataSource;

    public static void setDataSource(DataSource dataSource) {
        AbstractDaoImpl.dataSource = dataSource;
    }
}
