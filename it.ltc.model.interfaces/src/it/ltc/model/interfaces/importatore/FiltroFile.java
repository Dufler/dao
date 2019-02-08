package it.ltc.model.interfaces.importatore;

import java.io.File;
import java.io.FileFilter;

public class FiltroFile implements FileFilter {

	protected final String regex;
	
	public FiltroFile(String fileNameRegex) {
		this.regex = fileNameRegex;
	}
	
	@Override
	public boolean accept(File pathname) {
		boolean accept = false;
		if (pathname.isFile()) {
			accept = pathname.getName().matches(regex);
		}
		return accept;
	}

}
