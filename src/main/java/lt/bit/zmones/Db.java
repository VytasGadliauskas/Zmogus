package lt.bit.zmones;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Db {
    private static Connection con = null;
    private static Statement stmt = null;
    private static Db dbinstance = null;

    private Db() {

    }

    public static Connection connectDb(){
        if (dbinstance == null) {
            dbinstance = new Db();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/Adresu_knyga", "user", "user123");
                stmt = con.createStatement();
                    return con;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return con;
    }

    public static Connection getCon() {
        if (con == null) {
            connectDb();
        }
        return con;
    }

    public void disconnectDb() throws SQLException {
        con.close();
    }

}