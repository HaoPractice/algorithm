package com.sf.practice.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sf.practice.common.BasicTestCase;
import com.sf.utils.AccumlativeStopWatch;

public class Sort extends BasicTestCase {
  private int sortArrLenth = 30;
  private int printThreshold = 50;

  // private List<ComparableObject> toSort = new LinkedList<GeneratorCustomer.ComparableObject>();
  private List<ComparableObject> toSort = new ArrayList<Sort.ComparableObject>();

  String HEAP_SORT = "HEAP_SORT";
  String BUBBLE_SORT = "BUBBLE_SORT";
  String SELECT_SORT = "SELECT_SORT";
  String INSERT_SORT = "INSERT_SORT";
  String SHELL_SORT = "SHELL_SORT";
  String SHELL_SORT2 = "SHELL_SORT2";

  private long startTime;
  private long swaptimes;

  AccumlativeStopWatch sw = new AccumlativeStopWatch("");
  private boolean bigHeap = true;

  @Before
  public void prepareToSort() {
    for (int i = 0; i < sortArrLenth; i++) {
      toSort.add(ComparableObject.newComparableObject(sortArrLenth));
    }
    System.out.println("===========================before sort");
    printResult();
    // prepareHeapList();
    startTime = System.currentTimeMillis();
  }

  @SuppressWarnings("unused")
  private void prepareHeapList() {
    toSort.clear();
    toSort.add(new ComparableObject(28));
    toSort.add(new ComparableObject(27));
    toSort.add(new ComparableObject(27));
    toSort.add(new ComparableObject(27));
    toSort.add(new ComparableObject(26));
    toSort.add(new ComparableObject(24));
    toSort.add(new ComparableObject(25));
    toSort.add(new ComparableObject(23));
    toSort.add(new ComparableObject(21));
    toSort.add(new ComparableObject(24));
    toSort.add(new ComparableObject(16));
    toSort.add(new ComparableObject(22));
    toSort.add(new ComparableObject(17));
    toSort.add(new ComparableObject(23));
    toSort.add(new ComparableObject(1));
    toSort.add(new ComparableObject(0));
    toSort.add(new ComparableObject(11));
    toSort.add(new ComparableObject(0));
    toSort.add(new ComparableObject(12));
    toSort.add(new ComparableObject(14));
    toSort.add(new ComparableObject(12));
    toSort.add(new ComparableObject(13));
    toSort.add(new ComparableObject(7));
    toSort.add(new ComparableObject(9));
    toSort.add(new ComparableObject(3));
    toSort.add(new ComparableObject(10));
    toSort.add(new ComparableObject(1));
    toSort.add(new ComparableObject(4));
    toSort.add(new ComparableObject(5));
    toSort.add(new ComparableObject(25));
  }

  private void printResult() {
    if (sortArrLenth <= printThreshold) {
      System.out.println(toSort);
      // for (ComparableObject comparableObject : toSort) {
      // System.out.print(comparableObject.toSuperString());
      // }
      // System.out.println();
    }
  }

   @Test
  public void quickSort() {
//    Collections.sort(toSort);
//    quickSort(toSort, 0, toSort.size() - 1);
     quickSortModifiedVersion(toSort, 0, toSort.size() - 1);
  }

  private void quickSort(List<ComparableObject> toSort, int left, int right) {
    if (left >= right) {
      return;
    }
    ComparableObject p = toSort.get(left);
    
    int i = left;
    int j = right;
    while(i < j ){
      while(i < j && toSort.get(j).compareTo(p) > 0){
        j--;
      }
      while(i < j && toSort.get(i).compareTo(p) <= 0){
        i++;
      }
      if (i != j) {
        swap(toSort, j, i);
      }
    }
    swap(toSort, i, left);
    quickSort(toSort,left,i - 1);
    quickSort(toSort,i + 1,right);
  }
  private void quickSortModifiedVersion(List<ComparableObject> toSort, int left, int right) {
    if (left >= right) {
      return;
    }
    int position = left;
    ComparableObject p = toSort.get(position);
    int i = left;
    int j = right;
    
//    int leftPoint = i;
//    int rightPoint = j;
    while(i < j ){
      // 先移动i，会导致排序结果错误
      while(i < j && toSort.get(i).compareTo(p) <= 0){
        i++;
      }
//      rightPoint = j;
      while(i < j && toSort.get(j).compareTo(p) >= 0){
        j--;
      }
      if (i < j) {
        swap(toSort, j, i);
      }
    }
    
//    swap(toSort, j, left);　这里是重点，和标志位交换不是因为是i或者j，而是要看：1、标志位是在分界处的左边还是右边；2、在分界处的左边：标志位和分界处左边交换，反之和分界处的右边交换
    int leftPoint = getLeftPoint(toSort,left,position);
    swap(toSort, leftPoint, position);
    System.out.println("left:"+leftPoint);
    System.out.println("j:"+j+",i:"+i);
    
    quickSortModifiedVersion(toSort,left,j - 1);
    quickSortModifiedVersion(toSort,j + 1,right);
  }
  private int getLeftPoint(List<ComparableObject> toSort2,int left , int position) {
    for (int i = left; i < toSort2.size(); i++) {
      ComparableObject comparableObject = toSort2.get(i);
      if (comparableObject.compareTo(toSort2.get(position))> 0 ) {
        return i;
      }
    }
    return 0;
  }

