package com.smorg.backend;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smorg.data.Bundle;
import com.smorg.data.GoalDAO;

public class AddEventServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		System.out.println("Inside doGet");
		Bundle bundle = deserializeData(req.getInputStream());
		
		if (bundle.getEventId() != null && bundle.getGoalId() != null ) {
			
			GoalDAO dao = new GoalDAOJPA();
			System.out.println("will add event :servlet");
			dao.addEventToGoal(bundle.getGoalId(), bundle.getEventId());
			resp.setStatus(HttpServletResponse.SC_OK);
			System.out.println("Response sent :Servlet");
		}else {
			resp.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
		}

		
	}
	
	private Bundle deserializeData(InputStream inputStream) {
    	
		ObjectInputStream objectInputStream = null;
    	Bundle bundle;
		try {
    		objectInputStream = new ObjectInputStream(inputStream);
    		Object object = objectInputStream.readObject();
    		bundle = (Bundle) object;
    	} catch (ClassNotFoundException e) {
    		System.out.println("ClassNotFound exception in deserialize Goal");
    		bundle = null;
    	} catch (Exception e) {
    		System.out.println("Exception in deserialize Goal");
    		bundle = null;
    	}
    	return bundle;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req,resp);
	}


}
