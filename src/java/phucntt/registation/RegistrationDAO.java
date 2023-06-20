/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucntt.registation;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import phucntt.util.DBHelper;

/**
 *
 * @author nguye
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws SQLException, NamingException {
        // Tao connection
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Boolean result = false;
        try {
            con = DBHelper.makeConnection();
            
                // Tao cau lenh SQL
                if (con != null) {
                    //SELECT fullName, roleID FROM tblUsers WHERE userID=? AND password=? 
                String sql = "SELECT userID, password "
                        + "From tblUsers "
                        + "Where userID=? AND password=?";
                // Tao statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                // Excute Query()
                rs = stm.executeQuery();
                if(rs.next()){
                    result = true;
                }// Chi tra ve 1 dong la vi username la khoa chinh
                // Xu li ket qua
                
            }// Check connection
        } 
        catch(Exception e){
            System.out.println(e);
        }
        finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    private List<RegistrationDTO> accountList;

    public List<RegistrationDTO> getAccountList() {
        return accountList;
    }
    public void searchLastname(String searchValue) throws SQLException{
         // Tao connection
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Boolean result = true;
        try {
            con = DBHelper.makeConnection();
                // Tao cau lenh SQL
                if (con != null) {
                String sql = "Select userID, password, fullName, roleID "
                        + "From tblUsers "
                        + "Where fullName Like ?";
                // Tao statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                // Excute Query()
                rs = stm.executeQuery();
                
                // Xu li ket qua
                while(rs.next()){
                    String userName = rs.getString("userID");
                    String password = rs.getString("password");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    
                    RegistrationDTO dto = new RegistrationDTO(userName, fullName, password, roleID);
                    if(this.accountList == null){
                        this.accountList = new ArrayList<>();
                    }
                    this.accountList.add(dto);
                }
                
            }// Check connection
        } 
        catch(Exception e){
            System.out.println(e);
        }
        finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        //return result;
    }
    
    public boolean deleteAccount(String userID) throws SQLException{
        // Tao connection
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Boolean result = false;
        try {
            con = DBHelper.makeConnection();
            
                // Tao cau lenh SQL
                if (con != null) {
                   String sql = "Delete From tblUsers "
                           + "Where userID=?";
                // Tao statement object
                stm = con.prepareStatement(sql);
                stm.setString(1,userID);
                // Excute Query()
                int effectRow = stm.executeUpdate();
                if(effectRow > 0){
                    result = true;
                }// Chi tra ve 1 dong la vi username la khoa chinh
                // Xu li ket qua
                
            }// Check connection
        } 
        catch(Exception e){
            System.out.println(e);
        }
        finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
     public boolean updateAccount(String userID, String password, String isChk) throws SQLException{
        // Tao connection
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Boolean result = false;
        try {
            con = DBHelper.makeConnection();
            
                // Tao cau lenh SQL
                if (con != null) {
                    String sql = "UPDATE tblUsers SET password=?, roleID=? WHERE userID=?";
                   //String sql = "Update From tblUsers "
                        //   + "Where userID=?";
                // Tao statement object
                stm = con.prepareStatement(sql);
                stm.setString(1,password);
                stm.setString(2, isChk);
                stm.setString(3, userID);
                // Excute Query()
                int effectRow = stm.executeUpdate();
                if(effectRow > 0){
                    result = true;
                    
                }// Chi tra ve 1 dong la vi username la khoa chinh
                
                // Xu li ket qua
                
            }// Check connection
        } 
        catch(Exception e){
            System.out.println(e);
        }
        finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
}
