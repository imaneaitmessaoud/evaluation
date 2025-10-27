package ma.projet.exercice1.service;


import ma.projet.exercice1.classes.Categorie;
import ma.projet.exercice1.classes.Produit;
import ma.projet.exercice1.dao.IDao;
import ma.projet.exercice1.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class ProduitService implements IDao<Produit> {
    
    @Override
    public boolean create(Produit produit) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(produit);
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
    public boolean update(Produit produit) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(produit);
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
    public boolean delete(Produit produit) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(session.contains(produit) ? produit : session.merge(produit));
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
    public Produit findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Produit.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Produit> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Produit", Produit.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Méthode pour afficher les produits par catégorie
    public List<Produit> findByCategorie(Categorie categorie) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Produit> query = session.createQuery(
                "FROM Produit p WHERE p.categorie = :categorie", Produit.class);
            query.setParameter("categorie", categorie);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Méthode pour afficher les produits commandés entre deux dates
    public List<Produit> findProduitsCommandesEntreDates(Date dateDebut, Date dateFin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT DISTINCT p FROM Produit p " +
                        "JOIN p.ligneCommandes lc " +
                        "JOIN lc.commande c " +
                        "WHERE c.date BETWEEN :dateDebut AND :dateFin";
            
            Query<Produit> query = session.createQuery(hql, Produit.class);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Méthode pour afficher les produits avec prix supérieur à 100 DH (requête nommée)
    public List<Produit> findByPrixSuperieur(float prix) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Produit> query = session.createNamedQuery("Produit.findByPrixSuperieur", Produit.class);
            query.setParameter("prix", prix);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}