import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.sql.*;
import java.net.URL;

public class test {
    private static URL        propertyURL = BadLoader.class
                                            .getResource("/loader.cnf");

    private static Connection         con = null;
    private static PreparedStatement  stmt = null;
    private static boolean            verbose = false;

    private static void openDB(String host, String dbname,
                               String user, String pwd) {
      try {
        //
        Class.forName("org.postgresql.Driver");
      } catch(Exception e) {
        System.err.println("Cannot find the Postgres driver. Check CLASSPATH.");
        System.exit(1);
      }
      String url = "jdbc:postgresql://" + host + "/" + dbname;
      Properties props = new Properties();
      props.setProperty("user", user);
      props.setProperty("password", pwd);
      try {
        con = DriverManager.getConnection(url, props);
        if (verbose) {
          System.out.println("Successfully connected to the database "
                             + dbname + " as " + user);
        }
      } catch (SQLException e) {
        System.err.println("Database connection failed");
        System.err.println(e.getMessage());
        System.exit(1);
      }
      /*
      try {
        stmt = con.prepareStatement("insert into students(studentid,name)"
                                    + " values(?,?)");
      } catch (SQLException e) {
        System.err.println("Insert statement failed");
        System.err.println(e.getMessage());
        closeDB();
        System.exit(1);
      }
      */
    }

    private static void closeDB() {
       if (con != null) {
         try {
           if (stmt != null) {
             stmt.close();
           }
           con.close();
           con = null;
         } catch (Exception e) {
           // Forget about it
         }
       }
    }

    private static void loadData(String studentid, String name)
                  throws SQLException {
        if (con != null) {
          stmt.setString(1, studentid);
          stmt.setString(2, name);
          stmt.executeUpdate();
        }
    }

    public static void main(String[] args) {
        closeDB();
    }
}

