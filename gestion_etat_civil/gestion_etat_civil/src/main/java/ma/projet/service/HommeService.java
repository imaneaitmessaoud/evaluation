package ma.projet.service;

import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.util.HibernateUtil;
import ma.projet.dao.IDao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class HommeService implements IDao<Homme> {

    @Override
    public Homme create(Homme h) {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(h);
        em.getTransaction().commit();
        em.close();
        return h;
    }

    @Override
    public Homme update(Homme h) {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        Homme merged = em.merge(h);
        em.getTransaction().commit();
        em.close();
        return merged;
    }

    @Override
    public void delete(Homme h) {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        Homme managed = em.find(Homme.class, h.getId());
        if (managed != null) em.remove(managed);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Homme findById(Integer id) {
        EntityManager em = HibernateUtil.getEntityManager();
        Homme h = em.find(Homme.class, id);
        em.close();
        return h;
    }

    @Override
    public List<Homme> findAll() {
        EntityManager em = HibernateUtil.getEntityManager();
        List<Homme> list = em.createQuery("SELECT h FROM Homme h", Homme.class).getResultList();
        em.close();
        return list;
    }

    // Afficher les épouses d’un homme entre deux dates
    public List<Mariage> getEpousesBetweenDates(Integer hommeId, LocalDate d1, LocalDate d2) {
        EntityManager em = HibernateUtil.getEntityManager();
        TypedQuery<Mariage> q = em.createQuery(
            "SELECT m FROM Mariage m WHERE m.homme.id = :hid AND m.dateDebut BETWEEN :d1 AND :d2",
            Mariage.class);
        q.setParameter("hid", hommeId);
        q.setParameter("d1", d1);
        q.setParameter("d2", d2);
        List<Mariage> res = q.getResultList();
        em.close();
        return res;
    }

    // Tous les mariages d'un homme avec détails (femme, dates, nbrEnfants)
    public List<Mariage> getMariagesDetailsByHomme(Integer hommeId) {
        EntityManager em = HibernateUtil.getEntityManager();
        TypedQuery<Mariage> q = em.createQuery(
            "SELECT m FROM Mariage m JOIN FETCH m.femme WHERE m.homme.id = :hid ORDER BY m.dateDebut",
            Mariage.class);
        q.setParameter("hid", hommeId);
        List<Mariage> res = q.getResultList();
        em.close();
        return res;
    }
}
