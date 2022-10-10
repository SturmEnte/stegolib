pub fn int_vec_to_string(vec: &Vec<u32>) -> String {
    let mut res: String = String::new();

    vec.iter().for_each(|num| {
        let mut string: String = num.to_string();
        let mut zeros: String = String::new();

        let mut n = 8 - string.len();
        while n > 0 {
            zeros.push_str("0");    
            n -= 1;
        }

        zeros.push_str(string.as_str());
        string = zeros;

        res.push_str(string.as_str());
    });

    res
}
