/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungdq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import trungdq.util.ApplicationConstants;

/**
 *
 * @author trung
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {

//    //private final String LOGIN_PAGE = "";
//    //private final String LOGIN_CONTROLLER = "LoginServlet";
//    //private final String LOGIN_CONTROLLER = "loginController";
//    //private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameServlet";
//    //private final String SEARCH_LASTNAME_CONTROLLER = "searchController";
//    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
//    private final String STARTUP_CONTROLLER = "StartUpServlet";
//    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
//    private final String ADD_TO_CART_CONTROLLER = "AddToCartServlet";
//    private final String VIEW_CART_PAGE = "viewCart.jsp";
//    private final String REMOVE_FROM_CART_CONTROLLER = "RemoveFromCartServlet";
//    private final String CREATE_ACCOUNT_CONTROLLER = "CreateAccountServlet";
//    private final String LOAD_DATA_CONTROLLER = "LoadDataServlet";

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
        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
        //1. Which button did user click ?
        String button = request.getParameter("btAction");
        //String url = LOGIN_PAGE;
        //String url = siteMaps.getProperty(LOGIN_PAGE);
        String url = siteMaps.getProperty(
                ApplicationConstants.DispatchFeature.LOGIN_PAGE);
        
        try {
            if (button == null) {//first time or startup app
                //transfer to Login page
                //check cookies to determine which page is transfered
                //url = STARTUP_CONTROLLER;
                url = siteMaps.getProperty(
                        ApplicationConstants.DispatchFeature.STARTUP_CONTROLLER);
            } else if (button.equals("Login")) {//user clicked Login
                //url = LOGIN_CONTROLLER;
                //url = siteMaps.getProperty(LOGIN_CONTROLLER);
                url = siteMaps.getProperty(
                        ApplicationConstants.DispatchFeature.LOGIN_CONTROLLER);
            } else if (button.equals("Search")) {//user clicked Search
                //url = SEARCH_LASTNAME_CONTROLLER;
                //url = siteMaps.getProperty(SEARCH_LASTNAME_CONTROLLER);
                url = siteMaps.getProperty(
                        ApplicationConstants.DispatchFeature.SEARCH_LASTNAME_CONTROLLER);
            } else if (button.equals("delete")) {//user clicked delete
                //url = DELETE_ACCOUNT_CONTROLLER;
                url = siteMaps.getProperty(
                        ApplicationConstants.DispatchFeature.DELETE_ACCOUNT_CONTROLLER);
            } else if (button.equals("Update")) {//user clicked delete
                //url = UPDATE_ACCOUNT_CONTROLLER;
                url = siteMaps.getProperty(
                        ApplicationConstants.DispatchFeature.UPDATE_ACCOUNT_CONTROLLER);
            } else if (button.equals("Add Book to Your Cart")) {//user clicked add to cart
                //url = ADD_TO_CART_CONTROLLER;
                url = siteMaps.getProperty(
                        ApplicationConstants.DispatchFeature.ADD_TO_CART_CONTROLLER);
            } else if (button.equals("View Your Cart")) {//user clicked view cart
                //url = VIEW_CART_PAGE;
                url = siteMaps.getProperty(
                        ApplicationConstants.DispatchFeature.VIEW_CART_PAGE);
            } else if (button.equals("Remove Selected Items")) {//user clicked view cart
                //url = REMOVE_FROM_CART_CONTROLLER;
                url = siteMaps.getProperty(
                        ApplicationConstants.DispatchFeature.REMOVE_FROM_CART_CONTROLLER);
            } else if (button.equals("Create New Account")) {//user clicked view cart
                //url = CREATE_ACCOUNT_CONTROLLER;
                url = siteMaps.getProperty(
                        ApplicationConstants.DispatchFeature.CREATE_ACCOUNT_CONTROLLER);
            } else if (button.equals("Go To Shopping")) {
                //url = LOAD_DATA_CONTROLLER;
                url = siteMaps.getProperty(
                        ApplicationConstants.DispatchFeature.LOAD_DATA_CONTROLLER);
            } else if (button.equals("CheckOut")) {
                url = siteMaps.getProperty(
                        ApplicationConstants.DispatchFeature.CHECK_OUT_CONTROLLER);
            } else if (button.equals("LogOut")) {
                url = siteMaps.getProperty(
                        ApplicationConstants.DispatchFeature.LOGOUT_CONTROLLER);
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
