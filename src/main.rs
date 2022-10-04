mod commands;

use std::env;

fn main() {
    let args: Vec<String> = env::args().collect();
    
    match args[1].as_str() {
        "lsb" => commands::least_significant_bit(args),
        "least-significant-bit" => commands::least_significant_bit(args),
        _ => (),
    }
}
