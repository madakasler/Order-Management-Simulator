package DataAccess;

import BusinessLogic.BillDoc;
import BusinessLogic.ClientDoc;
import DAO.ClientDao;
import DAO.OrderDao;
import DAO.ProductDao;
import Model.Meniu;
import Presentation.ViewClient;
import Presentation.ViewOrder;
import Presentation.ViewProduct;
import BusinessLogic.ProductDoc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
/**
* This class implements all action listeners for all buttons from View classes
* */

public class Controller {
    private ViewProduct viewProduct;
    private ViewClient viewClient;
    private ViewOrder viewOrder;
    private Meniu meniu;
    ClientDao clientDao = new ClientDao();
    ProductDao productDao = new ProductDao();
   OrderDao orderDao= new OrderDao();
    public Controller(ViewClient viewClient, ViewProduct viewProduct, ViewOrder viewOrder, Meniu meniu){
        this.meniu=meniu;
        this.viewClient=viewClient;
        this.viewProduct=viewProduct;
        this.viewOrder=viewOrder;
        this.viewProduct.viewAll(new viewAllProduct());
        this.viewClient.viewAll(new ViewListener() );
        this.viewOrder.viewAll(new viewAllOrder());
        this.viewClient.addClient(new addClientListener());
        this.viewClient.generateTxt(new generateClientTxt());
        this.viewClient.deleteClient(new deleteClient());
        this.viewProduct.deleteProduct(new deleteProduct());
        this.viewProduct.generateTxt(new generateProduct());
        this.viewProduct.addProduct(new addProduct());
        this.viewProduct.updateProd(new update());
        this.viewOrder.addOrder(new addOrder());
        this.viewOrder.generateTxt(new generateBill());
    }

    class ViewListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            viewClient.viewAll(e1 -> {

                try {
                    viewClient.addToTable();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            });
        }
    }
    class viewAllProduct implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            viewProduct.viewAll(e1 -> {

                try {
                    viewProduct.addToTable();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            });
        }
    }
    class viewAllOrder implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            viewOrder.viewAll(e1 -> {

                try {
                    viewOrder.addToTable();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            });
        }
    }

    class addClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            viewClient.addClient(e1 -> {

                try {
                    clientDao.setInsertClient(viewClient.getName());
                                    } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            });
        }
    }

    class generateClientTxt implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            viewClient.generateTxt(e1 -> {

                try {
                    ClientDoc clientDoc = new ClientDoc();
                    clientDoc.show();
                } catch (SQLException | IOException throwables) {
                    throwables.printStackTrace();
                }

            });
        }
    }
    class deleteClient implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            viewClient.deleteClient(e1 -> {

                clientDao.deleteByName(viewClient.getName());
                System.out.println(viewClient.getName());

            });
        }
    }

    class addProduct implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            viewProduct.addProduct(e1 -> {

                productDao.setInsertProduct(viewProduct.getName(),Integer.parseInt(viewProduct.getQuantity()),Float.parseFloat(viewProduct.getPrice()));
                ProductDao pdao = new ProductDao();
                pdao.setName(viewProduct.getName());
                pdao.setQuantity(Integer.parseInt(viewProduct.getQuantity()));
                pdao.setPrice(Float.parseFloat(viewProduct.getPrice()));
                meniu.add(pdao);


            });
        }
    }

    class generateProduct implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            viewProduct.generateTxt(e1 -> {

                try {
                    ProductDoc productDoc = new ProductDoc();
                    productDoc.show();
                } catch (SQLException | IOException throwables) {
                    throwables.printStackTrace();
                }

            });
        }
    }
    class deleteProduct implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            viewProduct.deleteProduct(e1 -> {

                productDao.deleteByNameProduct(viewProduct.getName());


            });
        }
    }

    class update implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            viewProduct.updateProd(e1 -> {

                productDao.updatePrice(Float.parseFloat(viewProduct.getPrice()), viewProduct.getName());


            });
        }
    }
    class addOrder implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            viewOrder.addOrder(e1 -> {
                for(ProductDao p :meniu.getProducts())
                {
                    if(p.getName().equals(viewOrder.getProduct()))
                    {
                        if(p.getQuantity()>=Integer.parseInt(viewOrder.getQuantity())) {
                            orderDao.setInsertBill(viewOrder.getName(), viewOrder.getProduct(), Integer.parseInt(viewOrder.getQuantity()),p.getPrice()* Integer.parseInt(viewOrder.getQuantity()));
                             p.updateQuantity(p.getQuantity()-Integer.parseInt(viewOrder.getQuantity()),p.getName());
                             p.setQuantity(p.getQuantity()-Integer.parseInt(viewOrder.getQuantity()));
                        }
                        else
                            JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(viewOrder),"STOC INSUFICIENT");



                    }
                }


            });
        }
    }
    class generateBill implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            viewOrder.generateTxt(e1 -> {

                try {
                    BillDoc billDoc = new BillDoc();
                    billDoc.show(viewOrder.getName());
                } catch (SQLException | IOException throwables) {
                    throwables.printStackTrace();
                }

            });
        }
    }
}
