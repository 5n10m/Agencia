<%-- 
    Document   : buscarVuelo
    Created on : 22-sep-2015, 17:12:50
    Author     : david.molins.goma
--%>

<%@page import="java.sql.Connection"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="javax.servlet.annotation.WebServlet"%>
<%@page import="javax.servlet.http.HttpServlet"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="javax.servlet.RequestDispatcher"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buscador de vols</title>
    </head>
    <body>
        <h1>Buscador de vols</h1>
        <h2>Ompli els camps per filtrar la cerca</h2>
        <br>
        <form action="buscarVuelo" method="GET">
            <table>
                <tr>
                    <th>Numero de vol:</th>
                    <td><center><input name=numero_vol size=6 maxlength=6 ></center></td>
                    <th>Companyia:</th>
                    <td><select name=companyia>
                            <option selected VALUE=Qualsevol> Qualsevol</option>
                            <%
                                Class.forName("org.sqlite.JDBC");
                                Connection connection = null;
                                try {
                                    connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Rio\\Dropbox\\UPC\\AD\\P2\\datasqlite3.db");
                                    Statement statement = connection.createStatement();
                                    ResultSet rs = statement.executeQuery("select distinct companyia from vols");

                                    while(rs.next()){
                                        %>  <option VALUE= <% out.print(rs.getString("companyia")); %> > <% out.print(rs.getString("companyia"));%> </option>    <%
                                    }
                            %>    
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>Ciutat d'origen:</th>
                    <td><select name=origen>
                            <option selected VALUE=Qualsevol> Qualsevol</option>
                            <% rs = statement.executeQuery("select distinct origen from vols");
            
                            while(rs.next()){
                                %>  <option VALUE= "<% out.print(rs.getString("origen"));%>" > <% out.print(rs.getString("origen"));%> </option>    <%
                            }
                            %>
                        </select>
                    </td>
                    <th>Hora de sortida:</th>
                    <th><input name=hora_sortida size=2 maxlength=2> h.</th>
                </tr>
                <tr>
                    <th>Ciutat destí:</th>
                    <td><select name=desti>
                            <option selected VALUE=Qualsevol> Qualsevol</option>
                           <% rs = statement.executeQuery("select distinct desti from vols");
            
                            while(rs.next()){
                                %>  <option VALUE= <% out.print(rs.getString("desti")); %> > <% out.print(rs.getString("desti"));%> </option>    <%
                            }
                            %>
                        </select>
                    </td>
                    <th>Hora d'arribada:</th>
                    <th><input name=hora_arribada size=2 maxlength=2> h.</th>
                </tr>                
            </table>
            <br>
            <br>
            <input name=Busca type=submit value="Buscar">
            <br>
        </form>
        <br>
        <h3><a href="menu.html">Tornar al menu</a></h3>
    </body>
    <% 
        connection.close();
    }
    catch(SQLException e) {
        System.err.println(e.getMessage());
    }
    finally {
        try {
            if(connection != null)
                connection.close();
        }
        catch(SQLException e) {
            //Error en tancar la connexio
            System.err.println(e.getMessage());
        }
    }
    %>
</html>
