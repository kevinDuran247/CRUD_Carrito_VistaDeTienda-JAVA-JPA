/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelosDAO;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jpacontroller.ProductosJpaController;
import jpacontroller.exceptions.IllegalOrphanException;
import jpacontroller.exceptions.NonexistentEntityException;
import modelos.Productos;

/**
 *
 * @author Kevin Duran
 */
public class ProductosDAO {
     ProductosJpaController controladorProductos = new ProductosJpaController();
    
    public List<Productos> consultarProductos() {
        return this.controladorProductos.findProductosEntities();
    }
    
    public void insertarProducto(Productos productos) {
        this.controladorProductos.create(productos);
    }
    
    public void eliminarProducto (Integer id){
         try {
             this.controladorProductos.destroy(id);
         } catch (IllegalOrphanException ex) {
             Logger.getLogger(ProductosDAO.class.getName()).log(Level.SEVERE, null, ex);
         } catch (NonexistentEntityException ex) {
             Logger.getLogger(ProductosDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    public void modificarProducto(Productos productos) throws Exception {
        this.controladorProductos.edit(productos);
    }
    
    public Productos buscarProductoId(Integer id) {
       return this.controladorProductos.findProductos(id);
    }
   
    
}
