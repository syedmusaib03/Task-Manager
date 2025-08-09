package com.syedMusaib.Task_Manager.Service;

import com.syedMusaib.Task_Manager.Entity.TaskEntity;
import com.syedMusaib.Task_Manager.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskEntity addTask(TaskEntity task) {
        return taskRepository.save(task);
    }

    public List<TaskEntity> getTask() {
        return taskRepository.findAll();
    }

    public TaskEntity getTaskById(int id) {
        return taskRepository.findById(id).orElse(null);
    }

    public TaskEntity updateTask(int id, TaskEntity updatedTask) {
        TaskEntity existing = getTaskById(id);
        if (existing != null) {
            existing.setTitle(updatedTask.getTitle());
            existing.setDescription(updatedTask.getDescription());
            existing.setDeadLine(updatedTask.getDeadLine());
            existing.setCompleted(updatedTask.isCompleted());
            return taskRepository.save(existing);
        }
        return null;
    }

    public boolean deleteTask(int id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
