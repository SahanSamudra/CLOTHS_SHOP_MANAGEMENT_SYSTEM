package controller;

import DB.DbConnection;
import model.Customer;
import model.Item;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemSaveController {

    public List<String> getItemIds() throws SQLException, ClassNotFoundException {
        PreparedStatement stm=DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item");
        ResultSet rst=stm.executeQuery();
        List<String> ids=new ArrayList<>();
        while (rst.next()){
            ids.add(rst.getString(1));
        }
        return ids;
    }

    public boolean saveItem(Item M) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT into Item VALUES (?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
//        PreparedStatement Stm= DbConnection.getInstance().getConnection().prepareStatement("INSERT into Item VALUES (?,?,?,?,?,?,?)");
        System.out.println(M.getIid());
        stm.setObject(1,M.getIid());
        stm.setObject(2,M.getType());
        stm.setObject(3,M.getPrice());
        stm.setObject(4,M.getQty());
        stm.setObject(5,M.getSize());
        stm.setObject(6,M.getSupplier());
        stm.setObject(7,M.getTcost());
        return stm.executeUpdate()>0;
    }
    public ArrayList<Item> getAllItem() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item");
        ResultSet rst = stm.executeQuery();
        ArrayList<Item> items = new ArrayList<>();
        while (rst.next()) {
            items.add(new Item(rst.getString(1), rst.getString(2), rst.getDouble(3),
                    rst.getInt(4),rst.getString(5),rst.getString(6),rst.getDouble(7)));

        }
        return items;
    }

    public boolean updateItems(Item M) throws SQLException, ClassNotFoundException {

        PreparedStatement Stm= DbConnection.getInstance().getConnection().prepareStatement("UPDATE Item SET   type=?, price=?, qty=?, size=?, supplier=?, tcost=? WHERE Iid=? ");


        Stm.setObject(1,M.getType());
        Stm.setObject(2,M.getPrice());
        Stm.setObject(3,M.getQty());
        Stm.setObject(4,M.getSize());
        Stm.setObject(5,M.getSupplier());
        Stm.setObject(6,M.getTcost());
        Stm.setObject(7,M.getIid());


        return Stm.executeUpdate()>0;
    }

    public boolean deleteItems(String Iid) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Item WHERE Iid='"+Iid+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }
    public Item getItems(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item WHERE Iid=?");
        preparedStatement.setObject(1, id);
        ResultSet rst = preparedStatement.executeQuery();
        if (rst.next()) {
            return new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getInt(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getDouble(7)
            );

        } else {
            return null;
        }
    }
}
