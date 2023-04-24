package model.model2;

import DB.DbConnection;
import model.Item;
import util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel2 {
    public static ArrayList<Item> getAllItem() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item");
        ResultSet rst = stm.executeQuery();
        ArrayList<Item> items = new ArrayList<>();
        while (rst.next()) {
            items.add(new Item(rst.getString(1), rst.getString(2), rst.getDouble(3),
                    rst.getInt(4),rst.getString(5),rst.getString(6),rst.getDouble(7)));

        }
        return items;
    }

    public static boolean changeQty(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE item set qty = ? where Iid = ?",item.getQty(),item.getIid());
    }
}
