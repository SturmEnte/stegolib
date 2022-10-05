use image::{self, DynamicImage};
use image::GenericImageView;

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
        encode(&args[3], &args[4], &args[5]);
        return;
    } else if mode == "decode" {
        println!("Not implemented yet");
        return;
    }

    println!("Not an valid mode!");
    println!("Valid modes: encode, decode");
    
}

fn encode(input_img: &String, input_file: &String, output_img: &String) {
    let img: DynamicImage = image::open(input_img).unwrap();
    for pixel in img.pixels() {
        println!("{:?}", pixel.2.0[0]);
    }
}