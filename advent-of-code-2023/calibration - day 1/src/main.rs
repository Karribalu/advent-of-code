use std::collections::HashMap;
use std::fs;
use std::time::Instant;

fn main() {
    let start = Instant::now();
    let file1 = fs::read_to_string("src/calibration_input.txt")
        .expect("file not found");
    // calibration(file1.lines().collect());
    let file2 = fs::read_to_string("src/calibration_with_letters_input")
        .expect("file not found");
    // calibration_with_letters();
    calibration_new();
    let end = Instant::now();
    let elapsed = end.duration_since(start);
    println!("Execution took time: {:.2?}", elapsed);
}
fn calibration(lines: Vec<&str>) {
    let mut total_sum = 0;
    for line in lines {
        let mut first_num = -1;
        let mut second_num = -1;
        for (i, char) in line.chars().enumerate() {
            if char.is_digit(10) {
                first_num = char.to_digit(10).unwrap() as i32;
                break;
            }
        }
        for (i, char) in line.chars().rev().enumerate() {
            if char.is_digit(10) {
                second_num = char.to_digit(10).unwrap() as i32;
                break;
            }
        }
        total_sum += (first_num * 10 + second_num);
    }
    println!("{}", total_sum);
}
fn calibration_new() {
    let file = fs::read_to_string("src/calibration_with_letters_input")
        .expect("file not found");
    let mut map = HashMap::new();
    map.insert("one", "o1e");
    map.insert("two", "t2o");
    map.insert("three", "t3e");
    map.insert("four", "f4r");
    map.insert("five","f5e");
    map.insert("six", "s6x");
    map.insert("seven","s7n");
    map.insert("eight", "e8t");
    map.insert("nine", "n9e");
    let mut total_sum = 0;
    for line in file.lines() {
        let mut temp_line = line.clone().to_owned();
        for (key, value) in map.iter() {
            if temp_line.contains(key) {
                temp_line = temp_line.replace(key, value);
            }
        }
        let mut first_num = -1;
        let mut second_num = -1;

        let mut first_idx = -1;
        let mut second_idx = -1;
        for (i, char) in temp_line.chars().enumerate() {
            if char.is_digit(10) {
                first_num = char.to_digit(10).unwrap() as i32;
                first_idx = i as i32;
                break;
            }
        }
        for (i, char) in temp_line.chars().rev().enumerate() {
            if char.is_digit(10) {
                second_num = char.to_digit(10).unwrap() as i32;
                second_idx = i as i32;
                break;
            }
        }
        println!("{} \n {} \n {}, {}",line, temp_line, first_num, second_num);

        total_sum += (first_num * 10 + second_num);
    }
    println!("{}", total_sum);
}