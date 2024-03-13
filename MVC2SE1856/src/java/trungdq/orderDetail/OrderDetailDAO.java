/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungdq.orderDetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import trungdq.util.DBHelper;

/**
 *
 * @author trung
 */
public class OrderDetailDAO implements Serializable {

    /*public boolean insertOrderDetail(String name, float price, int quantity, float total, String productid, String tOrderid)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "Insert Into OrderDetail("
                        + "name, price, quantity, total, productid, tOrderid"
                        + ") Values("
                        + "?, ?, ?, ?, ?, ?"
                        + ")";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                stm.setFloat(2, price);
                stm.setInt(3, quantity);
                stm.setFloat(4, total);
                stm.setString(5, productid);
                stm.setString(6, tOrderid);
                //4. Execute Query
                int effectRows = stm.executeUpdate(); // neu insert delete update thi la executeUpadate
                //5. Process result
                if (effectRows > 0) {
                    result = true;
                }
            }//connection has been available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }*/
    
    public boolean insertOrderDetail(OrderDetailDTO detailDTO)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "Insert Into OrderDetail("
                        + "name, price, quantity, total, productid, tOrderid"
                        + ") Values("
                        + "?, ?, ?, ?, ?, ?"
                        + ")";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, detailDTO.getName());
                stm.setFloat(2, detailDTO.getUnitPrice());
                stm.setInt(3, detailDTO.getQuantity());
                stm.setFloat(4, detailDTO.getTotal());
                stm.setString(5, detailDTO.getTbl_ProductDTO().getId());
                stm.setString(6, detailDTO.gettOrderDTO().getId());
                //4. Execute Query
                int effectRows = stm.executeUpdate(); // neu insert delete update thi la executeUpadate
                //5. Process result
                if (effectRows > 0) {
                    result = true;
                }
            }//connection has been available
        } finally {
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
