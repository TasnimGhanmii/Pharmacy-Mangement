package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddMedDAO {
    Connection con = null;

    public AddMedDAO(String url, String username, String password) {

        con = Connexion.getConnection(url, username, password);
    }

    public void AddMed(int medID,int qt,double pr,String Medname,String Compname) throws SQLException {
        PreparedStatement statement = con.prepareStatement("INSERT INTO medecine (ID, quantity, price, `company name`, name) VALUES (?, ?, ?, ? , ? )");
        statement.setInt(1, medID);
        statement.setInt(2, qt);
        statement.setDouble(3, pr);
        statement.setString(5, Medname);
        statement.setString(4, Compname);
        statement.executeUpdate();
    }
}
