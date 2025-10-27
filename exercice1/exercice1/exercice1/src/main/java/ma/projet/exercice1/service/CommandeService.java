package ma.projet.exercice1.service;


import ma.projet.exercice1.classes.Commande;
import ma.projet.exercice1.classes.LigneCommandeProduit;
import ma.projet.exercice1.dao.IDao;
import ma.projet.exercice1.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.SimpleDateFormat;
import java.util.List;

public class CommandeService implements IDao<Commande> {
    
    @Override
    public boolean create(Commande commande) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(commande);
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
    public boolean update(Commande commande) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(commande);
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
    public boolean delete(Commande commande) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(session.contains(commande) ? commande : session.merge(commande));
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
    public Commande findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Commande commande = session.get(Commande.class, id);
            if (commande != null) {
                Hibernate.initialize(commande.getLigneCommandes());
            }
            return commande;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Commande> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Commande", Commande.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Méthode pour afficher les produits commandés dans une commande donnée
    public void afficherProduitsCommande(int commandeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Commande commande = session.get(Commande.class, commandeId);
            
            if (commande == null) {
                System.out.println("Commande non trouvée !");
                return;
            }
            
            Hibernate.initialize(commande.getLigneCommandes());
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
            System.out.println("Commande : " + commande.getId() + "     Date : " + sdf.format(commande.getDate()));
            System.out.println("Liste des produits :");
            System.out.println("Référence\tPrix\tQuantité");
            
            for (LigneCommandeProduit ligne : commande.getLigneCommandes()) {
                System.out.printf("%s\t\t%.0f DH\t%d%n", 
                    ligne.getProduit().getReference(),
                    ligne.getProduit().getPrix(),
                    ligne.getQuantite());
            }
            System.out.println();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}