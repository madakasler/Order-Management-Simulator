package DAO;

import Model.Bill;
import DataAccess.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
* Class that is used to create a bill after an order
* */
public class BillDao extends AbstractDao<Bill>{
    private int id;
    private String firstName;

    private String product;
    private int quantity;
    private float price;
    private int idProd;
    private int idClient;

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    /**
@param quantity
@param name
@param price
@param idClient
@param idProduct
@param product are the fields from database
* */
    public  void setInsertBill(String name, String product, int quantity, float price, int idClient, int idProduct) throws SQLException {
        String insert= "INSERT INTO bill (firstName, product,quantity,price,idProd,idClient)"+"Values(?,?,?,?,?,?)";
        Connection con = ConnectionFactory.createConnection();

        try{
            PreparedStatement st = con.prepareStatement(insert);
            st.setString(1,name);
            st.setString(2,product);
            st.setInt(3,quantity);
            st.setFloat(4,price);
            st.setInt(5,idClient);
            st.setInt(6,idProduct);

            st.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
