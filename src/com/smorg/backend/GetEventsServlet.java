package com.smorg.backend;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smorg.data.Goal;
import com.smorg.data.GoalDAO;

public class GetEventsServlet extends HttpServlet {
	
	  /**
     * doGet is the same as doPost.
     */
	private static final Logger log = Logger.getLogger(GetGoalsServlet.class.getName());
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
	    throws IOException {
	doPost(req, resp);
    }

    /**
     * Gets all of the events in the data store and returns them 
     * 
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
	    throws IOException {
	resp.setContentType("application/x-java-serialized-object");
	Long goalId = deserializeGoalId(req.getInputStream());
	log.info("");
	ArrayList<String> events = getEvents(goalId);
	System.out.println("");
	sendEvents(events, resp);
    }

    private ArrayList<String> getEvents(Long goalId) {
    	GoalDAO dao = new GoalDAOJPA();
    	ArrayList<String> result = dao.getGoalEvents(goalId);
    	return result;
	}

	private Long deserializeGoalId(InputStream inputStream) {
    	
    	ObjectInputStream objectInputStream = null;
    	try {
    		objectInputStream = new ObjectInputStream(inputStream);
    		Object object = objectInputStream.readObject();
    		return (Long) object;
    	} catch (ClassNotFoundException e) {
    		System.out.println("ClassNotFound exception in deserialize goal Id");
    		return null;
    	} catch (Exception e) {
    		System.out.println("Exception in deserialize user Id");
    		return null;
    	}
    	
    }

    /**
     * method  to send an ArrayList of
     * event Ids for user  stored in the DB over the Output Stream 
     */
    private void sendEvents(ArrayList<String> events, HttpServletResponse resp) {
    	log.info("sending goals");
    	ObjectOutputStream objectOutputStream = null;
    	ByteArrayOutputStream byteArrayOutputStream = null;
    	System.out.println("3");
    	try {
    		byteArrayOutputStream = new ByteArrayOutputStream();
    		objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
    		objectOutputStream.writeObject(events);
    		objectOutputStream.flush();
    		int bufferLength = byteArrayOutputStream.size();
    		System.out.println(bufferLength);
    		log.info(""+ bufferLength);
    		resp.setContentLength(bufferLength);
    		byteArrayOutputStream.writeTo(resp.getOutputStream());
    		byteArrayOutputStream.flush();
    
    	} catch (IOException e) {
    		e.printStackTrace();
	}

    }

}
