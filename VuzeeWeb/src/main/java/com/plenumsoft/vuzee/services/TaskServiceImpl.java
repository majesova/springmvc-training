package com.plenumsoft.vuzee.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plenumsoft.vuzee.entities.Task;
import com.plenumsoft.vuzee.entities.TaskStatistics;
import com.plenumsoft.vuzee.repositories.TaskRepository;
@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskRepository taskRepository;
	
	@Override
	public Long addTask(Task task) {

		task= taskRepository.saveAndFlush(task);
		return task.getId();
	}

	@Override
	public Task findByTitle(String title) {

		return taskRepository.findByTitle(title);
	}

	@Override
	public List<TaskStatistics> findStatistics() {

		return taskRepository.findStatistics();
	}
	
}
