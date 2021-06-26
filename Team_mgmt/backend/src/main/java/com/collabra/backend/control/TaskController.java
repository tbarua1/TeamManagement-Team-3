package com.collabra.backend.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.collabra.backend.dto.NewTaskDTO;
import com.collabra.backend.dto.WorkDTO;
import com.collabra.backend.enti.Task;
import com.collabra.backend.resourc.CalendarListResource;
import com.collabra.backend.resourc.CalendarResource;
import com.collabra.backend.resourc.TaskListResource;
import com.collabra.backend.resourc.TaskRecordListResource;
import com.collabra.backend.service.TaskService;
import com.collabra.backend.util.Client;

@RestController
public class TaskController {

  final String clientUrl = Client.clientUrl;

  @Autowired
  private TaskService service;

  @PostMapping("/createTask")
  @CrossOrigin(origins = clientUrl)
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<Boolean> createTask(@RequestBody NewTaskDTO newTaskDto) {
    Task task = new Task();
    BeanUtils.copyProperties(newTaskDto.getTask(), task);
    service.createTask(task, newTaskDto.getUser());
    return new ResponseEntity<Boolean>(true,HttpStatus.OK);
  }

  @GetMapping("/getAllTaskRecordByUser/{pid}/{uid}")
  @CrossOrigin(origins = clientUrl)
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<TaskRecordListResource> getAllTaskRecordByUser(@PathVariable("pid") Long pid, @PathVariable("uid") Long uid) {
    // service call
    TaskRecordListResource trlr = service.getAllTaskRecordOfUserAndProgram(uid, pid);
    return new ResponseEntity<TaskRecordListResource>(trlr, HttpStatus.OK);
  }
  
  @GetMapping("/getAllTaskByUser/{pid}/{uid}")
  @CrossOrigin(origins = clientUrl)
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<TaskListResource> getAllTaskByUser(@PathVariable("pid") Long pid, @PathVariable("uid") Long uid) {
    // service call
    TaskListResource tlr = service.getAllTaskOfUserAndProgram(uid, pid);
    return new ResponseEntity<TaskListResource>(tlr, HttpStatus.OK);
  }
  
  @GetMapping("/getAllTaskRecord")
  @CrossOrigin(origins = clientUrl)
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<TaskRecordListResource> getAllTaskRecord() {
    // service call
    TaskRecordListResource trlr = service.getAllTaskRecord();
    return new ResponseEntity<TaskRecordListResource>(trlr, HttpStatus.OK);
  }

  @GetMapping("/getAllTaskByProgram/{pid}")
  @CrossOrigin(origins = clientUrl)
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<TaskListResource> getAllTaskByProgram(@PathVariable("pid") Long pid) {
    // service call
    TaskListResource tlr = service.getAllTaskByProgram(pid);
    return new ResponseEntity<TaskListResource>(tlr, HttpStatus.OK);
  }
  
  @PostMapping("/updateTaskStatus/{status}")
  @CrossOrigin(origins = clientUrl)
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<?> updateTaskStatus(@PathVariable("status") String status) {
    return new ResponseEntity<>(HttpStatus.OK);
  }
  
  @GetMapping("/getTaskCalendar/{pid}/{uid}")
  @CrossOrigin(origins = clientUrl)
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<CalendarListResource> getTaskCalendar(@PathVariable("pid") Long pid, @PathVariable("uid") Long uid) {
    // service call
    TaskListResource tlr =  service.getAllTaskOfUserAndProgram(uid, pid);
    List<CalendarResource> cl = new ArrayList<CalendarResource>();
    List<Task> tl = tlr.getTaskList();
    for(Task t: tl) {
      CalendarResource cr = new CalendarResource();
      cr.setTitle(t.getName());
      cr.setStart(t.getStartTime());
      cr.setEnd(t.getDeadline());
      cl.add(cr);
    }
    CalendarListResource clr = new CalendarListResource();
    clr.setEvents(cl);
    
    return new ResponseEntity<CalendarListResource>(clr, HttpStatus.OK);
  }
  
  @PostMapping("/addTaskWork/{pid}/{uid}/{tid}")
  @CrossOrigin(origins = clientUrl)
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<?> addWork(@PathVariable("pid") Long pid, @PathVariable("uid") Long uid, @PathVariable("tid") Long tid, @RequestBody WorkDTO work) {
    // service call
    service.addWork(uid, pid, tid, work.getWork());
    return new ResponseEntity<Boolean>(true, HttpStatus.OK);
  }
  
  @GetMapping("/getTaskWork/{pid}/{uid}/{tid}")
  @CrossOrigin(origins = clientUrl)
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<WorkDTO> getWork(@PathVariable("pid") Long pid, @PathVariable("uid") Long uid, @PathVariable("tid") Long tid) {
    // service call
    WorkDTO response = service.getWork(uid, pid, tid);
    return new ResponseEntity<WorkDTO>(response, HttpStatus.OK);
  }
}
