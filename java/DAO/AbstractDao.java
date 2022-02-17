package DAO;

import DataAccess.ConnectionFactory;

import javax.swing.table.DefaultTableModel;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
/**
* Abstract class that is used to create different SQL queries on anytype of object
*
*
*  */

public abstract class AbstractDao<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDao.class.getName());
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDao() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    /**
* @param field  is used to help me create a certain select query depending on it
*@return
* */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" * ");
        sb.append(" FROM ");
        if(type.getSimpleName().equals("ClientDao") || type.getSimpleName().equals("Client") )
            sb.append("Client");
        if(type.getSimpleName().equals("OrderDao") || type.getSimpleName().equals("Order") )
            sb.append("Order");
        if(type.getSimpleName().equals("BillDao")|| type.getSimpleName().equals("Bill") )
            sb.append("Bill");
        if(type.getSimpleName().equals("ProductDao")|| type.getSimpleName().equals("Product") )
            sb.append("Product");
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }
    private String createInsert(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("Insert");
        sb.append(" INTO ");

        if(type.getSimpleName().equals("ClientDao") || type.getSimpleName().equals("Client") )
            sb.append("Client");
        if(type.getSimpleName().equals("OrderDao") || type.getSimpleName().equals("Order") )
            sb.append("Order");
        if(type.getSimpleName().equals("BillDao")|| type.getSimpleName().equals("Bill") )
            sb.append("Bill");
        if(type.getSimpleName().equals("ProductDao")|| type.getSimpleName().equals("Product") )
            sb.append("Product");
        sb.append(field);
        return sb.toString();
    }
    public T findById(int id) {
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.createConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        }catch(SQLException e) {
            LOGGER.fine(type.getName() + "DAO:findBy" + e.getMessage());
        }
        return null;
    }

    /**
     *
     * @param name
     * @param id
     * @return
     */

    public T Update(String name, String id) {
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        String query = updateQuery(name,id);
        try {
            connection = ConnectionFactory.createConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(id));
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        }catch(SQLException e) {
            LOGGER.fine(type.getName() + "DAO:Update" + e.getMessage());
        }
        return null;
    }

    public T Insert(String toInsert, String id) {
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsert(toInsert);
        try {
            connection = ConnectionFactory.createConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(id));
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        }catch(SQLException e) {
            LOGGER.fine(type.getName() + "DAO:Update" + e.getMessage());
        }
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public T Delete(String id) {
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        String query = deleteQuery(id);
        try {
            connection = ConnectionFactory.createConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(id));
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        }catch(SQLException e) {
            LOGGER.fine(type.getName() + "DAO:Delete" + e.getMessage());
        }
        return null;
    }

    /**
     *
     * @param model
     * @param resultSet
     * @return
     */

    public  DefaultTableModel fields (DefaultTableModel model, ResultSet resultSet)
    {
        ArrayList<String> fields = new ArrayList<String>();
        try {
            while (resultSet.next())
            {
                for(Field f : type.getDeclaredFields() ){
                    fields.add(f.getName());
                }
                break;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(fields.size()==4)
        {
            String [] campuri = new String[4];
            for(int count =0; count<fields.size();count++)
            {
                campuri[count]=fields.get(count);
            }
            model.addRow(campuri);
        }
        else if(fields.size()==3)
        {
            String [] campuri = new String[3];
            for(int count =0 ;count<fields.size();count++)
            {
                campuri[count]=fields.get(count);
            }
            model.addRow(campuri);
        }
        else if(fields.size()==6) {
            String[] campuri = new String[6];
            for (int count = 0; count < fields.size(); count++) {
                campuri[count] = fields.get(count);
            }
            model.addRow(campuri);
        }
        return model;

    }
    /**
* @param field1  is used to help me create a certain update query depending on it
* @param field2  is used to tell me what is the new value* @return
*
* */
    private String updateQuery(String field1, String field2) {
        StringBuilder sb = new StringBuilder();
        sb.append("update ");
        if(type.getSimpleName().equals("ClientDao") || type.getSimpleName().equals("Client") )
            sb.append("Client");
        if(type.getSimpleName().equals("OrderDao") || type.getSimpleName().equals("Order") )
            sb.append("Order");
        if(type.getSimpleName().equals("BillDao")|| type.getSimpleName().equals("Bill") )
            sb.append("Bill");
        if(type.getSimpleName().equals("ProductDao")|| type.getSimpleName().equals("Product") )
            sb.append("Product");
        sb.append(" set " + field1 + " =?");
        sb.append(" WHERE " + field2 + " =?");
        return sb.toString();
    }
/**
* @param name  is used to help me create a certain delete depending on it
*
* */
    public void deleteByName(String name) {
        PreparedStatement preparedStatement = null;
        String query = deleteQuery("firstName");
        Connection connection = null;

        try {
            connection = ConnectionFactory.createConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        }catch(SQLException e) {
            LOGGER.fine(type.getName() + "DAO:deleteByName" + e.getMessage());
        }
    }

    /**
* @param product  is used to help me create a certain delete query depending on it
*
* */
    public void deleteByNameProduct(String product) {
        PreparedStatement preparedStatement = null;
        String query = deleteQuery("name");
        Connection connection = null;

        try {
            connection = ConnectionFactory.createConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product);
            preparedStatement.executeUpdate();
        }catch(SQLException e) {
            LOGGER.fine(type.getName() + "DAO:deleteByName" + e.getMessage());
        }
    }
    /**
* @param id  is used to help me create a certain delect query depending on id
*
* */
    public void deleteByidClient(int id) {
        PreparedStatement preparedStatement = null;
        String query = deleteQuery("idClient");
        Connection connection = null;

        try {
            connection = ConnectionFactory.createConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch(SQLException e) {
            LOGGER.fine(type.getName() + "DAO:delete by id client: " + e.getMessage());
        }
    }
    /**
     * is used to help me create a certain delect query depending on id
     * @param id
     * */
    public void deleteByidProduct(int id) {
        PreparedStatement preparedStatement = null;
        String query = deleteQuery("idProd");
        Connection connection = null;
        try {
            connection = ConnectionFactory.createConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch(SQLException e) {
            LOGGER.fine(type.getName() + "DAO:delete by id produs" + e.getMessage());
        }
    }
    /**
     * @param quant
     * @param name is used to help me create a certain update query depending on @name
     *
     * */
    public void updateQuantity(int quant, String name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = updateQuery("quantity", "name");
        try {
            connection = ConnectionFactory.createConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, quant);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
        }catch(SQLException e) {
            LOGGER.fine(type.getName() + "DAO:deleteByName" + e.getMessage());
        }
    }
    /**
     * @param price
     * @param name is used to help me create a certain update query depending on @name
     *
     * */
    public void updatePrice(float price, String name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = updateQuery("price", "name");
        try {
            connection = ConnectionFactory.createConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setFloat(1, price);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
        }catch(SQLException e) {
            LOGGER.fine(type.getName() + "DAO:deleteByName" + e.getMessage());
        }
    }
    /**
     * @param field  is used to help me create a certain delete query depending on it
     *
     * */
    private String deleteQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("delete");
        sb.append(" from ");
        if(type.getSimpleName().equals("ClientDao") || type.getSimpleName().equals("Client") )
            sb.append("Client");
        if(type.getSimpleName().equals("OrderDao") || type.getSimpleName().equals("Order") )
            sb.append("Order");
        if(type.getSimpleName().equals("BillDao")|| type.getSimpleName().equals("Bill") )
            sb.append("Bill");
        if(type.getSimpleName().equals("ProductDao")|| type.getSimpleName().equals("Product") )
            sb.append("Product");
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    private List<T> createObjects(ResultSet rs){
        List<T> list = new ArrayList<T>();

        try {
            while(rs.next()) {
                T instance = type.newInstance();
                for(Field field : type.getDeclaredFields()) {
                    Object value = rs.getObject(field.getName());
                    PropertyDescriptor pd = new PropertyDescriptor(field.getName(), type);
                    Method method = pd.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        }catch(InstantiationException e) {
            e.getStackTrace();
        } catch (java.beans.IntrospectionException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return list;
    }
}


