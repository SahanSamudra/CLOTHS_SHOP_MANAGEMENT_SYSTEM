package controller;

import DB.DbConnection;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerSaveController implements CustomerDetails {

    public List<String> getCusIDs() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer");
        ResultSet rst = stm.executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(rst.getString(1));
        }
        return ids;
    }

    @Override
    public boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "insert into Customer values(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setObject(1, customer.getCustomerId());
        preparedStatement.setObject(2, customer.getCustomerName());
        preparedStatement.setObject(3, customer.getNoOfContact());
        preparedStatement.setObject(4, customer.getCustomerAddress());
        return preparedStatement.executeUpdate() > 0;
    }

    public ArrayList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer");
        ResultSet rst = stm.executeQuery();
        ArrayList<Customer> customers = new ArrayList<>();
        while (rst.next()) {
            customers.add(new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4)));

        }
        return customers;
    }


    public boolean updateCustomer(Customer C) throws SQLException, ClassNotFoundException {

        PreparedStatement Stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Customer SET   name=?, contact=?, address=? WHERE cid=? ");

        Stm.setObject(1, C.getCustomerName());
        Stm.setObject(2, C.getCustomerAddress());
        Stm.setObject(3, C.getNoOfContact());
        Stm.setObject(4, C.getCustomerId());


        return Stm.executeUpdate() > 0;
    }

    public boolean deleteCustomer(String cid) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Customer WHERE cid='" + cid + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Customer getCustomer(String cid) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer WHERE cid=?");
        preparedStatement.setObject(1, cid);
        ResultSet rst = preparedStatement.executeQuery();
        if (rst.next()) {
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)

            );

        } else {
            return null;
        }
    }
}
