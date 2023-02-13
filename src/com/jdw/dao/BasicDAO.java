package com.jdw.dao;

import com.jdw.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BasicDAO<T> {
    private QueryRunner queryRunner = new QueryRunner();

    public int update(String sql, Object... args) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        int update = queryRunner.update(connection, sql, args);
        return update;
    }

    public Object queryForOne(String sql, Object... args) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        Object query = queryRunner.query(connection, sql, new ScalarHandler<>(), args);
        return query;
    }

    public T queryForOneList(String sql, Class<T> tClass, Object... args) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        T query = queryRunner.query(connection, sql, new BeanHandler<T>(tClass), args);
        return query;
    }

    public List<T> queryForList(String sql, Class<T> tClass, Object... args) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        List<T> query = queryRunner.query(connection, sql, new BeanListHandler<T>(tClass), args);
        return query;
    }
}
