package modele;

import javax.servlet.http.HttpSession;

public class Administrateur extends Personnel {

	public Administrateur(int id, HttpSession session) {
		super(id, session);
	}

}
