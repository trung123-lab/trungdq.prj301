/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungdq.orderDetail;

import java.io.Serializable;
import trungdq.tOrder.TOrderDTO;
import trungdq.tbl_Product.Tbl_ProductDTO;

/**
 *
 * @author trung
 */
public class OrderDetailDTO implements Serializable{
   private int id;
   private String name;
   private float unitPrice;
   private int quantity;
   private float total;
   private TOrderDTO tOrderDTO;
   private Tbl_ProductDTO tbl_ProductDTO;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO( String name, float unitPrice, int quantity, float total, TOrderDTO tOrderDTO, Tbl_ProductDTO tbl_ProductDTO) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.total = total;
        this.tOrderDTO = tOrderDTO;
        this.tbl_ProductDTO = tbl_ProductDTO;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the unitPrice
     */
    public float getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the total
     */
    public float getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(float total) {
        this.total = total;
    }

    /**
     * @return the tOrderDTO
     */
    public TOrderDTO gettOrderDTO() {
        return tOrderDTO;
    }

    /**
     * @param tOrderDTO the tOrderDTO to set
     */
    public void settOrderDTO(TOrderDTO tOrderDTO) {
        this.tOrderDTO = tOrderDTO;
    }

    /**
     * @return the tbl_ProductDTO
     */
    public Tbl_ProductDTO getTbl_ProductDTO() {
        return tbl_ProductDTO;
    }

    /**
     * @param tbl_ProductDTO the tbl_ProductDTO to set
     */
    public void setTbl_ProductDTO(Tbl_ProductDTO tbl_ProductDTO) {
        this.tbl_ProductDTO = tbl_ProductDTO;
    }

    
    
}
