package controller;

import DB.DbConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderFormController {


    public String getOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst =
                DbConnection.getInstance().getConnection().
                        prepareStatement("SELECT oid FROM Orders ORDER BY oid DESC LIMIT 1"
                        ).executeQuery();
        if (rst.next()) {
            int tempId = Integer.
                    parseInt(rst.getString("oid").split("-")[1]);
            tempId = (tempId + 1);
            if (tempId < 9) {
                System.out.println(tempId);
                return "P-00" + tempId;
            } else if (tempId < 99) {
                return "P-0" + tempId;

            } else {
                return "P-" + tempId;
            }
        } else {
            return "P-001";
        }

    }

}
