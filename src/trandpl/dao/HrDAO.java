/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trandpl.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import trandpl.dbutil.DBConnection;
import trandpl.pojo.HrPojo;


public class HrDAO {
    
    public static int getNewHrId()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select max(hrid) from hr");
        int hrId=101;
        rs.next();
        String strId=rs.getString(1);
        if(strId!=null)
        {
            hrId=Integer.parseInt(strId.substring(3))+1;
        }
        return hrId;
    }
    public static int addNewHr(HrPojo obj)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select userid from users where userid=?");
        ps.setString(1,obj.getUserId());
        ResultSet rs=ps.executeQuery();
        if(rs.next())
            return -1;
        ps=conn.prepareStatement("insert into users values(?,?,?,?,?,?)");
        ps.setString(1,obj.getUserId());
        ps.setString(2,obj.getHrId());
        ps.setString(3,obj.getHrName());
        ps.setString(4,obj.getPassword());
        ps.setString(5,obj.getType());
        ps.setString(6,"Y");
        
        int x=ps.executeUpdate();
        int y=0;
        if(x==1)
        {
           ps=conn.prepareStatement("insert into hr values(?,?,?,?)");
           ps.setString(1,obj.getHrId());
           ps.setString(2,obj.getMobile());
           ps.setString(3,obj.getCompanyName());
           ps.setString(4,obj.getProfession());
           
           y=ps.executeUpdate();
        }
        return y;
    }
    public static List<HrPojo> getAllHr()throws SQLException
    {
       Connection conn=DBConnection.getConnection();
       Statement st=conn.createStatement();
       ResultSet rs=st.executeQuery("select hr.hrid,users.userid,users.name,hr.phone,hr.companyname from users,hr where hr.hrid=users.id and users.active='Y' order by hr.hrid");
       List<HrPojo>HrList=new ArrayList<>();
       while(rs.next())
       {
           HrPojo obj=new HrPojo();
           obj.setHrId(rs.getString(1));
           obj.setUserId(rs.getString(2));
           obj.setHrName(rs.getString(3));
           obj.setMobile(rs.getString(4));
           obj.setCompanyName(rs.getString(5));
           
           HrList.add(obj);
       }
       return HrList;
    }
     public static String getCompanyNameById(String hrId)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select companyname from hr where hrid=?");
        ps.setString(1,hrId);
        ResultSet rs=ps.executeQuery();
        rs.next();
        return rs.getString(1);
    }
}
