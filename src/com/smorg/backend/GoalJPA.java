package com.smorg.backend;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.smorg.data.Goal;

/**
 * 
 */
@Entity
public class GoalJPA implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goalId;
    
    private String userId;
    private List<Long> eventIds;
    private String title;
    private String description;
    private Double progress;
    private Date startDate;
    private Date deadline;

    /**
     * 
     */
    public GoalJPA(Goal goal) {
    	goalId = null;
    	setUserId(goal.getUserId());
    	setEventIds(goal.getEventIds());
    	setTitle(goal.getTitle());
    	setDescription(goal.getDescription());
    	setProgress(goal.getProgress());
    	setStartDate(goal.getStartDate());
    	setDeadline(goal.getDeadline());
    }
    
    public Goal getGoal(){
    	Goal goal = new Goal();
    	goal.setGoalId(getGoalId());
    	goal.setUserId(getUserId());
    	goal.setEventIds(getEventIds());
    	goal.setProgress(getProgress());
    	goal.setDeadline(getDeadline());
    	goal.setTitle(getTitle());
    	goal.setDescription(getDescription());
    	goal.setStartDate(getStartDate());
    	return goal;
    }

	public Long getGoalId() {
		return goalId;
	}

	public void setGoalId(Long goalId) {
		this.goalId = goalId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Long> getEventIds() {
		return eventIds;
	}

	public void setEventIds(List<Long> eventIds) {
		this.eventIds = eventIds;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getProgress() {
		return progress;
	}

	public void setProgress(Double progress) {
		this.progress = progress;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
}
