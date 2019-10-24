/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class dBaseAccount {
    String dbusername = "root";
    String dbpassword = "";
    String dburl = "jdbc:mysql://localhost:3306/mytravelagency";
    String dbdriver = "com.mysql.jdbc.Driver";
    Connection dbcon;
    
    void dbConnect() throws SQLException
    {
        dbcon = DriverManager.getConnection(dburl, dbusername, dbpassword);
    }
    
    void dbClose() throws SQLException
    {
     dbcon.close();
    }
    
    public ResultSet getSourceCity() throws SQLException
    {
     dbConnect();
     String sql = "select distinct source from flight";
     PreparedStatement pstmt = dbcon.prepareStatement(sql);
     ResultSet rst = pstmt.executeQuery(sql);   
     return (rst);
    }
    public ResultSet getDestinationCity() throws SQLException
    {
        dbConnect();
        String sql = "select distinct destination from flight";
        PreparedStatement pstmt = dbcon.prepareStatement(sql);
        ResultSet rst = pstmt.executeQuery(sql);       
        return (rst);
    }
    public ResultSet getFlight(String s,String d) throws SQLException
    {
        dbConnect();
        String sql = "select * from flight where source = " + "'" + s + "'" + "and destination = " + "'" + d + "'";
        PreparedStatement pstmt = dbcon.prepareStatement(sql);
        ResultSet rst = pstmt.executeQuery(sql);
        return (rst);
    }
   public ResultSet departFlight(String s) throws SQLException
    {
        dbConnect();
        String sql = "select departtime,arrivaltime,fare from flight where flightid =" + "'" + s + "'";
        PreparedStatement pstmt = dbcon.prepareStatement(sql);
        ResultSet rst = pstmt.executeQuery(sql);
        return (rst);
    }
  
   public int insertdata(String source,String destination,String departflightcode,String returnflightcode,String fare,String salutation,String firstname,String lastname,String contact) throws SQLException
   {
       int x;
       dbConnect();
       String sql = "INSERT INTO mytravelagency.bookings(source,destination,departflightcode,returnflightcode,fare,salutation,firstname,lastname,contact)Values(?,?,?,?,?,?,?,?,?)";
       PreparedStatement pstmt = dbcon.prepareStatement(sql);
       pstmt.setString(1,source);
       pstmt.setString(2,destination);
       pstmt.setString(3,departflightcode);
       pstmt.setString(4,returnflightcode);
       pstmt.setString(5,fare);
       pstmt.setString(6,salutation);
       pstmt.setString(7,firstname);
       pstmt.setString(8,lastname);
       pstmt.setString(9,contact);
        x=pstmt.executeUpdate();
       return x;
   }
}
