package com.example.ToDoList2.service;

import com.example.ToDoList2.entity.Task;
import com.example.ToDoList2.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceTask {

    @Autowired
    TaskRepo repo;

    public Task createTask(Task t){
       return repo.save(t);
    }

    public List<Task> getAllTasks(){
        return repo.findAll();
    }

    public List<Task> getTasksByStatus(boolean completed){
        return repo.findByCompleted(completed);
    }

    public List<Task> getTasksOrderByDueDate(){
        return repo.findByOrderByDueDateAsc();
    }

    public Optional<Task> getTaskById(Long id){
        return repo.findById(id);
    }

    public void deleteTask(Long id){
        repo.deleteById(id);
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setCompleted(taskDetails.isCompleted());
        task.setDueDate(taskDetails.getDueDate());
        task.setPriority(taskDetails.getPriority());
        return repo.save(task);
    }

    public List<Task> searchTasks(String priority, Boolean completed, LocalDate dueDate) {
        return repo.findByPriorityAndCompletedAndDueDate(priority, completed, dueDate);
    }

}
