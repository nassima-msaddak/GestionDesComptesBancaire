package org.gestion.banque.metier;

import java.util.List;

import org.gestion.banque.entities.Client;
import org.gestion.banque.entities.Compte;
import org.gestion.banque.entities.Employe;
import org.gestion.banque.entities.Groupe;
import org.gestion.banque.entities.Operation;

public interface IBanqueMetier {

    // les ajouts --> add
	 public Client addClient(Client c);
	 public Employe addEmploye(Employe e, Long codeSup);
	 public Groupe addGroupe(Groupe g);
	 public void addEmployeToGroupe(Long codeEmp,Long codeGr);
	 public Compte addCompte(Compte c , Long codeClient ,Long CodeEmploye);
     
    
	 //les 'effectuer' --> dans la couche metier  
	 
     public void versement(String codeCompte, double montant, Long codeEmploye);
	 public void retrait(String codeCompte, double montant, Long codeEmploye);
	 public void virement(String codeCompteRetrait,String codeCompteVersement,double montant, Long codeEmploye);
    
    
    
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
