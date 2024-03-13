/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungdq.tOrder;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import trungdq.util.DBHelper;
import java.sql.Date;
import java.sql.ResultSet;

/**
 *
 * @author trung
 */
public class TOrderDAO implements Serializable {

    private int counter = 0;

    public boolean insertOrder(TOrderDTO orderDTO)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "Insert Into tOrder("
                        + "id, date, name, total"
                        + ") Values("
                        + "?, ?, ?, ?"
                        + ")";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, orderDTO.getId());
                stm.setDate(2, orderDTO.getDate());
                stm.setString(3, orderDTO.getName());
                stm.setFloat(4, orderDTO.getTotal());
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

    public int getTOrderId()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "SELECT MAX(CAST(SUBSTRING(id, 2, LEN(id) - 1) AS INT)) AS max_id "
                        + "FROM tOrder "
                        + "WHERE id LIKE 'O%'";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);

                //4. Execute Query
                rs = stm.executeQuery(); // neu insert delete update thi la executeUpadate
                //5. Process result
                if (rs.next()) {
                    counter = rs.getInt("max_id");
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
        return counter;
    }

    public String generateOxxx()
            throws SQLException, NamingException {
        int counter1 = getTOrderId();
        counter1++;
        return "O" + String.format("%03d", counter1);
    }

    public TOrderDTO createTOrder(String username, float total)
            throws SQLException, NamingException {
        long currentTimeMillis = System.currentTimeMillis();
        Date nowDay = new Date(currentTimeMillis);
        String tOrderid = generateOxxx();
        TOrderDTO orderDTO = new TOrderDTO(tOrderid, nowDay, username, total);
        boolean result = insertOrder(orderDTO);
        if (result) {
            return orderDTO;
        }
        return null;
    }
}
