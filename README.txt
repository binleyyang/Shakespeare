/* Binley Yang
 * CSC 172
 * Shakespeare
 * Email: byang8@u.rochester.edu
 * */


-----------------------------------HYPOTHESIS-----------------------------------------

There are 3366.0 many different words and 21076 total words in the sonnets.txt file.
There are 3448.0 many different words and 26113 total words in the control.txt file.

The hypothesis is correct as ratio of (SD/SS)/(ED/ES) is 1.209526626430741 > 1.

My control file consists of around 5-6 famous contemporary short stories, and I believe
this is a good control because the authors are well-versed in the art of language, as 
was Shakespeare, but the ratio test confirms that Shakespeare used a wider variety of 
language than modern writers.

----------------------------------INSTRUCTIONS-----------------------------------------

To compile, javac Main.java
To run, java Main

The program will then display the results from the ratio test, and proceed to query the 
user to a word to search for in the concordance. To exit the while-loop or search,
enter 'escape' into the command-line, and the next part of the program sorts the words 
in the sonnets, and then merges the dictionary list, and from there finds the 
Elizabethanism's in the sonnets.

I implemented a Comparable mergesort algorithm for Elizabethanism detection, and a 
Quadratic Probe Hashing method to handle collisions during the insertion phase of 
building the concordance.

-----------------------------------TRANSCRIPT------------------------------------------

Last login: Thu Nov  7 19:04:05 on ttys000
dhcp-10-5-8-232:~ Binley$ cd desktop/shakespeare
dhcp-10-5-8-232:shakespeare Binley$ java Main
There are 3366.0 many different words and 21076 total words in the sonnets.txt file.
There are 3448.0 many different words and 26113 total words in the control.txt file.

The hypothesis is correct as ratio of (SD/SS)/(ED/ES) is 1.209526626430741 > 1

Enter the word you want to search for, enter 'escape' to exit.
breast
breast occurs 7 times, in lines: 367, 387, 405, 813, 1844, 1871, 2597, 
Please enter another word to search for: 
lawful
lawful occurs 3 times, in lines: 592, 831, 2409, 
Please enter another word to search for: 
falsehood
falsehood occurs 2 times, in lines: 806, 2321, 
Please enter another word to search for: 
conquest
conquest occurs 3 times, in lines: 102, 770, 1255, 
Please enter another word to search for: 
derp
Word not found. Please try again or enter another word.
wailing
wailing occurs 1 times, in lines: 703, 
Please enter another word to search for: 
surmount
surmount occurs 1 times, in lines: 1048, 
Please enter another word to search for: 
escape
Sorting sonnets and merging them with the lexicon and writing to 'output.txt', please wait:



