package com.smorg.backend;

public final class QueryStrings {
	
	public final static String deleteQuery =
			"DELETE FROM GoalJPA n WHERE n.goalId = :goalId";
	public final static String getAllQuery = 
			"SELECT n FROM GoalJPA n WHERE n.userId = :userId";
	public final static String getAll = 
			"SELECT n FROM GoalJPA n";
			

}
