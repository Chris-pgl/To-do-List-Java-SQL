package com.example.ToDoList2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    private String description;

    @NotNull(message = "Completed status is mandatory")
    private boolean completed;

    @NotNull(message = "Completed status is mandatory")
    private String priority; // meglio un Enum? Alta, Media, Bassa?

    private LocalDate dueDate; //Campo per la scadenza

    public Task(){

    }

    public Task(String title, String description, LocalDate dueDate, String priority){
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.completed = false;

    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
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

    public boolean isCompleted(){
        return completed;
    }

    public void setCompleted(boolean completed){
        this.completed = completed;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @PrePersist
    protected void onCreate() {
        if (this.dueDate == null) {
            this.dueDate = LocalDate.from(LocalDateTime.now());
        }
    }
}
