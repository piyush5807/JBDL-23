package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamsExample {

    public static void main(String[] args) {

        // Iterative
        List<String> cities = Arrays.asList("Delhi");

        String[]arr = {"Delhi", "Pune", "Udaipur", "Itanagar"};
//
//        List<String> vowelCities = new ArrayList<>();
//        for(String city : cities){
//            if(city.startsWith("A") || city.startsWith("U") ||
//                    city.startsWith("O") || city.startsWith("I") ||
//                    city.startsWith("E")){
//             vowelCities.add(city.toUpperCase());
//            }
//        }
//
//        System.out.println(vowelCities);

        // Declarative | Functional Programing

//        List<String> vowelCities = cities.stream()
//                .filter(s -> (s.startsWith("A") || s.startsWith("E") || s.startsWith("I") ||
//                            s.startsWith("O") || s.startsWith("U")))
//                .map(x -> x.toUpperCase())
//                .collect(Collectors.toList());
//
//        System.out.println(vowelCities);
//


//        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
//
//        int temp = 0;
//        for(Integer num : numbers){
//            if(num % 2 == 0){
//                temp += num * num;
//            }
//        }
//
//        System.out.println(temp);
//
//        System.out.println(numbers.stream()
//                .filter(n -> n % 2 == 0)
//                .map(n -> n * n)
//                .reduce(0, (a, b) -> a + b));
//
//
//        String answer = "";
//        for(String city : cities){
//            answer += city + " ";
//        }
//
//        System.out.println(answer);
//
////        System.out.println("ABC" + null);
//
//        System.out.println(cities.stream()
//                .reduce("", (a, b) -> a + b + " "));
//
//        System.out.println(Arrays.stream(arr).reduce("", (a, b) -> a + b + " "));

        // XYZ  DEFG

        System.out.println(cities.stream().sorted((a, b) -> {

            System.out.println(a + " " + b);
            if(a.length() == b.length()){
                return a.compareTo(b);
            }

            return a.length() - b.length();
        }).collect(Collectors.toList()));

    }

    // Q1. You have a list of integers, you need to find the sum of squares of even numbers
    // [1, 2, 3, 4, 5, 6, 7, 8] = [4 + 16 + 36 + 64 = 120]

    // Q2. You have a list of string, you need to concatenate them


}
