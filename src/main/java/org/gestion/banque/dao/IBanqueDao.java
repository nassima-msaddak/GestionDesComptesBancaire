package org.gestion.banque.dao;
//on est dans la couche dao --> donc on ne met que les fonctionalités de base (select, add, delete,update) 
// --> çàd que : ajout , consulter, supprimer , mettre à jour --> afin de distinguer la couche dao de la couche metier
// --> et dans la couche metier , on met ce qui est spécifications fonctionnels

import java.util.List;

import org.gestion.banque.entities.Client;
import org.gestion.banque.entities.Compte;
import org.gestion.banque.entities.Employe;
import org.gestion.banque.entities.Groupe;
import org.gestion.banque.entities.Operation;

public interface IBanqueDao {
	
     // les ajouts --> add
	 public Client addClient(Client c);
	 public Employe addEmploye(Employe e, Long codeSup);
	 public Groupe addGroupe(Groupe g);
	 public void addEmployeToGroupe(Long codeEmp,Long codeGr);
	 public Compte addCompte(Compte c , Long codeClient ,Long CodeEmploye);
     public Operation addOperation(Operation op , String codeCompte , Long codeEmploye);
	 
	 
     
	 //les 'effectuer' --> dans la couche metier --> donc à  éliminer de cette couche
	 
     // public void versement(String codeCompte, double montant, Long codeEmploye);
	 // public void retrait(String codeCompte, double montant, Long codeEmploye);
	 //  public void virement(String codeCompteRetrait,String codeCompteVersement,double montant, Long codeEmploye);
     
     
     
     //les 'consulter'  --> get
	 public Compte getCompte(String codeCompte);
	 public List<Operation> getOperationsCompte(String codeCompte);
	 public Client getClient(Long codeClient);
	 public List<Client>  getClientsParMotClé(String mc);
	 public List<Compte> getComptesClient(Long codeClient);
	 public List<Compte> getComptesCreerParEmploye(Long codeEmploye);
	 public List<Employe>  getEmployes();
	 public List<Groupe>  getGroupes();
     public List<Employe> getEmployesGroupe(String codegroupe);
	 
}
