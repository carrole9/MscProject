package com.ILoveLAMP.populateDB;


import java.io.Closeable;
import java.io.IOException;
import java.text.ParseException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class FolderListner {
	 public enum States {BEFORESTARTED, STARTED, PAUSED, SHUTTINGDOWN};
	    private States state;
	   
	    @Inject
	    private PopulateDB db;
	    
	    @PostConstruct
	    public void initialize() throws ParseException {
	        state = States.BEFORESTARTED;
	        // Perform intialization
	        state = States.STARTED;
	        System.out.println("Service Started");
	        db.watch();
	    }
	    
	    
}