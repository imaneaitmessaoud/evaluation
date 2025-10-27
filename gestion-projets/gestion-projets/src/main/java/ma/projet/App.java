package ma.projet;

import ma.projet.classes.*;
import ma.projet.service.*;
import ma.projet.util.HibernateUtil;

import java.util.Date;
import java.util.List;

public class App {
    public static void main(String[] args) {
        EmployeService employeService = new EmployeService();
        ProjetService projetService = new ProjetService();
        TacheService tacheService = new TacheService();
        EmployeTacheService etService = new EmployeTacheService();

        Employe chef = new Employe();
        chef.setNom("Dupont");
        chef.setPrenom("Jean");
        chef.setTelephone("0600000000");
        employeService.save(chef);

        Projet p = new Projet();
        p.setNom("Projet A");
        p.setDateDebut(new Date());
        p.setDateFin(new Date());
        p.setChef(chef);
        projetService.save(p);

        Tache t = new Tache();
        t.setNom("Analyse");
        t.setDateDebut(new Date());
        t.setDateFin(new Date());
        t.setPrix(1000);
        t.setProjet(p);
        tacheService.save(t);

        EmployeTache et = new EmployeTache();
        et.setEmploye(chef);
        et.setTache(t);
        et.setDateDebutReelle(new Date());
        et.setDateFinReelle(new Date());
        etService.save(et);

        List<Tache> tachesParEmploye = employeService.getTachesRealiseesParEmploye(chef.getId());
        System.out.println("Tâches réalisées par " + chef.getNom() + ": " + tachesParEmploye.size());

        List<Projet> projetsGeres = employeService.getProjetsGeresParEmploye(chef.getId());
        System.out.println("Projets gérés: " + projetsGeres.size());

        List<Tache> tachesPlan = projetService.getTachesPlanifiees(p.getId());
        System.out.println("Tâches planifiées pour projet: " + tachesPlan.size());

        List<Object[]> tachesReal = projetService.getTachesRealiseesAvecDatesReelles(p.getId());
        System.out.println("Tâches réalisées avec dates réelles: " + tachesReal.size());

        HibernateUtil.shutdown();
    }
}
