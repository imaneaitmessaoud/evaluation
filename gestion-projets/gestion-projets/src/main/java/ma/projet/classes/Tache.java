package ma.projet.classes;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


import java.util.Date;


@Entity
@Table(name = "tache")
@NamedQueries({
@NamedQuery(name = "Tache.findAbovePrice",
query = "SELECT t FROM Tache t WHERE t.prix > :prix")
})
public class Tache {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;


private String nom;


@Temporal(TemporalType.DATE)
private Date dateDebut;


@Temporal(TemporalType.DATE)
private Date dateFin;


// dates réelles
@Temporal(TemporalType.DATE)
private Date dateDebutReelle;


@Temporal(TemporalType.DATE)
private Date dateFinReelle;


private double prix;


@ManyToOne
@JoinColumn(name = "projet_id")
private Projet projet;


// association avec EmployeTache
@OneToOne(mappedBy = "tache", cascade = CascadeType.ALL)
private EmployeTache employeTache;

public Tache() {}

public Tache(String nom, Date dateDebut, Date dateFin, Projet projet) {
    this.nom = nom;
    this.dateDebut = dateDebut;
    this.dateFin = dateFin;
    this.projet = projet;
}
public Tache(String nom, Date dateDebut, Date dateFin, double prix, Projet projet) {
    this.nom = nom;
    this.dateDebut = dateDebut;
    this.dateFin = dateFin;
    this.prix = prix;
    this.projet = projet;
}

// getters / setters
public Integer getId() { return id; }
public void setId(Integer id) { this.id = id; }
public String getNom() { return nom; }
public void setNom(String nom) { this.nom = nom; }
public Date getDateDebut() { return dateDebut; }
public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }
public Date getDateFin() { return dateFin; }
public void setDateFin(Date dateFin) { this.dateFin = dateFin; }
public Date getDateDebutReelle() { return dateDebutReelle; }
public void setDateDebutReelle(Date dateDebutReelle) { this.dateDebutReelle = dateDebutReelle; }
public Date getDateFinReelle() { return dateFinReelle; }
public void setDateFinReelle(Date dateFinReelle) { this.dateFinReelle = dateFinReelle; }
public double getPrix() { return prix; }
public void setPrix(double prix) { this.prix = prix; }
public Projet getProjet() { return projet; }
public void setProjet(Projet projet) { this.projet = projet; }
public EmployeTache getEmployeTache() { return employeTache; }
public void setEmployeTache(EmployeTache employeTache) { this.employeTache = employeTache; }
}