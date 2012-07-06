package com.smorg.backend;

public final class QueryStrings {
	
	public final static String DELETE_QUERY =
			"DELETE FROM GoalJPA n WHERE n.goalId = :goalId";
	public final static String GET_ALL_USER_QUERY = 
			"SELECT n FROM GoalJPA n WHERE n.userId = :userId";
	public final static String GET_GOAL =
			"SELECT n FROM GoalJPA n WHERE n.goalId= :goalId";
	public final static String GET_ALL_QUERY = 
			"SELECT n FROM GoalJPA n";
			

}
