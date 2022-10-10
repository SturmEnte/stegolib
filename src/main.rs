mod commands;
mod utility;
mod error_messages;

use std::env;

fn main() {
    let args: Vec<String> = env::args().collect();

    if args.len() < 2 {
        println!("Please enter a command. You can see all commands witht the help command (stegolib help)");
        return;
    }
    
    match args[1].as_str() {
        "lsb" => commands::least_significant_bit(args),
        "least-significant-bit" => commands::least_significant_bit(args),
        _ => (),
    }
}
