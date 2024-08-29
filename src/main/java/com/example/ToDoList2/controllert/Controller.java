package com.example.ToDoList2.controllert;

import com.example.ToDoList2.entity.Task;
import com.example.ToDoList2.repo.TaskRepo;
import com.example.ToDoList2.service.ServiceTask;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Task createTask(@RequestBody Task task) {
        System.out.println("Received Task: " + task);
        return service.createTask(task);
    }

    @GetMapping
    public List<Task> getAllTasks(){
        return service.getAllTasks();
    }

    @GetMapping("/status/{completed}")
    public List<Task> getTasksByStatus(@RequestParam boolean completed){
        return service.getTasksByStatus(completed);
    }

    @GetMapping("/due")
    public List<Task> getTasksByOrderByDueDate(){
        return service.getTasksOrderByDueDate();
    }
    @GetMapping("{id}")
    public Optional<Task> getTaskByID(@PathVariable Long id){
        return service.getTaskById(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        return service.updateTask(id, taskDetails);
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
