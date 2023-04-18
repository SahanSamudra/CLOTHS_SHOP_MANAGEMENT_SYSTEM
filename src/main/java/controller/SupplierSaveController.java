package controller;

import DB.DbConnection;

import model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierSaveController implements SupplierDetails {

    public List<String> getSupplierIDs() throws SQLException, ClassNotFoundException {
        PreparedStatement stm=DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Supplier");
        ResultSet rst=stm.executeQuery();
        List<String> ids=new ArrayList<>();
        while (rst.next()){
            ids.add(rst.getString(1));
        }
        return ids;
    }

    @Override
    public boolean saveSupplier(Supplier supplier) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO  Supplier VALUES (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setObject(1, supplier.getSid());
        preparedStatement.setObject(2, supplier.getSnic());
        preparedStatement.setObject(3, supplier.getName());

        preparedStatement.setObject(4, supplier.getContact());
        preparedStatement.setObject(5, supplier.getAddress());
        preparedStatement.setObject(6, supplier.getCompany());
        return preparedStatement.executeUpdate() > 0;
    }

    public ArrayList<Supplier> getAllSupplier() throws SQLException, ClassNotFoundException {
        PreparedStatement stm=DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Supplier");
        ResultSet rst=stm.executeQuery();
        ArrayList<Supplier>suppliers=new ArrayList<>();
        while (rst.next()){
            suppliers.add(new Supplier(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),
                    rst.getString(5),rst.getString(6)));
        }
        return suppliers;
    }
    public boolean updateSupplier(Supplier S) throws SQLException, ClassNotFoundException {

        PreparedStatement Stm= DbConnection.getInstance().getConnection().prepareStatement("UPDATE Supplier SET   snic=?, sname=?, scontact=?, saddress=?, scompany=? WHERE sid=? ");

        Stm.setObject(1,S.getSnic());
        Stm.setObject(2,S.getName());
        Stm.setObject(3,S.getContact());
        Stm.setObject(4,S.getAddress());
        Stm.setObject(5,S.getCompany());
        Stm.setObject(6,S.getSid());

        return Stm.executeUpdate()>0;
    }

    public boolean deleteSupplier(String sid) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Supplier WHERE sid='"+sid+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }
    public Supplier getSupplier(String sid) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Supplier WHERE sid=?");
        preparedStatement.setObject(1, sid);
        ResultSet rst = preparedStatement.executeQuery();
        if (rst.next()) {
            return new Supplier(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)

            );

        } else {
            return null;
        }
    }


}
