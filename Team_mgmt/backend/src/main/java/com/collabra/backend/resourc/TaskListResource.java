package com.collabra.backend.resourc;

import java.util.List;

import com.collabra.backend.enti.Task;

public class TaskListResource {

  private List<Task> taskList;

  public List<Task> getTaskList() {
    return taskList;
  }

  public void setTaskList(List<Task> taskList) {
    this.taskList = taskList;
  }
}
