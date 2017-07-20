/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.sf.practice.temptest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Test1 {

  public static void main(String[] args) throws Exception {
    
    
    int[] i = getArray();
    System.out.println(Arrays.toString(i));
    i= new int[]{64, 52, 70, 14, 85, 33, 43, 91, 27, 73};

    int left = 0;
    int right = i.length - 1;
    int iii = 0;
    while (left < right) {
      while (i[left] % 2 == 0 && left< right) {
        iii++;
        left++;
      }
      while (i[right] % 2 != 0 && left< right) {
        iii++;
        right--;
      }
      if (left != right) {
        int tmp = i[left];
        i[left] = i[right];
        i[right] = tmp;
      }
    }
    System.out.println(iii);
    
    System.out.println(Arrays.toString(i));
//    testArray();
    i= new int[]{64, 52, 70, 14, 85, 33, 43, 91, 27, 73};
    fill(i);
    System.out.println(Arrays.toString(i));
  }
  public static void fill(int[] arr){
    int count = 0;
    int iii = 0;
    for (int i = 0; i < arr.length-count; i++) {
        
        for (int j = 0; j < arr.length; j++) {
          iii++;
            int temp = 0;
            if(arr[j]%2==0){
                count++;
                if(j!=arr.length-count){
                temp = arr[j];
                arr[j]=arr[arr.length-count];
                arr[arr.length-count]=temp;
                }
                break;
            }
        }
        
    }
    System.out.println(iii);
}
  private static int[] getArray() {
    Random r = new Random();
    int[] array = new int[10];
    int i = 0;
    while (i< 10) {
      boolean exists = false;
      int random = r.nextInt(100)+1;
      for (int j = 0; j < i; j++) {
        if(array[j] == random){
          exists = true;
          break;
        }
      }
      if ( ! exists ) {
        array[i++] = random;
      }
    }
    
    return array;
  }
  private static boolean printOriginal = true;
  private static boolean printDiff = true;
  public static void testArray() throws NoSuchAlgorithmException {
    Random random = new Random();
    
    long currentTimeMillis = 0L;
    for (int i = 0; i < 10; i++) {
      int[] array1 = generateRandomArray(random,10);
      int[] array2 = generateRandomArray(random,10);
      long start = System.currentTimeMillis();
      System.out.println("---------------------------------------------------------");
      printDiff(array1,array2);
      long end = System.currentTimeMillis();
      currentTimeMillis+= (end - start);
    }
    System.out.println("total time:"+currentTimeMillis);
  }
  
  private static void printDiff(int[] array1, int[] array2) {
    Set<Integer> set1 = arrayToSet(array1);
    Set<Integer> set2 = arrayToSet(array2);
    if (printOriginal) {
      System.out.println("array1:"+set1);
      System.out.println("array2:"+set2);
    }
    Set<Integer> all = new HashSet<>();
    all.addAll(set1);
    all.addAll(set2);
    
    set1.retainAll(set2);
    all.removeAll(set1);
    if (all.isEmpty()) {
      System.out.println("asdfdsgfjrewgivkxcgj");
    }else {
      if (printDiff) {
        System.out.println("the diff part:"+all);
      }
    }
  }

  private static Set<Integer> arrayToSet(int[] array1) {
    Set<Integer> set = new HashSet<>();
    for (Integer integer : array1) {
      set.add(integer);
    }
    return set;
  }

  private static int[] generateRandomArray(Random instanceStrong,int size) {
    
    int[] random = new int[size];
    Set<Integer> result = new HashSet<>();
    while (result.size() < 10) {
      result.add(instanceStrong.nextInt(100) + 1);
    }
    int i = 0;
    for (Integer integer : result) {
      random[i++] = integer;
    }
    return random;
  }

  public static void testAnimal() {
    Animal dog = Animal.create("dog", 4);
    assertEquals("dog", dog.name());
    assertEquals(4, dog.numberOfLegs());

    // You probably don't need to write assertions like these; just illustrating.
    assertTrue(Animal.create("dog", 4).equals(dog));
    assertFalse(Animal.create("cat", 4).equals(dog));
    assertFalse(Animal.create("dog", 2).equals(dog));

    assertEquals("Animal{name=dog, numberOfLegs=4}", dog.toString());
  }
//  public static void testAnimal() {
//    Animal dog = Animal.create("dog", 4);
////    assertEquals("dog", dog.name());
////    assertEquals(4, dog.numberOfLegs());
//
//    // You probably don't need to write assertions like these; just illustrating.
////    assertTrue(Animal.create("dog", 4).equals(dog));
////    assertFalse(Animal.create("cat", 4).equals(dog));
////    assertFalse(Animal.create("dog", 2).equals(dog));
//
////    assertEquals("Animal{name=dog, numberOfLegs=4}", dog.toString());
//  }
}
