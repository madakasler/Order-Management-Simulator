package BusinessLogic;

import DataAccess.ConnectionFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDoc {
    private static String createProduct() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(" product ");
        return sb.toString();
    }
    public void show() throws IOException, SQLException {
        String products=createProduct();
        File file = new File("products.txt");
        FileWriter fw= new FileWriter(file,true);
        String show="";
        Connection connection = ConnectionFactory.createConnection();
        PreparedStatement st = connection.prepareStatement(products);
        ResultSet rs = st.executeQuery();
        while(rs.next()) {
            show=show+rs.getString(1)+" ";
            show=show+rs.getString(2)+" ";
            show=show+rs.getString(3)+" ";
            show=show+rs.getString(4)+"\n";
        }
        fw.write(show);
        fw.close();
    }
}
