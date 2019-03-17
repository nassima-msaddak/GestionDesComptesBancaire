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


@Entity
public class Groupe  implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CODE_GR")
	private Long codeGroupe;
	private String nomGroupe;
	//attributs d'association
	@ManyToMany(mappedBy="groupes")  //çàd l'association est décrite pour l'attribut  $$$ 'groupes' $$$ de la classe 'Employe' qui représente le type de la collection--> si non on fait comme suit:
	//@JoinTable(name="EMP_GR", joinColumns=@JoinColumn(name="CODE_GR")  ,inverseJoinColumns=@JoinColumn(name="CODE_EMP")   )
	private Collection<Employe> employes;  //un groupe contient plusieurs employes
	
	
	public Groupe(String nomGroupe) {
		super();
		this.nomGroupe = nomGroupe;
	}

	public Long getCodeGroupe() {
		return codeGroupe;
	}





	public void setCodeGroupe(Long codeGroupe) {
		this.codeGroupe = codeGroupe;
	}





	public String getNomGroupe() {
		return nomGroupe;
	}





	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}





	public Collection<Employe> getEmployes() {
		return employes;
	}





	public void setEmployes(Collection<Employe> employes) {
		this.employes = employes;
	}





	public Groupe() {
		super();// TODO Auto-generated constructor stub
	}

}
