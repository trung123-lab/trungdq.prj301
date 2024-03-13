/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungdq.cart;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import trungdq.tbl_Product.Tbl_ProductDAO;
import trungdq.tbl_Product.Tbl_ProductDTO;

/**
 *
 * @author trung
 */
public class CartObject implements Serializable {

    private Map<String, Tbl_ProductDTO> items;

    public Map<String, Tbl_ProductDTO> getItems() {
        return items;
    }

    public boolean addItemToCart(String id) throws SQLException, NamingException {
        boolean result = false;

        // 1. Check valid id
        if (id == null || id.trim().isEmpty()) {
            return result;
        }

        // 2. Check existing items
        if (this.items == null) {
            this.items = new HashMap<>();
        }

        // 3. Check existing item
        Tbl_ProductDAO productDAO = new Tbl_ProductDAO();
        Tbl_ProductDTO productDTO = this.items.get(id);
        if (productDTO != null) {
            // Item exists, increase quantity
            int quantity = productDAO.getProductQuantityById(id);
            if (quantity > productDTO.getQuantity()) {
                productDTO.setQuantity(productDTO.getQuantity() + 1);
            }
        } else {
            // Item does not exist, create a new item and add it to the cart
            Tbl_ProductDTO newProductDTO = productDAO.getProductDetailsById(id);
            if (newProductDTO != null) {
                newProductDTO.setQuantity(1); // Set initial quantity to 1
                this.items.put(id, newProductDTO); // Update the map with the new item
            }
        }

        result = true;
        return result;
    }

    public boolean removeItemFromCart(String id) {
        boolean result = false;
        //1. check existed items 
        if (this.items != null) {
            //2. check existed item
            if (this.items.containsKey(id)) {
                //3. remove item from cart
                this.items.remove(id);
                if (this.items.isEmpty()) {//moi lan remove thi kiem tra xem items co con phan tu nao ko neu ko thi gan items = null
                    this.items = null;
                }
                result = true;
            }//end item has existed
        }//items have existe

        return result;
    }

    public void clear() {
        this.items.clear();
    }
}
