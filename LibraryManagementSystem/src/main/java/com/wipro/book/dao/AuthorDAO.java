package com.wipro.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wipro.book.util.DBUtil;
import com.wipro.book.bean.AuthorBean;

public class AuthorDAO {

    private AuthorBean getAuthor(int authorCode) {

    Connection con = DBUtil.getDBConnection();
        String sql = "SELECT * FROM Author_Tbl WHERE Author_code = ?";

        try {
           PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, authorCode);
            ResultSet rs=ps.executeQuery();

            if (rs.next()) {
                AuthorBean authorBean= new AuthorBean();
                authorBean.setAuthorCode(rs.getInt(1));
                authorBean.setAuthorName(rs.getString(2));
                authorBean.setContactNo(rs.getLong(3));
                return authorBean;
            }
            else {
            return null;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
return null;
    }
   
    public AuthorBean getAuthor(String authorName) {

    Connection con = DBUtil.getDBConnection();
        String sql = "SELECT * FROM Author_Tbl WHERE Author_name = ?";

        try {
           PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, authorName);
            ResultSet rs=ps.executeQuery();

            if (rs.next()) {
                AuthorBean authorBean= new AuthorBean();
                authorBean.setAuthorCode(rs.getInt(1));
                authorBean.setAuthorName(rs.getString(2));
                authorBean.setContactNo(rs.getLong(3));
                return authorBean;
            }
            else {
            return null;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
return null;
    }
}