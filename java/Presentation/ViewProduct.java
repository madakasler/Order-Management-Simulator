package Presentation;

import BusinessLogic.ProductDoc;
import DAO.ProductDao;
import DataAccess.ConnectionFactory;
import Model.Meniu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;

/** This class is for Product's panel*/
public class ViewProduct {
    ProductDao p1= new ProductDao();
    private Meniu meniu;
    private JFrame framePP;
    private JFrame framePP1;
    private JFrame framePP2;

    private JPanel panel;
    private JPanel panel1;
    private JPanel panel2;

    private JTable table;


    private JLabel label;
    private JLabel label_1;
    private JLabel label_2;
    private JLabel label_3;
    private JLabel label_4;
    private JLabel label_5;
    private JLabel label_6;
    private JLabel label_7;
    private JLabel label_8;

    private JTextField textField;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextArea textArea_1;
    private JTextArea textArea_2;
    private JTextField textField6;

    private JRadioButton rButton;
    private JRadioButton rButton_1;
    private JRadioButton rButton_2;
    private JRadioButton rButton_3;
    private ButtonGroup checkboxes;

    private JButton button;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;


    public ViewProduct(Meniu meniu) {

        this.meniu=meniu;
        initialize();
    }
    /**
     * This method is used to initialize the textfields*/
    private void textFields() {
        // textFields

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(70, 20, 120, 40);
        panel.add(textField);

        textField1 = new JTextField();
        textField1.setColumns(10);
        textField1.setBounds(210, 20, 70, 40);
        panel.add(textField1);


        textField2 = new JTextField();
        textField2.setColumns(10);
        textField2.setBounds(300, 20, 150, 40);
        panel.add(textField2);






    }
    /**
     * This method is used to initialize the labels*/
    private void labels() {
        // labels
        label = new JLabel("Nume produs:");
        label.setBounds(100, 0, 80, 15);
        panel.add(label);

        label_1 = new JLabel("Cantitate produs:");
        label_1.setBounds(195, 0, 100, 15);
        panel.add(label_1);

        label_2 = new JLabel("Pret produs:");
        label_2.setBounds(330, 0, 100, 15);
        panel.add(label_2);









    }

    /**
     * This method is used to initialize the buttons*/

    private void buttons() {

        // buton
        button = new JButton("Add product");
        button.setFocusable(false);
        button.setBounds(40, 100, 110, 22);
        button.addActionListener(e1 -> {
           DAO.ProductDao productDao= new DAO.ProductDao();
            productDao.setInsertProduct(getName(),Integer.parseInt(getQuantity()),Float.parseFloat(getPrice()));
            ProductDao pdao = new ProductDao();
            pdao.setName(getName());
            pdao.setQuantity(Integer.parseInt(getQuantity()));
            pdao.setPrice(Float.parseFloat(getPrice()));
            meniu.add(pdao);


        });
        panel.add(button);

        button1 = new JButton("Edit product");
        button1.setFocusable(false);
        button1.setBounds(150, 100, 99, 22);
        button1.addActionListener(e1 -> {
            ProductDao productDao= new ProductDao();
            productDao.updatePrice(Float.parseFloat(getPrice()), getName());


        });
        panel.add(button1);


        button2 = new JButton("Delete product");
        button2.setFocusable(false);
        button2.setBounds(250, 100, 125, 22);
        button2.addActionListener(e1 -> {
            ProductDao productDao= new ProductDao();
            productDao.deleteByNameProduct(getName());


        });
        panel.add(button2);


        button3 = new JButton("View all");
        button3.setFocusable(false);
        button3.setBounds(375, 100, 99, 22);
        button3.addActionListener(e1 -> {

            try {
                addToTable();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });
        panel.add(button3);

        button4 = new JButton("Generate txt");
        button4.setFocusable(false);
        button4.setBounds(190, 140, 130, 22);
        button4.addActionListener(e1 -> {

            try {
                ProductDoc productDoc = new ProductDoc();
                productDoc.show();
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }

        });
        panel.add(button4);



    }
    /**
     * This method is used to initialize the tabel*/
    private void tabel() {

        String[] coloane = { "Id", "Name" ,"Quantity","Price"};


        table = new JTable(20,4) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setBounds(50, 200, 400, 400);
        JScrollPane js = new JScrollPane(table);
        js.setBounds(50, 200, 400, 400);
        panel.add(table);

    }
    /**
     *  @throws SQLException if underlying service fail
     * This method is used to add to the table*/
    public void addToTable() throws SQLException {
        DefaultTableModel model = new DefaultTableModel(new String[]{"Id","Name","Quantity","Price"}, 0);
        Connection con = ConnectionFactory.createConnection();
        String select= "Select * from product";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from product");
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        model=p1.fields(model,rs);
        table.setModel(model);
        while(rs.next()) {
            String d = rs.getString(1);
            String e = rs.getString(2);
            String f = rs.getString(3);
            String g = rs.getString(4);
            model.addRow(new Object[]{d,e,f,g});
            table.setModel(model);
        }
    }
/**initialize the frame and panel*/
    private void initialize() {

//frameul
        framePP = new JFrame();
        framePP.setTitle("Product");
        framePP.setSize(600, 500);
        framePP.setBounds(100, 300, 600, 700);
        framePP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framePP.getContentPane().setLayout(null);


//panel
        panel = new JPanel();
        panel.setBounds(29, 74, 600, 500);
        framePP.getContentPane().add(panel);
        panel.setLayout(null);






        labels();
        textFields();
        buttons();
        tabel();



        framePP.setVisible(true);
    }

    private String getPolynomial1(){
        return textField.getText();
    }
    private String getPolynomial2(){
        return textField1.getText();
    }
    public void confirm(ActionListener listenerForConfirm)
    {
        button.addActionListener(listenerForConfirm);
    }

    public void setText(String text)
    {
        textArea_1.setText(text);
    }

    public String getName()
    {
        return textField.getText();

    }

    public String getQuantity()
    {
        return textField1.getText();

    }
    public String getPrice()
    {
        return textField2.getText();

    }

    public String getMinimumArrivalTime()
    {
        return textField3.getText();

    }


    public String getMaximumArrivalTime()
    {
        return textField4.getText();

    }

    public String getMaximumProcessingTime()
    {
        return textField5.getText();

    }

    public String getMinimumProcessingTime()
    {
        return textField6.getText();

    }
    public void viewAll(ActionListener viewAll)
    {
        button3.addActionListener(viewAll);
    }

    public void addProduct(ActionListener addProd)
    {
        button.addActionListener(addProd);
    }

    public void generateTxt(ActionListener gen)
    {
        button4.addActionListener(gen);
    }
    public void deleteProduct(ActionListener deleteProd)
    {
        button2.addActionListener(deleteProd);
    }
     public void updateProd(ActionListener updateProd)
    {
        button1.addActionListener(updateProd);
    }



}
