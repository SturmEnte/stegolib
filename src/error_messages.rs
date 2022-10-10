pub fn wrong_command_usage(command_name: &String) {
    println!("Wrong command usage!");
    println!("Please use this command like this: {} encode [input-image] [input-file] [output-image]", command_name);
    println!("Or like this: {} decode [input-image] [output-file]", command_name);
}