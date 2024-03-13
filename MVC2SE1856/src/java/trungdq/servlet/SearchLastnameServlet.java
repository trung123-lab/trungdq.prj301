/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungdq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import trungdq.account.AccountDAO;
import trungdq.account.AccountDTO;
import trungdq.util.ApplicationConstants;

/**
 *
 * @author trung
 */
@WebServlet(name = "SearchLastnameServlet", urlPatterns = {"/SearchLastnameServlet"})
public class SearchLastnameServlet extends HttpServlet {

    //private final String SEARCH_PAGE = "homePage";
    //private final String SEARCH_PAGE = "search.jsp";
    //private final String RESULT_PAGE = "homePage";

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
        //1.get all client information
        String searchValue = request.getParameter("txtSearchValue");
        String url = siteMaps.getProperty(
                ApplicationConstants.SearchLastNameFeature.SEARCH_PAGE);

        try {
            if (!searchValue.trim().isEmpty()) {
                //2. call Model
                //2.1 New DAO Object
                AccountDAO dao = new AccountDAO();
                //2.2 Call method of DAO
                dao.searchLastname(searchValue);
                List<AccountDTO> result = dao.getAccounts();
                //3. process Result
                url = siteMaps.getProperty(
                        ApplicationConstants.SearchLastNameFeature.RESULT_PAGE);
                request.setAttribute("SEARCH_RESULT", result);
            }// search when value is valid

        } catch (SQLException ex) {
            log("SearchLastnameServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("SearchLastnameServlet_Naming: " + ex.getMessage());
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
