package org.gestion.banque.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.gestion.banque.entities.Client;
import org.gestion.banque.entities.Compte;
import org.gestion.banque.entities.Employe;
import org.gestion.banque.entities.Groupe;
import org.gestion.banque.entities.Operation;

public class BanqueDaoImpl implements IBanqueDao {
	@PersistenceContext   //on a besoin de cette annotation --> parceque aprés on aura  besoin de configurer JPA avec Spring
// Pour gérer la persistance , on doit faire appel à JPA --> d'où l'attribut 'EntityManager' qui permet de faire le mapping objet relationnel ORM
	private EntityManager em;   //ce attribut pour le mapping objet relationnel ORM
	
	
	//on va utiliser Hibernate mais, On fait au début appel à JPA  et aprés  on décide qu'est ce qu'on va utiliser (   (JPA/Hibernate) ou (JPA/TopLink)  ) --> on gère les transations avec Spring
	@Override
	public Client addClient(Client c) { 
		em.persist(c);   // code très simple (puisqu'on gère les transactions avec Spring) --> ensuite , je retourne le client enregistré
		return c;
	}

	@Override
	public Employe addEmploye(Employe e, Long codeSup) { 
		
		if (codeSup != null) {
			Employe employeSup=em.find(Employe.class, codeSup);
			e.setEmployeSuperieur(employeSup);
		}
		
		em.persist(e); // l'employe 'e' est l'objet Employe aprés avoir associer à son attribut 'employeSuperieur' l'objet Employe touver à partir du codeSup (parametre non nulle de la function)
		return e;
	}

	@Override
	public Groupe addGroupe(Groupe g) { 
		em.persist(g);
		return g;
	}

	@Override
	public void addEmployeToGroupe(Long codeEmp, Long codeGr) {  //on doit chercher les deux objets Employe et Groupe --> puis les associer
		
		Employe employe = em.find(Employe.class, codeEmp); 
		Groupe groupe = em.find(Groupe.class, codeGr);
		
		// puisque l'association (entre les entity) est dans les deux sens (bidirectionnel) --> donc , on doit ajouter dans les deux sens 
		 employe.getGroupes().add(groupe); 
	     groupe.getEmployes().add(employe);
	}

	@Override
	public Compte addCompte(Compte c, Long codeClient, Long CodeEmploye) {
		
		Employe employe = em.find(Employe.class, CodeEmploye);
		Client client = em.find(Client.class, codeClient);
		
		c.setClient(client);
		c.setEmploye(employe);
		
		em.persist(c);
		
		return c ;
	}

	@Override
	public Operation addOperation(Operation op, String codeCompte, Long codeEmploye) {
		 
		Compte compte = em.find(Compte.class, codeCompte);  //ou bien Compte compte = getCompte(codeCompte);
		Employe employe = em.find(Employe.class, codeEmploye) ;    
		
		op.setCompte(compte);
		op.setEmploye(employe);
			
		em.persist(op);
		
		return op;
	}

	@Override
	public Compte getCompte(String codeCompte) {
		Compte compte = em.find(Compte.class, codeCompte);  
		
		//on doit gérer le cas où on n'a pas trouver un compte --> lever l'exception et afficher un message
		if(compte==null) throw new RuntimeException("Compte introuvable !");
			 
		return compte;
	}

	@Override
	public List<Operation> getOperationsCompte(String codeCompte) {
		// on va utiliser cette fois ci 'Query' ---- ( on doit choisir Query de JPA çàd javax.persistence.Query)
		// 'find' , on l'utilise quand on va chercher un objet sachant sa clé primère --> si non (si c'est une liste), on doit passer par une requete 'Query'
		Query request = em.createQuery("select op from Operation op where op.compte.codeCompte =: x ");  //  CODE_COMPTE est le non de la colonne (de l'attribut codeCompte )
		request.setParameter("x", codeCompte);  
		// fin de la requete
		
		
		// retourner le resultat trouvé
		 return request.getResultList();   
	}

	@Override
	public Client getClient(Long codeClient) { 
		// recherche de client à partir de son clé primère --> donc , on utilise 'find' et non pas une requete
		Client client = em.find(Client.class, codeClient);
		
		
		//on doit gérer le cas où on n'a pas trouver un client --> lever l'exception et afficher un message
	    if(client==null) throw new RuntimeException("Client introuvable !");
				
		return client;
	}

	@Override
	public List<Client> getClientsParMotClé(String mc) { 
		
		Query request = em.createQuery(" select c from Client  c  where  c.nomClient =: x ");
		request.setParameter(  "x"    ,   "%"+mc+"%"  ) ;
		

		return request.getResultList();
	}

	@Override
	public List<Compte> getComptesClient(Long codeClient) {
		Query request = em.createQuery("select c from Compte c where c.client.codeClient =: x ");
		request.setParameter("x", codeClient);
		
		
		return request.getResultList();
	}

	@Override
	public List<Compte> getComptesCreerParEmploye(Long codeEmploye) {
		 Query request = em.createQuery("select c from Compte c where c.employe.codeEmploye =: x");
		 request.setParameter("x", codeEmploye);
		return request.getResultList();
	}

	@Override
	public List<Employe> getEmployes() {
		 Query request = em.createQuery("select e from Employe e"); 
		return request.getResultList();
	}

	@Override
	public List<Groupe> getGroupes() {
		Query request = em.createQuery("select g from Groupe g"); 
		return request.getResultList();
	}

	@Override
	public List<Employe> getEmployesGroupe(String codegroupe) {
		Query request = em.createQuery("select e from Employe e where e.groupes.codeGroupe =: x ");
		request.setParameter("x", codegroupe);
		
		
		return request.getResultList();
	}

	 
}
