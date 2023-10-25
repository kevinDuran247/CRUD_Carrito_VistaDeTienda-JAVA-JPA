/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelosDAO;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jpacontroller.CategoriasJpaController;
import jpacontroller.ProveedoresJpaController;
import jpacontroller.exceptions.IllegalOrphanException;
import jpacontroller.exceptions.NonexistentEntityException;
import modelos.Categorias;
import modelos.Proveedores;

/**
 *
 * @author Kevin Duran
 */
public class ProveedorYCategoriasDAO {
    CategoriasJpaController controladorCategorias = new CategoriasJpaController();
    ProveedoresJpaController controladorProveedores = new ProveedoresJpaController();
    
    //CATEGORIAS
     public List<Categorias> consultarCategorias() {
        return this.controladorCategorias.findCategoriasEntities();
    }
    public void insertarCategoria(Categorias categorias) {
        this.controladorCategorias.create(categorias);
    }
    public void eliminarCategoria(Integer id) {
        try {
            this.controladorCategorias.destroy(id);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(ProveedorYCategoriasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ProveedorYCategoriasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Categorias obtenerCategoriaPorId(Integer id) {
        return this.controladorCategorias.findCategorias(id);
    }
    
    //PROVEEDORES
     public List<Proveedores> consultarProveedores() {
        return this.controladorProveedores.findProveedoresEntities();
    }
    public void insertarProveedor(Proveedores proveedores) {
        this.controladorProveedores.create(proveedores);
    }
    public void eliminarProveedor(Integer id) {
        try {
            this.controladorProveedores.destroy(id);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(ProveedorYCategoriasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ProveedorYCategoriasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Proveedores obtenerProveedorPorId(Integer id) {
        return this.controladorProveedores.findProveedores(id);
    }
     
}
