package model.model2;

import DB.DbConnection;
import model.Item;
import model.ReturnItem;
import tm.ReturnItemTm;
import util.CrudUtil;

import javax.swing.text.DateFormatter;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ReturnModel2 {
    public static boolean addData(ReturnItem returnItem) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO returnItem VALUES(?,?,?,?,?,?)",returnItem.getId(),returnItem.getQty(),
                returnItem.getSize(),returnItem.getItemId(),returnItem.getDate(),returnItem.getOrderId());
    }


    public static boolean updateData(ReturnItem returnItem) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE returnItem set qty=? , size_=? , item_id = ? , date = ? , o_id = ? where id = ?",returnItem.getQty()
        ,returnItem.getSize(),returnItem.getItemId(),returnItem.getDate(),returnItem.getOrderId(),returnItem.getId());
    }

    public static boolean deleteRecord(ReturnItem returnItem) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM returnItem where id = ?",returnItem.getId());
    }

    public static ArrayList<ReturnItem> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM returnItem");
        ArrayList<ReturnItem> list = new ArrayList<>();
        while (rs.next()){
            list.add(new ReturnItem(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),
                    rs.getDate(5),rs.getString(6)));
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
        ResultSet rs = CrudUtil.execute("SELECT ri.id , ri.qty , ri.size_ , ri.item_id , ri.date , i.price , o.odate" +
                " from returnItem ri inner join item i on ri.item_id = i.Iid inner join orders o on ri.o_id = o.oid");
        ArrayList<ReturnItemTm> list = new ArrayList<>();
        while (rs.next()){
            list.add(new ReturnItemTm(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),
                    rs.getDate(5),rs.getDouble(6), Date.valueOf(LocalDate.parse(rs.getString(7), DateTimeFormatter.ofPattern("dd-MM-yyyy")))));
        }

        return list;
    }

    //public static ArrayList<ReturnItem> getAllByOrderId(String orderId){
        //select i.Iid,i.type,i.price,i.qty,i.size,i.supplier,i.tcost from item i inner join item_reports ir on i.Iid = ir.itemcode where ir.oid = 'P-009'
    //}

    public static ArrayList<Item> getAllByOrderId(String orderId)throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("select i.Iid,i.type," +
                "i.price,i.qty,i.size,i.supplier,i.tcost from item i inner join item_reports ir on i.Iid = ir.itemcode " +
                "where ir.oid = '"+orderId+"'");
        ResultSet rst = stm.executeQuery();
        ArrayList<Item> items = new ArrayList<>();
        while (rst.next()) {
            items.add(new Item(rst.getString(1), rst.getString(2), rst.getDouble(3),
                    rst.getInt(4),rst.getString(5),rst.getString(6),rst.getDouble(7)));

        }
        return items;
    }
}
