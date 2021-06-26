package com.collabra.backend.resourc;

import java.util.List;

import com.collabra.backend.enti.TaskRecord;

public class TaskRecordListResource {
  private List<TaskRecord> taskRecordList;

  public List<TaskRecord> getTaskRecordList() {
    return taskRecordList;
  }

  public void setTaskRecordList(List<TaskRecord> taskRecordList) {
    this.taskRecordList = taskRecordList;
  }
}
