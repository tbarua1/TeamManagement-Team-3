package com.collabra.backend.dto;

import java.util.List;

import com.collabra.backend.enti.Program;

public class ProgramListDTO {
  private List<Program> programList;

  public List<Program> getProgramList() {
    return programList;
  }

  public void setProgramList(List<Program> programList) {
    this.programList = programList;
  }
}
