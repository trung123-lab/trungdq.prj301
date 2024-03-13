/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungdq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trungdq.account.AccountDAO;
import trungdq.account.AccountDTO;
import trungdq.util.ApplicationConstants;
import trungdq.util.DBHelper;

/**
 *
 * @author trung
 */
public class LoginServlet extends HttpServlet {

    //private final String INVALID_PAGE = "invalid.html";
    //private final String INVALID_PAGE = "invalidPage";
    //private final String SEARCH_PAGE = "search.jsp";
    //private final String SEARCH_PAGE = "homePage";

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
        PrintWriter out = response.getWriter();
         //0. get context scope & get siteMaps attribute
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
        //1. get all client information
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String url = siteMaps.getProperty(ApplicationConstants.LoginFeature.INVALID_PAGE);

        try {
            //2.Call Model
            //2.1 New DAO Object
            AccountDAO dao = new AccountDAO();
            //2.2 Call method of DAO
            //boolean result = dao.checkLogin(username, password);
            AccountDTO result = dao.checkLogin(username, password);
            //3. Process result
            if (result != null) {
                url = siteMaps.getProperty(ApplicationConstants.LoginFeature.SEARCH_PAGE);
                HttpSession session = request.getSession();
                session.setAttribute("USER", result);
                //write cookie 
//                Cookie cookie = new Cookie(username, password);
//                cookie.setMaxAge(60 * 1);
//                response.addCookie(cookie);
            }
        } catch (SQLException ex) {
            log("LoginServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("LoginServlet_Naming: " + ex.getMessage());
        } finally {
//            response.sendRedirect(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
