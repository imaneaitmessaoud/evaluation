package ma.projet.exercice1.classes;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produit")
@NamedQueries({
    @NamedQuery(
        name = "Produit.findByPrixSuperieur",
        query = "SELECT p FROM Produit p WHERE p.prix > :prix"
    )
})
public class Produit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false, unique = true)
    private String reference;
    
    @Column(nullable = false)
    private float prix;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categorie_id", nullable = false)
    private Categorie categorie;
    
    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LigneCommandeProduit> ligneCommandes = new ArrayList<>();
    
    public Produit() {
    }
    
    public Produit(String reference, float prix, Categorie categorie) {
        this.reference = reference;
        this.prix = prix;
        this.categorie = categorie;
    }
    
    // Getters et Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getReference() {
        return reference;
    }
    
    public void setReference(String reference) {
        this.reference = reference;
    }
    
    public float getPrix() {
        return prix;
    }
    
    public void setPrix(float prix) {
        this.prix = prix;
    }
    
    public Categorie getCategorie() {
        return categorie;
    }
    
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    
    public List<LigneCommandeProduit> getLigneCommandes() {
        return ligneCommandes;
    }
    
    public void setLigneCommandes(List<LigneCommandeProduit> ligneCommandes) {
        this.ligneCommandes = ligneCommandes;
    }
    
    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", reference='" + reference + '\'' +
                ", prix=" + prix +
                ", categorie=" + (categorie != null ? categorie.getLibelle() : "null") +
                '}';
    }
}