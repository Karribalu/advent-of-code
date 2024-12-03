package org.example;

import org.springframework.data.repository.init.ResourceReader;

import java.io.*;
import java.util.*;

public class Main {
    private static long findTotalDistance() throws IOException {
        long totalDistance = 0;
        ClassLoader classLoader = ResourceReader.class.getClassLoader();
        List<Integer> firstLine = new ArrayList<>();
        List<Integer> secondLine = new ArrayList<>();
        try (InputStream inputStream = classLoader.getResourceAsStream("input-1.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitted = line.split(" {3}");
                firstLine.add(Integer.parseInt(splitted[0]));
                secondLine.add(Integer.parseInt(splitted[1]));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(firstLine);
        Collections.sort(secondLine);
        for(int i = 0;i < firstLine.size();i++){
            totalDistance += Math.abs(firstLine.get(i) - secondLine.get(i));
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i: secondLine){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        totalDistance = 0;
        for(int i: firstLine){
            totalDistance += (i * map.getOrDefault(i, 0));
        }
        return totalDistance;
    }
    public static void main(String[] args) throws IOException {
        System.out.println(findTotalDistance());
    }
}