package model;

import DB.DbConnection;
import to.AttendanceTo;
import to.WorkingDayTo;
import util.CrudUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class WorkingDayModel {
    public static WorkingDayTo getWorkingDay(Date date) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * from working_day where date = ?",date);
        if(rs.next()){
            return new WorkingDayTo(rs.getString(1),rs.getDate(2));
        }
        return null;
    }

    public static boolean addWorkingDay(ArrayList<AttendanceTo> attendance) throws SQLException, ClassNotFoundException {
        if(attendance.size()<1)return false;
        DbConnection.getInstance().getConnection().setAutoCommit(false);
        try {
            boolean b = CrudUtil.execute("insert into working_day VALUES(?,?)", attendance.get(0).getDayId(), LocalDate.now());
            if(b){
                boolean b1 = AttendanceModel.addAttendance(attendance);
                if(b1){
                    DbConnection.getInstance().getConnection().commit();
                    return true;
                }
            }
            DbConnection.getInstance().getConnection().rollback();
            return false;
        }catch (SQLException e){
            DbConnection.getInstance().getConnection().rollback();
            throw e;
        }finally {
            DbConnection.getInstance().getConnection().setAutoCommit(true);
        }

    }

     public static String getNewId() throws SQLException, ClassNotFoundException {
        String lastCourseId=getLastId();
        if(lastCourseId==null){
            return "D-00000001";
        }else{
            String[] split=lastCourseId.split("[D][-]");
            int lastDigits=Integer.parseInt(split[1]);
            lastDigits++;
            String newCourseId=String.format("D-%08d", lastDigits);
            return newCourseId;
        }
    }

    private static String getLastId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT id from working_day order by id DESC limit 1");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }



}
