/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucntt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import phucntt.registation.RegistrationDAO;

/**
 *
 * @author nguye
 */
public class UpdateServlet extends HttpServlet {
    private final String ERROR_PAGE = "error.html";

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
        String url = ERROR_PAGE;
        String userID = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String isChk = request.getParameter("chkAdmin");
        String lastSearch = request.getParameter("lastSearchValue");
        try {
            //1. Call model
            //1.1 New DAO
            RegistrationDAO dao = new RegistrationDAO();
            //1.2 Call method
            boolean result = dao.updateAccount(userID, password, isChk);
            //2. Process
            if(result){
            //2.1 Call back search use UrlRewriting
                url = "DispatchServlet?btAction=Search&txtSearch=" + lastSearch;
            //2.2 Call invalid if wrong
            
            }
 
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
//            catch (NamingException ex){
//                ex.printStackTrace();
//            }
        finally{
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
