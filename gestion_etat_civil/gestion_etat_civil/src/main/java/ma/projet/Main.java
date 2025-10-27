package ma.projet;

import ma.projet.beans.Homme;
import ma.projet.beans.Femme;
import ma.projet.beans.Mariage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Création de l'homme
        Homme safi = new Homme("SAFI", "SAID", "0600000000", "Rabat", LocalDate.of(1970, 1, 1), new ArrayList<>());

        // Création des femmes
        Femme salima = new Femme("SALIMA", "RAMI", "0600000001", "Rabat", LocalDate.of(1970, 5, 5), new ArrayList<>());
        Femme amal = new Femme("AMAL", "ALI", "0600000002", "Casablanca", LocalDate.of(1972, 3, 3), new ArrayList<>());
        Femme wafa = new Femme("WAFA", "ALAOUI", "0600000003", "Marrakech", LocalDate.of(1975, 7, 7), new ArrayList<>());
        Femme karima = new Femme("KARIMA", "ALAMI", "0600000004", "Fes", LocalDate.of(1968, 9, 9), new ArrayList<>());

        // Création des mariages
        Mariage mariage1 = new Mariage(safi, salima, LocalDate.of(1990, 9, 3), null, 4);
        Mariage mariage2 = new Mariage(safi, amal, LocalDate.of(1995, 9, 3), null, 2);
        Mariage mariage3 = new Mariage(safi, wafa, LocalDate.of(2000, 11, 4), null, 3);
        Mariage mariage4 = new Mariage(safi, karima, LocalDate.of(1989, 9, 3), LocalDate.of(1990, 9, 3), 0);

        // Ajouter mariages à l'homme
        safi.getMariages().add(mariage1);
        safi.getMariages().add(mariage2);
        safi.getMariages().add(mariage3);
        safi.getMariages().add(mariage4);

        // Affichage
        System.out.println("Nom : " + safi.getNom() + " " + safi.getPrenom());

        // Mariages en cours
        System.out.println("Mariages En Cours :");
        int count = 1;
        for (Mariage m : safi.getMariages()) {
            if (m.getDateFin() == null) {
                System.out.printf("%d. Femme : %s %s   Date Début : %s    Nbr Enfants : %d%n",
                        count++, m.getFemme().getNom(), m.getFemme().getPrenom(),
                        m.getDateDebut().format(formatter), m.getNbrEnfants());
            }
        }

        // Mariages échoués
        System.out.println("\nMariages échoués :");
        count = 1;
        for (Mariage m : safi.getMariages()) {
            if (m.getDateFin() != null) {
                System.out.printf("%d. Femme : %s %s  Date Début : %s%n   Date Fin : %s    Nbr Enfants : %d%n",
                        count++, m.getFemme().getNom(), m.getFemme().getPrenom(),
                        m.getDateDebut().format(formatter),
                        m.getDateFin().format(formatter),
                        m.getNbrEnfants());
            }
        }
    }
}
