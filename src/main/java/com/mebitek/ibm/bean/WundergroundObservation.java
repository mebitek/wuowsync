package com.mebitek.ibm.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WundergroundObservation implements Serializable {

 private List<WundergroundCondition> observations = new ArrayList<>();

 public List<WundergroundCondition> getObservations() {
  return observations;
 }

 public void setObservations(List<WundergroundCondition> observations) {
  this.observations = observations;
 }
}
