package com.example.ToDoList2.repo;

import com.example.ToDoList2.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

    List<Task> findByCompleted(boolean completed);
    List<Task> findByOrderByDueDateAsc();

    //List<Task> findByPriorityAndCompletedAndDueDate(String priority, Boolean completed, LocalDate dueDate);
    @Query("SELECT t FROM Task t WHERE "
            + "(:priority IS NULL OR t.priority = :priority) AND "
            + "(:completed IS NULL OR t.completed = :completed) AND "
            + "(:dueDate IS NULL OR t.dueDate = :dueDate)")
    List<Task> findByPriorityAndCompletedAndDueDate(@Param("priority") String priority,
                              @Param("completed") Boolean completed,
                              @Param("dueDate") LocalDate dueDate);

}


