/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelosDAO;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jpacontroller.ClienteJpaController;
import jpacontroller.exceptions.IllegalOrphanException;
import jpacontroller.exceptions.NonexistentEntityException;
import modelos.Cliente;

/**
 *
 * @author Kevin Duran
 */
public class ClientesDAO {

    ClienteJpaController controladorClientes = new ClienteJpaController();

    public List<Cliente> consultarClientes() {
        return this.controladorClientes.findClienteEntities();
    }

    public void insertarCliente(Cliente cliente) {
        this.controladorClientes.create(cliente);
    }

    public void eliminarCliente(Integer id) {
        try {
            this.controladorClientes.destroy(id);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Cliente buscarClienteId(Integer id) {
        return this.controladorClientes.findCliente(id);
    }

    public void modificarCliente(Cliente cliente) throws Exception {
        this.controladorClientes.edit(cliente);
    }

}
