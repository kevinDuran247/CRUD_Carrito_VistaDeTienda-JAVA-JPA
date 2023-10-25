/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpacontroller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpacontroller.exceptions.NonexistentEntityException;
import modelos.Detallepedido;
import modelos.Pedidos;
import modelos.Productos;

/**
 *
 * @author Kevin Duran
 */
public class DetallepedidoJpaController implements Serializable {

    public DetallepedidoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("tiendaPU");
    }

    public DetallepedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

   public List<Detallepedido> findDetallePedidosByIdPedido(Integer idPedido) {
    EntityManager em = getEntityManager();
    try {
        Pedidos pedido = em.find(Pedidos.class, idPedido);
        TypedQuery<Detallepedido> query = em.createQuery("SELECT d FROM Detallepedido d WHERE d.idpedido = :pedido", Detallepedido.class);
        query.setParameter("pedido", pedido);
        List<Detallepedido> detallePedidos = query.getResultList();
        
        for (Detallepedido detalle : detallePedidos) {
            Productos producto = em.find(Productos.class, detalle.getIdproducto().getIdproducto());

            detalle.getIdproducto().setNombreProducto(producto.getNombreProducto());
        }
        
        return detallePedidos;
    } finally {
        em.close();
    }
}





    public Integer obtenerIdPorEstado(String estado) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT p.idpedido FROM Pedidos p WHERE p.estado = :estado");
            query.setParameter("estado", estado);

            List<Integer> results = query.getResultList();
            if (results.isEmpty()) {
                // No se encontró ningún pedido con el estado especificado
                return null;
            } else {
                // Devuelve el ID del primer pedido que cumple con los criterios
                return results.get(0);
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void create(Detallepedido detallepedido) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(detallepedido);
        em.getTransaction().commit();
        em.close();
    }

    public void edit(Detallepedido detallepedido) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallepedido persistentDetallepedido = em.find(Detallepedido.class, detallepedido.getIddetalle());
            Pedidos idpedidoOld = persistentDetallepedido.getIdpedido();
            Pedidos idpedidoNew = detallepedido.getIdpedido();
            Productos idproductoOld = persistentDetallepedido.getIdproducto();
            Productos idproductoNew = detallepedido.getIdproducto();
            if (idpedidoNew != null) {
                idpedidoNew = em.getReference(idpedidoNew.getClass(), idpedidoNew.getIdpedido());
                detallepedido.setIdpedido(idpedidoNew);
            }
            if (idproductoNew != null) {
                idproductoNew = em.getReference(idproductoNew.getClass(), idproductoNew.getIdproducto());
                detallepedido.setIdproducto(idproductoNew);
            }
            detallepedido = em.merge(detallepedido);
            if (idpedidoOld != null && !idpedidoOld.equals(idpedidoNew)) {
                idpedidoOld.getDetallepedidoCollection().remove(detallepedido);
                idpedidoOld = em.merge(idpedidoOld);
            }
            if (idpedidoNew != null && !idpedidoNew.equals(idpedidoOld)) {
                idpedidoNew.getDetallepedidoCollection().add(detallepedido);
                idpedidoNew = em.merge(idpedidoNew);
            }
            if (idproductoOld != null && !idproductoOld.equals(idproductoNew)) {
                idproductoOld.getDetallepedidoCollection().remove(detallepedido);
                idproductoOld = em.merge(idproductoOld);
            }
            if (idproductoNew != null && !idproductoNew.equals(idproductoOld)) {
                idproductoNew.getDetallepedidoCollection().add(detallepedido);
                idproductoNew = em.merge(idproductoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detallepedido.getIddetalle();
                if (findDetallepedido(id) == null) {
                    throw new NonexistentEntityException("The detallepedido with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallepedido detallepedido;
            try {
                detallepedido = em.getReference(Detallepedido.class, id);
                detallepedido.getIddetalle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallepedido with id " + id + " no longer exists.", enfe);
            }
            Pedidos idpedido = detallepedido.getIdpedido();
            if (idpedido != null) {
                idpedido.getDetallepedidoCollection().remove(detallepedido);
                idpedido = em.merge(idpedido);
            }
            Productos idproducto = detallepedido.getIdproducto();
            if (idproducto != null) {
                idproducto.getDetallepedidoCollection().remove(detallepedido);
                idproducto = em.merge(idproducto);
            }
            em.remove(detallepedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallepedido> findDetallepedidoEntities() {
        return findDetallepedidoEntities(true, -1, -1);
    }

    public List<Detallepedido> findDetallepedidoEntities(int maxResults, int firstResult) {
        return findDetallepedidoEntities(false, maxResults, firstResult);
    }

    private List<Detallepedido> findDetallepedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallepedido.class));
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

    public Detallepedido findDetallepedido(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallepedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallepedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallepedido> rt = cq.from(Detallepedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
