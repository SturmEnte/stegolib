pub fn decimal_to_binary(decimal: u8) -> String {
    let mut result: String = String::new();
    let mut rest: i16 = decimal as i16;

    let mut i: i16 = 7;
    while i >= 0 {
        let n: i16 = rest - 2i16.pow(i as u32);
        if n >= 0 {
            rest = n;
            result.push_str("1");
        } else {
            result.push_str("0");
        }

        i -= 1;
    }

    result
}

pub fn binary_to_decimal(binary: &String) -> u8 {
    let mut result: u8 = 0;

    let binary_vec: Vec<char> = binary.chars().collect();

    let mut n: i8 = 7;
    binary_vec.iter().for_each(|char| {
        result += char_to_u8(char) * 2u8.pow(n as u32);
        n -= 1;
    });

    result
}

fn char_to_u8(char: &char) -> u8 {
    const RADIX: u32 = 10;
    char.to_digit(RADIX).unwrap().try_into().unwrap()
}