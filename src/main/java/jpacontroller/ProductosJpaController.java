/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpacontroller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelos.Categorias;
import modelos.Proveedores;
import modelos.Detallepedido;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpacontroller.exceptions.IllegalOrphanException;
import jpacontroller.exceptions.NonexistentEntityException;
import modelos.Productos;

/**
 *
 * @author Kevin Duran
 */
public class ProductosJpaController implements Serializable {

    public ProductosJpaController() {
        this.emf = Persistence.createEntityManagerFactory("tiendaPU");
    }

    public ProductosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Productos productos) {
        if (productos.getDetallepedidoCollection() == null) {
            productos.setDetallepedidoCollection(new ArrayList<Detallepedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categorias idcategoria = productos.getIdcategoria();
            if (idcategoria != null) {
                idcategoria = em.getReference(idcategoria.getClass(), idcategoria.getIdcategoria());
                productos.setIdcategoria(idcategoria);
            }
            Proveedores idproveedor = productos.getIdproveedor();
            if (idproveedor != null) {
                idproveedor = em.getReference(idproveedor.getClass(), idproveedor.getIdproveedor());
                productos.setIdproveedor(idproveedor);
            }
            Collection<Detallepedido> attachedDetallepedidoCollection = new ArrayList<Detallepedido>();
            for (Detallepedido detallepedidoCollectionDetallepedidoToAttach : productos.getDetallepedidoCollection()) {
                detallepedidoCollectionDetallepedidoToAttach = em.getReference(detallepedidoCollectionDetallepedidoToAttach.getClass(), detallepedidoCollectionDetallepedidoToAttach.getIddetalle());
                attachedDetallepedidoCollection.add(detallepedidoCollectionDetallepedidoToAttach);
            }
            productos.setDetallepedidoCollection(attachedDetallepedidoCollection);
            em.persist(productos);
            if (idcategoria != null) {
                idcategoria.getProductosCollection().add(productos);
                idcategoria = em.merge(idcategoria);
            }
            if (idproveedor != null) {
                idproveedor.getProductosCollection().add(productos);
                idproveedor = em.merge(idproveedor);
            }
            for (Detallepedido detallepedidoCollectionDetallepedido : productos.getDetallepedidoCollection()) {
                Productos oldIdproductoOfDetallepedidoCollectionDetallepedido = detallepedidoCollectionDetallepedido.getIdproducto();
                detallepedidoCollectionDetallepedido.setIdproducto(productos);
                detallepedidoCollectionDetallepedido = em.merge(detallepedidoCollectionDetallepedido);
                if (oldIdproductoOfDetallepedidoCollectionDetallepedido != null) {
                    oldIdproductoOfDetallepedidoCollectionDetallepedido.getDetallepedidoCollection().remove(detallepedidoCollectionDetallepedido);
                    oldIdproductoOfDetallepedidoCollectionDetallepedido = em.merge(oldIdproductoOfDetallepedidoCollectionDetallepedido);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Productos productos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            productos = em.merge(productos); // Fusiona los cambios del producto en la base de datos
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = productos.getIdproducto();
                if (findProductos(id) == null) {
                    throw new NonexistentEntityException("El producto con ID " + id + " ya no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productos productos;
            try {
                productos = em.getReference(Productos.class, id);
                productos.getIdproducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Detallepedido> detallepedidoCollectionOrphanCheck = productos.getDetallepedidoCollection();
            for (Detallepedido detallepedidoCollectionOrphanCheckDetallepedido : detallepedidoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Productos (" + productos + ") cannot be destroyed since the Detallepedido " + detallepedidoCollectionOrphanCheckDetallepedido + " in its detallepedidoCollection field has a non-nullable idproducto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Categorias idcategoria = productos.getIdcategoria();
            if (idcategoria != null) {
                idcategoria.getProductosCollection().remove(productos);
                idcategoria = em.merge(idcategoria);
            }
            Proveedores idproveedor = productos.getIdproveedor();
            if (idproveedor != null) {
                idproveedor.getProductosCollection().remove(productos);
                idproveedor = em.merge(idproveedor);
            }
            em.remove(productos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Productos> findProductosEntities() {
        return findProductosEntities(true, -1, -1);
    }

    public List<Productos> findProductosEntities(int maxResults, int firstResult) {
        return findProductosEntities(false, maxResults, firstResult);
    }

    private List<Productos> findProductosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Productos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Productos findProductos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Productos.class, id);
        } finally {
            em.close();
        }
    }

    public List<Productos> findProductoss(Integer[] ids) {
        EntityManager em = getEntityManager();
        try {
            List<Productos> productos = new ArrayList<>();

            for (Integer id : ids) {
                Productos producto = em.find(Productos.class, id);
                if (producto != null) {
                    productos.add(producto);
                }
            }

            return productos;
        } finally {
            em.close();
        }
    }

    public int getProductosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Productos> rt = cq.from(Productos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
