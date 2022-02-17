package DAO;

import DataAccess.ConnectionFactory;
import Model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * Class that is used to create an order
 * */
public class OrderDao extends AbstractDao<Order>{
    private int id;
    private String firstName;
    private String product;
    private int quantity;


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

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * This method is used to create a JTable
     * @param name
     * @param quantity
     * @param cost
     * @param product
     */

    public  void setInsertBill(String name, String product, int quantity,float cost) {


        String insert= "INSERT INTO warehouse.order (firstName,product,quantity,cost) "+" Values(?,?,?,?)";
        Connection con = ConnectionFactory.createConnection();

        try{
            PreparedStatement st = con.prepareStatement(insert);
            st.setString(1,name);
            st.setString(2,product);
            st.setInt(3,quantity);
            st.setFloat(4,cost);


            st.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
