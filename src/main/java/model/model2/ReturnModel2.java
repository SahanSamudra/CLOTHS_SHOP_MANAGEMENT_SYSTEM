package model.model2;

import DB.DbConnection;
import model.Item;
import model.ReturnItem;
import tm.ReturnItemTm;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReturnModel2 {
    public static boolean addData(ReturnItem returnItem) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO returnItem VALUES(?,?,?,?,?)",returnItem.getId(),returnItem.getQty(),
                returnItem.getSize(),returnItem.getItemId(),returnItem.getDate());
    }


    public static boolean updateData(ReturnItem returnItem) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE returnItem set qty=? , size_=? , item_id = ? , date = ? where id = ?",returnItem.getQty()
        ,returnItem.getSize(),returnItem.getItemId(),returnItem.getDate(),returnItem.getId());
    }

    public static boolean deleteRecord(ReturnItem returnItem) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM returnItem where id = ?",returnItem.getId());
    }

    public static ArrayList<ReturnItem> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM returnItem");
        ArrayList<ReturnItem> list = new ArrayList<>();
        while (rs.next()){
            list.add(new ReturnItem(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),
                    rs.getDate(5)));
        }

        return list;
    }

    public static boolean addData(ReturnItem returnItem, Item item) throws SQLException, ClassNotFoundException {
        DbConnection.getInstance().getConnection().setAutoCommit(false);
        try{
            boolean execute = addData(returnItem);
            if(execute){
                boolean b = ItemModel2.changeQty(item);
                if(b){
                    DbConnection.getInstance().getConnection().commit();
                    return true;
                }
            }
            DbConnection.getInstance().getConnection().rollback();
        }catch (Exception e){
            DbConnection.getInstance().getConnection().rollback();
            throw e;
        }finally {
            DbConnection.getInstance().getConnection().setAutoCommit(true);
        }
        return false;
    }

    public static ArrayList<ReturnItemTm> getAllForTable() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT ri.id , ri.qty , ri.size_ , ri.item_id , ri.date , i.price from " +
                "returnItem ri inner join item i on ri.item_id = i.Iid");
        ArrayList<ReturnItemTm> list = new ArrayList<>();
        while (rs.next()){
            list.add(new ReturnItemTm(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),
                    rs.getDate(5),rs.getDouble(6)));
        }

        return list;
    }
}
