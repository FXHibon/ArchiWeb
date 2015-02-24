package fr.epsi.firstprojects.services;

import fr.epsi.firstprojects.beans.Utilisateur;

public class LogonService {

	public static boolean isAuthorized(Utilisateur user) {
		boolean authorized = true;
		if (user == null) {
			authorized = false;
		} else if (user.getLogin() == null || user.getPassword() == null){
			authorized = false;
		} else if (!user.getLogin().equals(user.getPassword())) {
			authorized = false;
		}
		return authorized;
	}
}
