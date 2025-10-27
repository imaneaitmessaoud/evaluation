package ma.projet.test;

import ma.projet.beans.Homme;
import ma.projet.beans.Femme;
import ma.projet.beans.Mariage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestEtatCivil {

    public static void main(String[] args) {

        // Création des hommes
        Homme h1 = new Homme("Ahmed", "Ali", "0612345678", "Casablanca", LocalDate.of(1980, 1, 1), new ArrayList<>());
        Homme h2 = new Homme("Mohamed", "Omar", "0623456789", "Rabat", LocalDate.of(1982, 2, 2), new ArrayList<>());
        Homme h3 = new Homme("Youssef", "Karim", "0634567890", "Marrakech", LocalDate.of(1985, 3, 3), new ArrayList<>());
        Homme h4 = new Homme("Rachid", "Samir", "0645678901", "Fès", LocalDate.of(1981, 4, 4), new ArrayList<>());
        Homme h5 = new Homme("Khalid", "Nabil", "0656789012", "Tangier", LocalDate.of(1983, 5, 5), new ArrayList<>());
        Homme h6 = new Homme("Hassan", "Amine", "0667890123", "Agadir", LocalDate.of(1984, 6, 6), new ArrayList<>());
        Homme h7 = new Homme("Othman", "Rami", "0678901234", "Meknès", LocalDate.of(1986, 7, 7), new ArrayList<>());
        Homme h8 = new Homme("Said", "Anas", "0689012345", "Tetouan", LocalDate.of(1987, 8, 8), new ArrayList<>());
        Homme h9 = new Homme("Imad", "Yassine", "0690123456", "El Jadida", LocalDate.of(1988, 9, 9), new ArrayList<>());
        Homme h10 = new Homme("Bilal", "Adil", "0601234567", "Oujda", LocalDate.of(1989, 10, 10), new ArrayList<>());

        // Création des femmes
        Femme f1 = new Femme("Fatima", "Leila", "0667890123", "Casablanca", LocalDate.of(1981, 4, 4), new ArrayList<>());
        Femme f2 = new Femme("Aicha", "Sara", "0678901234", "Rabat", LocalDate.of(1983, 5, 5), new ArrayList<>());
        Femme f3 = new Femme("Salma", "Nadia", "0689012345", "Marrakech", LocalDate.of(1986, 6, 6), new ArrayList<>());
        Femme f4 = new Femme("Imane", "Mouna", "0690123456", "Fès", LocalDate.of(1982, 7, 7), new ArrayList<>());
        Femme f5 = new Femme("Kenza", "Rania", "0601234567", "Tangier", LocalDate.of(1984, 8, 8), new ArrayList<>());
        Femme f6 = new Femme("Sara", "Lamia", "0612345678", "Agadir", LocalDate.of(1985, 9, 9), new ArrayList<>());
        Femme f7 = new Femme("Hind", "Samira", "0623456789", "Meknès", LocalDate.of(1987, 10, 10), new ArrayList<>());
        Femme f8 = new Femme("Nada", "Rita", "0634567890", "Tetouan", LocalDate.of(1988, 11, 11), new ArrayList<>());
        Femme f9 = new Femme("Oum", "Salma", "0645678901", "El Jadida", LocalDate.of(1989, 12, 12), new ArrayList<>());
        Femme f10 = new Femme("Laila", "Yasmina", "0656789012", "Oujda", LocalDate.of(1990, 1, 1), new ArrayList<>());

        // Liste globale de mariages
        List<Mariage> mariages = new ArrayList<>();

        // Création des mariages
        Mariage m1 = new Mariage(h1, f1, LocalDate.of(2000, 1, 1), null, 2);
        Mariage m2 = new Mariage(h2, f2, LocalDate.of(2002, 2, 2), null, 1);
        Mariage m3 = new Mariage(h3, f3, LocalDate.of(2004, 3, 3), null, 3);
        Mariage m4 = new Mariage(h4, f4, LocalDate.of(2006, 4, 4), null, 0);
        Mariage m5 = new Mariage(h5, f5, LocalDate.of(2008, 5, 5), null, 2);
        Mariage m6 = new Mariage(h6, f6, LocalDate.of(2010, 6, 6), null, 1);
        Mariage m7 = new Mariage(h7, f7, LocalDate.of(2012, 7, 7), null, 2);
        Mariage m8 = new Mariage(h8, f8, LocalDate.of(2014, 8, 8), null, 0);
        Mariage m9 = new Mariage(h9, f9, LocalDate.of(2016, 9, 9), null, 1);
        Mariage m10 = new Mariage(h10, f10, LocalDate.of(2018, 10, 10), null, 3);

        // Ajout des mariages aux listes des hommes et femmes
        h1.getMariages().add(m1); f1.getMariages().add(m1);
        h2.getMariages().add(m2); f2.getMariages().add(m2);
        h3.getMariages().add(m3); f3.getMariages().add(m3);
        h4.getMariages().add(m4); f4.getMariages().add(m4);
        h5.getMariages().add(m5); f5.getMariages().add(m5);
        h6.getMariages().add(m6); f6.getMariages().add(m6);
        h7.getMariages().add(m7); f7.getMariages().add(m7);
        h8.getMariages().add(m8); f8.getMariages().add(m8);
        h9.getMariages().add(m9); f9.getMariages().add(m9);
        h10.getMariages().add(m10); f10.getMariages().add(m10);

        // Ajout à la liste globale
        mariages.add(m1); mariages.add(m2); mariages.add(m3); mariages.add(m4); mariages.add(m5);
        mariages.add(m6); mariages.add(m7); mariages.add(m8); mariages.add(m9); mariages.add(m10);

        // Affichage
        for (Mariage m : mariages) {
            System.out.println("Mariage entre " + m.getHomme().getNom() + " et " + m.getFemme().getNom() +
                    ", début: " + m.getDateDebut() + ", nombre d'enfants: " + m.getNbrEnfants());
        }
    }
}
