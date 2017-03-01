package com.sf.practice.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sf.practice.common.BasicTestCase;
import com.sf.utils.AccumlativeStopWatch;
/**
 * 多个顾客消费，多个工厂生产
 * @author hao19
 *
 */
public class GeneratorCustomer extends BasicTestCase {
  private int sortArrLenth = 30;
  private int printThreshold = 50;
  
  private long startTime;
  private long swaptimes;
  
  
  
  AccumlativeStopWatch sw = new AccumlativeStopWatch("");
  
  /**
   * 
   * @author hao19
   *
   */
  public class Customer{
    
  }
  public class Generator{
    List<String> repository;
    public Generator(List<String> repository) {
      this.repository = repository;
    }
  }
  
  
  
}
