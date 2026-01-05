package dev.bruno.project_demand_management.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.bruno.project_demand_management.entity.ProjectEntity;
import dev.bruno.project_demand_management.entity.TaskEntity;
import dev.bruno.project_demand_management.util.PriorityEnum;
import dev.bruno.project_demand_management.util.StatusEnum;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    
    @Query(
        "SELECT t FROM TaskEntity t " +
        "WHERE (:status IS NULL OR t.status = :status) " +
        "AND (:priority IS NULL OR t.priority = :priority) " +
        "AND (:projectEntity IS NULL OR t.projectEntity = :projectEntity)")
    
    Page<TaskEntity> findTasks(
        @Param("status") StatusEnum status,
        @Param("priority") PriorityEnum priority,
        @Param("projectEntity") ProjectEntity projectEntity,
        PageRequest pageRequest
    );
}