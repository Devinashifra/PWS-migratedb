/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klmpk8.tratix;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import klmpk8.tratix.exceptions.NonexistentEntityException;
import klmpk8.tratix.exceptions.PreexistingEntityException;

/**
 *
 * @author HP
 */
public class DetailpenumpangJpaController implements Serializable {

    public DetailpenumpangJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detailpenumpang detailpenumpang) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(detailpenumpang);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetailpenumpang(detailpenumpang.getId()) != null) {
                throw new PreexistingEntityException("Detailpenumpang " + detailpenumpang + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detailpenumpang detailpenumpang) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            detailpenumpang = em.merge(detailpenumpang);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = detailpenumpang.getId();
                if (findDetailpenumpang(id) == null) {
                    throw new NonexistentEntityException("The detailpenumpang with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detailpenumpang detailpenumpang;
            try {
                detailpenumpang = em.getReference(Detailpenumpang.class, id);
                detailpenumpang.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detailpenumpang with id " + id + " no longer exists.", enfe);
            }
            em.remove(detailpenumpang);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detailpenumpang> findDetailpenumpangEntities() {
        return findDetailpenumpangEntities(true, -1, -1);
    }

    public List<Detailpenumpang> findDetailpenumpangEntities(int maxResults, int firstResult) {
        return findDetailpenumpangEntities(false, maxResults, firstResult);
    }

    private List<Detailpenumpang> findDetailpenumpangEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detailpenumpang.class));
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

    public Detailpenumpang findDetailpenumpang(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detailpenumpang.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetailpenumpangCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detailpenumpang> rt = cq.from(Detailpenumpang.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
