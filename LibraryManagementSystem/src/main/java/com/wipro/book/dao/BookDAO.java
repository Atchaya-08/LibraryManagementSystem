package com.wipro.book.dao;



import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;



import com.wipro.book.bean.*;

import com.wipro.book.util.*;



public class BookDAO {

private Connection con=DBUtil.getDBConnection();

public int createBook(BookBean bookBean) {

	int result=0;

	try {

		PreparedStatement ps =con.prepareStatement("Insert into book_tbl values(?,?,?,?,?");

		ps.setString(1, bookBean.getIsbn());

        ps.setString(2, bookBean.getBookName());

        ps.setString(3, String.valueOf(bookBean.getBookType())); 

        ps.setFloat(4, bookBean.getAuthor().getAuthorCode());

        ps.setDouble(5, bookBean.getCost());



        int rows = ps.executeUpdate();

        if (rows > 0) {

            result = 1; 

        }



    } catch (SQLException e) {

        e.printStackTrace(); 

        result = 0; 

    }



    return result;

}

public BookBean fetchBook(String isbn) {

	try {

		PreparedStatement ps=con.prepareStatement("Select * from book_tbl  join author_tbl using (author_code) where isbn=?");

		ps.setString(1, isbn);

		ResultSet rs=ps.executeQuery();

		

		AuthorBean author=new AuthorBean();

		author.setAuthorCode(rs.getInt("Author_code"));

		author.setAuthorName(rs.getString("Author_Name"));

		author.setContactNo(rs.getLong("Author_number"));

		

		BookBean book=new BookBean();

		book.setBookName(rs.getString("Book_title"));

		book.setBookType(rs.getString("Book_type").charAt(0));

		book.setCost(rs.getInt("book_cost"));

		book.setIsbn(isbn);

		

		return book;

		

	} catch (SQLException e) {

		

		e.printStackTrace();

	}

	return null;

	

	

}



}