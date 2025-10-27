package ma.projet.classes;




import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "projet")
public class Projet {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;


private String nom;


@Temporal(TemporalType.DATE)
private Date dateDebut;


@Temporal(TemporalType.DATE)
private Date dateFin;


// Chef de projet (employe)
@ManyToOne
@JoinColumn(name = "chef_id")
private Employe chef;


@OneToMany(mappedBy = "projet", cascade = CascadeType.ALL, orphanRemoval = true)
private Set<Tache> taches = new HashSet<>();


// getters / setters
public Integer getId() { return id; }
public void setId(Integer id) { this.id = id; }
public String getNom() { return nom; }
public void setNom(String nom) { this.nom = nom; }
public Date getDateDebut() { return dateDebut; }
public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }
public Date getDateFin() { return dateFin; }
public void setDateFin(Date dateFin) { this.dateFin = dateFin; }
public Employe getChef() { return chef; }
public void setChef(Employe chef) { this.chef = chef; }
public Set<Tache> getTaches() { return taches; }
public void setTaches(Set<Tache> taches) { this.taches = taches; }
}