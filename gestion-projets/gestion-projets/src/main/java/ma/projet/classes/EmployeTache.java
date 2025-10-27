package ma.projet.classes;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;




@Entity
@Table(name = "employe_tache")
public class EmployeTache {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;


@Temporal(TemporalType.DATE)
private Date dateDebutReelle;


@Temporal(TemporalType.DATE)
private Date dateFinReelle;


@ManyToOne
@JoinColumn(name = "employe_id")
private Employe employe;


@OneToOne
@JoinColumn(name = "tache_id")
private Tache tache;


// getters / setters
public Integer getId() { return id; }
public void setId(Integer id) { this.id = id; }
public Date getDateDebutReelle() { return dateDebutReelle; }
public void setDateDebutReelle(Date dateDebutReelle) { this.dateDebutReelle = dateDebutReelle; }
public Date getDateFinReelle() { return dateFinReelle; }
public void setDateFinReelle(Date dateFinReelle) { this.dateFinReelle = dateFinReelle; }
public Employe getEmploye() { return employe; }
public void setEmploye(Employe employe) { this.employe = employe; }
public Tache getTache() { return tache; }
public void setTache(Tache tache) { this.tache = tache; }
}