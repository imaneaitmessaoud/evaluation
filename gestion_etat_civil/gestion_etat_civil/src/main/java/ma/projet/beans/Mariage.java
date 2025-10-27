package ma.projet.beans;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mariage")
public class Mariage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Homme homme;

    @ManyToOne
    private Femme femme;

    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int nbrEnfants;

    public Mariage() {}

    public Mariage(Homme homme, Femme femme, LocalDate dateDebut, LocalDate dateFin, int nbrEnfants) {
        this.homme = homme;
        this.femme = femme;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbrEnfants = nbrEnfants;
    }

    // Getters et setters
    public Integer getId() { return id; }

    public Homme getHomme() { return homme; }
    public void setHomme(Homme homme) { this.homme = homme; }

    public Femme getFemme() { return femme; }
    public void setFemme(Femme femme) { this.femme = femme; }

    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }

    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }

    public int getNbrEnfants() { return nbrEnfants; }
    public void setNbrEnfants(int nbrEnfants) { this.nbrEnfants = nbrEnfants; }
}
