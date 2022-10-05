
// [Cmd name] [en- or decode] [input-image] [input-file] [output-image]
pub fn least_significant_bit(args: Vec<String>) {

    if args.len() < 6 {
        println!("Wrong command usage!");
        println!("Please use this command like this: {} [mode] [input-image] [input-file] [output-image]", args[1]);
        return;
    }

    let mode: &String = &args[2];
    let input_img: &String = &args[3];
    let input_file: &String = &args[4];
    let output_img: &String = &args[5];

    println!("Mode: {mode}");
    println!("Input image: {input_img}");
    println!("Input file: {input_file}");
    println!("Output image: {output_img}");

    if mode == "encode" {
        encode();
        return;
    } else if mode == "decode" {
        println!("Not implemented yet");
        return;
    }

    println!("Not an valid mode!");
    println!("Valid modes: encode, decode");
    
}

fn encode() {
    }
}