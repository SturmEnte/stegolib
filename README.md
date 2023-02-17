# Stegolib
A small steganography program made using Java

## Features
- LSB (Least Significant Bit) encoding and decoding

## LSB (Least Significant Bit)
This hiding method will only change the last and therefore least significant bit of each color (red, green, blue) of each pixel in a image to hide the data

### Note
This might not work for non text-only files because the encoder adds a NUL character to the end of the data and the decoder will look for that character and stops, when finding it.
