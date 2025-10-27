package ma.projet.service;

import ma.projet.beans.Femme;
import ma.projet.util.HibernateUtil;
import ma.projet.dao.IDao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

public class FemmeService implements IDao<Femme> {

    @Override
    public Femme create(Femme f) {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(f);
        em.getTransaction().commit();
        em.close();
        return f;
    }

    @Override
    public Femme update(Femme f) {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        Femme merged = em.merge(f);
        em.getTransaction().commit();
        em.close();
        return merged;
    }

    @Override
    public void delete(Femme f) {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        Femme managed = em.find(Femme.class, f.getId());
        if (managed != null) em.remove(managed);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Femme findById(Integer id) {
        EntityManager em = HibernateUtil.getEntityManager();
        Femme f = em.find(Femme.class, id);
        em.close();
        return f;
    }

    @Override
    public List<Femme> findAll() {
        EntityManager em = HibernateUtil.getEntityManager();
        List<Femme> list = em.createQuery("SELECT f FROM Femme f", Femme.class).getResultList();
        em.close();
        return list;
    }

    // Nombre d'enfants d'une femme entre deux dates (requête native nommée)
    public long getNombreEnfantsBetween(Integer femmeId, LocalDate d1, LocalDate d2) {
        EntityManager em = HibernateUtil.getEntityManager();
        Query q = em.createNamedQuery("Mariage.sumEnfantsByFemmeBetween");
        q.setParameter(1, femmeId);
        q.setParameter(2, d1);
        q.setParameter(3, d2);
        Object single = q.getSingleResult();
        em.close();
        if (single == null) return 0;
        if (single instanceof Number) return ((Number) single).longValue();
        return 0;
    }

    // Femmes mariées au moins deux fois (requête nommée)
    public List<Femme> findFemmesMarriedAtLeastTwo() {
        EntityManager em = HibernateUtil.getEntityManager();
        TypedQuery<Femme> q = em.createNamedQuery("Femme.findMarriedAtLeastTwo", Femme.class);
        List<Femme> res = q.getResultList();
        em.close();
        return res;
    }
}
