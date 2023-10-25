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
import modelos.Categorias;
import modelos.Productos;
import modelos.Proveedores;
import modelosDAO.ProductosDAO;
import modelosDAO.ProveedorYCategoriasDAO;

/**
 *
 * @author Kevin Duran
 */
@WebServlet(name = "productosserv", urlPatterns = {"/productosserv"})
public class productosserv extends HttpServlet {

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
            out.println("<title>Servlet productosserv</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet productosserv at " + request.getContextPath() + "</h1>");
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
        //Eliminar producto
        if (request.getParameter("idp") != null) {
            int id = Integer.parseInt(request.getParameter("idp"));
            productosDAO.eliminarProducto(id);
        }

        //Vista modificar
        if (request.getParameter("idpm") != null) {
            int idpm = Integer.parseInt(request.getParameter("idpm"));
            Productos productos = productosDAO.buscarProductoId(idpm);

            // Obtener el nombre del proveedor y la categoría
            int idProveedor = productos.getIdproveedor().getIdproveedor();
            int idCategoria = productos.getIdcategoria().getIdcategoria();

            Proveedores proveedor = proCatDAO.obtenerProveedorPorId(idProveedor);
            Categorias categoria = proCatDAO.obtenerCategoriaPorId(idCategoria);

            productos.setNombreProveedor(proveedor.getNombreProveedor());
            productos.setNombreCategoria(categoria.getNombreCategoria());
            
            List<Proveedores> listaProveedores = proCatDAO.consultarProveedores();
            List<Categorias> listaCategorias = proCatDAO.consultarCategorias();

            request.setAttribute("productos", productos);
            request.setAttribute("proveedores", listaProveedores);
            request.setAttribute("categorias", listaCategorias);

            RequestDispatcher dispatcher2 = request.getRequestDispatcher("modificarProducto.jsp");
            dispatcher2.forward(request, response);
        }

        // Consultar selects
        List<Proveedores> consultp = proCatDAO.consultarProveedores();
        request.setAttribute("consultap", consultp);
        List<Categorias> consultc = proCatDAO.consultarCategorias();
        request.setAttribute("consultac", consultc);

        // Consultar productos
        List<Productos> consultt = productosDAO.consultarProductos();
        // Recorre la lista de productos y obtén el nombre del proveedor para cada producto
        for (Productos producto : consultt) {
            int idProveedor = producto.getIdproveedor().getIdproveedor();
            int idCategoria = producto.getIdcategoria().getIdcategoria();

            Proveedores proveedor = proCatDAO.obtenerProveedorPorId(idProveedor);
            Categorias categoria = proCatDAO.obtenerCategoriaPorId(idCategoria);

            producto.setNombreProveedor(proveedor.getNombreProveedor());
            producto.setNombreCategoria(categoria.getNombreCategoria()); // Asignar el nombre de la categoría al producto

        }
        request.setAttribute("consultat", consultt);

        RequestDispatcher dispatcher = request.getRequestDispatcher("productos.jsp");
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
        String nombreProductoM = request.getParameter("nombreProductoM");
        if (nombreProductoM == null) {
            //AGREGAR PRODUCTO
            String nombreProducto = request.getParameter("nombreProducto");
            String precioProductoString = request.getParameter("precioProducto");
            double precioProducto = Double.parseDouble(precioProductoString);
            String urlImagen = request.getParameter("imagenProducto");
            String existenciasProductoString = request.getParameter("existenciasProducto");
            int existenciasProducto = Integer.parseInt(existenciasProductoString);
            String proveedorSTring = request.getParameter("nombreProveedor");
            int idproveedor = Integer.parseInt(proveedorSTring);
            String categoriaSTring = request.getParameter("nombreCategoria");
            int idcategoria = Integer.parseInt(categoriaSTring);

            // Obtén el objeto Proveedores y Categorias basados en los IDs
            Proveedores proveedor = proCatDAO.obtenerProveedorPorId(idproveedor);
            Categorias categoria = proCatDAO.obtenerCategoriaPorId(idcategoria);

            int idproductoVacio = 0;
            boolean ofertadoVacio = false;
            double precioOfertaVacio = 0;
            String descripcionVacio = "";

            Productos productos = new Productos(idproductoVacio, categoria, proveedor,
                    nombreProducto, precioProducto, ofertadoVacio,
                    precioOfertaVacio, existenciasProducto, descripcionVacio, urlImagen);

            productosDAO.insertarProducto(productos);
        } else {//MODIFICAR CLIENTE
            String idMStr = request.getParameter("idM");
            String nombreProveedorMStr = request.getParameter("nombreProveedorM");
            String nombreCategoriaMStr = request.getParameter("nombreCategoriaM");
            String precioProductoMStr = request.getParameter("precioProductoM");
            String existenciasProductoMStr = request.getParameter("existenciasProductoM");

            String imagenProductoM = request.getParameter("imagenProductoM");
            int idM = Integer.parseInt(idMStr);
            int idproveedorM = Integer.parseInt(nombreProveedorMStr);
            int idcategoriaM = Integer.parseInt(nombreCategoriaMStr);
            int existenciasProductoM = Integer.parseInt(existenciasProductoMStr);
            double precioProductoM = Double.parseDouble(precioProductoMStr);
            boolean ofertadoVacioM = false;
            double precioOfertaVacioM = 0;
            String descripcionVacioM = "";

            // Obtén el objeto Proveedores y Categorias basados en los IDs
            Proveedores nombreProveedorM = proCatDAO.obtenerProveedorPorId(idproveedorM);
            Categorias nombreCategoriaM = proCatDAO.obtenerCategoriaPorId(idcategoriaM);

            Productos productoM = new Productos(idM, nombreCategoriaM, nombreProveedorM, nombreProductoM,
                    precioProductoM, ofertadoVacioM, precioOfertaVacioM,
                    existenciasProductoM, descripcionVacioM, imagenProductoM);
            try {
                productosDAO.modificarProducto(productoM);
            } catch (Exception ex) {
                Logger.getLogger(productosserv.class.getName()).log(Level.SEVERE, null, ex);
            }
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
