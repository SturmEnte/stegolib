pub fn remove_last_char(string: &str) -> &str {
    let mut chars = string.chars();
    chars.next_back();
    chars.as_str()
}