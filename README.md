# javaprograms
These are programs that I have made in college classes.
Here is a list and explanation of what each of them do:

#[HillCipher](https://github.com/dreamwork801/javaprograms/tree/master/hillcipher)
A hillcipher is an encryption tactic that uses matrix multiplication - http://en.wikipedia.org/wiki/Hill_cipher
I made this in my Security in Computing class. I added sample input files for the program to run properly. The sample files contains one file that is the key and one file that is the text to be encrypted.

After running "javac hillcipher.java" in the command line to compile the code, you can run the program like so:

java hillcipher inkey1.txt infile1.txt

#[CRCheck](https://github.com/dreamwork801/javaprograms/tree/master/CRCheck)
A crcheck is a way to make sure that a file was not tampered with by a third party. http://en.wikipedia.org/wiki/Cyclic_redundancy_check
I made this in my Security in Computing class. It calculate the CRC for any given text. It has 2 modes, a calculate mode (c) where it calculate the CRC, and a verify mode (v), where it verifies the CRC calculated is the same as the CRC attached to the file. Once you compile the code in the command line using "javac crcheck.java", you can run the program like so:

For the calculate part run this:
java crcheck c WC-ngi.txt

For the verification part run this:
java crcheck v WC-ngi.crc
