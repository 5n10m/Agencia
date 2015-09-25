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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author david.molins.goma
 */
@WebServlet(urlPatterns = {"/buscarHotel"})
public class buscarHotel extends HttpServlet {
    
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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        Class.forName("org.sqlite.JDBC");
        Connection connection = null;
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            connection = DriverManager.getConnection("jdbc:sqlite:"+System.getProperty("user.dir")+"\\datasqlite3.db");
            Statement statement = connection.createStatement();
            String query = "select * from hotels where ";
            if(!request.getParameter("nom_hotel").isEmpty()){
                query = query +"nom = \""+ request.getParameter("nom_hotel")+"\",";
            }
            if(!"Qualsevol".equals(request.getParameter("companyia"))){
                query = query + "cadena = \""+ request.getParameter("companyia")+"\",";
            }
            if(!"Qualsevol".equals(request.getParameter("ciutat"))){
                query = query + "ciutat = \""+ request.getParameter("ciutat")+"\",";
            }
            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Login Agencia viatges</title>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Resultats de la busqueda -Hotels</h1>");
            out.println("<table summary=\"\">");
            out.println("<tr>");
            out.println("<td>Nom</td><td>Cadena</td><td>carrer</td><td>numero</td><td>CP</td><td>ciutat</td><td>Pais</td><td>nº Habitacions</td>");
            out.println("</tr>");
            
            out.println(query);
            
            out.println("</table>");
            out.println("<br>");
            out.println("<input name=accedeix type=submit value=\"accedeix\">");
            out.println("<br>");
            out.println("</form>");
            out.println("</body>");
            
            ResultSet rs = statement.executeQuery(query);
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
            Logger.getLogger(buscarHotel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(buscarHotel.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(buscarHotel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(buscarHotel.class.getName()).log(Level.SEVERE, null, ex);
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
