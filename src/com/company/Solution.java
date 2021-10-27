package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Solution {

    // Complete the triplets function below.
    static long triplets(int[] a, int[] b, int[] c) {

        Integer[] newA = eliminateDuplicates(a);
        Integer[] newB = eliminateDuplicates(b);
        Integer[] newC = eliminateDuplicates(c);


        Arrays.sort(newA);
        Arrays.sort(newB);
        Arrays.sort(newC);



        long sum = 0;
        for(int i=0; i<newB.length; i++){
            int opa = Arrays.binarySearch(newA, newB[i]);
            if(opa < 0) opa = opa * -1 - 1;
            else opa++;
            System.out.println(newB[i]+","+opa);
            int opc = Arrays.binarySearch(newC, newB[i]);
            if(opc < 0) opc = opc * -1 - 1;
            else opc++;
            System.out.println(newB[i]+","+opc);
            sum += opa * opc;

        }

        return sum;
    }

    public static Integer[] eliminateDuplicates(int[] b){
        Set setb = new HashSet();
        for(int i=0; i<b.length; i++) setb.add(b[i]);
        Integer[] newB = new Integer[setb.size()];
        newB = new ArrayList<Integer>(setb).toArray(newB);
        return newB;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        List<Integer> list = Arrays.asList(1, 8, 6, 7, 7, 1, 1, 2);

        //Map<String, Integer> map = list.stream().collect(Collectors.groupingBy(s -> s)).entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry-> entry.getValue().size()));
        //Map<Object, Long> map = list.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        //Map<Object, Long> map = list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));


        double mean = list.stream().collect(Collectors.teeing(Collectors.summingDouble(i->i), Collectors.counting(), (sum, count)-> sum/count));
        System.out.println(mean);


        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("file.txt")));

       /* String[] lenaLenbLenc = scanner.nextLine().split(" ");

        int lena = Integer.parseInt(lenaLenbLenc[0]);

        int lenb = Integer.parseInt(lenaLenbLenc[1]);

        int lenc = Integer.parseInt(lenaLenbLenc[2]);

        int[] arra = new int[lena];

        String[] arraItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lena; i++) {
            int arraItem = Integer.parseInt(arraItems[i]);
            arra[i] = arraItem;
        }

        int[] arrb = new int[lenb];

        String[] arrbItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenb; i++) {
            int arrbItem = Integer.parseInt(arrbItems[i]);
            arrb[i] = arrbItem;
        }

        int[] arrc = new int[lenc];

        String[] arrcItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenc; i++) {
            int arrcItem = Integer.parseInt(arrcItems[i]);
            arrc[i] = arrcItem;
        }

        long ans = triplets(arra, arrb, arrc);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();*/
    }
}
