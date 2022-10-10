use std::path::Path;
use std::fs;

use image::{self, DynamicImage};
use image::GenericImageView;
use ascii_converter;

use crate::utility;
use crate::error_messages;

// [Cmd name] [en- or decode] [input-image] [input-file] [output-image]
pub fn least_significant_bit(args: Vec<String>) {

    if args.len() < 3 {
        error_messages::wrong_command_usage(&args[1]);
        return;
    }

    let mode: &String = &args[2];

    if mode == "encode" {
        if args.len() < 6 {
            error_messages::wrong_command_usage(&args[1]);
            return;
        }

        println!("Mode: {mode}");
        println!("Input image: {}", &args[3]);
        println!("Input file: {}", &args[4]);
        println!("Output image: {}", &args[5]);

        encode(Path::new(&args[3]), Path::new(&args[4]), Path::new(&args[5]));
        return;
    } else if mode == "decode" {
        if args.len() < 5 {
            error_messages::wrong_command_usage(&args[1]);
            return;
        }

        println!("Mode: {mode}");
        println!("Input image: {}", &args[3]);
        println!("Output file: {}", &args[4]);

        decode(Path::new(&args[3]), Path::new(&args[4]));
        return;
    }

    println!("Not an valid mode!");
    println!("Valid modes: encode, decode");
    
}

fn encode(input_img_path: &Path, input_file_path: &Path, output_img_path: &Path) {
    if !input_img_path.exists() {
        println!("The input image does not exist!");
        return;
    }

    if !input_file_path.exists() {
        println!("The input file does not exist!");
        return;
    }

    if output_img_path.exists() {
        println!("The output image does already exist!");
        return;
    }

    let input_file: String =  fs::read_to_string(input_file_path).unwrap();
    let hidden_binary: Vec<char> = utility::int_vec_to_string(&ascii_converter::string_to_binary(input_file.as_str()).unwrap()).chars().collect();

    let img: DynamicImage = image::open(input_img_path).unwrap();

    let mut i: usize = 0;
    for mut pixel in img.pixels() {
        let mut colors: [String; 4] = [
            utility::decimal_to_binary(pixel.2.0[0]), // r
            utility::decimal_to_binary(pixel.2.0[1]), // g
            utility::decimal_to_binary(pixel.2.0[2]), // b
            utility::decimal_to_binary(pixel.2.0[3])  // a
        ];

        let mut j: usize = 0;

        while i < hidden_binary.len() && j < 4 {
            colors[j] = String::from(utility::remove_last_char(colors[j].as_str()));
            colors[j].push_str(hidden_binary[i].to_string().as_str());

            j += 1;
            i += 1;
        }

        pixel.2.0[0] = utility::binary_to_decimal(&colors[0]);
        pixel.2.0[1] = utility::binary_to_decimal(&colors[1]);
        pixel.2.0[2] = utility::binary_to_decimal(&colors[2]);
        pixel.2.0[3] = utility::binary_to_decimal(&colors[3]);

        if i >= hidden_binary.len() {
            break;
        }
    }

    img.save(output_img_path).unwrap();
}

fn decode(input_img_path: &Path, output_file_path: &Path) {
    if !input_img_path.exists() {
        println!("The input image does not exist!");
        return;
    }

    if output_file_path.exists() {
        println!("The output file does already exist!");
        return;
    }

    let mut result: String = String::new();
    let mut buffer: String = String::new();
    let mut i = 0;

    let img: DynamicImage = image::open(input_img_path).unwrap();
    for pixel in img.pixels() {
        let r: String = utility::decimal_to_binary(pixel.2.0[0]);
        let g: String = utility::decimal_to_binary(pixel.2.0[1]);
        let b: String = utility::decimal_to_binary(pixel.2.0[2]);
        let a: String = utility::decimal_to_binary(pixel.2.0[3]);


        // let number = "01101".parse::<u128>().unwrap();
        // println!("{}", number);

        let mut j = 0;

        while j < 4 {
            // println!("{}", r.chars().last().unwrap().to_string().parse::<u128>().unwrap());
            buffer.push_str(r.chars().last().unwrap().to_string().as_str());
            j += 1;
            i += 1;
        }

        if i == 8 {
            // Buffer
            println!("{}", buffer.parse::<u128>().unwrap());
            println!("{}", buffer);
            buffer = String::new();
            i = 0;
        }

        // println!("{r}|{g}|{b}|{a}");
    }
}