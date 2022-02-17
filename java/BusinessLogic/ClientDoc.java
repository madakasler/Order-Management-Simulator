package BusinessLogic;

import DataAccess.ConnectionFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDoc {
    private static String createClients() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(" client ");
        return sb.toString();
    }
    public void show() throws IOException, SQLException {
        String clients=createClients();
        File file = new File("clients.txt");
        FileWriter fw= new FileWriter(file,true);
        String show="";
        Connection connection = ConnectionFactory.createConnection();
        PreparedStatement st = connection.prepareStatement(clients);
        ResultSet rs = st.executeQuery();
        while(rs.next()) {
            show=show+rs.getString(1)+" ";
            show=show+rs.getString(2)+"\n";

        }
        fw.write(show);
        fw.close();
    }
}
