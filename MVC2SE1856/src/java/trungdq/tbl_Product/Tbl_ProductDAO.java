/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungdq.tbl_Product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import trungdq.util.DBHelper;

/**
 *
 * @author trung
 */
public class Tbl_ProductDAO implements Serializable {

    public void addBook()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "select Sku, name, description, unit_price, quantity, status "
                        + "From tbl_Product "
                        + "where quantity > 0 and status = 1";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process result
                while (rs.next()) {
                    //5.1 get data from ResultSet
                    String productId = rs.getString("Sku");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    float price = rs.getFloat("unit_price");
                    int quantity = rs.getInt("quantity");
                    boolean role = rs.getBoolean("status");
                    //5.2 set data to DTO properties
                    Tbl_ProductDTO dto = new Tbl_ProductDTO(
                            productId, name, description, price, quantity, role);
                    if (this.products == null) {
                        this.products = new ArrayList<>();
                    }//end account have NOT existed
                    this.products.add(dto);
                }//end product has existed
            }//connection has been available
        } finally {
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
    }
    
    public boolean decreaseQuantity(String productId, int quantity)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "Update tbl_Product "
                        + "SET quantity = (select quantity "
                        + "From tbl_Product "
                        + "where Sku = ? ) - ? "
                        + "where Sku = ? ";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, productId);
                stm.setInt(2, quantity);
                stm.setString(3, productId);
                //4. Execute Query
                int effectRows = stm.executeUpdate();
                //5. Process result
                if(effectRows > 0) {
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

    public Tbl_ProductDTO getProductDetailsById(String id)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Tbl_ProductDTO result = null;
        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "select name, description, unit_price, status "
                        + "From tbl_Product "
                        + "Where Sku = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process result
                if (rs.next()) {
                    //5.1 get data from ResultSet
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    float price = rs.getFloat("unit_price");
                    boolean role = rs.getBoolean("status");
                    //5.2 set data to DTO properties
                    result = new Tbl_ProductDTO(
                            id, name, description, price, 0, role);
                    //end account have NOT existed
                }//end product has existed
            }//connection has been available
        } finally {
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

    
    public int getProductQuantityById(String id)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int quantity = 0;
        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "select quantity "
                        + "From tbl_Product "
                        + "Where Sku = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process result
                if (rs.next()) {
                    //5.1 get data from ResultSet
                    quantity = rs.getInt("quantity");
                    //end account have NOT existed
                }//end product has existed
            }//connection has been available
        } finally {
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
        return quantity;
    }
    
    private List<Tbl_ProductDTO> products;

    public List<Tbl_ProductDTO> getProducts() {
        return products;
    }

}
