package ma.projet.exercice1.service;

import ma.projet.exercice1.classes.LigneCommandeProduit;
import ma.projet.exercice1.dao.IDao;
import ma.projet.exercice1.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LigneCommandeService implements IDao<LigneCommandeProduit> {
    
    @Override
    public boolean create(LigneCommandeProduit ligneCommande) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(ligneCommande);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean update(LigneCommandeProduit ligneCommande) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(ligneCommande);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean delete(LigneCommandeProduit ligneCommande) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(session.contains(ligneCommande) ? ligneCommande : session.merge(ligneCommande));
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public LigneCommandeProduit findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(LigneCommandeProduit.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<LigneCommandeProduit> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM LigneCommandeProduit", LigneCommandeProduit.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}