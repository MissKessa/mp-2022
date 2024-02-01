package uo.mp.s6.greenhouse.controllers;

import java.util.ArrayList;
import java.util.List;

public class DeviceScanner {

  private final List<Checkable> checkables = new ArrayList<>();

  public void add(Checkable c) {
    checkables.add(c);
  }

  public List<String> monitor() {
    List<String> result = new ArrayList<>();

    for (Checkable ch : checkables)
      if (!ch.check()) {
        result.add("WARNING: " + ch.toString() + " is not in good condition");
      }
    return result;
  }
}
