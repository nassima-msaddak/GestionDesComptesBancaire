package org.gestion.banque.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Employe implements Serializable {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name ="CODE_EMP")
	private Long codeEmploye;
	private String nomEmploye;
	
	//attributs d'association
	@ManyToOne
	@JoinColumn(name="CODE_EMP_SUP")
	private Employe employeSuperieur; // un employe possede son superieur hierarchique
	@ManyToMany
	   // puisque on a "ManyToMany" donc on aura besoin à une table de jointure qu'on va la nommée "EMP_GR" (mais si on écrit  uniquement @ManyToMany , il y'aura des valeurs par défaut pour le nom de la table et les noms des colonnes de la table crée)  --> d'où l'annotation suivante
	   // @JoinTable(name="EMP_GR") --> cette entity (table) contient par defaut deux colonnes (deux clés étrangères)  qui présentent les identifiants de la table 'employe' et 'groupe'   $mais on peut les spécifier comme suit : @JoinTable(     name="EMP_GR",     joinColumns=@JoinColumn(name="CODE_EMP"),    inverseJoinColumns= @JoinColumn(name="CODE_GR")) $
	 @JoinTable(     name="EMP_GR",     joinColumns=@JoinColumn(name="CODE_EMP"),    inverseJoinColumns= @JoinColumn(name="CODE_GR"))
	private Collection<Groupe> groupes; //un employe appartient à plusieurs groupe
	
	
	//les getters et setters 
	public Long getCodeEmploye() {
		return codeEmploye;
	}

	public void setCodeEmploye(Long codeEmploye) {
		this.codeEmploye = codeEmploye;
	}

	public String getNomEmploye() {
		return nomEmploye;
	}

	public void setNomEmploye(String nomEmploye) {
		this.nomEmploye = nomEmploye;
	}

	public Employe getEmployeSuperieur() {
		return employeSuperieur;
	}

	public void setEmployeSuperieur(Employe employeSuperieur) {
		this.employeSuperieur = employeSuperieur;
	}

	public Collection<Groupe> getGroupes() {
		return groupes;
	}

	public void setGroupes(Collection<Groupe> groupes) {
		this.groupes = groupes;
	}

	public Employe(String nomEmploye) {
		super();
		this.nomEmploye = nomEmploye;
	}

	public Employe() {
		super();// TODO Auto-generated constructor stub
	}

}
