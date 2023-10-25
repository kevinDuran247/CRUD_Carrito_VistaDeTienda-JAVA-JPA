/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jpacontroller.ClienteJpaController;
import modelos.Cliente;
import modelosDAO.ClientesDAO;

/**
 *
 * @author Kevin Duran
 */
@WebServlet(name = "clientesserv", urlPatterns = {"/clientesserv"})
public class clientesserv extends HttpServlet {

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
            out.println("<title>Servlet clientesserv</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet clientesserv at " + request.getContextPath() + "</h1>");
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
        try {

            //Eliminar cliente
            if (request.getParameter("ide") != null) {
                int id = Integer.parseInt(request.getParameter("ide"));
                clienteDAO.eliminarCliente(id);
            }

            //Vista modificar
            if (request.getParameter("idm") != null) {
                int idm = Integer.parseInt(request.getParameter("idm"));
                Cliente cliente = clienteDAO.buscarClienteId(idm);
                request.setAttribute("cliente", cliente);
                RequestDispatcher dispatcher2 = request.getRequestDispatcher("modificarCliente.jsp");
                dispatcher2.forward(request, response);
            }

            //Consultar clientes
            List<Cliente> consult = clienteDAO.consultarClientes();
            request.setAttribute("consulta", consult);
            RequestDispatcher dispatcher = request.getRequestDispatcher("clientes.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {

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

        // MODIFICAR CLIENTE
        String nombresM = request.getParameter("nombresM");
        String apellidosM = request.getParameter("apellidosM");
        String direccionM = request.getParameter("direccionM");
        String telefonoM = request.getParameter("telefonoM");
        String sexoM = request.getParameter("sexoM");
        String paisM = request.getParameter("paisM");
        String claveM = request.getParameter("claveM"); 
        String correoM = request.getParameter("correoM");
        String idStrM = request.getParameter("idmo");
        int idM = 0;
        try {
            idM = Integer.parseInt(idStrM);
        } catch (NumberFormatException e) {
           
            e.printStackTrace(); 
        }

        try {


            Cliente clienteM = new Cliente(idM, nombresM, apellidosM, sexoM, direccionM, telefonoM, paisM, claveM, correoM);

            ClienteJpaController controladorClientes = new ClienteJpaController();
            controladorClientes.edit(clienteM);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception ex) {
            Logger.getLogger(clientesserv.class.getName()).log(Level.SEVERE, null, ex);
        }

        doGet(request, response);
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
