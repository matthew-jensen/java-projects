/**
*
* Matt Jensen
* 3/20/19
*
* Draws a basic calendar to the console.
* Calendar is scalable with a class constant.
* = and | characters are used for the boundary of the calendar.
*
*/

import java.util.*; // uses scanner
import java.io.*; // uses file

public class Assignment3 {

    public static final String[][] EVENTS = new String[12][];
    public static final String[] OPTIONS = {"e", "t", "n", "p", "q", "ev", "fp"};
    public static final String[] OPTION_DESC = { 
        "to enter a date and display the corresponding calendar",
        "to get todays date and display the today's calendar",
        "to display the next month",
        "to display the previous month",
        "to quit the program",
        "event mode",
        "file print mode",
    };

    /**
    *
    * The width of a single cell of the calendar.
    *
    */
    public static final int CELL_WIDTH = 10;

    /**
    * 
    * Prompts user for a date input on the console.
    * Parses input of the form "mm/dd".
    * Displays month in the top of output.
    * Displays mm and dd separately at the bottom.
    * Displays the month associated with input.
    * Displays current month in same format below.
    *
    */
    public static void main(String [] args) {
        Scanner console = new Scanner(System.in);
        int month, day = 1;
        int currentYear, newYear = 0;
        Calendar date = Calendar.getInstance();
        setEventYear(date.get(Calendar.YEAR));
        currentYear = date.get(Calendar.YEAR);
        
        String option = promptForOption(console);
        while( ! option.equals("q") ) {
            //"to enter a date and display the corresponding calendar",
            //"to display the previous month",
            if( option.equals("ev") ) {
                String eventLine = promptForEvent(console);
                addEvent(eventFromLine(eventLine));
            }
            else if(option.equals("fp") ) {
                File file = promptForFile(console);
                try{
                    PrintStream out = new PrintStream(file);
                    String dateLine = promptForDateLine(console);
                    month = monthFromLine(dateLine);
                    day = dayFromLine(dateLine);
                    date.set(Calendar.getInstance().get(Calendar.YEAR), month, day);
                    drawCalendar(date, out); // draw the month
                    out.close();
                }
                catch(FileNotFoundException error) {
                    continue;
                }
            }
            else {
                if(option.equals("e") ) {
                    String dateLine = promptForDateLine(console);
                    // input 
                    month = monthFromLine(dateLine);
                    day = dayFromLine(dateLine);
                    date.set(Calendar.getInstance().get(Calendar.YEAR), month, day);
                }
                //"to get todays date and display the today's calendar",
                if( option.equals("t") ) {
                    // current
                    date = Calendar.getInstance();
                }
                //"to display the next month",
                if( option.equals("n") ) {
                    date.add(Calendar.MONTH, 1);
                }
                //"to display the previous month",
                if(option.equals("p") ) {
                    date.add(Calendar.MONTH, -1);
                }
                newYear = date.get(Calendar.YEAR);
                if(newYear != currentYear) {
                    setEventYear(newYear);
                }
                
                drawCalendar(date, System.out); // draw the month
            }
            option = promptForOption(console);
        }
    }

    // read in a file of events if they exists
    public static void loadEventsFile() {
        File eventFile = new File("calendarEvents.txt");
        if( ! eventFile.exists()) {
           return; 
        }
        try {   
            Scanner fileScanner = new Scanner(eventFile);
            while( fileScanner.hasNextLine() ) {
                String eventLine = fileScanner.nextLine();
                addEvent(eventFromLine(eventLine));
            }
        }
        catch(FileNotFoundException notFound){
            return;
        }
    }

    // prompt loop for file name of file to print to
    public static File promptForFile(Scanner console){
        System.out.println("What filename? (example.txt)");
        System.out.println();
        String input = console.next();
        File file = new File(input);
        return file;
    }

    // prompt loop for a (mm/dd) date input
    public static String promptForDateLine(Scanner console){
            System.out.println("What date would you like displayed? (mm/dd)");
            System.out.println();
            String input = console.next();
            while( ! validDateInput(input) ) {
                System.out.println("Try again.");
                System.out.println("What date would you like displayed? (mm/dd)");
                input = console.next();
            }
        return input;
    }

