package DataAccess;

import Model.Meniu;
import Presentation.ViewClient;
import Presentation.ViewOrder;
import Presentation.ViewProduct;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
   Meniu meniu = new Meniu();


    ViewClient viewC = new ViewClient();
    ViewProduct viewP = new ViewProduct(meniu);
    ViewOrder viewO = new ViewOrder(meniu);
    //Controller controller = new Controller(viewC, viewP,viewO,meniu);

    }
}
