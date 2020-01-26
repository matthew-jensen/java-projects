import java.util.*;
import java.lang.StringBuilder;
import java.io.*;
import java.lang.Math;
import org.knowm.xchart.*;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;

public class SortingTest{
    // orchestrates timing and graphing on sorting algorithms.
    // call instead of SortingPack directly.
	public static void main(String[] args) {
        // results.csv
        //SortingTest.time(); 
        SortingTest.graph(); 
    }
    public static final String OUTPUT_FILE = "time.csv";

    // pre: csv formatted file at ./OUTPUT_FILE
    // pre: xcharts lib in base_path/libs/.jar
    // post: png chart of array length vs sort runtime.
    public static void graph() {
		Scanner scanner = null;
        ArrayList<Double> x = new ArrayList<Double>();
        ArrayList<Double> insertion = new ArrayList<Double>();
        ArrayList<Double> merge = new ArrayList<Double>();
        ArrayList<Double> quick = new ArrayList<Double>();
        ArrayList<Double> heap = new ArrayList<Double>();
        ArrayList<Double> linear = new ArrayList<Double>();
        ArrayList<Double> quadratic = new ArrayList<Double>();
        ArrayList<Double> log = new ArrayList<Double>();
        ArrayList<Double> nlog = new ArrayList<Double>();
		try {
			//Get the scanner instance
			scanner = new Scanner(new File(OUTPUT_FILE));
			//Use Delimiter as COMMA
			scanner.useDelimiter("\n");
            String header = scanner.next();
			while(scanner.hasNext()) {
                Scanner line = new Scanner(scanner.next());
                line.useDelimiter(",");
                double n = Double.parseDouble(line.next());
                x.add(n);
                //linear.add(n); 
                //quadratic.add(n*n); 
                //log.add(Math.log(n)); 
                //nlog.add(n * Math.log(n)); 
                insertion.add(Double.parseDouble(line.next()));
                merge.add(Double.parseDouble(line.next()));
                quick.add(Double.parseDouble(line.next()));
                heap.add(Double.parseDouble(line.next()));
			}
            XYChart chart = new XYChartBuilder()
                .width(600)
                .height(400)
                .title("Comparing Sorting Algorithm Runtimes")
                .xAxisTitle("Input Size n")
                .yAxisTitle("Running Time T(n)")
                .build();
            chart.addSeries("Insertion Sort", x, insertion);
            chart.addSeries("Merge Sort", x, merge);
            chart.addSeries("Quick Sort", x, quick);
            chart.addSeries("Heap Sort", x, heap);
            //chart.addSeries("O(n)", x, linear);
            //chart.addSeries("O(n^2)", x, quadratic);
            //chart.addSeries("O(lgn)", x, log);
            //chart.addSeries("O(nlgn)", x, nlog);
            //BitmapEncoder.getBufferedImage(chart);
            try {
                BitmapEncoder.saveBitmapWithDPI(chart, "plot.jpg", BitmapFormat.JPG, 500);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
		} 
		catch (FileNotFoundException fe) 
		{
			fe.printStackTrace();
		}
		finally
		{
			scanner.close();
		}
    }

	/** 
    *
    * times sorting algos
    *
    * @pre  SortingPack with working sort.
    * @post csv formatted file at ./OUTPUT_FILE
    * length, insertionSort, ...,
    * 100, 3453.23, 3453.3, ..., 
    *
    **/
	public static void time() {
		String[] methods = {"insertionSort", "mergeSort", "quickSort", "heapSort"};
        SortingTest test = new SortingTest();

        printToFile("length");
        for(String method:methods) {
            printToFile(",");
            printToFile(method);
        }
        printToFile("\n");
        for(int i = 459100; i <= 1000000; i += 1000) {
            printToFile(String.valueOf(i));
            for(String method:methods) {
                int[] input = randomArray(i);
                printToFile(",");
                double time = test.sortingTime(method, input);
                printToFile(String.valueOf(time));
            }
            printToFile("\n");
        }
    }

	/** 
    *
    * print to output file append only
    *
    * @param    bytes   characters to print
    * @see  https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html
    *
    **/
    private static void printToFile(String bytes) {
        try {
            // Put some bytes in a buffer so we can
            // write them. Usually this would be
            // image data or something. Or it might
            // be unicode text.
            byte[] buffer = bytes.getBytes();
            FileOutputStream outputStream = new FileOutputStream(OUTPUT_FILE, true);
            // write() writes as many bytes from the buffer
            // as the length of the buffer. You can also
            // use
            // write(buffer, offset, length)
            // if you want to write a specific number of
            // bytes, or only part of the buffer.
            outputStream.write(buffer);
            // Always close files.
            outputStream.close();       
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
	}

	/** 
    *
    * generate random array
    *
    * @param    size   length of array
	* @return   array   random array
    *
    **/
    private static int[] randomArray(int size) {
        int[] data = new int[size];
        Random random = new Random();
        for(int i = 0; i < size; i++) {
            data[i] = random.nextInt(200) + 1;
        }
        return data;
    }

	/** 
    *
    * counts the time it takes to sort arr using sortingAlgorithm
    *
	* @param    sortingAlgorithm    name of algo
    * @param    array   random array
	* @return data: double
    *
    **/
	public double sortingTime(String sortingAlgorithm, int[] arr) {
		long startTime = System.currentTimeMillis();
		if(sortingAlgorithm == "insertionSort") {
			SortingPack.insertionSort(arr);
		} else if( sortingAlgorithm == "quickSort") {
			SortingPack.quickSort(arr);

		} else if( sortingAlgorithm == "mergeSort") {
			SortingPack.mergeSort(arr);

		} else if( sortingAlgorithm == "heapSort") {
			SortingPack.heapSort(arr);
		}
		else {
			//throw new Exception
		}
		long endTime = System.currentTimeMillis();
		double elapsedTime =(double) (endTime - startTime);
        System.out.println("length: " + arr.length + "\tmethod: " + sortingAlgorithm + "\ttime: " + elapsedTime);
		return elapsedTime;
	}
    private static void printFile(String string) {
        try (PrintStream out = new PrintStream(new FileOutputStream("results.csv"))) {
            out.print(string);
        }
        catch ( FileNotFoundException e) {
            return;
        }
    }

  // you are welcome to add any supporting methods
}
