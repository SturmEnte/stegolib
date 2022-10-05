
// [Cmd name] [input-image] [input-file] [output-image]
pub fn least_significant_bit(args: Vec<String>) {

    if args.len() < 5 {
        println!("Wrong command usage!");
        println!("Please use this command like this: {} [input-image] [input-file] [output-image]", args[1]);
        return;
    }

    args.iter().for_each(|arg| {
        println!("{}", arg);
    });
}