/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trandpl.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import trandpl.dbutil.DBConnection;
import trandpl.pojo.ParticipantPojo;

public class ParticipantsDAO {
    
    public static int getNewParticipantId()throws SQLException 
    {
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select max(pid) from participants");
        int pId=101;
        rs.next();
        String strId=rs.getString(1);
        if(strId!=null)
        {
            pId=Integer.parseInt(strId.substring(3))+1;
        }
        return pId;
    }
    public static int addNewParticipant(ParticipantPojo obj)throws SQLException,FileNotFoundException 
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select userid from users where userid=?");
        ps.setString(1,obj.getUserId());
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
           return -1;   
        }
        ps=conn.prepareStatement("insert into users values(?,?,?,?,?,?)");
        ps.setString(1,obj.getUserId());
        ps.setString(2,obj.getId());
        ps.setString(3,obj.getName());
        ps.setString(4,obj.getPassword());
        ps.setString(5,obj.getType());
        ps.setString(6,"Y");
        
        int x=ps.executeUpdate();
        int y=0;
        if(x==1)
        {
            ps=conn.prepareStatement("insert into participants values(?,?,?,?,?)");
            ps.setString(1,obj.getId());
            ps.setString(2,obj.getPhone());
            ps.setString(3,obj.getSkills());
            ps.setString(4,obj.getQualification());
            File f=obj.getResume();
            InputStream fin=new FileInputStream(f.getPath());
            ps.setBlob(5, fin);
            
            y=ps.executeUpdate();
        }
        return y;
    }
    public static ParticipantPojo getParticipantDetails(String participantId)throws SQLException 
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select users.name,users.userid,participants.phone from users,participants where pid=?");
        ps.setString(1,participantId);
        ResultSet rs=ps.executeQuery();
        rs.next();
        ParticipantPojo participant=new ParticipantPojo();
        participant.setName(rs.getString(1));
        participant.setUserId(rs.getString(2));
        participant.setPhone(rs.getString(3));
        return participant;
    }
    public static boolean updateResume(String pId,File file)throws SQLException,FileNotFoundException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update participants set resume=? where pid=?");
        FileInputStream fobj=new FileInputStream(file);
        ps.setBlob(1,fobj);
        ps.setString(2,pId);
        return 1==ps.executeUpdate();
    }
    public static List<ParticipantPojo> getAllParticipantsDetails(String hrId)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select name,userid,skills,qualification from users,participants,jobs,results where users.id=participants.pid and participants.pid=results.participantid and results.jobid=jobs.jobid and jobs.hrid=?");
        ps.setString(1,hrId);
        ResultSet rs=ps.executeQuery();
        List<ParticipantPojo> ptList=new ArrayList<>();
        while(rs.next())
        {
            ParticipantPojo pt=new ParticipantPojo();
            pt.setName(rs.getString(1));
            pt.setUserId(rs.getString(2));
            pt.setSkills(rs.getString(3));
            pt.setQualification(rs.getString(4));
            
            ptList.add(pt);
        }
        return ptList;
    }
}
