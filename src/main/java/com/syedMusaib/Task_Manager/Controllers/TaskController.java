package com.syedMusaib.Task_Manager.Controllers;

import com.syedMusaib.Task_Manager.Entity.TaskEntity;
import com.syedMusaib.Task_Manager.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskEntity>> getTasks() {
        return ResponseEntity.ok(taskService.getTask());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable int id) {
        TaskEntity task = taskService.getTaskById(id);
        if (task == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<TaskEntity> createTask(@RequestBody TaskEntity taskRequest) {
        return ResponseEntity.status(201).body(taskService.addTask(taskRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable int id, @RequestBody TaskEntity updatedTask) {
        TaskEntity task = taskService.updateTask(id, updatedTask);
        if (task == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        if (!taskService.deleteTask(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
