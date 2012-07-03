package com.smorg.backend;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smorg.data.GoalDAO;

@SuppressWarnings("serial")
public class RemoveGoalServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		System.out.println("Inside doGet");
		Long goalId = deserializeGoalId(req.getInputStream());
		// DAO instance
		if (goalId != null) {
			GoalDAO dao = new GoalDAOJPA();
			// saving goal
			System.out.println("Goal Info: " + goalId.toString());
			dao.removeGoal(goalId);
			resp.setStatus(HttpServletResponse.SC_OK);
			System.out.println("Goal removed: " + goalId.toString());
		}else {
			resp.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
		}

		
	}
	
	private Long deserializeGoalId(InputStream inputStream) {
    	
    	ObjectInputStream objectInputStream = null;
    	try {
    		objectInputStream = new ObjectInputStream(inputStream);
    		Object object = objectInputStream.readObject();
    		return (Long) object;
    	} catch (ClassNotFoundException e) {
    		System.out.println("ClassNotFound exception in deserialize Goal Id");
    		return null;
    	} catch (Exception e) {
    		System.out.println("Exception in deserializing Goal Id");
    		return null;
    	}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req,resp);
	}
	
	 
}
