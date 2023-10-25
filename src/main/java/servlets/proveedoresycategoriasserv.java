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
import modelos.Proveedores;
import modelosDAO.ProveedorYCategoriasDAO;

/**
 *
 * @author Kevin Duran
 */
@WebServlet(name = "proveedoresycategoriasserv", urlPatterns = {"/proveedoresycategoriasserv"})
public class proveedoresycategoriasserv extends HttpServlet {

    ProveedorYCategoriasDAO proCatDAO = new ProveedorYCategoriasDAO();

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
            out.println("<title>Servlet proveedoresycategoriasserv</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet proveedoresycategoriasserv at " + request.getContextPath() + "</h1>");
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

        //Eliminar proveedor
        if (request.getParameter("ide") != null) {
            int id = Integer.parseInt(request.getParameter("ide"));
            proCatDAO.eliminarProveedor(id);
        }
        
        //Eliminar categoria
        if (request.getParameter("idec") != null) {
            int id = Integer.parseInt(request.getParameter("idec"));
            proCatDAO.eliminarCategoria(id);
        }

        //Consulta general categorias y proveddor
        List<Proveedores> consult = proCatDAO.consultarProveedores();
        List<Categorias> consultC = proCatDAO.consultarCategorias();
        request.setAttribute("consulta", consult);
        request.setAttribute("consultac", consultC);
        RequestDispatcher dispatcher = request.getRequestDispatcher("proveedorYcategorias.jsp");
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

        String nombreCategoria = request.getParameter("nombreCategoria");
        if (nombreCategoria == null) {
            //AGREGAR PROVEEDOR
            String nombreProveedor = request.getParameter("nombreProveedor");
            String telefonoProveedor = request.getParameter("telefonoProveedor");
            String contactoVacio = "";
            Proveedores proveedor = new Proveedores(nombreProveedor, telefonoProveedor, contactoVacio);
            proCatDAO.insertarProveedor(proveedor);
        } else {
            //AGRAGAR CATEGORIA
            int idVacio = 0;
            Categorias categoria = new Categorias(idVacio, nombreCategoria);
            proCatDAO.insertarCategoria(categoria);
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
