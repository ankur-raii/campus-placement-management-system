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
import trandpl.pojo.QuestionPojo;

/**
 *
 * @author hmayw
 */
public class QuestionDAO {
   public static boolean setQuestion (List<QuestionPojo> questions)throws SQLException 
   {
       Connection conn=DBConnection.getConnection();
       PreparedStatement ps=conn.prepareStatement("insert into questions values(?,?,?,?,?,?,?,?)");
       int count=0;
       for(QuestionPojo que:questions)
       {
           ps.setString(1,que.getJobId());
           ps.setInt(2,que.getqNo());
           ps.setString(3,que.getQuestion());
           ps.setString(4,que.getOption1());
           ps.setString(5,que.getOption2());
           ps.setString(6,que.getOption3());
           ps.setString(7,que.getOption4());
           ps.setInt(8,que.getCorrectOption());
           
           int result=ps.executeUpdate();
           if(result==1)
               count++;
       }
       if(count==questions.size())
           return true;
       else
           return false;
   }
   public static void setJobStatus(String jobId)throws SQLException
   {
       Connection conn=DBConnection.getConnection();
       PreparedStatement ps=conn.prepareStatement("update jobs set status=1 where jobid=?");
       ps.setString(1,jobId);
       ps.executeUpdate();
   }
   public static List<QuestionPojo> getPaperByJobId(String jobId)throws SQLException
   {
       Connection conn=DBConnection.getConnection();
       PreparedStatement ps=conn.prepareStatement("select * from questions where jobid =?");
       ps.setString(1,jobId);
       ResultSet rs=ps.executeQuery();
       List<QuestionPojo>questions=new ArrayList<>();
       while(rs.next()){
          QuestionPojo que=new QuestionPojo();
          que.setJobId(rs.getString(1));
          que.setqNo(rs.getInt(2));
          que.setQuestion(rs.getString(3));
          que.setOption1(rs.getString(4));
          que.setOption2(rs.getString(5));
          que.setOption3(rs.getString(6));
          que.setOption4(rs.getString(7));
          que.setCorrectOption(rs.getInt(8));
          questions.add(que);
       }
       return questions;
   }
   public static boolean updateQuestion(List<QuestionPojo>questions)throws SQLException
   {
       Connection conn=DBConnection.getConnection();
       PreparedStatement ps=conn.prepareStatement("update questions set question=?,option1=?,option2=?,option3=?,option4=?,correctoption=? where jobid=? and qno=?");
       int count=0;
       for(QuestionPojo que:questions)
       {
           ps.setString(1,que.getQuestion());
           ps.setString(2,que.getOption1());
           ps.setString(3,que.getOption2());
           ps.setString(4,que.getOption3());
           ps.setString(5,que.getOption4());
           ps.setInt(6,que.getCorrectOption());
           ps.setString(7,que.getJobId());
           ps.setInt(8,que.getqNo());
           
           int result=ps.executeUpdate();
           if(result==1)
               count++;
       }
       if(count==questions.size())
           return true;
       else
           return false;
   }
}
