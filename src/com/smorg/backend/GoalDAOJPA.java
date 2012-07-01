package com.smorg.backend;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import com.smorg.data.Goal;
import com.smorg.data.GoalDAO;

public class GoalDAOJPA implements GoalDAO {

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
	public ArrayList<Goal> getAllGoals() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeGoal(Goal goal) {
		// TODO Auto-generated method stub
		
	}

}
