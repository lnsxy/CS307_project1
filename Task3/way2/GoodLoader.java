import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.sql.*;

public class GoodLoader {
    private static final int  BATCH_SIZE = 500;
    private static Connection         con = null;
    private static PreparedStatement[]  stmt = new PreparedStatement[10];
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
        con.setAutoCommit(false);
        if (verbose) {
          System.out.println("Successfully connected to the database "
                             + dbname + " as " + user);
        }
      } catch (SQLException e) {
        System.err.println("Database connection failed");
        System.err.println(e.getMessage());
        System.exit(1);
      }

      try {
        stmt[0]=con.prepareStatement("insert into city values (?)");
        stmt[1]=con.prepareStatement("insert into containers values (?,?)");
        stmt[2]=con.prepareStatement("insert into company values (?)");
        stmt[3]=con.prepareStatement("insert into item values(?,?,?,?,?)");
        stmt[4]=con.prepareStatement("insert into courier values(?,?,?,?,?)");
        stmt[5]=con.prepareStatement("insert into ship values(?,?)");
        stmt[6]=con.prepareStatement("insert into import values(?,?,?,?)");
        stmt[7]=con.prepareStatement("insert into export values(?,?,?,?)");
        stmt[8]=con.prepareStatement("insert into retrieval values(?,?,?,?,?,?)");
        stmt[9]=con.prepareStatement("insert into delivery values(?,?,?,?,?,?)");
      } catch (SQLException e) {
        System.err.println("Insert statement failed");
        System.err.println(e.getMessage());
        closeDB();
        System.exit(1);
      }
    }

    private static void closeDB() {
        if (con != null) {
          try {
            for(int i=0;i<10;i++){
                if(stmt[i]!=null){
                    stmt[i].close();
                }
            }
            con.close();
            con = null;
          } catch (Exception e) {
            // Forget about it
          }
        }
     }
    private static void load(int statement_type,String...parts) {//just load the data and execute it
        try {
            for(int i=1;i<=parts.length;i++){
                stmt[statement_type].setString(i, parts[i-1]);
            }
            stmt[statement_type].addBatch();
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
            openDB();
            while ((line = infile.readLine()) != null) {
                parts = line.split(",");//hope there is no fucking comma in the data
                load(0,parts[3] );//
                load(0,parts[10] );
                load(0,parts[18] );
                load(0,parts[15] );
                load(1,parts[21],parts[22]);
                load(2,parts[24]);
                load(3,parts[0],parts[1],parts[2],parts[25],parts[21]);
                load(4,parts[5],parts[7],parts[6],parts[24],parts[15]);
                load(4, parts[11],parts[13],parts[12],parts[24],parts[18]);
                load(5,parts[23],parts[24]);
                load(6,parts[0],parts[18],parts[20],parts[19] );
                load(7,parts[0],parts[15],parts[17],parts[16]);
                load(8,parts[0],parts[5],parts[7],parts[3],parts[4],parts[8]);
                load(9,parts[0],parts[11],parts[13],parts[10],parts[9],parts[14]);
                cnt++;
                if (cnt % BATCH_SIZE == 0) {
                    for(int i=0;i<10;i++){
                        stmt[i].executeBatch();
                        stmt[i].clearBatch();
                    }
                  }
                /*fuck! */
              }
            closeDB();
            end = System.currentTimeMillis();
            System.out.println(cnt + " records successfully loaded");
            System.out.println("Loading speed : " 
                               + (cnt * 1000)/(end - start)
                               + " records/s");
        }  catch (SQLException se) {
            System.err.println("SQL error: " + se.getMessage());
            try {
              con.rollback();
              for(int i=0;i<10;i++){
                stmt[i].close();
              }

            } catch (Exception e2) {
            }
            closeDB();
            System.exit(1);
         } catch (IOException e) {
            System.err.println("Fatal error: " + e.getMessage());
            try {
              con.rollback();
              for(int i=0;i<10;i++){
                stmt[i].close();
              }
            } catch (Exception e2) {
            }
            closeDB();
            System.exit(1);
         }   
        


    }


}


