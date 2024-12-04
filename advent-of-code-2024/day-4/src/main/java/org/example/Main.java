package org.example;

import org.springframework.data.repository.init.ResourceReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    enum Directions {
        FORWARD,
        BACKWARD,
        FORWARD_DIAGONAL_FRONT,
        FORWARD_DIAGONAL_BACK,
        BACKWARD_DIAGONAL_FRONT,
        BACKWARD_DIAGONAL_BACK,
        VERTICAL_FORWARD,
        VERTICAL_BACK
    }

    private static int findXmas() throws IOException {
        ClassLoader classLoader = ResourceReader.class.getClassLoader();
        long count = 0;
        try (InputStream inputStream = classLoader.getResourceAsStream("input.txt");
             final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            Map<Integer, String> verticalLines = new HashMap<>();
            String line = "";
            List<String> lines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
//                for(int i = 0;i < line.length();i++){
//                    verticalLines.put(i, verticalLines.getOrDefault(i, "") + line.charAt(i));
//                }
//                for(int i = 0;i < line.length() - 4;i++){
//                    if(line.charAt(i) == 'X' && line.charAt(i + 1) == 'M' && line.charAt(i + 2) == 'A', line.charAt(i + 3) == 'S'){
//                        count++;
//                    }
//                    if(i > 3 && line.charAt(i) == 'S' && line.charAt(i - 1) == 'A' && line.charAt(i - 2) == 'M' && line.charAt(i - 3) == 'X'){
//                        count++;
//                    }
//                }
                lines.add(line);
            }
            int lineSize = lines.get(0).length();
            for (int i = 0; i < lines.size(); i++) {
                String currentLine = lines.get(i);
                for (int j = 0; j < lineSize; j++) {
                    if (currentLine.charAt(i) == 'X') {
                        count += find()
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private static int find(List<String> lines, int row, int col, Directions currentDirection, Character lastChar){
        int nextRow = row;
        int nextCol = col;
        switch (currentDirection){
            case FORWARD: {
                nextCol++;
            }
                break;
            case BACKWARD:{
                nextCol--;
            }
                break;
            case FORWARD_DIAGONAL_FRONT: {
                nextRow++;
                nextCol++;
            }
                break;
            case FORWARD_DIAGONAL_BACK:{
                nextCol--;
                nextRow++;
            }
                break;
            case BACKWARD_DIAGONAL_FRONT:{
                nextCol++;
                nextRow--;
            }
                break;
            case BACKWARD_DIAGONAL_BACK:{
                nextCol--;
                nextRow--;
            }
                break;
            case VERTICAL_FORWARD:{
                nextRow++;
            }
                break;
            case VERTICAL_BACK:{
                nextRow--;
            }
                break;
        }
        if(nextRow < 0 || nextRow >= lines.size() || nextCol <0 || nextCol >= lines.get(0).length()) {
            return 0;
        }
        if(lastChar == null){
            lastChar = 'X';
        }
        switch (lastChar){
            case null:
                lastChar = 'X';
                brak;
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}