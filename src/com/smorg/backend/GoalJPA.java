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
 * Helper class to store all of the information from a message into a
 * JPA data store. The JPA data store requires a unique key and has
 * some annotations to describe the fields of the object to be stored.
 * Other then the key and annotations this is the same class as a
 * Note. A constructor is provided to initialize this instance with a
 * Note instance and a getNote method is provided to return a note
 * instance from the current instance 
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
     * Initializes instance give an Note instance. This is a helper
     * constructor that makes it easy to take a Note and store it with
     * a key in the format required by JPA
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
