package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddUserDAO {
    Connection con = null;

    public AddUserDAO(String url, String username, String password) {

        con = Connexion.getConnection(url, username, password);
    }

    public void addUser(String email,String name,String username,String dob,String number,String password,String adress,String userRole) throws SQLException {
        {
            PreparedStatement statement = con.prepareStatement("INSERT INTO person (role,name,email,phone,address,dob,password,username) VALUES (?, ?, ?, ? , ? , ?, ? , ?)");statement.setString(1, adress);
        statement.setString(6, dob);
        statement.setString(3, email);
        statement.setString(2, name);
        statement.setString(4, number);
        statement.setString(1, userRole);
        statement.setString(5,adress);
        statement.setString(7, password);
            statement.setString(8,username);

            statement.executeUpdate();
    }
    //quest
}}