  private void quickSortErrorVersion(List<ComparableObject> toSort, int left, int right) {
    if (left >= right) {
      return;
    }
    int position = left;
    ComparableObject p = toSort.get(position);
    System.out.println("***");
    int i = left;
    int j = right;
    
    while(i < j ){
      System.out.println("---");
      printResult();
      // 先移动i，会导致排序结果错误
      while(i < j && toSort.get(i).compareTo(p) <= 0){
        i++;
      }
      while(i < j && toSort.get(j).compareTo(p) >= 0){
        j--;
      }
      if (i < j) {
        swap(toSort, j, i);
      }
      printResult();
      System.out.println("---");
    }
    
//    swap(toSort, j, left);　这里是重点，和标志位交换不是因为是i或者j，而是要看：1、标志位是在分界处的左边还是右边；2、在分界处的左边：标志位和分界处左边交换，反之和分界处的右边交换
    swap(toSort, j, left);
    System.out.println("left:"+left);
    System.out.println("j:"+j+",i:"+i);
    
    quickSortErrorVersion(toSort,left,j - 1);
    quickSortErrorVersion(toSort,j + 1,right);
  }

  @Test
  public void heapSort() {
    bigHeap = false;
    System.out.println(HEAP_SORT);
    System.out.println("before heapify");
    printResult();
    heapify();
    System.out.println("after heapify");
    printResult();

    int size = toSort.size();
    for (int i = size - 1; i >= 1; i--) {
      // System.out.println("堆底和堆顶交换-----------------");
      swap(toSort, 0, i);
      // 堆底和堆顶交换后，将排好序的区域，下标扩张1，堆大小减一
      shiftDownToHeap(toSort, 0, i - 1);
    }
  }

  private void heapify() {
    int size = toSort.size();
    for (int i = (size - 1) / 2; i >= 0; i--) {
      shiftDownToHeap(toSort, i, size - 1);
    }
  }


  /**
   * 将数组堆化
   * 
   * @param toSort2
   * @param start
   * @param end
   */
  private void shiftDownToHeap(List<ComparableObject> toSort, int start, int end) {

    // printResult(); // show process

    int parent = start;
    int left = 2 * start + 1;
    int right = 2 * start + 2;
    if (right <= end) {
      if (bigHeap) {
        if (toSort.get(parent).compareTo(toSort.get(right)) < 0) {
          parent = right;
        }
      } else {
        if (toSort.get(parent).compareTo(toSort.get(right)) > 0) {
          parent = right;
        }
      }
    }
    if (left <= end) {
      if (bigHeap) {
        if (toSort.get(parent).compareTo(toSort.get(left)) < 0) {
          parent = left;
        }
      } else {
        if (toSort.get(parent).compareTo(toSort.get(left)) > 0) {
          parent = left;
        }
      }
    }
    if (parent != start) {
      swap(toSort, parent, start);
      shiftDownToHeap(toSort, parent, end);
    }
  }


  //
  // @Test
  // public void quickSort(){
  // System.out.println("quickSort");
  //
  //
  // }
  //
  
  /**
   * it's not bubble sort
   */
//  @Test
  public void bubbleSort_err() {
    System.out.println(BUBBLE_SORT);
    int size = toSort.size();
    for (int i = 1; i < size; i++) {
      for (int j = size - i - 1; j < size - 1; j++) {
        ComparableObject firstObject = toSort.get(j);
        ComparableObject secondObject = toSort.get(j + 1);
        if (firstObject.compareTo(secondObject) < 0) {
          swap(toSort, j, j + 1);
        }
      }
    }
  }
  
  @Test
  public void bubbleSort() {
    System.out.println(BUBBLE_SORT);
    int size = toSort.size();
    for (int i = 1; i < size; i += 1) {
      boolean hasSort = false;
      for (int j = 0; j < size - i; j+=1) {
        if (toSort.get(j).compareTo(toSort.get(j+1))>0) {
          swap(toSort,j,j+1);
          hasSort  = true;
        }
      }
      if (!hasSort) {
        return;
      }
    }
  }

