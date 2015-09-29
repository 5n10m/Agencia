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
            connection = DriverManager.getConnection("jdbc:sqlite:F:\\windows\\AD\\P2\\Agencia\\datasqlite3.db");
            Statement statement = connection.createStatement();
            String query = "select * from hotels";
            
            Boolean parametre = false; 
            
            String p = request.getParameter("nom_hotel");
            out.println(p);
            if(!p.isEmpty()){
                if(!parametre) query = query +" where ";
                query = query +"nom = \""+ p +"\"";
                parametre = true;
            }
            
            p = request.getParameter("companyia");
            if(!"Qualsevol".equals(p)){
                if(!parametre) query = query +" where ";
                else query = query + " and ";
                query = query + "cadena = \""+ p +"\"";
                parametre = true;
                
            }
            
            p = request.getParameter("ciutat");
            if(!"Qualsevol".equals(p)){
                if(!parametre) query = query +" where ";
                else query = query + " and ";
                query = query + "ciutat = \""+ p +"\"";
                parametre = true;
            }
            
            p = request.getParameter("clase");
            if(!"Qualsevol".equals(p)){
                if(!parametre) query = query +" where ";
                else query = query + " and ";
                query = query + "estrelles = \""+ p +"\"";
                parametre = true;
            }
            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Buscador d'hotels</title>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<style>\n" +
                "table, td, th {\n" +
                "    border: 1px solid darkcyan;\n" +
                "}\n" +
                "\n" +
                "th {\n" +
                "    background-color: blue;\n" +
                "    color: white;\n" +
                "}\n" +
                "</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Resultats de la busqueda - Hotels</h1>");
            out.println("<table border=\"1\">");
            out.println("<tr>");
            out.println("<th>Nom</th><th>Cadena</th><th>carrer</th><th>numero</th><th>CP</th><th>ciutat</th><th>Provincia</th><th>Pais</th><th>nº Habitacions</th><th>Categoria</th>");
            out.println("</tr>");
            
            //out.println(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+
                        "</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+
                        "</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td><td>"+rs.getString(9)+
                        "</td><td>"+rs.getString(10)+"</tr>");
            }
            
            out.println("</table>");
            out.println("<br>");
            out.println("&nbsp; <br> <a href=\"buscarHotel.jsp\">Torna a la busqueda d'hotels</a>");            
            out.println("&nbsp; <br> <a href=\"menu.html\">Menu</a>");
            out.println("<br>");
            out.println("</form>");
            out.println("</body>");
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
