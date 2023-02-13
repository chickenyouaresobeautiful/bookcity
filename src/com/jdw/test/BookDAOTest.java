package com.jdw.test;

import com.jdw.dao.BookDAO;
import com.jdw.pojo.Book;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class BookDAOTest {
    private BookDAO bookDAO = new BookDAO();

    @Test
    public void addBook() throws SQLException {
        int i = bookDAO.addBook(new Book(null, "斗破苍穹", 50.0, "天蚕土豆", 10000, 5, "static/img/default.jpg"));
        System.out.println(i);
    }

    @Test
    public void deleteBookById() throws SQLException {
        int i = bookDAO.deleteBookById(21);
        System.out.println(i);
    }

    @Test
    public void updateBook() throws SQLException {
        int i = bookDAO.updateBook(new Book(6, "斗罗大陆", 60.0, "唐家三少", 20000, 10, "static/img/default.jpg"));
        System.out.println(i);
    }

    @Test
    public void selectBookById() throws SQLException {
        Book book = bookDAO.selectBookById(2);
        System.out.println(book);
    }

    @Test
    public void selectBooks() throws SQLException {
        List<Book> books = bookDAO.selectBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
