    package ma.projet.classes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employe")
public class Employe {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;


private String nom;
private String prenom;
private String telephone;


// projets gérés
@OneToMany(mappedBy = "chef")
private Set<Projet> projets = new HashSet<>();


// association EmployeTache
@OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
private Set<EmployeTache> employeTaches = new HashSet<>();

public Employe() {}

public Employe(String nom, String prenom, String telephone) {
    this.nom = nom;
    this.prenom = prenom;
    this.telephone = telephone;
}
// getters / setters
public Integer getId() { return id; }
public void setId(Integer id) { this.id = id; }
public String getNom() { return nom; }
public void setNom(String nom) { this.nom = nom; }
public String getPrenom() { return prenom; }
public void setPrenom(String prenom) { this.prenom = prenom; }
public String getTelephone() { return telephone; }
public void setTelephone(String telephone) { this.telephone = telephone; }
public Set<Projet> getProjets() { return projets; }
public void setProjets(Set<Projet> projets) { this.projets = projets; }
public Set<EmployeTache> getEmployeTaches() { return employeTaches; }
public void setEmployeTaches(Set<EmployeTache> employeTaches) { this.employeTaches = employeTaches; }
}