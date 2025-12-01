/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trandpl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import trandpl.dbutil.DBConnection;
import trandpl.pojo.ParticipantJobPojo;
import trandpl.pojo.ParticipantResultPojo;
import trandpl.pojo.ResultsPojo;


public class ResultsDAO {
    
    public static boolean applyForJob(ResultsPojo obj)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select * from results where participantid=? and jobid=?");
        ps.setString(1,obj.getpId());
        ps.setString(2,obj.getjId());
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            return false;
        }
        ps=conn.prepareStatement("insert into results values(?,?,?,?)");
        ps.setString(1,obj.getpId());
        ps.setString(2,obj.getjId());
        ps.setDouble(3,obj.getPercentage());
        ps.setString(4,obj.getSelectedByHr());
        
        return 1==ps.executeUpdate();
    }
    public static List<ParticipantJobPojo> getAllAppliedJobs(String participantId)throws SQLException
    {
       Connection conn=DBConnection.getConnection();
       PreparedStatement ps=conn.prepareStatement("select jobs.jobid,jobtitle,companyname,tags from jobs,hr,results where jobs.jobid=results.jobid and jobs.hrid=hr.hrid and participantid=? and percentage=-1");
       ps.setString(1,participantId);
       
       ResultSet rs=ps.executeQuery();
       List<ParticipantJobPojo>jobs=new ArrayList<>();
       
       ParticipantJobPojo obj=null;
      while(rs.next())
      {
           obj=new ParticipantJobPojo();
           obj.setJobId(rs.getString(1));
           obj.setJobTitle(rs.getString(2));
           obj.setCompanyName(rs.getString(3));
           obj.setTags(rs.getString(4));
           
           jobs.add(obj);
      }
      return jobs;
    }
    public static boolean setResult(ResultsPojo rs)throws SQLException
    {
        PreparedStatement ps;
        ps=DBConnection.getConnection().prepareStatement("update results set percentage=? where participantid=? and jobid=?");
        ps.setDouble(1,rs.getPercentage());
        ps.setString(2,rs.getpId());
        ps.setString(3,rs.getjId());
        return 1==ps.executeUpdate();
    }
    public static List<ParticipantResultPojo> getCompletedJobs(String participantId)throws SQLException
    {
         Connection conn=DBConnection.getConnection();
         PreparedStatement ps=conn.prepareStatement("select jobs.jobid,jobs.jobtitle,hr.companyname,jobs.tags,results.percentage from results,jobs,hr where results.jobid=jobs.jobid and jobs.hrid=hr.hrid and results.percentage!=-1 and results.participantid=? order by jobid");
         ps.setString(1, participantId);
         ResultSet rs=ps.executeQuery();
         List<ParticipantResultPojo> jobs=new ArrayList<>();
         while(rs.next())
         {
             ParticipantResultPojo result=new ParticipantResultPojo();
             result.setJobId(rs.getString(1));
             result.setJobTitle(rs.getString(2));
             result.setCompanyName(rs.getString(3));
             result.setTags(rs.getString(4));
             result.setPercentage(rs.getDouble(5));
             
             jobs.add(result);
         }
         return jobs;
    }
}
