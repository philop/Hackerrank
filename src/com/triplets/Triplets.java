package com.triplets;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Triplets {

    static long countTriplets(List<Long> arr, long r) {
        Map<Long, TreeSet<Integer>> indexCountingMap = IntStream.range(0, arr.size()).boxed()
                .collect(Collectors.groupingBy(arr::get, Collectors.toCollection(()->new TreeSet<>(Collections.reverseOrder()))));
        Map<Long, Long> countingMap = indexCountingMap.entrySet().stream().collect(Collectors.toMap(Map.Entry<Long, TreeSet<Integer>>::getKey, entry->Long.valueOf(entry.getValue().size())));
        //Map<Long, Long> countingMap = arr.stream().collect(Collectors.groupingBy(el -> el, Collectors.counting()));
        return countingMap.entrySet().stream().map((el) -> {
            Long counterMid = countingMap.get(el.getKey() * r);
            Long counterEnd = countingMap.get(el.getKey() * r * r);
            if (counterMid != null && counterEnd != null && r != 1)
                //return el.getValue() * counterMid * counterEnd;
                return countPermutations(r, indexCountingMap, el);
            else if (counterMid != null && counterEnd != null)
                return el.getValue() * (counterMid - 1) * (counterEnd - 2)/ 6L;
            else return 0L;
        }).reduce(0L, Long::sum);
    }

    private static Long countPermutations(long r, Map<Long, TreeSet<Integer>> indexCountingMap, Map.Entry<Long, Long> el) {
        TreeSet<Integer> indexSetStarts = indexCountingMap.get(el.getKey());
        TreeSet<Integer> indexSetMids = indexCountingMap.get(el.getKey() * r);
        TreeSet<Integer> indexSetEnds = indexCountingMap.get(el.getKey() * r * r);

        Optional<Long> reducedStarts = indexSetEnds.stream().map(end -> {
            if(indexSetMids.higher(end) != null)
                return indexSetMids.tailSet(indexSetMids.higher(end));
            else return Collections.emptySet();
        }).flatMap(midSet -> midSet.stream().map(mid->{
            if(indexSetStarts.higher((Integer) mid) != null)
                return (long) indexSetStarts.tailSet(indexSetStarts.higher((Integer) mid)).size();
            else return 0L;
        }))

        .reduce(Long::sum);

        return (long) reducedStarts.get();
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
        testCase("1 2 1 2 4", 2, 3);
        testCase("1 2 2 4", 2, 2);
        testCase("1 3 9 9 27 81", 3, 6);
        testCase("1 5 5 25 125", 5, 4);
        testCase("1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1", 1, 161700);
        testCase(t.readFile( "input.txt"), 3, 2325652489l);
    }

    private static void testCase(String testCase, int r, long result) {
        List<Long> testArr1 = convertStringCase(testCase);
        System.out.println(result == countTriplets(testArr1, r) ? true : result+", "+countTriplets(testArr1, r));
    }

    private static List<Long> convertStringCase(String testCase) {
        return Arrays.stream(testCase.split(" ")).map(Long::parseLong).collect(Collectors.toList());
    }


}
