package ma.projet;

import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.classes.Employe;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.SimpleDateFormat;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("? Application de gestion de projets ? démarrage...");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // try-with-resources pour fermer correctement la session
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            // Création d’un employé chef de projet
            Employe chef = new Employe("Dupont", "Jean", "0601020304");
            session.persist(chef);

            // Création d’un projet
            Projet projet = new Projet();
            projet.setNom("Gestion de stock");
            projet.setChef(chef);
            projet.setDateDebut(sdf.parse("14/01/2013"));
            session.persist(projet);

            // Ajout des tâches (on lie la tâche au projet)
            Tache t1 = new Tache("Analyse", sdf.parse("10/02/2013"), sdf.parse("20/02/2013"), projet);
            Tache t2 = new Tache("Conception", sdf.parse("10/03/2013"), sdf.parse("15/03/2013"), projet);
            Tache t3 = new Tache("Développement", sdf.parse("10/04/2013"), sdf.parse("25/04/2013"), projet);

            session.persist(t1);
            session.persist(t2);
            session.persist(t3);

            tx.commit();

            // --- Affichage : on récupère les tâches depuis la base pour être sûr ---
            System.out.println("\nProjet : " + projet.getId() + "     Nom : " + projet.getNom()
                    + "     Date début : 14 Janvier 2013");
            System.out.println("Liste des tâches:");
            System.out.println("Num Nom            Date Début Réelle   Date Fin Réelle");

            // Requête typée pour récupérer les tâches du projet
            List<Tache> taches = session.createQuery(
                    "select t from Tache t where t.projet.id = :pId", Tache.class)
                    .setParameter("pId", projet.getId())
                    .list();

            for (Tache t : taches) {
                System.out.printf("%-4d %-15s %-18s %-18s%n",
                        t.getId(),
                        t.getNom(),
                        t.getDateDebut() == null ? "N/A" : sdf.format(t.getDateDebut()),
                        t.getDateFin() == null ? "N/A" : sdf.format(t.getDateFin()));
            }
        } finally {
            // ferme la SessionFactory proprement
            HibernateUtil.shutdown();
        }

        System.out.println("? Fin du programme.");
    }
}
