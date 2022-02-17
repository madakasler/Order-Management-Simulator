package DAO;

import DataAccess.ConnectionFactory;

import java.sql.*;
/**
 * Class that is used to create a a client
 * */
public class ClientDao extends AbstractDao<ClientDao>{
    private int id;
    private String firstName;
    Connection con = ConnectionFactory.createConnection();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /*
    @name is the field from database
    * */
    public  void setInsertClient(String name) throws SQLException {
        String insert= "INSERT INTO client (firstName)  Values('" + name+"')";
        try{
            PreparedStatement st = con.prepareStatement(insert);


            st.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
/**
 * This method is used to create a JTable
 *  @throws SQLException if underlying service fail
 */

    public  void viewClients()throws SQLException {

        String select= "Select * from client";
       Statement st = con.createStatement();
       ResultSet rs = st.executeQuery("select * from client");

        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        while(rs.next()) {
            for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                Object object = rs.getObject(columnIndex);
                System.out.printf("%s ", object == null ? "NULL" : object.toString());
            }
            System.out.println();
        }

    }
}
