package org.example;

import org.springframework.data.repository.init.ResourceReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static long findMul(){
        ClassLoader classLoader = ResourceReader.class.getClassLoader();
        long total = 0;
        try (InputStream inputStream = classLoader.getResourceAsStream("input.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            // Reading the entire content of the file into a single string
            String content = reader.lines().reduce("", (acc, line) -> acc + line);
            // Use the content as needed
            String regex = "don't\\(\\).*?(do\\(\\)|$)";
            String result = content.replaceAll(regex, "");
            System.out.println(result);
            content = result;
            for(int index: findAllOccurrences(content, "mul(")){
                String firstNumber = "";
                boolean commaFound = false;
                String secondNumber = "";
                for(int i = index + 4; i < content.length();i++){
                    char c = content.charAt(i);
                    if(c == ')'){
                        if(commaFound && !firstNumber.isEmpty() && firstNumber.length() <= 3 && !secondNumber.isEmpty() && secondNumber.length() <= 3 && isNumber(firstNumber) && isNumber(secondNumber)){
                            System.out.println(content.substring(index, i + 1));
                            total += ((long) Integer.parseInt(firstNumber) * Integer.parseInt(secondNumber));
                        }
                        break;
                    }else if(c == ','){
                        if(commaFound){
                            break;
                        }else{
                            commaFound = true;
                        }
                    }else if(Character.isDigit(c)){
                        if(commaFound){
                            secondNumber += c;
                        }else{
                            firstNumber += c;
                        }
                    }else{
//                        System.out.println(content.substring(index, i));
                        break;
                    }
                }

            }
            return total;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    private static boolean isNumber(String num){
        for(char c: num.toCharArray()){
            if(c >= '0' && c <= '9'){
                continue;
            }else{
                return false;
            }
        }
        return true;
    }
    public static List<Integer> findAllOccurrences(String text, String substring) {
        List<Integer> indices = new ArrayList<>();
        int index = text.indexOf(substring); // Find the first occurrence

        while (index != -1) {
            indices.add(index); // Add the found index to the list
            index = text.indexOf(substring, index + 1); // Look for the next occurrence
        }

        return indices;
    }
    public static void main(String[] args) {
        System.out.println(findMul());

    }
}