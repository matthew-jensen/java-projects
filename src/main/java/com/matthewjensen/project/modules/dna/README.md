# Topics:
DNA.java focuses on arrays and file/text processing.
DNA requires the two input files dna.txt and ecoli.txt.
These files are saved in the spec/ folder. This can be changed with INPUT\_PATH.
DNA processes input files, parses the genomic information, and save the output in a new file.

## Learning Outcomes:


#Background
DNA consists of long chains of chemical compounds called nucleotides.
Four nucleotides are present in DNA: Adenine (A), Cytosine (C), Guanine (G), and Thymine (T).
This are organized into subunits called "codons".
Most genes contain condons for building proteins, some do not.
Each codon (e.g. TAC GGA) uniquely encodes a single amino acid, a building block of proteins.
The sequences of DNA that encode proteins occur between a start codon (which we will assume to be ATG) and a stop codon (which is any of TAA, TAG, or TGA).
Not all regions of DNA are genes; large portions that do not lie between a
Start: ATG.
Stop: TAA, TAG, TGA.
Often high percentages of Cytosine (C) and Guanine (G) are indicators of important genetic data.


#Input
- The DNA input data consists of line pairs. 
- The input file exists, is readable, and contains valid input.
- The first line has the name of the nucleotide sequence.
- The second is the nucleotide sequence itself.
- Each character in a sequence of nucleotides will be A, C, G, T, or a dash character, "-", either upper or lowercase.
- You may assume that each sequence's number of nucleotides (without dashes) will be a multiple of 3,

##Example

> cure for cancer protein
> ATGCCACTATGGTAG
> captain picard hair growth protein
> ATgCCAACATGgATGCCcGATAtGGATTgA
> bogus protein
> CCATt-AATgATCa-CAGTt
> ...

#Process:
- from the original nucleotide sequence string to nucleotide counts.
- from nucleotide counts to mass percentages.
- from the original nucleotide sequence string to codon triplets.

#Output:

For each nucleotide sequence:
- nucleotide counts
    - Counts the occurrences of each of the four nucleotides ['A', 'C', 'G', 'T'].
- mass percentages
    - Calculates the mass percentage occupied by each nucleotide type, rounded to one digit past the decimal point.
- codons 
    - Reports the codons (TAG) present in each sequence and 
- protein-coding gene
    - begins with a valid start codon ["ATG"]
    - ends with a valid stop codon ["TAA", "TAG", or "TGA")
    - contains at least 5 total codons (including its initial start codon and final stop codon)
    - Cytosine (C) and Guanine (G) combined account for at least 30% of its total mass

##Example

> This program reports information about DNA
> nucleotide sequences that may encode
> proteins. Input file name? dna.txt
> Output file name? output.txt
> Output file output.txt after above execution (partial):
> Region Name: cure for cancer protein
> Nucleotides: ATGCCACTATGGTAG
> Nuc. Counts: [4, 3, 4, 4]
> Total Mass%: [27.3, 16.8, 30.6, 25.3] of 1978.8
> Codons List: [ATG, CCA, CTA, TGG, TAG]
> Is Protein?: YES
> ...

#Approach

##Constants

```java
int MIN_CODONS = 5; //the minimum number of codons a valid protein must have
int MIN_MASS_PERCENT = 30; // the percentage of mass from C and G in order for a protein to be valid
int UNIQUE_NUCLEOTIDES = 4; // the number of unique nucleotides (representing A, C, G, and T)
int CODONS_PER_NUCLEAOTIDE = 3; // the number of nucleotides per codon 
```

```java
/*
*
* begins with an introduction and prompts for input and output file names.
* reads the input file to process its nucleotide sequences.
* outputs the results into the given output file.
*
* @return void
*
*/
public static void main(String[] args) {

}
```

```java
/*
*
* assumes the user will type the name of an existing input file.
* input is in the proper format.
*
* @params   console 
* @return   files   array of input and output files for processing and saving
*
*/
public static String[] promptForFileNames(Scanner console) {

}
```



```java
/*
*
* Converts char nucleotide to molar mass
*
* mass percentages, use the following as the mass of each nucleotide (grams/mol).
* "junk" regions are excluded from many parts of your computations, but they do contribute mass to the total.
* Adenine (A): 135.128
* Cytosine (C): 111.103
* Guanine (G): 151.128
* Thymine (T): 125.107
* Junk (-): 100.000
*
* @params   nucleotide  char of nucleotide.
* @return   molarMass   rounded to nearest tenth.
*
*/
public static double nucleotideToMolarMass(char nucleotide) {
    double[] masses = {135.128, 111.103, 151.128, 125.107};
    double rounded = Math.round(mass * 10.0) / 10.0;
}
```

```java
/*
*
* Next, write code to pass over a nucleotide sequence and count the number of As, Cs, Gs, and Ts.
* use a String's charAt method to get individual characters.
*
* @params   sequence    sequence of 
* @return   counts    array of size 4.
*
*/
public static int[] nucleotideCountFromSequence(String sequence) {

}
```

```java
/*
*
* converts a single character (i.e. A, C, T, G) into indices (i.e. 0 to 3).
*
* @params   nucleotide  char of nucleotide.
* @return   index   int mapping of nucleotide.
*
*/
public static int nucleotideIndexFromChar(char nucleotide) {

}
```

```java
/*
*
* eliminate these characters.
*
* @params   sequence    raw sequence
* @return   sequence    without junk DNA '-'.
*
*/
public static String nucleotidesOnlyFromSequence(String sequence) {

}
```

```java
/*
*
* break apart the sequence into codons and examine each codon.
*
* @params   sequence    String of nucleotides or raw.
* @return   codons  Array of strings of codons.
*
*/
public static String[] codonsFromSequence(String sequence) {

}
```

```java
/*
*
* Saves output to file using PrintStream as described in Section 6.4 of the textbook.
* overwrites any existing data in the output file (this is the default PrintStream behavior).
* nucleotide sequence is output in uppercase
* the nucleotide counts and mass percentages are shown in A, C, G, T order.
*
* @params   path    String path of output filename
* @return   void;
*
*/
public static void saveOutput(String path) {

}
```
