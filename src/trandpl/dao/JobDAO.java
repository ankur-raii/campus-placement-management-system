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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import trandpl.dbutil.DBConnection;
import trandpl.pojo.JobPojo;
import trandpl.pojo.ParticipantJobPojo;

/**
 *
 * @author hmayw
 */
public class JobDAO {
    public static int getJobId()throws SQLException 
    {
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select max(jobid) from jobs");
        int hrId=101;
        rs.next();
        String id=rs.getString(1);
        if(id!=null)
            hrId=Integer.parseInt(rs.getString(1).substring(4))+1;
        return hrId;
    }
    public static boolean addNewJob(JobPojo obj)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into jobs values(?,?,?,?,?)");
        ps.setString(1,obj.getJobId());
        ps.setString(2,obj.getJobTitle());
        ps.setString(3,obj.getHrId());
        ps.setString(4,obj.getTags());
        ps.setInt(5,obj.getStatus());
        
        return 1==ps.executeUpdate();
    }
    public static List<JobPojo> getActiveJobs(String hrId)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select jobid,jobtitle,tags,status from jobs where hrid=? and status!=-1");
        ps.setString(1, hrId);
        ResultSet rs=ps.executeQuery();
        List<JobPojo>jobs=new ArrayList<>();
        JobPojo obj=null;
        while(rs.next())
        {
            obj=new JobPojo();
            obj.setJobId(rs.getString(1));
            obj.setJobTitle(rs.getString(2));
            obj.setTags(rs.getString(3));
            obj.setStatus(rs.getInt(4));
            jobs.add(obj);
        }
        return jobs;
    }
    public static boolean removeJob(String jobId)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update jobs set status=-1 where jobid=?");
        ps.setString(1,jobId);
        return 1==ps.executeUpdate();
    }
    public static List<JobPojo> getAllEditableJob(String hrId)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select jobid,jobtitle,tags,status from jobs where hrid=? and status=0");
        ps.setString(1, hrId);
        ResultSet rs=ps.executeQuery();
        List<JobPojo>jobs=new ArrayList<>();
        JobPojo obj=null;
        while(rs.next())
        {
            obj=new JobPojo();
            obj.setJobId(rs.getString(1));
            obj.setJobTitle(rs.getString(2));
            obj.setTags(rs.getString(3));
            obj.setStatus(rs.getInt(4));
            jobs.add(obj);
        }
        return jobs;
    }
    public static boolean modifyJob(JobPojo job)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update jobs set jobtitle=?,tags=? where jobid=?");
        ps.setString(1,job.getJobTitle());
        ps.setString(2,job.getTags());
        ps.setString(3,job.getJobId());
        return 1==ps.executeUpdate();
    }
    public static List<JobPojo> getAllJobsForEditingQuiz(String hrId)throws SQLException
    {
          Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select jobid,jobtitle,tags,status from jobs where hrid=? and status=1");
        ps.setString(1, hrId);
        ResultSet rs=ps.executeQuery();
        List<JobPojo>jobs=new ArrayList<>();
        JobPojo obj=null;
        while(rs.next())
        {
            obj=new JobPojo();
            obj.setJobId(rs.getString(1));
            obj.setJobTitle(rs.getString(2));
            obj.setTags(rs.getString(3));
            obj.setStatus(rs.getInt(4));
            jobs.add(obj);
        }
        return jobs;
    }
    public static List<ParticipantJobPojo> getAllOpenJobs()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select jobid,jobtitle,tags,jobs.hrid,companyname from jobs,hr where hr.hrid=jobs.hrid and status=1");
        List<ParticipantJobPojo>jobs=new ArrayList<>();
        ParticipantJobPojo obj=null;
        while(rs.next())
        {
            obj=new ParticipantJobPojo();
            obj.setJobId(rs.getString(1));
            obj.setJobTitle(rs.getString(2));
            obj.setTags(rs.getString(3));
            obj.setHrId(rs.getString(4));
            obj.setCompanyName(rs.getString(5));
            jobs.add(obj);
        }
        return jobs; 
    }
}
