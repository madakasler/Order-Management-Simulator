package Presentation;

import BusinessLogic.ClientDoc;
import DAO.ClientDao;
import DataAccess.ConnectionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;

public class ViewClient {

    private JFrame framePP;


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
    ClientDao c1 = new ClientDao();
    /** This class is for Client's panel*/
    public ViewClient() {
        initialize();
    }

    /**
    * This method is used to initialize the textfields*/
    private void textFields() {
        // textFields

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(175, 20, 150, 40);
        panel.add(textField);

    }
    /**
     * This method is used to initialize the labels*/

    private void labels() {
        // labels
        label = new JLabel("Nume Client:");
        label.setBounds(210, 5, 100, 10);
        panel.add(label);
    }


    /**
     * This method is used to initialize the buttons*/
    private void buttons() {

        // buton
        button = new JButton("Add client");
        button.setBounds(40, 100, 110, 22);
        button.addActionListener(e1 -> {

            try {
                ClientDao clientDao = new ClientDao();
                clientDao.setInsertClient(getName());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });
        panel.add(button);

        button1 = new JButton("Edit client");
        button1.setFocusable(false);
        button1.setBounds(150, 100, 99, 22);
        panel.add(button1);


        button2 = new JButton("Delete client");
        button2.setFocusable(false);
        button2.setBounds(250, 100, 125, 22);
        button2.addActionListener(e1 -> {
            ClientDao clientDao= new ClientDao();
            clientDao.deleteByName(getName());
            System.out.println(getName());

        });
        panel.add(button2);


        button3 = new JButton("View all");
        button3.setFocusable(false);
        button3.setBounds(375, 100, 99, 22);
        button3.addActionListener( e1 -> {

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
                ClientDoc clientDoc = new ClientDoc();
                clientDoc.show();
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }

        });
        panel.add(button4);



    }
    /**
     * This method is used to initialize the tabel*/
    private void tabel() {

        String[] coloane = { "Id", "Name" };


            table = new JTable(0,0) {
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
     *
     * @throws SQLException
     */
    public void addToTable() throws SQLException {
        DefaultTableModel model = new DefaultTableModel(new String[]{"Id","Name"}, 0);
        Connection con = ConnectionFactory.createConnection();
        String select= "Select * from client";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from client");

        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

       model=c1.fields(model,rs);
       table.setModel(model);
       while(rs.next()) {
            String d = rs.getString(1);
           System.out.println(d);
            String e = rs.getString(2);
            model.addRow(new Object[]{d,e});
            table.setModel(model);
        }
    }

/**initialize the frame and pannel*/
    private void initialize() {

//frameul
        framePP = new JFrame();
        framePP.setTitle("Client");
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

    public String getName(){
        return textField.getText();
    }

    public void viewAll(ActionListener viewAll1)
    {

        button3.addActionListener(viewAll1);

    }
    public void addClient(ActionListener addClient)
    {
        System.out.println("hai1");
        button.addActionListener(addClient);
    }

    public void generateTxt(ActionListener gen)
    {
        button4.addActionListener(gen);
    }
    public void deleteClient(ActionListener deleteClient)
    {
        button2.addActionListener(deleteClient);
    }

}
