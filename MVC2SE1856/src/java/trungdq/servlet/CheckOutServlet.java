/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungdq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trungdq.cart.CartObject;
import trungdq.orderDetail.OrderDetailDAO;
import trungdq.orderDetail.OrderDetailDTO;
import trungdq.tOrder.TOrderDAO;
import trungdq.tOrder.TOrderDTO;
import trungdq.tbl_Product.Tbl_ProductDAO;
import trungdq.tbl_Product.Tbl_ProductDTO;
import trungdq.util.ApplicationConstants;

/**
 *
 * @author trung
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //0. get context scope & get siteMaps attribute
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        String url = siteMaps.getProperty(ApplicationConstants.CheckOutFeature.ERROR_PAGE);
        try {
            //1. cust goes to the cart place 
            HttpSession session = request.getSession();
            //2. cust takes his/her cart
            CartObject cart = (CartObject) session.getAttribute("CART");
            //Check to see if the cart already has items
            if (cart != null) {
                if (!cart.getItems().isEmpty()) {
                    String username = request.getParameter("txtUsername");
                    float grandTotal = Float.parseFloat(request.getParameter("grandTotal"));
                    TOrderDAO dAO = new TOrderDAO();
                    TOrderDTO orderDTO = dAO.createTOrder(username, grandTotal);
                    if (orderDTO != null) {
                        session.setAttribute("USER_ORDER", orderDTO);
                        List<OrderDetailDTO> purchasedItems = new ArrayList<>();
                        for (Map.Entry<String, Tbl_ProductDTO> entry : cart.getItems().entrySet()) {
                            Tbl_ProductDTO productDTO = entry.getValue();
                            String name = productDTO.getName();
                            int quantity = productDTO.getQuantity();
                            float price = productDTO.getPrice();
                            float total = quantity * price;
                            OrderDetailDAO detailDAO = new OrderDetailDAO();
                            OrderDetailDTO detailDTO = new OrderDetailDTO(name, price, quantity, total, orderDTO, productDTO);
                            boolean result1 = detailDAO.insertOrderDetail(detailDTO);
                            if (result1) {
                                purchasedItems.add(detailDTO);
                                Tbl_ProductDAO productDAO = new Tbl_ProductDAO();
                                boolean result2 = productDAO.decreaseQuantity(productDTO.getId(), quantity);
                                if (result2) {
                                    url = siteMaps.getProperty(
                                            ApplicationConstants.CheckOutFeature.SHOW_ORDERDETAIL_PAGE);
                                }
                            }
                        }
                        session.setAttribute("PRODUCT_ITEM", purchasedItems);
                    }
                }
                cart.clear();
            }

        } catch (SQLException ex) {
            log("CheckOutServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("CheckOutServlet_Naming: " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
