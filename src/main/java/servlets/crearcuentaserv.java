/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Cliente;
import modelosDAO.ClientesDAO;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Kevin Duran
 */
@WebServlet(name = "crearcuentaserv", urlPatterns = {"/crearcuentaserv"})
public class crearcuentaserv extends HttpServlet {

    ClientesDAO clienteDAO = new ClientesDAO();

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet crearcuentaserv</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet crearcuentaserv at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    // Función para calcular el hash SHA-256 de una cadena
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    int idCliente = 0;
    String nombres = request.getParameter("nombres");
    String apellidos = request.getParameter("apellidos");
    String sexo = request.getParameter("sexo");
    String direccion = request.getParameter("direccionM");
    String telefono = request.getParameter("telefonoM");
    String pais = request.getParameter("pais");
    String clave = request.getParameter("clave"); // Contraseña sin encriptar
    String correo = request.getParameter("correo");

    try {

        Cliente cliente = new Cliente(idCliente, nombres, apellidos, sexo, direccion, telefono, pais, clave, correo);
        clienteDAO.insertarCliente(cliente);
        response.sendRedirect("carrito.jsp");
    } catch (Exception e) {
        // Manejo de errores en caso de problemas con BCrypt o cualquier otro error
        e.printStackTrace();
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
