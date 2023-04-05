package controller;

import model.Customer;

import java.sql.SQLException;

public interface CustomerDetails {
    boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException;
}
