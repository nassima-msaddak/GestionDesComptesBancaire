package org.gestion.banque.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_OP",discriminatorType=DiscriminatorType.STRING,length=1) // "V" : si Versement et "R": si retrait
public class Operation implements Serializable {

	//attributs de l'entité
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long numeroOperation;
	private Date dateOperation;
	private double montant;
	
	//attributs d'association
	@ManyToOne
	@JoinColumn(name="CODE_EMP")
	private Employe employe; //une operation est faite par un employe
    @ManyToOne
    @JoinColumn(name="CODE_COMPTE")   //"CODE_COMPTE" est la clé étrangère vers la table 'COMPTE'
	private Compte compte; // une operation appartient à un compte
	 
	
	
	
	//on ne garde pas dans le constructeur en parametres  les attributs d'association
	public Operation(Date dateOperation, double montant) {
		super();
		this.dateOperation = dateOperation;
		this.montant = montant;
	}




	public Operation() {
		super();// TODO Auto-generated constructor stub
	}




	public Long getNumeroOperation() {
		return numeroOperation;
	}




	public void setNumeroOperation(Long numeroOperation) {
		this.numeroOperation = numeroOperation;
	}




	public Date getDateOperation() {
		return dateOperation;
	}




	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}




	public double getMontant() {
		return montant;
	}




	public void setMontant(double montant) {
		this.montant = montant;
	}




	public Employe getEmploye() {
		return employe;
	}




	public void setEmploye(Employe employe) {
		this.employe = employe;
	}




	public Compte getCompte() {
		return compte;
	}




	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	
	
}
