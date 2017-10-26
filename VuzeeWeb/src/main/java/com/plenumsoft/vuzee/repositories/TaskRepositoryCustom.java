package com.plenumsoft.vuzee.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.plenumsoft.vuzee.entities.TaskStatistics;

public interface TaskRepositoryCustom {

	List<TaskStatistics> findStatistics();

}
