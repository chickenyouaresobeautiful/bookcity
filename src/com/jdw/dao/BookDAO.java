package com.jdw.dao;

import com.jdw.pojo.Book;

import java.sql.SQLException;
import java.util.List;

public class BookDAO extends BasicDAO<Book> {
    public int addBook(Book book) throws SQLException {
        return update("insert into t_book values(null,?,?,?,?,?,?)", book.getName(), book.getPrice(), book.getAuthor(), book.getSales(), book.getStock(), book.getImg_Path());
    }

    public int deleteBookById(int id) throws SQLException {
        return update("delete from t_book where id = ?", id);
    }

    public int updateBook(Book book) throws SQLException {
        String sql = "update t_book set name = ?,author = ?,price = ?,sales = ?,stock = ?,img_Path = ? where id = ?";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImg_Path(), book.getId());
    }

    public Book selectBookById(int id) throws SQLException {
        String sql = "select * from t_book where id = ?";
        return queryForOneList(sql, Book.class, id);
    }

    public List<Book> selectBooks() throws SQLException {
        String sql = "select * from t_book";
        return queryForList(sql, Book.class);
    }

    public Integer queryForPageTotalCount() throws SQLException {
        Number number = (Number) queryForOne("select count(*) from t_book");
        return number.intValue();
    }

    public List<Book> queryForItems(int begin, int pageSize) throws SQLException {
        String sql = "select * from t_book limit ? ,?";
        return queryForList(sql, Book.class, begin, pageSize);
    }

    public Integer queryForPageTotalCount(double min, double max) throws SQLException {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number number = (Number) queryForOne(sql, min, max);
        return number.intValue();
    }

    public List<Book> queryForPageItems(int begin,int pageSize,double min,double max) throws SQLException {
        String sql = "select * from t_book where price between ? and ? limit ?,?";
        return queryForList(sql,Book.class,min,max,begin,pageSize);
    }
}
