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
import modelos.Cliente;
import modelos.Detallepedido;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import jpacontroller.exceptions.IllegalOrphanException;
import jpacontroller.exceptions.NonexistentEntityException;
import modelos.Pedidos;

/**
 *
 * @author Kevin Duran
 */
public class PedidosJpaController implements Serializable {

    public PedidosJpaController() {
        this.emf = Persistence.createEntityManagerFactory("tiendaPU");
    }

    public PedidosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Pedidos> findPedidosByCliente(Integer idCliente) {
    EntityManager em = getEntityManager();
    try {
        Cliente cliente = em.find(Cliente.class, idCliente);
        TypedQuery<Pedidos> query = em.createQuery("SELECT p FROM Pedidos p WHERE p.idcliente = :cliente", Pedidos.class);
        query.setParameter("cliente", cliente);
        return query.getResultList();
    } finally {
        em.close();
    }
}


    public void insertarPedido(Pedidos pedidos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pedidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pedidos pedidos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedidos persistentPedidos = em.find(Pedidos.class, pedidos.getIdpedido());
            Cliente idclienteOld = persistentPedidos.getIdcliente();
            Cliente idclienteNew = pedidos.getIdcliente();
            Collection<Detallepedido> detallepedidoCollectionOld = persistentPedidos.getDetallepedidoCollection();
            Collection<Detallepedido> detallepedidoCollectionNew = pedidos.getDetallepedidoCollection();
            List<String> illegalOrphanMessages = null;
            for (Detallepedido detallepedidoCollectionOldDetallepedido : detallepedidoCollectionOld) {
                if (!detallepedidoCollectionNew.contains(detallepedidoCollectionOldDetallepedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallepedido " + detallepedidoCollectionOldDetallepedido + " since its idpedido field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idclienteNew != null) {
                idclienteNew = em.getReference(idclienteNew.getClass(), idclienteNew.getIdcliente());
                pedidos.setIdcliente(idclienteNew);
            }
            Collection<Detallepedido> attachedDetallepedidoCollectionNew = new ArrayList<Detallepedido>();
            for (Detallepedido detallepedidoCollectionNewDetallepedidoToAttach : detallepedidoCollectionNew) {
                detallepedidoCollectionNewDetallepedidoToAttach = em.getReference(detallepedidoCollectionNewDetallepedidoToAttach.getClass(), detallepedidoCollectionNewDetallepedidoToAttach.getIddetalle());
                attachedDetallepedidoCollectionNew.add(detallepedidoCollectionNewDetallepedidoToAttach);
            }
            detallepedidoCollectionNew = attachedDetallepedidoCollectionNew;
            pedidos.setDetallepedidoCollection(detallepedidoCollectionNew);
            pedidos = em.merge(pedidos);
            if (idclienteOld != null && !idclienteOld.equals(idclienteNew)) {
                idclienteOld.getPedidosCollection().remove(pedidos);
                idclienteOld = em.merge(idclienteOld);
            }
            if (idclienteNew != null && !idclienteNew.equals(idclienteOld)) {
                idclienteNew.getPedidosCollection().add(pedidos);
                idclienteNew = em.merge(idclienteNew);
            }
            for (Detallepedido detallepedidoCollectionNewDetallepedido : detallepedidoCollectionNew) {
                if (!detallepedidoCollectionOld.contains(detallepedidoCollectionNewDetallepedido)) {
                    Pedidos oldIdpedidoOfDetallepedidoCollectionNewDetallepedido = detallepedidoCollectionNewDetallepedido.getIdpedido();
                    detallepedidoCollectionNewDetallepedido.setIdpedido(pedidos);
                    detallepedidoCollectionNewDetallepedido = em.merge(detallepedidoCollectionNewDetallepedido);
                    if (oldIdpedidoOfDetallepedidoCollectionNewDetallepedido != null && !oldIdpedidoOfDetallepedidoCollectionNewDetallepedido.equals(pedidos)) {
                        oldIdpedidoOfDetallepedidoCollectionNewDetallepedido.getDetallepedidoCollection().remove(detallepedidoCollectionNewDetallepedido);
                        oldIdpedidoOfDetallepedidoCollectionNewDetallepedido = em.merge(oldIdpedidoOfDetallepedidoCollectionNewDetallepedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pedidos.getIdpedido();
                if (findPedidos(id) == null) {
                    throw new NonexistentEntityException("The pedidos with id " + id + " no longer exists.");
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
            Pedidos pedidos;
            try {
                pedidos = em.getReference(Pedidos.class, id);
                pedidos.getIdpedido();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedidos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Detallepedido> detallepedidoCollectionOrphanCheck = pedidos.getDetallepedidoCollection();
            for (Detallepedido detallepedidoCollectionOrphanCheckDetallepedido : detallepedidoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pedidos (" + pedidos + ") cannot be destroyed since the Detallepedido " + detallepedidoCollectionOrphanCheckDetallepedido + " in its detallepedidoCollection field has a non-nullable idpedido field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente idcliente = pedidos.getIdcliente();
            if (idcliente != null) {
                idcliente.getPedidosCollection().remove(pedidos);
                idcliente = em.merge(idcliente);
            }
            em.remove(pedidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pedidos> findPedidosEntities() {
        return findPedidosEntities(true, -1, -1);
    }

    public List<Pedidos> findPedidosEntities(int maxResults, int firstResult) {
        return findPedidosEntities(false, maxResults, firstResult);
    }

    private List<Pedidos> findPedidosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pedidos.class));
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

    public Pedidos findPedidos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pedidos.class, id);
        } finally {
            em.close();
        }
    }

    public int getPedidosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pedidos> rt = cq.from(Pedidos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
