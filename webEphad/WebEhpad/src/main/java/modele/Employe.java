package modele;

import javax.servlet.http.HttpSession;

public class Employe extends Personnel {

	private int ide;

	public Employe(int idE, int idP, HttpSession session) {
		super(idP, session);
		this.ide = idE;
	}

	public int getIdE() {
		return ide;
	}

	@Override
	public String toString() {
		return this.getNom() + " " + this.getPrenom();
	}

}
