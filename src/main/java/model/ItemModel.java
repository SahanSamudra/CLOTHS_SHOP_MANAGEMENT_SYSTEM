package model;

public class ItemModel {

    /*private static String sql;
    private String ItemID;


    public static boolean save(Item item) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO item (Iid,type,price,qty,size,supplier)VALUES(?,?,?,?,?,?) ";
        return CrudUtil.execute(sql,item.getId(),item.getType(),item.getPrice(),item.getQty(),item.getSize(),item.getSupplier());

    }

    public static Item search(String id) throws SQLException, ClassNotFoundException {
        sql="SELECT * FROM item WHERE Iid = ?";
        ResultSet resultSet = CrudUtil.execute(sql, id);

        if (resultSet.next()) {
            String Iid=resultSet.getString(1);
            String  type=resultSet.getString(2);
            String price = resultSet.getString(3);
            String qty = resultSet.getString(4);
            String size=resultSet.getString(5);
            String supplier=resultSet.getString(6);

            return new Item(Iid,type,price,qty,size,supplier);
        }
        return null;

    }

    public static boolean update(Item item) throws SQLException, ClassNotFoundException {
        String sql ="UPDATE item SET Iid=?,type=?,price=?,qty=?,size=?,supplier=?";
        return  CrudUtil.execute(sql,item.getId(),item.getType(),item.getPrice(),item.getQty(),item.getSize(),item.getSupplier());

    }

    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM customer WHERE Iid = ?";
        return CrudUtil.execute(sql,id);
    }
    public static Customer getAllItemsCode() throws SQLException, ClassNotFoundException {
        String sql = "SELECT Iid FROM item";
        ResultSet resultSet = CrudUtil.execute(sql);
        return resultSet;
    }
    public static ResultSet getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM item";
        ResultSet resultSet = CrudUtil.execute(sql);
        return resultSet;
    }*/
}
