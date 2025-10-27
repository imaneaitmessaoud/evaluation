package ma.projet.exercice1.test;

import ma.projet.exercice1.classes.Categorie;
import ma.projet.exercice1.classes.Commande;
import ma.projet.exercice1.classes.LigneCommandeProduit;
import ma.projet.exercice1.classes.Produit;
import ma.projet.exercice1.service.CategorieService;
import ma.projet.exercice1.service.CommandeService;
import ma.projet.exercice1.service.LigneCommandeService;
import ma.projet.exercice1.service.ProduitService;
import ma.projet.exercice1.util.HibernateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestApplication {
    
    public static void main(String[] args) {
        
        // Initialisation des services
        CategorieService categorieService = new CategorieService();
        ProduitService produitService = new ProduitService();
        CommandeService commandeService = new CommandeService();
        LigneCommandeService ligneCommandeService = new LigneCommandeService();
        
        try {
            // Test 1 : Création des catégories
            System.out.println("=== Test 1 : Création des catégories ===");
            Categorie cat1 = new Categorie("INFO", "Informatique");
            Categorie cat2 = new Categorie("ELEC", "Électronique");
            
            categorieService.create(cat1);
            categorieService.create(cat2);
            System.out.println("Catégories créées avec succès !\n");
            
            // Test 2 : Création des produits
            System.out.println("=== Test 2 : Création des produits ===");
            Produit p1 = new Produit("ES12", 120, cat1);
            Produit p2 = new Produit("ZR85", 100, cat1);
            Produit p3 = new Produit("EE85", 200, cat2);
            Produit p4 = new Produit("PP11", 80, cat2);
            Produit p5 = new Produit("AA22", 150, cat1);
            
            produitService.create(p1);
            produitService.create(p2);
            produitService.create(p3);
            produitService.create(p4);
            produitService.create(p5);
            System.out.println("Produits créés avec succès !\n");
            
            // Test 3 : Création des commandes
            System.out.println("=== Test 3 : Création des commandes ===");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            Commande cmd1 = new Commande(sdf.parse("14/03/2013"));
            Commande cmd2 = new Commande(sdf.parse("20/05/2013"));
            Commande cmd3 = new Commande(sdf.parse("15/06/2013"));
            
            commandeService.create(cmd1);
            commandeService.create(cmd2);
            commandeService.create(cmd3);
            System.out.println("Commandes créées avec succès !\n");
            
            // Test 4 : Création des lignes de commande
            System.out.println("=== Test 4 : Création des lignes de commande ===");
            LigneCommandeProduit lcp1 = new LigneCommandeProduit(7, p1, cmd1);
            LigneCommandeProduit lcp2 = new LigneCommandeProduit(14, p2, cmd1);
            LigneCommandeProduit lcp3 = new LigneCommandeProduit(5, p3, cmd1);
            LigneCommandeProduit lcp4 = new LigneCommandeProduit(3, p4, cmd2);
            LigneCommandeProduit lcp5 = new LigneCommandeProduit(10, p5, cmd3);
            
            ligneCommandeService.create(lcp1);
            ligneCommandeService.create(lcp2);
            ligneCommandeService.create(lcp3);
            ligneCommandeService.create(lcp4);
            ligneCommandeService.create(lcp5);
            System.out.println("Lignes de commande créées avec succès !\n");
            
            // Test 5 : Afficher les produits par catégorie
            System.out.println("=== Test 5 : Produits par catégorie ===");
            List<Produit> produitsInfo = produitService.findByCategorie(cat1);
            System.out.println("Produits de la catégorie Informatique :");
            for (Produit p : produitsInfo) {
                System.out.println(" - " + p);
            }
            System.out.println();
            
            // Test 6 : Afficher les produits commandés entre deux dates
            System.out.println("=== Test 6 : Produits commandés entre deux dates ===");
            Date dateDebut = sdf.parse("01/03/2013");
            Date dateFin = sdf.parse("31/05/2013");
            List<Produit> produitsEntreDates = produitService.findProduitsCommandesEntreDates(dateDebut, dateFin);
            System.out.println("Produits commandés entre " + sdf.format(dateDebut) + " et " + sdf.format(dateFin) + " :");
            for (Produit p : produitsEntreDates) {
                System.out.println(" - " + p);
            }
            System.out.println();
            
            // Test 7 : Afficher les produits d'une commande
            System.out.println("=== Test 7 : Produits d'une commande ===");
            commandeService.afficherProduitsCommande(cmd1.getId());
            
            // Test 8 : Afficher les produits avec prix > 100 DH
            System.out.println("=== Test 8 : Produits avec prix > 100 DH (Requête nommée) ===");
            List<Produit> produitsChers = produitService.findByPrixSuperieur(100);
            System.out.println("Produits avec prix supérieur à 100 DH :");
            for (Produit p : produitsChers) {
                System.out.println(" - " + p.getReference() + " : " + p.getPrix() + " DH");
            }
            System.out.println();
            
            // Test 9 : Afficher toutes les catégories
            System.out.println("=== Test 9 : Liste de toutes les catégories ===");
            List<Categorie> categories = categorieService.findAll();
            for (Categorie c : categories) {
                System.out.println(" - " + c);
            }
            System.out.println();
            
            // Test 10 : Afficher tous les produits
            System.out.println("=== Test 10 : Liste de tous les produits ===");
            List<Produit> produits = produitService.findAll();
            for (Produit p : produits) {
                System.out.println(" - " + p);
            }
            
        } catch (ParseException e) {
            System.err.println("Erreur de format de date : " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erreur lors de l'exécution : " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Fermer la SessionFactory
            HibernateUtil.shutdown();
            System.out.println("\n=== Application terminée ===");
        }
    }
}