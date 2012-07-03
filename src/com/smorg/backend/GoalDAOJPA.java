package com.smorg.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.smorg.data.Goal;
import com.smorg.data.GoalDAO;

public class GoalDAOJPA implements GoalDAO {
	private static final Logger log = Logger.getLogger(GetGoalsServlet.class.getName());

	@Override
	public void addGoal(Goal goal) {
		EntityManager entityManager = null;
		try {
			entityManager = EMF.get().createEntityManager();
		    GoalJPA goalJPA = new GoalJPA(goal);
		    entityManager.persist(goalJPA);
		} finally {
			entityManager.close();
		}
		
	}

	@Override
	public ArrayList<Goal> getAllGoals(String userId) {
		EntityManager entityManager = null;
		List<GoalJPA> dbGoals = null;
		log.info("getAllGoals JPA method");
		ArrayList<Goal> goals = new ArrayList<Goal>();
		try {
			entityManager = EMF.get().createEntityManager();
		    Query query = entityManager.createQuery(QueryStrings.getAll);
		    dbGoals = query.getResultList();
		    if ((dbGoals != null) && (dbGoals.size() > 0)) {
			for (GoalJPA goal : dbGoals) {
				System.out.println("inside the enhanced for loop");
			    goals.add(goal.getGoal());
			}
		    }
		} finally {
			entityManager.close();
		}
		return goals;
	}

	@Override
	public void removeGoal(Long goalId) {
		
		EntityManager entityManager = null;
		try {
			entityManager = EMF.get().createEntityManager();
		    Query query = entityManager.createQuery(QueryStrings.deleteQuery);
		    query.setParameter("goalId", goalId);
		    int numberUpdated = query.executeUpdate();
		} finally {
			entityManager.close();
		}
	    }

		
	}




