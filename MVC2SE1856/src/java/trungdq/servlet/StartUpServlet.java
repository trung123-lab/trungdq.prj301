/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungdq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trungdq.account.AccountDAO;
import trungdq.account.AccountDTO;
import trungdq.util.ApplicationConstants;

/**
 *
 * @author trung
 */
@WebServlet(name = "StartUpServlet", urlPatterns = {"/StartUpServlet"})
public class StartUpServlet extends HttpServlet {

//    private final String LOGIN_PAGE = "login.html";
//    private final String SEARCH_PAGE = "welcome.jsp";
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
        String url = siteMaps.getProperty(ApplicationConstants.StartUpFeature.LOGIN_PAGE);//neu cookies khong ton tai thi chuyen sang trang login
        try {
            //1. check existed cookie
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                //2. get name and value (username and password)
                Cookie lastCookie = cookies[cookies.length - 1];
                String username = lastCookie.getName();
                String password = lastCookie.getValue();
                //3. check authentication of username and password (call DAO)
                AccountDAO dao = new AccountDAO();
//                boolean result = dao.checkLogin(username, password);
                AccountDTO result = dao.checkLogin(username, password);
                //4. process result
                if (result != null) {//neu checklogin thanh cong thi chuyen sang trang search dong
                    url = siteMaps.getProperty(ApplicationConstants.StartUpFeature.SEARCH_PAGE);
                }
            }//end no first time
        } catch (SQLException ex) {
            log("StartUpServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("StartUpServlet_Naming: " + ex.getMessage());
        } finally {
            response.sendRedirect(url); //xai gi cung duoc boi vi cookies ghi tren file nho 
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
