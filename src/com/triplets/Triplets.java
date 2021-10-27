package com.triplets;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Triplets {

    static long countTriplets(List<Long> arr, long r) {
        Map<Long, Long> countingMap = arr.stream().collect(Collectors.groupingBy(el -> el, Collectors.counting()));
        return countingMap.entrySet().stream().map((el) -> {
            Long counterMid = countingMap.get(el.getKey() * r);
            Long counterEnd = countingMap.get(el.getKey() * r * r);
            if (counterMid != null && counterEnd != null && r != 1)
                return el.getValue() * counterMid * counterEnd;
            else if (counterMid != null && counterEnd != null && r == 1)
                return (el.getValue()-0) * (counterMid - 1) * (counterEnd - 2)/6;
            else return 0l;
        }).reduce(0l, (el, accumulator) -> accumulator + el);
    }

    private String readFile(String name){
        try {
            URL url = getClass().getResource(name);
            File myObj = new File(url.getPath());
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                return data;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String args[]) {

        Triplets t = new Triplets();
        testCase("1 2 2 4", 2, 2);
        testCase("1 3 9 9 27 81", 3, 6);
        testCase("1 5 5 25 125", 5, 4);
        testCase(t.readFile( "input.txt"), 3, 2325652489l);
        testCase("1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1", 1, 161700);

    }

    private static void testCase(String testCase, int r, long result) {
        List<Long> testArr1 = convertStringCase(testCase);
        System.out.println(result == countTriplets(testArr1, r) ? true : result+", "+countTriplets(testArr1, r));
    }

    private static List<Long> convertStringCase(String testCase) {
        return Arrays.stream(testCase.split(" ")).map(Long::parseLong).collect(Collectors.toList());
    }


}
