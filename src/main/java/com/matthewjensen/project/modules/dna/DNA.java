import java.util.*;
import java.io.*;
import java.text.*;

public class DNA {

    int MIN_CODONS = 5; //the minimum number of codons a valid protein must have
    int MIN_MASS_PERCENT = 30; // the percentage of mass from C and G in order for a protein to be valid
    int UNIQUE_NUCLEOTIDES = 4; // the number of unique nucleotides (representing A, C, G, and T)
    public static final int CODONS_PER_NUCLEOTIDE = 3; // the number of nucleotides per codon 
    public static final String INPUT_DIR = "input/"; // the number of nucleotides per codon 
    public static final String OUTPUT_DIR = "output/"; // the number of nucleotides per codon 
    public static final boolean DEBUG = false; // controls print statements for debugging
    public static final double[] MOLAR_MASSES = {135.128, 111.103, 151.128, 125.107, 100.00};
    public static final char[] NUCLEOTIDES = {'A', 'T', 'G', 'C', '-'};

    /*
    *
    * begins with an introduction and prompts for input and output file names.
    * reads the input file to process its nucleotide sequences.
    * outputs the results into the given output file.
    *
    * @return void
    *
    */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        String[] files = promptForFileNames(console);
        File inFile = new File(INPUT_DIR + files[0]);
        File outFile = new File(OUTPUT_DIR + files[1]);
        if( inFile.canRead() && inFile.exists() ) {
            Scanner input = new Scanner(inFile);

            int sequenceCount = linesInFile(inFile) / 2;

            String[] sequences = new String[sequenceCount];
            String[] sequenceNames = new String[sequenceCount];

            int lineCount = 1;
            int sequenceIndex = 0;
            while( input.hasNextLine() ) {
                String line = input.nextLine(); 
                if ( lineCount % 2 == 0 ) {
                    sequences[sequenceIndex] = line.toUpperCase();
                    sequenceIndex++;
                }
                else {
                    sequenceNames[sequenceIndex] = line;
                }
                lineCount++;
            }
            
            PrintStream output = new PrintStream(outFile);
            saveOutput(sequences, sequenceNames, output);
            //output = System.out;
            //saveOutput(sequences, sequenceNames, output);
        }
    }

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
    public static void saveOutput(String[] sequences, String[] names, PrintStream output) {

        for( int i = 0; i < sequences.length; i++) {
            output.print("Region Name: ");
            output.println(names[i]);
            output.print("Nucleotides: ");
            output.println(sequences[i]);
            output.print("Nuc. Counts: ");
            output.println(Arrays.toString(nucleotideCountFromSequence(sequences[i])));
            output.print("Total Mass%: ");
            output.print(Arrays.toString(nucleotideMassPercentFromSequence(sequences[i])));
            output.println(" of "  + totalMassFromSequence(sequences[i]));
            output.print("Codons List: ");
            output.println(Arrays.toString(codonsFromSequence(sequences[i])));
            output.print("Is Protein?: ");
            if(isProtein(sequences[i])) {
                output.println("YES");
            }
            else {
                output.println("NO");
            }
            output.println();
        }
    }
    // return boolean test for protein sequence
    public static boolean isProtein(String sequence) {
        double[] masses = nucleotideMassPercentFromSequence(sequence);
        if(masses[nucleotideIndexFromChar('G')] + masses[nucleotideIndexFromChar('C')] <= 30 )
        {
            return false;
        }
        String[] codons = codonsFromSequence(sequence);
        if( ! codons[0].equals("ATG"))
        {
            return false;
        }
        if(! codons[codons.length - 1].equals("TAA") && ! codons[codons.length - 1].equals("TAG") && ! codons[codons.length - 1].equals("TGA"))
        {
            return false;
        }
        if(codons.length < 5)
        {
            return false;
        }
        return true;
    }
    // non junk masses from sequence
    public static double[] nucleotideMassesFromSequence(String sequence) {
        double[] masses = new double[4];
        int[] counts = nucleotideCountFromSequence(sequence);
        DecimalFormat df = new DecimalFormat("#.##");      
        for(int i = 0; i < masses.length; i++) {
           masses[i] = Double.valueOf(df.format(counts[i] * MOLAR_MASSES[i]));
        }
        return masses;
    }
    // non junk mass percentages from sequence
    public static double[] nucleotideMassPercentFromSequence(String sequence) {
        double[] masses = nucleotideMassesFromSequence(sequence);
        double total = totalMassFromSequence(sequence);
        double[] percentages = new double[4];
        DecimalFormat df = new DecimalFormat("#.##");      
        for(int i = 0; i < percentages.length; i++) {
            percentages[i] = Double.valueOf(df.format(masses[i] / total * 100));
        }
        return percentages;
    }
    // all mass of sequence, junk too
    public static double totalMassFromSequence(String sequence) {
        DecimalFormat df = new DecimalFormat("#.#");      
        double total = 0.0;
        
        double[] masses = new double[MOLAR_MASSES.length];
        int[] counts = new int[MOLAR_MASSES.length];
        for(int i = 0; i < counts.length; i++) {
           counts[i] = nucleotideCount(sequence, NUCLEOTIDES[i]);
        }
        for(int i = 0; i < masses.length; i++) {
           masses[i] = Double.valueOf(df.format(counts[i] * MOLAR_MASSES[i]));
        }
        for(int i = 0; i < masses.length; i++) {
            total += masses[i];
        }
        return Double.valueOf(df.format(total));
    }
    // count of char nulceotide in sequence
    public static int nucleotideCount(String sequence, char nucleotide) {
        int count = 0;
        for(int i = 0; i < sequence.length(); i++) {
            if( sequence.charAt(i) == nucleotide) {
                count++;
            }
        }
        return count;
    }
    
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
        return MOLAR_MASSES[nucleotideIndexFromChar(nucleotide)];
    }

    /*
    *
    * pass over a nucleotide sequence and count the number of As, Cs, Gs, and Ts.
    * uses a String.charAt to get individual characters.
    *
    * @params   sequence    sequence of 
    * @return   counts    array of size 4.
    *
    */
    public static int[] nucleotideCountFromSequence(String sequence) {
        
        int[] counts = new int[4];
        for(int i = 0; i < sequence.length(); i++) {
            counts[nucleotideIndexFromChar(sequence.charAt(i))]++;
        }
        return counts;

    }

    /*
    *
    * converts a single character (i.e. A, C, T, G) into indices (i.e. 0 to 3).
    * includes junk character
    *
    * @params   nucleotide  char of nucleotide.
    * @return   index   int mapping of nucleotide.
    *
    */
    public static int nucleotideIndexFromChar(char nucleotide) {
        int index = 0;
        if(nucleotide == 'a' || nucleotide =='A') {
            index = 0;
        }
        if(nucleotide == 'c' || nucleotide =='C') {
            index = 1;
        }
        if(nucleotide == 'g' || nucleotide =='G') {
            index = 2;
        }
        if(nucleotide == 't' || nucleotide =='T') {
            index = 3;
        }
        if(nucleotide == '-') {
            index = 4;
        }
        return index;
    }

    /*
    *
    * eliminate these characters.
    *
    * @params   sequence    raw sequence
    * @return   sequence    without junk DNA '-'.
    *
    */
    public static String removeJunk(String sequence) {
        String newSequence = "";
        for(int i = 0; i < sequence.length(); i++) {
            if(sequence.charAt(i) != '-') {
                newSequence += sequence.charAt(i);
            }
        }
        return newSequence;

    }

    /*
    *
    * break apart the sequence into codons and examine each codon.
    *
    * @params   sequence    String of nucleotides or raw.
    * @return   codons  Array of strings of codons.
    *
    */
    public static String[] codonsFromSequence(String sequence) {
        int count = sequence.length() / CODONS_PER_NUCLEOTIDE;
        String[] codons = new String[count];
        //String[] codons = new String[sequence.length / CODONS_PER_NUCLEOTIDE + 1];
        String current = "";
        int index = 0;
        for(int i = 0; i < sequence.length(); i++) {
            current += sequence.charAt(i);
            if((i + 1) % CODONS_PER_NUCLEOTIDE == 0 ) {
                codons[index] = current;
            index++;
                current = ""; 
            }
        }
        return codons;

    }

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
        System.out.println("This program reports information about DNA");
        System.out.println("nucleotide sequences that may encode");
        System.out.println("proteins.");

        if(DEBUG == true) {
            String[] files = {"dna.txt", "dna.txt"};
            return files;
        }

        System.out.print("Input file name? ");
        String[] files = new String[2];
        files[0] = console.nextLine();
        while( ! validFilename(files[0]) ) {
            System.out.print("Input file name? ");
            files[0] = console.nextLine();
        }
        System.out.print("Output file name? ");
        files[1] = console.nextLine();
        while( ! validFilename(files[1]) ) {
            System.out.println("Invalid. Try again.");
            System.out.print("Output file name?");
            files[1] = console.nextLine();
        }
        System.out.println();
        return files;

    }
    //count of lines in file
    public static int linesInFile(File file) throws FileNotFoundException {
        int count = 1;
        Scanner input = new Scanner(file);
        while( input.hasNextLine() ) {
            String line = input.nextLine(); 
            count++;
        }
        return count;
    }

    //all files are valid
    public static boolean validFilename(String name) {
        return true;
    }
}
