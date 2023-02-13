package com.jdw.web;

import com.jdw.pojo.Book;
import com.jdw.pojo.Page;
import com.jdw.service.BookService;
import com.jdw.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ClientBookServlet extends BaseServlet {
    private BookService bookService = new BookService();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("clientBookServlet?action=page");
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        double min = WebUtils.parseDouble(req.getParameter("min"), 0);
        double max = WebUtils.parseDouble(req.getParameter("max"), Integer.MAX_VALUE);
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);
        StringBuilder stringBuilder = new StringBuilder("clientBookServlet?action=pageByPrice");
        if (req.getParameter("min") != null){
            stringBuilder.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max") != null){
            stringBuilder.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(String.valueOf(stringBuilder));
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

}
