/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author david
 */
@WebServlet(urlPatterns = {"/StartDB"})
public class StartDB extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Class.forName("org.sqlite.JDBC");
        Connection connection = null;
        try{
            //"+System.getProperty("user.dir")+"
            connection = DriverManager.getConnection("jdbc:sqlite:"+System.getProperty("user.dir")+"\\datasqlite3.db");
            Statement statement = connection.createStatement();
            
            String query= "CREATE TABLE usuaris " +
                   "(user TEXT PRIMARY KEY     NOT NULL," +
                   " password          TEXT    NOT NULL);"; 
           statement.executeUpdate(query);
           query = "CREATE TABLE hotels " +
                   "(nom TEXT PRIMARY KEY     NOT NULL," +
                   " cadena           TEXT    NOT NULL, " +
                   " carrer           TEXT    NOT NULL, " +
                   " numero           TEXT    NOT NULL, " +
                   " cp               TEXT    NOT NULL, " +
                   " ciutat           TEXT    NOT NULL, "+
                   " provincia        TEXT    NOT NULL, " +
                   " pais             TEXT    NOT NULL, " +
                   " numero_habitacions TEXT    NOT NULL );";
            statement.executeUpdate(query);
            /**
             * CREATE TABLE hotels 
                   (nom TEXT PRIMARY KEY     NOT NULL,
                   cadena           TEXT    NOT NULL, 
                   carrer           TEXT    NOT NULL, 
                   numero           TEXT    NOT NULL, 
                   cp               TEXT    NOT NULL, 
                   ciutat           TEXT    NOT NULL,
                   provincia        TEXT    NOT NULL, 
                   pais             TEXT    NOT NULL, 
                   numero_habitacions TEXT    NOT NULL,
                   estrelles TEXT NOT NULL)
             */
            /**
             * CREATE TABLE usuaris 
                  ( user TEXT PRIMARY KEY     NOT NULL,
                    password          TEXT    NOT NULL)
             */
            /**
             * CREATE TABLE vols
                  ( numero    TEXT PRIMARY KEY     NOT NULL,
                    companyia             TEXT     NOT NULL,
                    origen                TEXT     NOT NULL,
                    hora_sortida          TEXT     NOT NULL,
                    desti                 TEXT     NOT NULL,
                    hora_arribada         TEXT     NOT NULL)
             */
            
            out.println("<p> Login correcte <p>");
            RequestDispatcher rd = request.getRequestDispatcher("menu.html");
            rd.include(request, response);
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        finally{
            try{
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e.getMessage());
            }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StartDB.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StartDB.class.getName()).log(Level.SEVERE, null, ex);
        }
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
