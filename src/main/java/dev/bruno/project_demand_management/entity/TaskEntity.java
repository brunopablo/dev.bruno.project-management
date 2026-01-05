package dev.bruno.project_demand_management.entity;

import java.time.LocalDate;

import dev.bruno.project_demand_management.util.enums.PriorityEnum;
import dev.bruno.project_demand_management.util.enums.StatusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_task")
public class TaskEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    
    private String title;
    
    private String description;
    
    private StatusEnum status;
    
    private PriorityEnum priority;
    
    private LocalDate dueDate;
    
    @ManyToOne
    @JoinColumn(name="fk_project_id")
    private ProjectEntity projectEntity;
    
    public TaskEntity() {
    }
    
    public TaskEntity(
            Long id,
            String title,
            String description,
            StatusEnum status,
            PriorityEnum priority,
            LocalDate dueDate,
            ProjectEntity projectId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
        this.projectEntity = projectId;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
    
    public StatusEnum getStatus() {
        return status;
    }
    
    public void setStatus(StatusEnum status) {
        this.status = status;
    }
    
    public PriorityEnum getPriority() {
        return priority;
    }
    
    public void setPriority(PriorityEnum priority) {
        this.priority = priority;
    }
    
    public LocalDate getDueDate() {
        return dueDate;
    }
    
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    
    public ProjectEntity getProjectEntity() {
        return projectEntity;
    }
    
    public void setProjectEntity(ProjectEntity projectId) {
        this.projectEntity = projectId;
    }
}