use std::path::Path;
use std::fs;

use image::{self, DynamicImage};
use image::GenericImageView;
use ascii_converter;

use crate::utility;

// [Cmd name] [en- or decode] [input-image] [input-file] [output-image]
pub fn least_significant_bit(args: Vec<String>) {

    if args.len() < 6 {
        println!("Wrong command usage!");
        println!("Please use this command like this: {} [mode] [input-image] [input-file] [output-image]", args[1]);
        return;
    }

    let mode: &String = &args[2];

    println!("Mode: {mode}");
    println!("Input image: {}", &args[3]);
    println!("Input file: {}", &args[4]);
    println!("Output image: {}", &args[5]);

    if mode == "encode" {
        encode(Path::new(&args[3]), Path::new(&args[4]), Path::new(&args[5]));
        return;
    } else if mode == "decode" {
        decode(Path::new(&args[3]));
        println!("Not implemented yet!");
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
    let hidden_binary: Vec<char> = int_vec_to_string(ascii_converter::string_to_binary(input_file.as_str()).unwrap()).chars().collect();
    
    println!("{:?}", hidden_binary);

    let img: DynamicImage = image::open(input_img_path).unwrap();
    for mut pixel in img.pixels() {
        let r: String = utility::decimal_to_binary(pixel.2.0[0]);
        let g: String = utility::decimal_to_binary(pixel.2.0[1]);
        let b: String = utility::decimal_to_binary(pixel.2.0[2]);
        let a: String = utility::decimal_to_binary(pixel.2.0[3]);

        println!("{r}|{g}|{b}|{a}");

        pixel.2.0[0] = utility::binary_to_decimal(r);
        pixel.2.0[1] = utility::binary_to_decimal(g);
        pixel.2.0[2] = utility::binary_to_decimal(b);
        pixel.2.0[3] = utility::binary_to_decimal(a);

        println!("{}|{}|{}|{}", pixel.2.0[0], pixel.2.0[1], pixel.2.0[2], pixel.2.0[3]);
    }

    img.save(output_img_path).unwrap();
}

fn decode(input_img_path: &Path) {
    let img: DynamicImage = image::open(input_img_path).unwrap();
    for pixel in img.pixels() {
        let r: String = utility::decimal_to_binary(pixel.2.0[0]);
        let g: String = utility::decimal_to_binary(pixel.2.0[1]);
        let b: String = utility::decimal_to_binary(pixel.2.0[2]);
        let a: String = utility::decimal_to_binary(pixel.2.0[3]);

        println!("{r}|{g}|{b}|{a}");
    }
}

fn int_vec_to_string(vec: Vec<u32>) -> String {
    let mut res: String = String::new();

    vec.iter().for_each(|num| {
        let mut string: String = num.to_string();
        let mut zeros: String = String::new();

        let mut n = 8 - string.len();
        while n > 0 {
            zeros.push_str("0");    
            n -= 1;
        }

        zeros.push_str(string.as_str());
        string = zeros;

        res.push_str(string.as_str());
    });

    res
}