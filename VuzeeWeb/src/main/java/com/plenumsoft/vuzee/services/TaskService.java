package com.plenumsoft.vuzee.services;

import java.util.List;

import com.plenumsoft.vuzee.entities.Task;
import com.plenumsoft.vuzee.entities.TaskStatistics;

public interface TaskService {
	
	Long addTask(Task task);
	
	Task findByTitle(String title);
	
	List<TaskStatistics> findStatistics();
}
