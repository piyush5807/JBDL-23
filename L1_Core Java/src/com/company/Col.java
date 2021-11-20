package com.company;

import java.util.*;

public class Col {

    private int field1;
    private int field2;

    public static void main(String[] args){

        // Arraylist
        ArrayList<String> cities = new ArrayList<>();
        cities.add("Delhi");
        cities.add("Mumbai");
        cities.add("Kolkata");

        cities.addAll(Arrays.asList("Delhi", "Pune"));

        System.out.println(cities);

        cities.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        System.out.println(cities);

        // Arrays

//        String[]arr = new String[5];
//        arr[0] = "Delhi";
//        arr[1] = "Mumbai";
//        arr[2] = "Kolkata";
//        arr[3] = "";

        String[] arr = {"Delhi", "Mumbai", "Kolkata", "Chennai", "Bangalore"};
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }

        System.out.println();

        Arrays.sort(arr);

        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }

        System.out.println();


        // Sets

        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("Delhi");
        hashSet.addAll(Arrays.asList("Mumbai", "Chennai", "Pune", "Punjab", "Delhi"));

        System.out.println(hashSet);


        // Map
        // Question - implement a way to store which person has joined the jbdl session

        HashMap<String, Boolean> hashMap = new HashMap<>();

        hashMap.put("Sachin", true);
        hashMap.put("Arnav", false);
        hashMap.put("Sachin", false);
        hashMap.put("Ashutosh", false);
        hashMap.put("Deepak", false);
        hashMap.put("Krati", false);
        hashMap.put("Lakshmi", false);

        System.out.println(hashMap);

        // TreeMap

        TreeMap<String, Boolean> treeMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        treeMap.put("Sachin", true);
        treeMap.put("Arnav", false);
        treeMap.put("Ashutosh", false);
        treeMap.put("Deepak", false);
        treeMap.put("Krati", false);
        treeMap.put("Lakshmi", false);

        System.out.println(treeMap);
    }

//    @Override
//    public int compareTo(Col o) {
//        return this.field1 - o.field1;
//    }
}
