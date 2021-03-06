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
		    Query query = entityManager.createQuery(QueryStrings.GET_ALL_USER_QUERY);
		    System.out.println(QueryStrings.GET_ALL_USER_QUERY);
		    System.out.println(query);
		    query.setParameter("userId", userId);
		    dbGoals = query.getResultList();
		    if ((dbGoals != null) && (dbGoals.size() > 0)) {
			for (GoalJPA goal : dbGoals) {
				System.out.println("inside the enhanced for loop");
				System.out.println(QueryStrings.GET_ALL_USER_QUERY);
			    System.out.println(query);
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
		    Query query = entityManager.createQuery(QueryStrings.DELETE_QUERY);
		    query.setParameter("goalId", goalId);
		    int numberUpdated = query.executeUpdate();
		} finally {
			entityManager.close();
		}
	    }

	@Override
	public void addEventToGoal(Long goalId, String eventId) {
		
		EntityManager entityManager = null;
		GoalJPA goalJPA ;
		try {
			entityManager = EMF.get().createEntityManager();
		    Query query = entityManager.createQuery(QueryStrings.GET_GOAL);
		    query.setParameter("goalId", goalId);
		    goalJPA = (GoalJPA) query.getSingleResult();
		    goalJPA.addEvent(eventId);
		    entityManager.refresh(goalJPA);
		} finally {
			entityManager.close();
			
		}
		
	}

	@Override
	public ArrayList<String> getGoalEvents(Long goalId) {
		
		EntityManager entityManager = null;
		GoalJPA goalJPA;
		log.info("get Goal events  method");
		try {
			entityManager = EMF.get().createEntityManager();
		    Query query = entityManager.createQuery(QueryStrings.GET_GOAL);
		    System.out.println(QueryStrings.GET_ALL_USER_QUERY);
		    System.out.println(query);
		    query.setParameter("goalId", goalId);
		    goalJPA = (GoalJPA) query.getSingleResult();
		    
		} finally {
			entityManager.close();
		}
		 return new ArrayList<String> (goalJPA.getEventIds());
	}

		
	}




