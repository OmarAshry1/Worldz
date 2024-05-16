package com.example.finallproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.time.Period;
import java.sql.*;
class  user{
    private String name ;
    private String password;
    private int id;
    private String bio ;
    private String email;
    private String phone ;
    private boolean tempgender;
    private String gender;
    private int age;
    private String birthday;
    protected ArrayList<user> friendList = new ArrayList<user>(0);
    Connection connection;
    PreparedStatement p;
    boolean rs;

    public user () {}
    public user (int id,String email,String password, String username, String phone ,String gender, String birthday,int age, String bio) {
        this.id=id;
        this.email = email;
        this.password = password;
        this.name = username;
        this.phone = phone;
        this.gender = gender;
        this.birthday = birthday;
        this.age = age;
        this.bio = bio;
    }

    public void setname(String name) throws SQLException {
        this.name = name;
        connection = DriverManager.getConnection(database.path,database.user,database.pass);
        p = connection.prepareStatement("update users set username = ? where password = ?");
        p.setString(1,name);
        p.setString(2,this.password);
        rs = p.execute();
    }
    public String getname() {
        return name ;
    }
    public void setbio(String bio) throws SQLException {
        this.bio = bio ;
        connection = DriverManager.getConnection(database.path,database.user,database.pass);
        p = connection.prepareStatement("update users set bio = ? where password = ?");
        p.setString(1,bio);
        p.setString(2,this.password);
        rs = p.execute();
    }
    public String getbio() {
        return bio ;
    }
    public void setemail(String email) throws SQLException {
        this.email = email ;
        connection = DriverManager.getConnection(database.path,database.user,database.pass);
        p = connection.prepareStatement("update users set email = ? where password = ?");
        p.setString(1,email);
        p.setString(2,this.password);
        rs = p.execute();
    }
    public String getemail() {
        return email ;
    }
    public void setphonenumber(String phone) throws SQLException {
        this.phone = phone ;
        connection = DriverManager.getConnection(database.path,database.user,database.pass);
        p = connection.prepareStatement("update users set phone_number = ? where password = ?");
        p.setString(1,phone);
        p.setString(2,this.password);
        rs = p.execute();
    }
    public String getphonenumber() {
        return phone;
    }
    public void setpassword(String password) throws SQLException {
        this.password = password;
        connection = DriverManager.getConnection(database.path,database.user,database.pass);
        p = connection.prepareStatement("update users set password = ? where email = ?");
        p.setString(1,password);
        p.setString(2,this.email);
        rs = p.execute();
    }
    public String getpassword(){
        return password;
    }
    //YYYY-MM-DD format
    public void setbirth(String birthday) throws SQLException {
        this.birthday = birthday;
        connection = DriverManager.getConnection(database.path,database.user,database.pass);
        p = connection.prepareStatement("update users set birthday = ? where password = ?");
        p.setString(1,birthday);
        p.setString(2,this.password);
        rs = p.execute();
        LocalDate dateOfBirth = LocalDate.parse(birthday);
        int newage = database.calculateAge(dateOfBirth);
        this.age = newage;
        connection = DriverManager.getConnection(database.path,database.user,database.pass);
        p = connection.prepareStatement("update users set age = ? where password = ?");
        p.setInt(1,age);
        p.setString(2,this.password);
        rs = p.execute();
    }
    public String getbirth(){
        return birthday;
    }
    public int getage(){
        return age;
    }

    public String getgender(){
        return gender;
    }

    public int getID(){
        return id;
    }

    public void addfriend(user u){
        friendList.add(u);
    }
    public void removefriend(user u){
        friendList.remove(u);
    }


}

