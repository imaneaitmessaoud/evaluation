package ma.projet.beans;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "femme")
@NamedQuery(
    name = "Femme.findMarriedAtLeastTwo",
    query = "SELECT f FROM Femme f WHERE (SELECT COUNT(m) FROM Mariage m WHERE m.femme = f) >= 2"
)
public class Femme extends Personne {

    @OneToMany(mappedBy = "femme", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mariage> mariages;

    public Femme() {
        super();
    }

    // Constructeur complet
    public Femme(String nom, String prenom, String telephone, String adresse,
                 LocalDate dateNaissance, List<Mariage> mariages) {
        super(nom, prenom, telephone, adresse, dateNaissance);
        this.mariages = mariages;
    }

    // Getters et setters
    public List<Mariage> getMariages() {
        return mariages;
    }

    public void setMariages(List<Mariage> mariages) {
        this.mariages = mariages;
    }
}
