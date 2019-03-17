package org.gestion.banque.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("R") //l'enregistrement d'une instance de Retrait attribue la valeur "R" à la colonne "TYPE_OP" dans la table "Operation"
public class Retrait extends Operation {

	
	public Retrait() {
		super();// TODO Auto-generated constructor stub
	}

	public Retrait(Date dateOperation, double montant) {
		super(dateOperation, montant);
		// TODO Auto-generated constructor stub
	}

	
	
}
