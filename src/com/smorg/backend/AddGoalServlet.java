package com.smorg.backend;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.smorg.data.Goal;
import com.smorg.data.GoalDAO;

@SuppressWarnings("serial")
public class AddGoalServlet extends HttpServlet {
	private static final Logger log = Logger.getLogger(AddGoalServlet.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		System.out.println("Inside doGet");
		Goal goal = deserializeGoal(req.getInputStream());
		// DAO instance
		if (goal != null) {
			GoalDAO dao = new GoalDAOJPA();
			// saving note
			System.out.println("Goal Info: " + goal.toString());
			dao.addGoal(goal);
			resp.setStatus(HttpServletResponse.SC_OK);
			System.out.println("Goal Added: " + goal.toString());
		}else {
			resp.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
		}
		
	}
	
	private Goal deserializeGoal(InputStream inputStream) {
    	Goal result = null;
    	ObjectInputStream objectInputStream = null;
    	try {
    		objectInputStream = new ObjectInputStream(inputStream);
    		Object object = objectInputStream.readObject();
    		result = (Goal) object;
    	} catch (ClassNotFoundException e) {
    		System.out.println("ClassNotFound exception in deserializeGoal");
    		result = null;
    	} catch (Exception e) {
    		System.out.println("Exception in deserializeGoal");
    		result = null;
    	}
    	return result;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req,resp);
	}
	
	 
}
