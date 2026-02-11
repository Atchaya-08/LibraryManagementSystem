package com.wipro.book.servlets;

import java.io.IOException;

import com.wipro.book.bean.BookBean;
import com.wipro.book.dao.AuthorDAO;
import com.wipro.book.service.Administrator;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet{
/**
*
*/
private static final long serialVersionUID = 1L;
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
String operations=request.getParameter("operation");
if(operations.equals("AddBook")) {
String result=addBook(request);
if(request.equals("SUCCESS")) {
response.sendRedirect("Menu.html");
}
else if(result.equals("INVALID")) {
response.sendRedirect("Invalid.html");
}
else if(result.equals(operations)){
response.sendRedirect("Failure.html");
}

}
else if(operations.equals("Search")) {
String isbn=request.getParameter("isbn");
BookBean bookBean=viewBook(isbn);
if(bookBean==null) {
response.sendRedirect("Invalid.html");
}
else {
HttpSession session=request.getSession();
session.setAttribute("book", bookBean);
RequestDispatcher rd=request.getRequestDispatcher("ViewServlet");
rd.forward(request, response);
}

}
}
public String addBook(HttpServletRequest request) {

  String isbn = request.getParameter("isbn");
       String bookName = request.getParameter("book");
       String bookType = request.getParameter("bookType");
       String authorName = request.getParameter("authorName");
       String cost= request.getParameter("cost");
       
       BookBean bookBean=new BookBean();
       bookBean.setIsbn(isbn);
       bookBean.setBookName(bookName);
       bookBean.setBookType(bookType.charAt(0));
       bookBean.setCost(Float.parseFloat(cost));
       bookBean.setAuthor(new AuthorDAO().getAuthor(authorName));
       
       String result=new Administrator().addBook(bookBean);
       return result;
}
public BookBean viewBook(String isbn) {
return new Administrator().viewBook(isbn);
}
}
