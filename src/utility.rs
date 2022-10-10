mod binary;
mod string;
mod error_messages_util;

pub fn decimal_to_binary(decimal: u8) -> String {
    binary::decimal_to_binary(decimal)
}

pub fn binary_to_decimal(binary: &String) -> u8 {
    binary::binary_to_decimal(binary)
}

pub fn remove_last_char(string: &str) -> &str {
    string::remove_last_char(string)
}

pub mod error_messages {
    pub fn wrong_command_usage(command_name: &String) {
        wrong_command_usage(command_name);
    }
}