package Presentation;

import BusinessLogic.BillDoc;
import DAO.OrderDao;
import DAO.ProductDao;
import DataAccess.ConnectionFactory;
import Model.Meniu;
import Model.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;

public class ViewOrder extends Component {

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
    private Meniu meniu;
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
    OrderDao o1 = new OrderDao();
    /** This class is for Order's panel*/
    public ViewOrder(Meniu meniu) {

        this.meniu=meniu;
        initialize();
    }
    /**
     * This method is used to initialize the textfields*/
    public void textFields() {
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
    public void labels() {
        // labels
        label = new JLabel("Nume Client:");
        label.setBounds(100, 0, 80, 15);
        panel.add(label);

        label_1 = new JLabel("Cantitate produs:");
        label_1.setBounds(195, 0, 100, 15);
        panel.add(label_1);

        label_2 = new JLabel("Nume produs produs:");
        label_2.setBounds(330, 0, 100, 15);
        panel.add(label_2);









    }

    /**
     * This method is used to initialize the buttons*/

    public void buttons() {

        // buton
        button = new JButton("Add order");
        button.setFocusable(false);
        button.setBounds(70, 100, 110, 22);
        button.addActionListener(e1 -> {
            OrderDao orderDao= new OrderDao();
            for(ProductDao p :meniu.getProducts())
            {
                if(p.getName().equals(getProduct()))
                {
                    if(p.getQuantity()>=Integer.parseInt(getQuantity())) {
                        orderDao.setInsertBill(getName(), getProduct(), Integer.parseInt(getQuantity()),p.getPrice()* Integer.parseInt(getQuantity()));
                        p.updateQuantity(p.getQuantity()-Integer.parseInt(getQuantity()),p.getName());
                        p.setQuantity(p.getQuantity()-Integer.parseInt(getQuantity()));
                    }
                    else
                        JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(this),"STOC INSUFICIENT");
                }
            }
        });
        panel.add(button);

        button1 = new JButton("Generate bill");
        button1.setFocusable(false);
        button1.setBounds(179, 100, 130, 22);
        button1.addActionListener(e1 -> {

            try {
                BillDoc billDoc = new BillDoc();
                billDoc.show(getName());
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }

        });
        panel.add(button1);

        button2 = new JButton("View all");
        button2.setFocusable(false);
        button2.setBounds(310, 100, 125, 22);
        button2.addActionListener(e1 -> {

            try {
                addToTable();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });
        panel.add(button2);



    }
    /**
     * This method is used to initialize the tabel*/
    private void tabel() {

        String[] coloane ={"Id","Name", "Product","Quantity", "Cost"};


                table = new JTable(20,6) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setBounds(20, 200, 500, 400);
        JScrollPane js = new JScrollPane(table);
        js.setBounds(50, 200, 400, 400);
        panel.add(table);

    }
    /**
     * @throws SQLException if underlying service fail
     * This method is used to add to the table*/
    public void addToTable() throws SQLException {
        DefaultTableModel model = new DefaultTableModel(new String[]{"Id","Name", "Product","Quantity", "Cost"}, 0);
        Connection con = ConnectionFactory.createConnection();
        String select= "Select * from order";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from warehouse.order");
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        System.out.println(columnCount);
        model=o1.fields(model,rs);
        table.setModel(model);

        while(rs.next()) {
            String d = rs.getString(1);
            String e = rs.getString(2);
            String f = rs.getString(3);
            String g = rs.getString(4);
            String h = rs.getString(5);
            System.out.println(h);
            model.addRow(new Object[]{d,e,f,g,h});
            table.setModel(model);
        }
    }
/**initialize the frame and panel*/
    private void initialize() {

//frameul
        framePP = new JFrame();
        framePP.setTitle("Order");
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
    public String getProduct()
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
        button2.addActionListener(viewAll);
    }

    public void addOrder(ActionListener addOrder)
    {
        button.addActionListener(addOrder);
    }

    public void generateTxt(ActionListener gen)
    {
        button1.addActionListener(gen);
    }




}
