package ma.projet.service;

import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.classes.EmployeTache;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ProjetService {
    private final ma.projet.dao.impl.ProjetDaoImpl projetDao = new ma.projet.dao.impl.ProjetDaoImpl();

    public Projet save(Projet p) { return projetDao.save(p); }
    public Projet update(Projet p) { return projetDao.update(p); }
    public void delete(Projet p) { projetDao.delete(p); }
    public Projet findById(Integer id) { return projetDao.findById(id); }

    public List<Projet> findAll() { return projetDao.findAll(); }

    // tâches planifiées (dates prévues)
    public List<Tache> getTachesPlanifiees(Integer projetId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select t from Tache t where t.projet.id = :pId";
            return session.createQuery(hql, Tache.class)
                          .setParameter("pId", projetId)
                          .list();
        }
    }

    // tâches réalisées avec dates réelles -> retourne List< Object[] ] dans l'autre version,
    // ici on donne une méthode utile qui retourne directement les entités EmployeTache
    public List<EmployeTache> getTachesRealisees(Projet p) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from EmployeTache et where et.tache.projet.id = :idProjet and et.dateFinReelle is not null";
            return session.createQuery(hql, EmployeTache.class)
                          .setParameter("idProjet", p.getId())
                          .list();
        }
    }

    // si tu veux la méthode qui retourne Object[] (Tache + dates réelles) :
    public List<Object[]> getTachesRealiseesAvecDatesReelles(Integer projetId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select t, et.dateDebutReelle, et.dateFinReelle " +
                         "from EmployeTache et join et.tache t " +
                         "where t.projet.id = :pId and et.dateDebutReelle is not null";
            return session.createQuery(hql, Object[].class)
                          .setParameter("pId", projetId)
                          .list();
        }
    }
}
