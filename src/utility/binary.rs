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