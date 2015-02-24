package fr.epsi.firstprojects.jmx;

import org.apache.log4j.Level;



public class Logger implements LoggerMBean {

	@Override
	public String getLevel() {
		return org.apache.log4j.Logger.getRootLogger().getLevel().toString();
	}

	@Override
	public void setLevel(String level) {
		if (level != null && !level.equals("")) {
			org.apache.log4j.Logger.getRootLogger().error("Changement du log : " + level);
			if (level.equals("DEBUG")) {
				org.apache.log4j.Logger.getRootLogger().setLevel(Level.DEBUG);
			} else if (level.equals("INFO")) {
				org.apache.log4j.Logger.getRootLogger().setLevel(Level.INFO);
			} else if (level.equals("WARN")) {
				org.apache.log4j.Logger.getRootLogger().setLevel(Level.WARN);
			} else if (level.equals("ERROR")) {
				org.apache.log4j.Logger.getRootLogger().setLevel(Level.ERROR);
			} else {
				org.apache.log4j.Logger.getRootLogger().error("Niveau inconnu: " + level);
			}
		} else {
			org.apache.log4j.Logger.getRootLogger().error("Niveau non défini");
		}

	}

}
