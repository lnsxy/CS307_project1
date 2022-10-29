import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.sql.*;

public class AwfulLoader {
    private static Connection         con = null;
    private static PreparedStatement  stmt = null;
    private static boolean            verbose = false;

    private static String host="localhost";
    private static String dbname="postgres";
    private static String user="postgres";
    private static String pwd="kali";
    //Please change the lines above

    private static void openDB() {//get con//don't mind stmt, too much that I have to rewrite it
      try {
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
    private static void load(String statement,String...parts) {//just load the data and execute it
        try {
            stmt=con.prepareStatement(statement);
            for(int i=1;i<=parts.length;i++){
                stmt.setString(i, parts[i-1]);
            }
            stmt.executeUpdate();
        } catch (SQLException se) {
            System.err.println("SQL error: " + se.getMessage());
         }
       
    }//hope there isn't problem with type conversion
     public static void main(String[] args) {
        String  fileName = null;

        switch (args.length) {//in cmd use "java [-v] BadLoader filename", -v means more information//also loads filename
           case 1:
                fileName = args[0];
                break;
           case 2:
                switch (args[0]) {
                  case "-v":
                       verbose = true;
                       break;
                  default:
                       System.err.println("Usage: java [-v] BadLoader filename");
                       System.exit(1);
                }
                fileName = args[1];
                break;
           default:
                System.err.println("Usage: java [-v] BadLoader filename");
                System.exit(1);
        }
        //no need to use config

        try (BufferedReader infile 
                = new BufferedReader(new FileReader(fileName))) {
            long     start;
            long     end;
            //start and end is to record time
            String   line;//to read file
            String[] parts;//to "split" line
            int cnt=0;
            start = System.currentTimeMillis();
            
            while ((line = infile.readLine()) != null) {
                parts = line.split(",");//hope there is no fucking comma in the data
                openDB();
                load("insert into city values (?)",parts[3] );//
                load("insert into city values (?)",parts[10] );
                load("insert into city values (?)",parts[18] );
                load("insert into city values (?)",parts[15] );

                load("insert into containers values (?,?)",parts[21],parts[22]);

                load("insert into company values (?)",parts[24]);

                load("insert into item values(?,?,?,?,?)",parts[0],parts[1],parts[2],parts[25],parts[21]);

                load("insert into courier values(?,?,?,?,?)",parts[5],parts[7],parts[6],parts[24],parts[15]);
                load("insert into courier values(?,?,?,?,?)", parts[11],parts[13],parts[12],parts[24],parts[18]);

                load("insert into ship values(?,?)",parts[23],parts[24]);

                load("insert into import values(?,?,?,?)",parts[0],parts[18],parts[20],parts[19] );
                
                load("insert into export values(?,?,?,?)",parts[0],parts[15],parts[17],parts[16]);

                load("insert into retrieval values(?,?,?,?,?,?)",parts[0],parts[5],parts[7],parts[3],parts[4],parts[8]);

                load("insert into delivery values(?,?,?,?,?,?)",parts[0],parts[11],parts[13],parts[10],parts[9],parts[14]);
                cnt++;
                closeDB();
                /*fuck! */
              }
        
            end = System.currentTimeMillis();
            System.out.println(cnt + " records successfully loaded");
            System.out.println("Loading speed : " 
                               + (cnt * 1000)/(end - start)
                               + " records/s");
        }  catch (IOException e) {
           System.err.println("Fatal error: " + e.getMessage());
           closeDB();
           System.exit(1);
        }       
        


    }


}

