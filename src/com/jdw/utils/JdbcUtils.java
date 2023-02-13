package com.jdw.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private static DataSource dataSource;
    private static ThreadLocal<Connection> threadLocal = new InheritableThreadLocal<>();

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\IdeaProject\\bookcity\\src\\druid.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection == null){
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            threadLocal.set(connection);
        }
        return connection;
    }

   public static void commitAndClose() {
       Connection connection = threadLocal.get();
       if (connection != null){
           try {
               connection.commit();
               connection.close();
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
       }
       threadLocal.remove();
   }

    public static void rollbackAndClose() {
        Connection connection = threadLocal.get();
        if (connection != null){
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        threadLocal.remove();
    }
}
