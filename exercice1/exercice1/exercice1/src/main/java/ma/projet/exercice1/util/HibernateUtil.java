package ma.projet.exercice1.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import ma.projet.exercice1.classes.Categorie;
import ma.projet.exercice1.classes.Commande;
import ma.projet.exercice1.classes.LigneCommandeProduit;
import ma.projet.exercice1.classes.Produit;

public class HibernateUtil {
    
    private static SessionFactory sessionFactory;
    
    static {
        try {
            // Configuration programmatique
            Configuration configuration = new Configuration();
            
            // Propriétés de connexion MySQL
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", 
                "jdbc:mysql://localhost:3306/gestion_stock?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC");
            configuration.setProperty("hibernate.connection.username", "root");
            configuration.setProperty("hibernate.connection.password", "");
            
            // Propriétés Hibernate
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");            
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.format_sql", "true");
            
            // Pool de connexions
            configuration.setProperty("hibernate.connection.pool_size", "10");
            
            // Ajout des classes annotées
            configuration.addAnnotatedClass(Categorie.class);
            configuration.addAnnotatedClass(Produit.class);
            configuration.addAnnotatedClass(Commande.class);
            configuration.addAnnotatedClass(LigneCommandeProduit.class);
            
            // Création du ServiceRegistry
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();
            
            // Création de la SessionFactory
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Erreur lors de l'initialisation de SessionFactory: " + e.getMessage());
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}