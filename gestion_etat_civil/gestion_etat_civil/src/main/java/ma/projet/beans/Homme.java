package ma.projet.beans;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "homme")
public class Homme extends Personne {

    @OneToMany(mappedBy = "homme", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mariage> mariages;

    public Homme() {
        super();
    }

    // Constructeur complet
    public Homme(String nom, String prenom, String telephone, String adresse,
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
