package ma.projet.service;

import ma.projet.classes.Employe;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class EmployeService {
    private final ma.projet.dao.impl.EmployeDaoImpl employeDao = new ma.projet.dao.impl.EmployeDaoImpl();

    public Employe save(Employe e) { return employeDao.save(e); }
    public Employe update(Employe e) { return employeDao.update(e); }
    public void delete(Employe e) { employeDao.delete(e); }
    public Employe findById(Integer id) { return employeDao.findById(id); }

    // Tâches réalisées par l'employé
    public List<Tache> getTachesRealiseesParEmploye(Integer employeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select et.tache from EmployeTache et where et.employe.id = :eId and et.dateDebutReelle is not null";
            return session.createQuery(hql, Tache.class)
                          .setParameter("eId", employeId)
                          .list();
        }
    }

    // Projets gérés par l'employé (retourne List<Projet>)
    public List<Projet> getProjetsGeresParEmploye(Integer employeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select p from Projet p where p.chef.id = :eId";
            return session.createQuery(hql, Projet.class)
                          .setParameter("eId", employeId)
                          .list();
        }
    }
}
