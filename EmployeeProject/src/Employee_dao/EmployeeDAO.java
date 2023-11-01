/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Employee_dao;

import EmployeeProj_pojo.Employee;
import EmployeeProject_DButil.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Shubham Rathore
 */
public class EmployeeDAO {
    public static boolean addEmployee(Employee e)throws SQLException
    {
        Connection conn=DBConnection.getConnetion();
        PreparedStatement ps=conn.prepareStatement("insert into employees values(?,?,?)");
        ps.setInt(1, e.getEmpNo());
        ps.setString(2,e.getEmpName());
        ps.setDouble(3, e.getEmpSalary());
        int result=ps.executeUpdate();
        return result==1;
    }
    
    public static Employee findEmployeeById(int empno)throws SQLException
    {
        Connection conn=DBConnection.getConnetion();
        PreparedStatement ps=conn.prepareStatement("select * from employees where eno=?");
        ps.setInt(1, empno);
        ResultSet rs=ps.executeQuery();
        Employee e=null;
        if(rs.next())
        {
            e=new Employee();
            e.setEmpNo(rs.getInt(1));
            e.setEmpName(rs.getString(2));
            e.setEmpSalary(rs.getDouble(3));
        }
        return e;         
    }
    
    public static ArrayList<Employee> getAllEmployee()throws SQLException
    {
        ArrayList<Employee> eList=new ArrayList<>();
        Connection conn=DBConnection.getConnetion();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select * from Employees");
        while(rs.next())
        {
            Employee e=new Employee();
             e.setEmpNo(rs.getInt(1));
            e.setEmpName(rs.getString(2));
            e.setEmpSalary(rs.getDouble(3));
            eList.add(e);
        }
        return eList;
    }
            
    public static boolean deleteEmp(int empNo)throws SQLException
    {
     Connection conn=DBConnection.getConnetion();
     PreparedStatement ps=conn.prepareStatement("delete from employees where eno=?");
     ps.setInt(1, empNo);
     int result=ps.executeUpdate();
     return result==1;
    }
    
    public static boolean UpdateEmp(Employee e)throws SQLException
    {
      Connection conn=DBConnection.getConnetion();
      PreparedStatement ps=conn.prepareStatement("update employees set empname=?,empsalary=? where eno=?");
      ps.setString(1, e.getEmpName());
      ps.setDouble(2, e.getEmpSalary());
      ps.setInt(3,e.getEmpNo());
      int result=ps.executeUpdate();
      return result==1;
    }

}
