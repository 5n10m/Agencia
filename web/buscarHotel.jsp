<%-- 
    Document   : newjspbuscarHotel
    Created on : 22-sep-2015, 17:11:21
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
        <title>Buscador d'hotels</title>
    </head>
    <body>
        <h1>Buscador d'hotels</h1>
        &nbsp; <br>
        <form action="buscarHotel" method="GET">
            <table summary="">
                <tr>
                    <td>Nom del hotel:</td>
                    <td><input name=nom_hotel size=20 maxlength=20 value="NomHotel"></td>
                    <td>Cadena hotelera:</td>
                    <td><select name=companyia>
<%
        Class.forName("org.sqlite.JDBC");
        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:"+System.getProperty("user.dir")+"\\datasqlite3.db");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select distinct cadena from hotels");
            
            while(rs.next()){
                %>  <option VALUE= <% rs.getString("cadena"); %> > <% rs.getString("cadena");
            }
%>    
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Ciutat:</td>
                    <td><select name=companyia>
<% 
            rs = statement.executeQuery("select distinct ciutat from hotels");
            while(rs.next()){
                %>  <option VALUE= <% rs.getString("ciutat"); %> > <% rs.getString("ciutat");
            }
%>
                        </select>
                    </td>
                    <td>Estrelles:</td>
                    <td><select name=clase>
                            <option selected VALUE=1>*
                            <option VALUE=2>**
                            <option VALUE=3>***
                            <option VALUE=4>****
                            <option VALUE=5>*****
                        </select>
                    </td>
                </tr>
            </table>
            &nbsp; <br>
            <input name=registrar type=submit value="Registra">
            <br>
        </form>
        &nbsp; <br>
        <a href="menu.html">Menu</a>
    </body>
     <% 
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
    %>
</html>
