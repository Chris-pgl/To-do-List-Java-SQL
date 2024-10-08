package com.example.ToDoList2.controllert;

import com.example.ToDoList2.entity.Task;
import com.example.ToDoList2.exception.ResourceNotFoundException;
import com.example.ToDoList2.repo.TaskRepo;
import com.example.ToDoList2.service.ServiceTask;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class Controller {

    @Autowired
    ServiceTask service;


    @PostMapping
    public Task createTask(@RequestBody Task task) throws Exception {
        try {
            System.out.println("Received Task: " + task.getTitle() + task.getDescription() + task.getPriority());
            return service.createTask(task);
        } catch (Exception e){
           throw new Exception("Failed to create task: " + e.getMessage());
        }
    }

    @GetMapping
    public List<Task> getAllTasks(){
        return service.getAllTasks();
    }

    @GetMapping("/status/{completed}")
    public List<Task> getTasksByStatus(@RequestParam boolean completed) throws Exception {
        try {
            return service.getTasksByStatus(completed);
        } catch (Exception e){
            throw new Exception("Invalid value[" + completed + "].. " + e.getMessage());
        }
    }

    @GetMapping("/due")
    public List<Task> getTasksByOrderByDueDate(){
        return service.getTasksOrderByDueDate();
    }
    @GetMapping("{id}")
    public Optional<Task> getTaskByID(@PathVariable Long id){
        //Test errore personalizzato
        return Optional.ofNullable(service.getTaskById(id).orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found")));
    }

    /*
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        return service.updateTask(id, taskDetails); }
    */

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody Task taskDetail){
        Task updateTask = service.updateTask(id, taskDetail);
        if(updateTask == null){
            throw new ResourceNotFoundException("Task with id " + id + " not found");
        }
        return ResponseEntity.ok(updateTask);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        service.deleteTask(id);
    }



    //filtri avanzati

    public Controller(ServiceTask service){
        this.service = service;
    }

    @GetMapping("/search")
    public List<Task> searchTasks(
            @RequestParam(required = false) String priority,
            @RequestParam(required = false) Boolean completed,
            @RequestParam(required = false) LocalDate dueDate){
        return service.searchTasks(priority, completed, dueDate);
    }

}
