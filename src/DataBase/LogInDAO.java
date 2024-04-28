package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class LogInDAO {
    Connection con = null;


    public LogInDAO(String url, String username, String password) {

        con = Connexion.getConnection(url, username, password);
    }

    /*private void valid(ResultSet rs,String[] role) {
        try {

            if(rs.next())
            {
                role[0]=rs.getString("role");

            }

        }
        catch (SQLException e)
        {

        }


    }*/

    public ResultSet log(String email, String pass) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM person WHERE email=? AND password=?");
            stmt.setString(1, email);
            stmt.setString(2, pass);
            rs = stmt.executeQuery();
            if (rs == null) {

                return null;
            } else {
                return rs;
            }
        } catch (SQLException e) {

        }
        return rs;
    }
    }





    /*private String valid(String email, String password,String role) {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement("SELECT role FROM person WHERE email = ? AND password = ?");
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
            {
                role=resultSet.getString("role");
                return (role);
            }

        }
        catch (SQLException e)
        {
            return("");
        }

        return ("");
    }*/





