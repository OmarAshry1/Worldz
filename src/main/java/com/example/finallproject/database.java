package com.example.finallproject;
import com.mysql.cj.protocol.Resultset;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;

class database {

    static String path = "jdbc:mysql://mysql-26f97989-projectt.d.aivencloud.com:21775/login";
    static String user = "avnadmin";
    static String pass = "AVNS_EiyObUPm-D-bJt7DlFW";
    public static void Register(String email,String password, String username, String phone , String gender, String birthday,String bio) throws SQLException {
        LocalDate dateOfBirth = LocalDate.parse(birthday);
        int age = calculateAge(dateOfBirth);

        Connection connection = DriverManager.getConnection(path,user,pass);
        PreparedStatement p = connection.prepareStatement("INSERT INTO users(email,password,username,phone_number,gender,birthday,age,bio) VALUES (?,?,?,?,?,?,?,?)");
        p.setString(1,email);
        p.setString(2,password);
        p.setString(3,username);
        p.setString(4,phone);
        p.setString(5,gender);
        p.setString(6,birthday);
        p.setInt(7,age);
        if(bio == ""){
            p.setNull(8,java.sql.Types.VARCHAR);
        }
        else{
            p.setString(8,bio);
        }
        p.execute();

    }

    static String verpass = null;
    public static String login(String email, String password) throws SQLException {
        Connection connection = DriverManager.getConnection(path,user,pass);
        String s = "SELECT password FROM users WHERE email = ?";
        PreparedStatement p = connection.prepareStatement(s);
        p.setString(1,email);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            verpass = rs.getString("password");
        }
        return verpass;
    }


    public static void delete(String password) throws SQLException {
        Connection connection = DriverManager.getConnection(path,user,pass);
        PreparedStatement p = connection.prepareStatement("DELETE FROM users WHERE password = ?");
        p.setString(1,password);
        p.execute();
    }

    public static int calculateAge(LocalDate dateOfBirth)
    {

        LocalDate curDate = LocalDate.now();

        if ((dateOfBirth != null) && (curDate != null))
        {
            return Period.between(dateOfBirth, curDate).getYears();
        }
        else
        {
            return 0;
        }
    }









}

