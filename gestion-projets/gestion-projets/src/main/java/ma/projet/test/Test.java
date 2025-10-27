package ma.projet.test;

import ma.projet.classes.*;
import ma.projet.service.*;
import ma.projet.util.HibernateUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Test {

    private static Date makeDate(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.clear();
        c.set(year, month, day);
        return c.getTime();
    }

    public static void main(String[] args) {
        // services utilisés
        EmployeService employeService = new EmployeService();
        ProjetService projetService = new ProjetService();
        TacheService tacheService = new TacheService();
        EmployeTacheService employeTacheService = new EmployeTacheService();

        try {
            // --- Création de données de test ---
            Employe chef = new Employe();
            chef.setNom("Martin");
            chef.setPrenom("Paul");
            chef.setTelephone("0600000000");
            employeService.save(chef);

            Projet projet = new Projet();
            projet.setNom("Gestion de stock");
            projet.setDateDebut(makeDate(2013, Calendar.JANUARY, 14));
            projet.setDateFin(makeDate(2013, Calendar.DECEMBER, 31));
            projet.setChef(chef);
            projetService.save(projet);

            Tache t1 = new Tache();
            t1.setNom("Analyse");
            t1.setDateDebut(makeDate(2013, Calendar.FEBRUARY, 10));
            t1.setDateFin(makeDate(2013, Calendar.FEBRUARY, 20));
            t1.setProjet(projet);
            tacheService.save(t1);

            Tache t2 = new Tache();
            t2.setNom("Conception");
            t2.setDateDebut(makeDate(2013, Calendar.MARCH, 10));
            t2.setDateFin(makeDate(2013, Calendar.MARCH, 15));
            t2.setProjet(projet);
            tacheService.save(t2);

            Tache t3 = new Tache();
            t3.setNom("Développement");
            t3.setDateDebut(makeDate(2013, Calendar.APRIL, 10));
            t3.setDateFin(makeDate(2013, Calendar.APRIL, 25));
            t3.setProjet(projet);
            tacheService.save(t3);

            // associer l'employé aux tâches avec dates réelles
            EmployeTache et1 = new EmployeTache();
            et1.setEmploye(chef);
            et1.setTache(t1);
            et1.setDateDebutReelle(makeDate(2013, Calendar.FEBRUARY, 10));
            et1.setDateFinReelle(makeDate(2013, Calendar.FEBRUARY, 20));
            employeTacheService.save(et1);

            EmployeTache et2 = new EmployeTache();
            et2.setEmploye(chef);
            et2.setTache(t2);
            et2.setDateDebutReelle(makeDate(2013, Calendar.MARCH, 10));
            et2.setDateFinReelle(makeDate(2013, Calendar.MARCH, 15));
            employeTacheService.save(et2);

            EmployeTache et3 = new EmployeTache();
            et3.setEmploye(chef);
            et3.setTache(t3);
            et3.setDateDebutReelle(makeDate(2013, Calendar.APRIL, 10));
            et3.setDateFinReelle(makeDate(2013, Calendar.APRIL, 25));
            employeTacheService.save(et3);

            // --- Récupération et affichage ---
            List<EmployeTache> tachesRealisees = projetService.getTachesRealisees(projet);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            System.out.println("Projet : " + projet.getId() + "    Nom : " + projet.getNom()
                    + "    Date début : " + new SimpleDateFormat("dd MMMM yyyy").format(projet.getDateDebut()));
            System.out.println("Liste des tâches:");
            System.out.printf("%-4s %-15s %-18s %-18s%n", "Num", "Nom", "Date Début Réelle", "Date Fin Réelle");

            for (EmployeTache et : tachesRealisees) {
                System.out.printf("%-4d %-15s %-18s %-18s%n",
                        et.getTache().getId(),
                        et.getTache().getNom(),
                        et.getDateDebutReelle() == null ? "N/A" : sdf.format(et.getDateDebutReelle()),
                        et.getDateFinReelle() == null ? "N/A" : sdf.format(et.getDateFinReelle())
                );
            }

        } finally {
            // ferme proprement la SessionFactory
            HibernateUtil.shutdown();
        }
    }
}