    // event name of given calendar date
    public static String getEventForDate(Calendar date) {
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DAY_OF_MONTH) - 1;
        String eventName = EVENTS[month][day];
        if(eventName == null) {
            return "";
        }
        return eventName;
    }
    /**
    *
    * extract an event name from line
    *
    * @param    eventLine   mm/dd event_name parsing.
    * @return   month   day substring of the given date.
    *
    */
    public static String[] eventFromLine(String line) {
        Scanner scanner = new Scanner(line);
        String month = Integer.toString(monthFromLine(line));
        String day = Integer.toString(dayFromLine(line));
        String rawDate = scanner.next();
        String name = scanner.next();
        String[] event = {month, day, name};
        return event;
    }

    // add event name to multi dem. class array of events
    public static void addEvent(String[] event) {
        int month = Integer.parseInt(event[0]);
        int day = Integer.parseInt(event[1]) - 1;
        String name = event[2];
        EVENTS[month][day] = name; 
    }

    // is the event line valid?
    public static boolean validEventInput(String input) {
        if( ! validDateInput(input) ) {
            System.out.println("Error: Bad date.");
            return false;
        }
        Scanner scanner = new Scanner(input);
        String rawDate = scanner.next();
        if( ! scanner.hasNext() ) {
            System.out.println("Error: No event.");
            return false;
        }
        return true;
    }
    
    // regenerate EVENTS with new day quantities.
    // reloads events file automatically.
    public static void setEventYear(int year){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, 1); 
        cal.set(Calendar.DAY_OF_MONTH, 1); 
        String[][] clone = EVENTS.clone();
        //TODO: copy events to new year
        for(int i = 0; i < 12; i++) {
            EVENTS[i] = new String[cal.getMaximum(Calendar.DAY_OF_MONTH)];
        }
        loadEventsFile();
    }
    
    // prompt loop for events
    public static String promptForEvent(Scanner console) {
        System.out.println("Please type an event (mm/dd event_name)");
        System.out.println();

        String input = console.next() + " " + console.next();
        while( ! validEventInput(input) ) {
            System.out.println("Try again.");
            System.out.println("Please type an event (mm/dd event_name)");
            input = console.next() + " " + console.next();
        }
        return input;
    } 

    // prompt loop for options
    public static String promptForOption(Scanner console) {
        System.out.println("Please type a command");
        printOptions();
        String input = console.next();
        while(  ! validInput(input) ) {
            System.out.println("Please enter a valid command");
            System.out.println("Please type a command");
            printOptions();
            input = console.next();
            System.out.println();
        }
        return input.toLowerCase();
    } 

    // valid date line input?
    public static boolean validDateInput(String input) {
        Scanner scanner = new Scanner(input);

        if( ! scanner.hasNext() ) {
            System.out.println("Error: No Date Detected");
            return false;
        }
        String rawDate = scanner.next();
        scanner = new Scanner(rawDate.replace('/', ' '));
        if( ! scanner.hasNextInt() ) {
            System.out.println("Error: No Month");
            return false;
        }
        int month = scanner.nextInt();

        if( ! scanner.hasNextInt() ) {
            System.out.println("Error: No Day");
            return false;
        }
        int day = scanner.nextInt();

        if(month > 12 || month < 1 || day < 1 || day > 31) {
            System.out.println("Error: Date out of bounds");
            return false;
        }
        return true;
    }

    // prints the available options array
    public static void printOptions() {
        for(int i = 0; i < OPTIONS.length; i++) {
            System.out.println("\"" + OPTIONS[i] + "\" " + OPTION_DESC[i]);
        }
    }

    // valid option input?
    public static boolean validInput(String input) {
        String option = input.toLowerCase();
        String regex = "(" + String.join("|", OPTIONS) + ").*";
        //System.out.println(regex); 
        if( option.matches(regex) ) {
            return true;
        }
        return false;
    }


    /**
    *
    * draws a given month
    *
    * @param    date    which date
    * @param    out     where to print
    * @return   void
    *
    */
    public static void drawCalendar(Calendar date, PrintStream out) {
    
        out.println();
        out.printf("Month: %-" + (calendarWidth() - 8) + "s\n", date.get(Calendar.MONTH) + 1);

        // rows
        drawDivider('=', out);
        drawHeader(out);
        drawDivider('=', out);
        Calendar firstDay = (Calendar) date.clone();
        firstDay.set(Calendar.WEEK_OF_MONTH, 1);
        firstDay.set(Calendar.DAY_OF_WEEK, 1);
        for(int weekNumber = 0; weekNumber < date.getActualMaximum(Calendar.WEEK_OF_MONTH); weekNumber++) {
            drawWeekStartingOn(firstDay, date, out);
            drawDivider('-', out);
            firstDay.add(Calendar.DAY_OF_MONTH, 7);
        }
        
        out.println();
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DAY_OF_MONTH);
        drawDate(month, day, out); // display date
    }

    // calculated the total width of the calendar.
    public static int calendarWidth() {
        int totalWidth = CELL_WIDTH * 7;
        return totalWidth;
    }

    /**
    *
    * draws a single row of the calenday to console.
    *
    * @param    row     the row number to print.
    * @return   void
    *
    */
    public static void drawWeekStartingOn(Calendar firstDayOfWeek, Calendar date, PrintStream out) {
        Calendar currentDate = (Calendar) firstDayOfWeek.clone();
        Calendar lastDayOfWeek = (Calendar) firstDayOfWeek.clone();
        lastDayOfWeek.add(Calendar.DAY_OF_MONTH, 6);
        while(! currentDate.after(lastDayOfWeek)) {
            int day = currentDate.get(Calendar.DAY_OF_MONTH);
            String content = "";
            if( currentDate.get(Calendar.MONTH) == date.get(Calendar.MONTH)) { // valid dates only
                if(currentDate.equals(date)) {
                    content += "*";
                }
                content += day;
            }
            out.print("|");
            out.printf("%" + (CELL_WIDTH - 1) + "s", content);
            currentDate.add(Calendar.DAY_OF_MONTH, 1);
        }
        out.println("|");

        for(int i = 0; i < CELL_WIDTH / 2; i++) {
            currentDate = (Calendar) firstDayOfWeek.clone();
            while(! currentDate.after(lastDayOfWeek)) {
                String content = "";
                if(i == 0) {
                    String eventName = getEventForDate(currentDate);
                    content += eventName;
                    if (content.length() > CELL_WIDTH) {
                        content = content.substring(0, CELL_WIDTH - 4) + "...";
                    }
                }
                out.print("|");
                out.printf("%" + (CELL_WIDTH - 1) + "s", content);
                currentDate.add(Calendar.DAY_OF_MONTH, 1);
            }
            out.println("|");
        }
    }


    // draws divider with given char
    public static void drawDivider(char character, PrintStream out) {
        out.print(' ');
        for(int i = 0; i < calendarWidth() - 1; i++) {
            out.print(character);
        }
        out.println();
    }

    // draws day names
    public static void drawHeader(PrintStream out) {
        String[] strDays = new String[] { "Su", "Mo", "Tu", "We", "Th", "Fr", "Sa" };
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        while(cal.get(Calendar.DAY_OF_WEEK) < 7) {
            out.printf("| %-" + (CELL_WIDTH - 2) + "s", strDays[cal.get(Calendar.DAY_OF_WEEK) - 1 ]);
            cal.add(Calendar.DAY_OF_WEEK, 1);
        }
        out.printf("| %-" + (CELL_WIDTH - 2) + "s|\n", strDays[cal.get(Calendar.DAY_OF_WEEK) - 1 ]);

    }

    /**
    *
    * prints the month and date on two separate lines.
    *
    * @param    month   month for printing.
    * @param    day   day for printing.
    * @return   void
    *
    */
    public static void drawDate(int month, int day, PrintStream out) {
        out.printf("Month: %" + CELL_WIDTH +"s\n", month + 1);
        out.printf("Day:   %" + CELL_WIDTH +"s\n", day);
    }

    /**
    *
    * extract an integer value for the month from a given string.
    *
    * @param    date   date string of the form mm/dd.
    * @return   month   month substring of the given date.
    *
    */
    public static int monthFromLine(String line) {
        Scanner scanner = new Scanner(line.replace('/', ' '));
        int month = scanner.nextInt();
        return month - 1;
    }

    /**
    *
    * extract an integer value for the day from a given string.
    *
    * @param    date   date string of the form mm/dd.
    * @return   month   day substring of the given date.
    *
    */
    public static int dayFromLine(String line) {
        Scanner scanner = new Scanner(line.replace('/', ' '));
        int month = scanner.nextInt();
        int day = scanner.nextInt();
        return day;
    }


    /**
    *
    * draws ASCII art preamble of the calendar
    *
    * @return   void
    *
    */
    public static void drawArt() {
        int calHeight = 7 * CELL_WIDTH;
        //top
        printChar('_', calHeight);

        //sun
        System.out.println();
        System.out.print(" ");
        printChar('_', calHeight/4);
        System.out.println(" ");
        for(int i = 0; i < calHeight/10; i++) {
            System.out.print("|");
            printChar(' ', calHeight/4);
            System.out.println("|");
        }
        System.out.print("|");
        printChar('_', calHeight/4);
        System.out.println("|");


        //beam
        System.out.println();
        for(int line = 0; line < calHeight/ 3; line++) {
            printChar(' ', line / 2);
            System.out.print("\\");
            printChar('\\', line / 2);
            printChar(' ', line / 2);
            System.out.println();
        }

        //windows
        printChar('_', calHeight);
        System.out.println();
        System.out.println();
        
        //banner
        //wheels
    }

    public static void printChar(char character, int count) {
        for(int i = 0; i < count; i++) {
            System.out.print(character);
        }
    }
}
