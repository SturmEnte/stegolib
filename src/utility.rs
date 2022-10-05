mod binary;
mod string;

pub fn decimal_to_binary(decimal: u8) -> String {
    binary::decimal_to_binary(decimal)
}

pub fn binary_to_decimal(binary: String) -> u8 {
    binary::binary_to_decimal(binary)
}

pub fn remove_last_char(string: &str) -> &str {
    string::remove_last_char(string)
}