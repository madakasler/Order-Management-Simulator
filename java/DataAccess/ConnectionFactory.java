package DataAccess;

import java.sql.*;

public class ConnectionFactory {
    /**
This class is used to create the database connection between Intellij and MYSQL
* */
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost/warehouse";
    private static final String user = "root";
    private static final String password = "canoneos15";
    private static Connection con;

    private static ConnectionFactory singleInstance = new ConnectionFactory();
    /**
This method is used to create the driver
* */
    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
    This method is used to create the connection
     @return
    * */
    public static Connection createConnection() {
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
            System.exit(1);
        }
        return con;

    }
    /**
     * @throws SQLException if underlying service fail
   This method is used to get the connection
    @return
   * */
    public static Connection getConnection() throws SQLException {

        return (Connection) con.getMetaData();
    }
    /**
    This method is used to close the connection
     @param connection
    * */
    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    /**
        This method is used to close the statement
     @param statement
        * */
    public static void close(Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    /**
           This method is used to close the resultset
     @param resultSet
           * */
    public static void close(ResultSet resultSet) {
        try {
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


}
