package com.wipro.book.service;

import com.wipro.book.bean.BookBean;
import com.wipro.book.dao.BookDAO;

public class Administrator {

    public String addBook(BookBean bookBean) {

        
        if (bookBean == null)
            return "INVALID";

        if (bookBean.getBookName() == null || bookBean.getBookName().trim().isEmpty())
            return "INVALID";

        if (bookBean.getIsbn() == null || bookBean.getIsbn().trim().isEmpty())
            return "INVALID";

        if (bookBean.getBookType() == ' ')
            return "INVALID";

        if (bookBean.getBookType() != 'g' && bookBean.getBookType() != 't')
            return "INVALID";

        if (bookBean.getCost() == 0)
            return "INVALID";

        if (bookBean.getAuthor() == null ||
            bookBean.getAuthor().getAuthorName() == null ||
            bookBean.getAuthor().getAuthorName().trim().isEmpty())
            return "INVALID";

        
        BookDAO bookDAO = new BookDAO();
        int result = bookDAO.createBook(bookBean);

        if (result == 1)
            return "SUCCESS";
        else
            return "FAILURE";
    }

  public BookBean viewBook(String isbn) {
	  if(isbn == null || isbn.trim().isEmpty()) {
		  return null;
	  }
	BookDAO bookDAO = new BookDAO();
	BookBean bookBean = bookDAO.fetchBook(isbn);
	return bookBean;
  }
  }