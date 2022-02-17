package BusinessLogic;

import DataAccess.ConnectionFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Class that is used to generate a txt of a bill after an order
 * */
public class BillDoc {
    private static String createBill(String name) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(" warehouse.order ");
        sb.append(" WHERE order.firstName='"+name+"'");
        return sb.toString();
    }
    /**
    * @param name is used to create a bill on that name
     *  @throws SQLException if underlying service fail
    * */
    public void show(String name) throws IOException, SQLException {
        String order=createBill(name);
        File file = new File(name+"_bill.txt");
        FileWriter fw= new FileWriter(file,true);
        String show="";
        Connection connection = ConnectionFactory.createConnection();
        PreparedStatement st = connection.prepareStatement(order);
        ResultSet rs = st.executeQuery();
        while(rs.next()) {
            show=show+rs.getString(1)+" ";
            show=show+rs.getString(2)+" ";
            show=show+rs.getString(3)+" ";
            show=show+rs.getString(4)+" ";
            show=show+rs.getString(5)+"\n ";
        }
        fw.write(show);
        fw.close();

    }
}
