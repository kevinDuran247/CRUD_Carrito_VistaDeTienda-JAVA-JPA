/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Categorias;
import modelos.Productos;
import modelos.Proveedores;
import modelosDAO.ProductosDAO;
import modelosDAO.ProveedorYCategoriasDAO;

/**
 *
 * @author Kevin Duran
 */
@WebServlet(name = "catalogoserv", urlPatterns = {"/catalogoserv"})
public class catalogoserv extends HttpServlet {
    ProveedorYCategoriasDAO proCatDAO = new ProveedorYCategoriasDAO();
    ProductosDAO productosDAO = new ProductosDAO();

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
            out.println("<title>Servlet catalogoserv</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet catalogoserv at " + request.getContextPath() + "</h1>");
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
        // Consultar productos
        List<Productos> consultt = productosDAO.consultarProductos();
        // Recorre la lista de productos y obtén el nombre del proveedor para cada producto
        for (Productos producto : consultt) {
            int idProveedor = producto.getIdproveedor().getIdproveedor();
            int idCategoria = producto.getIdcategoria().getIdcategoria();

            Proveedores proveedor = proCatDAO.obtenerProveedorPorId(idProveedor);
            Categorias categoria = proCatDAO.obtenerCategoriaPorId(idCategoria);

            producto.setNombreProveedor(proveedor.getNombreProveedor());
            producto.setNombreCategoria(categoria.getNombreCategoria()); 

        }
        request.setAttribute("consultat", consultt);

        RequestDispatcher dispatcher = request.getRequestDispatcher("inicioTienda.jsp");
        dispatcher.forward(request, response);
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
