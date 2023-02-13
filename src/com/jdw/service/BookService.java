package com.jdw.service;

import com.jdw.dao.BookDAO;
import com.jdw.pojo.Book;
import com.jdw.pojo.Page;

import java.sql.SQLException;
import java.util.List;

public class BookService {
    private BookDAO bookDAO = new BookDAO();

    public void addBook(Book book) {
        try {
            bookDAO.addBook(book);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteBookById(int id) {
        try {
            bookDAO.deleteBookById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBook(Book book) {
        try {
            bookDAO.updateBook(book);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Book selectBookById(int id) {
        try {
            return bookDAO.selectBookById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> selectBooks() {
        try {
            return bookDAO.selectBooks();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Page<Book> page(int pageNo, int pageSize) throws SQLException {
        Page<Book> page = new Page<>();

        page.setPageSize(pageSize);

        Integer pageTotalCount = bookDAO.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);

        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize != 0){
            pageTotal += 1;
        }
        page.setPageTotal(pageTotal);
        if (pageNo < 1){
            pageNo = 1;
        }
        if (pageNo > pageTotal){
            pageNo = pageTotal;
        }
        page.setPageNo(pageNo);
        int begin = (pageNo - 1) * pageSize;
        List<Book> books = bookDAO.queryForItems(begin, pageSize);
        page.setItems(books);
        return page;
    }

    public Page<Book> pageByPrice(int pageNo, int pageSize,double min,double max) throws SQLException {
        Page<Book> page = new Page<>();
        Integer pageTotalCount = bookDAO.queryForPageTotalCount(min, max);
        page.setPageTotalCount(pageTotalCount);

        int begin = (pageNo - 1) * pageSize;
        List<Book> books = bookDAO.queryForPageItems(begin, pageSize, min, max);
        page.setItems(books);

        page.setPageNo(pageNo);

        page.setPageSize(pageSize);

        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize != 0){
            pageTotal += 1;
        }
        page.setPageTotal(pageTotal);

        if (pageNo < 1){
            pageNo = 1;
        }
        if (pageNo > pageTotal){
            pageNo = pageTotal;
        }
        page.setPageNo(pageNo);
        return page;
    }
}
