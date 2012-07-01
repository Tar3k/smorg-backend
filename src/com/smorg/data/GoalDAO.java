package com.smorg.data;

import java.io.Serializable;
import java.util.ArrayList;

public interface GoalDAO extends Serializable{

    /**
     * Persists Note information to a persistent data store
     */
    public void addGoal(Goal goal);

    /**
     * Gets all of the <code>Note</code> instances in the data store
     */
    public ArrayList<Goal> getAllGoals();

    /**
     * Removed an Note instance in data store that
     * matches note
     */
    public void removeGoal(Goal goal);
}
