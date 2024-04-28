package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Main {
    public static void main(String args[])
    {
        //linking the database
        String url="jdbc:mysql://127.0.0.1/Pharmacy";
        String username="root";
        String password="";
        String nomDrive="com.mysql.jdbc.Driver";
        try {
            Class.forName(nomDrive);
            System.out.println("chargement ok");
        } catch (ClassNotFoundException e) {
            System.out.print("erreur de chargement "+e.getMessage());
        }

        Connection con=null;
        try {
            con= DriverManager.getConnection(url,username,password);
            System.out.println("connected...");
        } catch (SQLException e) {
            System.out.println("erreur de connexion "+e.getMessage());
        }

        if(con!=null)
        {
            Statement st=null;
            {
                try {
                    st= con.createStatement();
                    System.out.println("ERROR ST");

                } catch (SQLException e) {
                    System.out.println("ERROR ST");
                }
            }
        }
        //req insertion



    }
}
