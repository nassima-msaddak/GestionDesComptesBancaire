package org.gestion.banque.metier;

import java.util.Date;
import java.util.List;

import org.gestion.banque.dao.IBanqueDao;
import org.gestion.banque.entities.Client;
import org.gestion.banque.entities.Compte;
import org.gestion.banque.entities.Employe;
import org.gestion.banque.entities.Groupe;
import org.gestion.banque.entities.Operation;
import org.gestion.banque.entities.Retrait;
import org.gestion.banque.entities.Versement;

import org.springframework.transaction.annotation.Transactional;   // Spring gère les transactions 

// gérer les transactions est à la charge de Spring (donc on va déléguer la gestion des transaction à Spring) et pour cette raison, on a inclu la dependence "spring-tx" --> à travers l'annotation @Transactional

@Transactional   // à cette emplacement (çàd avant la classe), signifie que toutes les méthodes sont transactionnels  --> çàd: à chaque fois où 
// j'appelle une méthode de cette couche ,Spring ouvre une transaction, exécute la méthode (et si la méthode genere une exception , il fait un aireback 
// si non il fait un commit.

//chaque méthode appelé, déclenche une transaction.
public class BanqueMetierImpl implements IBanqueMetier { //puis , on va faire le couplage faible avec la couche dao --> la couche metier va faire appel à la couche dao
    private IBanqueDao dao; // attribut pour le couplage faible (couche metier et couche dao)
    // la couche metier ne fait que appeler les methodes de la couche dao à travers l'attribut dao
    
    // les getters et setters de l'attribut du couplage qui est l'objet interface Dao
	public IBanqueDao getDao() {
		return dao;
	}
	public void setDao(IBanqueDao dao) {
		this.dao = dao;
	}
    // fin de getters et setters 
	
	@Override
	public Client addClient(Client c) {
		return dao.addClient(c);  
	}

	@Override
	public Employe addEmploye(Employe e, Long codeSup) { 
		return  dao.addEmploye(e, codeSup);
	}

	@Override
	public Groupe addGroupe(Groupe g) { 
		return dao.addGroupe(g);
	}

	@Override
	public void addEmployeToGroupe(Long codeEmp, Long codeGr) { 
		dao.addEmployeToGroupe(codeEmp, codeGr);
	}

	@Override
	public Compte addCompte(Compte c, Long codeClient, Long CodeEmploye) {
		return dao.addCompte(c, codeClient, CodeEmploye);
	}

	@Override
	public void versement(String codeCompte, double montant, Long codeEmploye) {
	 
		Versement operationVersement = new Versement( new Date() , montant );  //içi , on a uniquement créer l'objet
		
		dao.addOperation(operationVersement, codeCompte, codeEmploye); // içi , on a enregistrer les données
		
		// aprés le versement, on doit modifier le solde du compte sur lequel effectuer l'operation de versement
		Compte compteDuVersement = dao.getCompte(codeCompte);
		double nouveauSolde = compteDuVersement.getSolde() + montant;
		compteDuVersement.setSolde(nouveauSolde);  // normalement, il manque la mise à jours dans la base de donnée (à vérifier)
	}

	@Override
	public void retrait(String codeCompte, double montant, Long codeEmploye) {
	   
		Retrait operationRetrait = new Retrait( new Date() , montant );  //içi , on a uniquement créer l'objet
		
		dao.addOperation(operationRetrait, codeCompte, codeEmploye); // içi , on a enregistrer les données
		
		// aprés le retrait, on doit modifier le solde du compte sur lequel effectuer l'operation de retrait
		Compte compteDuRetrait = dao.getCompte(codeCompte);
		double nouveauSolde = compteDuRetrait.getSolde() - montant;
		compteDuRetrait.setSolde(nouveauSolde);  // normalement, il manque la mise à jours dans la base de donnée (à vérifier)
	}


	@Override
	public void virement(String codeCompteRetrait, String codeCompteVersement, double montant, Long codeEmploye) {
	    
		retrait(codeCompteRetrait,montant,codeEmploye);	
		versement(codeCompteVersement,montant,codeEmploye);
		
	}

	@Override
	public Compte getCompte(String codeCompte) { 
		return dao.getCompte(codeCompte);
	}

	@Override
	public List<Operation> getOperationsCompte(String codeCompte) { 
		return dao.getOperationsCompte(codeCompte);
	}

	@Override
	public Client getClient(Long codeClient) { 
		return dao.getClient(codeClient);
	}

	@Override
	public List<Client> getClientsParMotClé(String mc) { 
		return dao.getClientsParMotClé(mc);
	}

	@Override
	public List<Compte> getComptesClient(Long codeClient) { 
		return dao.getComptesClient(codeClient);
	}

	@Override
	public List<Compte> getComptesCreerParEmploye(Long codeEmploye) { 
		return dao.getComptesCreerParEmploye(codeEmploye);
	}

	@Override
	public List<Employe> getEmployes() { 
		return dao.getEmployes();
	}

	@Override
	public List<Groupe> getGroupes() { 
		return dao.getGroupes();
	}

	@Override
	public List<Employe> getEmployesGroupe(String codegroupe) { 
		return dao.getEmployesGroupe(codegroupe);
	}

	 

}
