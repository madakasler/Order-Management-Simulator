package DAO;

import DataAccess.ConnectionFactory;
import Model.Product;

import java.sql.*;
/**
 * Class that is used to create a product
 * */
public class ProductDao extends AbstractDao<Product> {
    private int id;
    private String name;
    private int quantity;
    private float price;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    /**
      name,quantity and price are the fields from database
     @param name
     @param price
     @param quantity
       * */
    public  void setInsertProduct(String name, int quantity, float price) {
        String insert= "INSERT INTO product (name, quantity,price)"+"Values(?,?,?)";
        Connection con = ConnectionFactory.createConnection();

        try{
            PreparedStatement st = con.prepareStatement(insert);
            st.setString(1,name);
            st.setInt(2,quantity);
            st.setFloat(3,price);
            st.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     *  @throws SQLException if underlying service fail
     * This method is used to create a JTable
     */
    public  void viewProducts() throws SQLException {
        Connection con = ConnectionFactory.createConnection();
        String select= "Select * from client";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from product");

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
