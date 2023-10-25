/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jpacontroller.ClienteJpaController;
import jpacontroller.DetallepedidoJpaController;
import jpacontroller.PedidosJpaController;
import jpacontroller.ProductosJpaController;
import modelos.Cliente;
import modelos.Detallepedido;
import modelos.Pedidos;
import modelos.Productos;
import modelosDAO.ClientesDAO;
import modelosDAO.ProductosDAO;

/**
 *
 * @author Kevin Duran
 */
@WebServlet(name = "comprasserv", urlPatterns = {"/comprasserv"})
public class comprasserv extends HttpServlet {

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
            out.println("<title>Servlet comprasserv</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet comprasserv at " + request.getContextPath() + "</h1>");
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClienteJpaController cliente = new ClienteJpaController();
        DetallepedidoJpaController detallep = new DetallepedidoJpaController();
        ProductosJpaController p = new ProductosJpaController();
        ProductosDAO productosDAO = new ProductosDAO();
        PedidosJpaController pedidoss = new PedidosJpaController();

        
        String apellidos = request.getParameter("user");
        String clave = request.getParameter("password");

        boolean clienteExiste = cliente.existeCliente(apellidos, clave);

        if (clienteExiste) {
            String[] nombresProductos = request.getParameterValues("nombreProducto");
            String[] precios = request.getParameterValues("precio");
            String[] existencias = request.getParameterValues("existencias");

         
            //INSERTAR PEDIDO
            int idcliente = cliente.obtenerIdCliente(apellidos, clave);
            double total = Double.parseDouble(request.getParameter("total"));
            UUID uniqueKey = UUID.randomUUID();
            String estado = uniqueKey.toString();
            int idpedido = 0;
            Date fechaActual = new Date();
            PedidosJpaController pedidos = new PedidosJpaController();
            ClientesDAO clienteDAO = new ClientesDAO();
            Cliente clientee = clienteDAO.buscarClienteId(idcliente);
            Pedidos pedido = new Pedidos(idpedido, clientee, fechaActual, total, estado);
            pedidos.insertarPedido(pedido);

            ///INSERTAR DETALLE PEDIDO
            int pedidoInt = detallep.obtenerIdPorEstado(estado);

            Pedidos detalle = pedidoss.findPedidos(pedidoInt);

            String[] cantidades = request.getParameterValues("cantidad");
            String[] subtotales = request.getParameterValues("subtotal");
            String[] ids = request.getParameterValues("id");

            for (int i = 0; i < ids.length; i++) {
                int iddetalle = 0; // Aquí deberías generar un nuevo ID para cada detalle
                int cantidad = Integer.valueOf(cantidades[i]);
                double subtotal = Double.valueOf(subtotales[i]);
                Productos producto = p.findProductos(Integer.valueOf(ids[i]));

                Detallepedido dp = new Detallepedido(iddetalle, detalle, producto, cantidad, subtotal);
                detallep.create(dp);
            }
            
            // Antes de redirigir a la factura.jsp, establece los atributos en el objeto request
            request.setAttribute("cantidades", cantidades);
            request.setAttribute("subtotales", subtotales);
            request.setAttribute("total", total);
            request.setAttribute("nombresProductos", nombresProductos);
            request.setAttribute("precios", precios);

            // Redirige a la factura.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("factura.jsp");
            dispatcher.forward(request, response);
          
        } else {
            String mensaje = "No tienes cuenta, por favor crea una.";
            response.setContentType("text/html");
            String script = "<script type='text/javascript'>alert('" + mensaje + "');";
            script += "window.location.href = 'loginCliente.jsp';</script>";
            response.getWriter().println(script);
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
