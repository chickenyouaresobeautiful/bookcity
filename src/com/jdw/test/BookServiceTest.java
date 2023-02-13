package com.jdw.test;

import com.jdw.pojo.Book;
import com.jdw.service.BookService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    private BookService bookService = new BookService();
    @Test
    void addBook() {
        bookService.addBook(new Book(null,"夜的命名术", 50.1,"会说话的肘子",20000,50,"static/img/default.jpg"));
    }

    @Test
    void deleteBookById() {
        bookService.deleteBookById(26);
    }

    @Test
    void updateBook() {
        bookService.updateBook(new Book(20,"夜的命名术", 50.1,"会说话的肘子",20000,50,"static/img/default.jpg"));
    }

    @Test
    void selectBookById() {
        Book book = bookService.selectBookById(20);
        System.out.println(book);
    }

    @Test
    void selectBooks() {
        List<Book> books = bookService.selectBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }
}