  @Test
  public void selectSort() {
    System.out.println(SELECT_SORT);
    int size = toSort.size();
    for (int i = 0; i < size; i++) {
      int index1 = size - i - 1;
      int minIndex = index1;
      ComparableObject firstObject = toSort.get(minIndex);
      for (int j = 0; j < index1; j++) {
        ComparableObject secondObject = toSort.get(j);
        if (firstObject.compareTo(secondObject) < 0) {
          minIndex = j;
          firstObject = toSort.get(minIndex);
        }
      }
      swap(toSort, index1, minIndex);
    }
  }
  // @Test
  // publicoid insertSort() {
  // System.out.println("insertSort");
  // int size = toSort.size();
  // for (int i = 1; i < size; i++) {
  // for (int j = 0; j < i; j++) {
  // ComparableObject firstObject = toSort.get(i - j);
  // ComparableObject secondObject = toSort.get(i);
  // if (firstObject.compareTo(secondObject) < 0) {
  // break;
  // }
  // }
  // swap(toSort, index1, minIndex);
  // }
  // }

  
  @Test
  public void insertSort() {
    System.out.println(INSERT_SORT);
    for (int i = 1; i < toSort.size(); i++) {
      int j = i - 1;
      ComparableObject out = toSort.get(i);
      while (j >= 0) {
        if (out.compareTo(toSort.get(j)) > 0) {
          toSort.set(j + 1, toSort.get(j));
          j--;
        } else {
          break;
        }
      }
      toSort.set(j + 1, out);
    }
  }

  // @Test
  // publicoid insertSort() {
  // System.out.println("insertSort");
  // int size = toSort.size();
  // for (int i = 1; i < size; i++) {
  // for (int j = 0; j < i; j++) {
  // ComparableObject firstObject = toSort.get(i - j);
  // ComparableObject secondObject = toSort.get(i);
  // if (firstObject.compareTo(secondObject) < 0) {
  // break;
  // }
  // }
  // swap(toSort, index1, minIndex);
  // }
  // }


  @Test
  public void shellSort() {
    System.out.println(SHELL_SORT);
    int size = toSort.size();

    for (int delta = size / 2; delta > 0; delta /= 2) {
//      for (int j = 0; j < delta; j++) {
      modInsertSort(toSort, size, delta);
//      }
    }
  }
  @Test
  public void shellSort2() {
    System.out.println(SHELL_SORT2);
    int size = toSort.size();
    
    for (int delta = size / 2; delta > 0; delta /= 2) {
//      for (int j = 0; j < delta; j++) {
      modBubbleSort(toSort, size, delta);
//      }
    }
  }

  private void modInsertSort(List<ComparableObject> toSort2, int size, int delta) {
    for (int j = delta; j < size; j += delta) {
      ComparableObject outObject = toSort2.get(j);
      int in = j - delta;
      while (in >= 0 && outObject.compareTo(toSort2.get(in)) > 0) {
        toSort2.set(in + delta, toSort2.get(in));
        in -= delta;
      }
      toSort2.set(in + delta, outObject);
    }
  }
  
  private void modBubbleSort(List<ComparableObject> toSort2, int size, int delta) {
    for (int j = delta; j < size; j += delta) {
      boolean hasSort = false;
      for (int i = 0; i < size - j; i+=delta) {
        if (toSort2.get(i).compareTo(toSort2.get(i+delta))>0) {
          swap(toSort2,i ,i+delta);
          hasSort  = true;
        }
      }
      if (!hasSort) {
        return;
      }
    }
  }

  @After
  public void afterSort() {
    System.out.println("spend time: " + (System.currentTimeMillis() - startTime));
    System.out.println("交换次数" + swaptimes);
    System.out.println("===========================after sort");
    printResult();
    System.out.println("\r\n\r\n");
  }

  public static class ComparableObject implements Comparable<ComparableObject> {

    static Random r = new Random();

    public static ComparableObject newComparableObject() {
      return newComparableObject(32);
    }

    public static ComparableObject newComparableObject(int randomMax) {
      return new ComparableObject(r.nextInt(randomMax));
    }

    public ComparableObject(int num) {
      super();
      this.num = num;
    }

    int num = 0;

    public int compareTo(ComparableObject o) {
      return this.num - o.num;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + num;
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      ComparableObject other = (ComparableObject) obj;
      if (num != other.num)
        return false;
      return true;
    }

    @Override
    public String toString() {
      return "" + num;
    }

    public String toSuperString() {
      return super.toString().substring(super.toString().lastIndexOf('@'));
    }

  }

  private <T> void swap(List<T> toSort, int j, int i) {
    if (i == j) {
      return;
    }
    swaptimes++;
    T object = toSort.get(j);
    toSort.set(j, toSort.get(i));
    toSort.set(i, object);
  }
}
