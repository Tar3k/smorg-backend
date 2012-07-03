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

/**
 * Servlet designed to be connected to directly from an Android app. The servlet
 * writes an serialized ArrayList of Goals as a single object to the
 * output stream
 */
@SuppressWarnings("serial")
public class GetGoalsServlet extends HttpServlet {

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
     * Gets all of the messages in the data store and returns them 
     * 
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
	    throws IOException {
	resp.setContentType("application/x-java-serialized-object");
	String userId ="smorg.app@gmail.com";
	log.info(userId);
	//System.out.println(userId);
			//deserializeUserId(req.getInputStream());
	ArrayList<Goal> goals = getAllGoals(userId);
	System.out.println(goals);
	log.info(goals.toString());
	sendNotes(goals, resp);
    }

    private String deserializeUserId(InputStream inputStream) {
    	
    	ObjectInputStream objectInputStream = null;
    	try {
    		objectInputStream = new ObjectInputStream(inputStream);
    		Object object = objectInputStream.readObject();
    		return (String) object;
    	} catch (ClassNotFoundException e) {
    		System.out.println("ClassNotFound exception in deserialize goal Id");
    		return null;
    	} catch (Exception e) {
    		System.out.println("Exception in deserialize user Id");
    		return null;
    	}
    	
    }
    /**
     * Get all of the Goal instances stored in the persistent data
     * store and return them in an ArrayList. Currently,
     * no filtering is done
     */
    private ArrayList<Goal> getAllGoals(String userId) {
	GoalDAO dao = new GoalDAOJPA();
	ArrayList<Goal> result = dao.getAllGoals(userId);
	return result;
    }

    /**
     * method  to send an ArrayList of
     * Goals for all user's goals stored in the DB over the Output Stream 
     */
    private void sendNotes(ArrayList<Goal> goals, HttpServletResponse resp) {
    	log.info("sending goals");
    	ObjectOutputStream objectOutputStream = null;
    	ByteArrayOutputStream byteArrayOutputStream = null;
    	System.out.println("3");
    	try {
    		byteArrayOutputStream = new ByteArrayOutputStream();
    		objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
    		objectOutputStream.writeObject(goals);
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
