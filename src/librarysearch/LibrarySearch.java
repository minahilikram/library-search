package librarysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import reference.Book;
import reference.Journal;
import reference.Reference;
import utils.FileIO;

/**
 *<p>Main class for adding or searching to and from the library respectively.</p>
 * 
 * @author Daniel Vijayakumar CIS*2430 Lab 3 Solution
 * @author mikram
 */
public class LibrarySearch {
    
    /*
     * -------------------------
     * INSTANCE/MEMBER VARIABLES
     * -------------------------
     */
    
    /**
     * <p>List of books/journals managed by the application.</p>
     */
    public static List<Reference> library = new ArrayList<>();
    
    /**
     *<p>HashMap of book/journal titles managed by the application.</p>
     */
    public static HashMap<String, HashSet> titlesMap = new HashMap<>();
    
    /**
     * <p>Path to the file containing saved books/journals.</p>
     */
    private static final String DATA_FILE_PATH = "build\\classes\\utils\\data\\library.txt";
    
    /*
     * -------------
     * OTHER METHODS
     * -------------
     */

    /**
     * <p>Main thread of execution. A main-menu consisting of four options: Add,
     * Search, Print and Quit, is implemented in the form of a command-loop. 
     * <ol>
     *  <li><b>Add</b>: Adds a new book/journal.</li>
     *  <li><b>Search</b>: Search for specific books/journals.</li>
     *  <li><b>Print</b>: Displays all existing books/journals.</li>
     *  <li><b>Quit</b>: Exits the application.</li>
     * </ol>
     * </p>
     * 
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        
        FileIO fileIO = new FileIO(DATA_FILE_PATH);
        
        if ((library = fileIO.readReferencesFromFile()) == null)
            library = new ArrayList<>();
        
        for (int i=0; i<library.size(); i++) {
            mapTitle(library.get(i).getTitle(), i);
        }
    }
    
    
    /*
     * ---------------------
     * HELPER METHODS
     * ---------------------
     */ 
    
    /**
     * Inserts a reference into the list of references.
     */
    public static String add(String reference, String title, String callNumber, String year, String auth, String pub, String org) {

        if (title.isEmpty())
            return "Title is a required field.";
        
        if (callNumber.isEmpty())
            return "Call number is a required field.";
        
        if (year.isEmpty())
            return "Year is a required field.";
        
        if (!(isUnique(callNumber, year)))
            return "Book you entered already exists.";
        
        if (reference.equalsIgnoreCase("book")) {
            book(auth, pub, callNumber, title, year);
            return "Book was added successfully.";
        }
        else if (reference.equalsIgnoreCase("journal")) {
            journal(callNumber, title, year, org);
            return "Journal was added successfully.";
        }
        
        return "Error: Something went wrong, please check your input again.";
    }
    
    /**
     * Inserts a book into the list of references.
     * @param callNumber Book call number
     * @param title Book title
     * @param year Book year
     */
    private static void book (String author, String pub, String title, String callNumber, String year) {
        StringTokenizer tokenizeAuthor;
        boolean auth;
        
        ArrayList<String> authorsArray = new ArrayList<>();

        if (pub.isEmpty())
            pub = "-";

        if (author.isEmpty())
            author = "-";
        
        tokenizeAuthor = new StringTokenizer(author, ",");
        
        // Adds all authors tokenized to the authors arraylist
        while (tokenizeAuthor.hasMoreTokens()) {
            auth = authorsArray.add(tokenizeAuthor.nextToken().trim());
            if (auth == false)
                break;
        }

        library.add(new Book(callNumber, authorsArray, title, pub, year));
        mapTitle(title, library.size()-1);
    }
    
    
    
    /**
     * Inserts a journal into the list of references.
     * @param callNumber Journal call number
     * @param title Journal title
     * @param year Journal year
     */
    private static void journal(String callNumber, String title, String year, String org) {

        if (org.isEmpty())
            org = "-";
        
        library.add(new Journal(callNumber, title, org, year));
        mapTitle(title, library.size()-1);
    }
    
    /**
     * Helper method, checks if the journal entered is unique or already exists.
     * 
     * @return <code>true</code> if book is unique;
     * <code>false</code> if the book exists
     */
    private static boolean isUnique(String callNumber, String year) {
        //If list is empty no need to check if name exists.
        if(library.isEmpty())
            return true;
        
        //Searches through the list for name combination.
        for(int i=0;i<library.size();i++)
            if (callNumber.equalsIgnoreCase(library.get(i).getCallNumber()) &&
            year.equalsIgnoreCase(library.get(i).getYear()))
                return false;
        
        return true;
    }
    
    /**
     * Creates a HashMap of titles corresponding to the position in the reference list.
     * @param title Book/journal title
     * @param i Book/Journal spot in the list
     */
    private static void mapTitle(String title, int i) {
        String[] titleArray;
        
        titleArray = title.split(" ");
        
        for (int j=0; j<titleArray.length; j++) {
            titleArray[j] = titleArray[j].toLowerCase();
            HashSet values = titlesMap.get(titleArray[j]);
            if (values == null) {
                values = new HashSet<>();
            }
            values.add(i);
            titlesMap.put(titleArray[j], values);
        }
    }
    
    /**
     * Gets user input for the search.
     */
    public static String getSearchInput(String title, String callNumber, String year) {
        String[] titleArray;
        HashSet<Integer> search = new HashSet<>();
        int i,j;
        ArrayList<Reference> searchList = new ArrayList<>();
        
        if (library.isEmpty())
            return "Library is emoty.\n";
        
        if (title.length() > 0) {
            titleArray = title.split(" ");
            for (i=0; i<titleArray.length; i++) {
                titleArray[i] = titleArray[i].toLowerCase();
                if (titlesMap.containsKey(titleArray[i])) {
                    search = titlesMap.get(titleArray[i]);
                    break;
                }
            }
            for (j=i; j<titleArray.length; j++) {
                titleArray[j] = titleArray[j].toLowerCase();
                if (titlesMap.containsKey(titleArray[j]))
                    search.retainAll(titlesMap.get(titleArray[j]));
            }
            for (Integer myInt : search)
                searchList.add(library.get(myInt));
        }
        else if (title.length() <= 0)
            searchList = (ArrayList)library;
                
        return (search(callNumber, year, searchList));
    }
    
    /**
     * Prints the string representations of all references in the database to
     * the screen.
     * @param searchCallNumber Call number to be searched for
     * @param searchYear Year to be searched for
     * @param searchList List of items to be searched for
     */
    private static String search (String searchCallNumber, String searchYear, ArrayList<Reference> searchList) {
        int count = 0;
        int year;
        int year2;
        String searchYear2;
        int check = 0;
        String search = "";
        
        for (int i=0; i<searchList.size(); i++) {
            if (searchYear.length() == 4) {
                if (searchCallNumber.equalsIgnoreCase(searchList.get(i).getCallNumber()) || searchCallNumber.isEmpty())
                    if (searchYear.equalsIgnoreCase(searchList.get(i).getYear()) || searchYear.isEmpty())
                            count++;
            }
            else if (searchYear.startsWith("-") && searchYear.length() == 5) {
                searchYear = searchYear.substring(1);
                year = Integer.parseInt(searchYear);
                if (searchCallNumber.equalsIgnoreCase(searchList.get(i).getCallNumber()) || searchCallNumber.isEmpty())
                    if ((Integer.parseInt(searchList.get(i).getYear())) <= year)
                            count++;
            }
            else if (searchYear.endsWith("-") && searchYear.length() == 5) {
                searchYear = searchYear.substring(0, 4);
                year = Integer.parseInt(searchYear);
                    if (searchCallNumber.equalsIgnoreCase(searchList.get(i).getCallNumber()) || searchCallNumber.isEmpty())
                        if ((Integer.parseInt(searchList.get(i).getYear())) >= year)
                                count++;
            }
            else if ((searchYear.length()==9) && searchYear.charAt(4) == '-') {
                searchYear2 = searchYear;
                searchYear = searchYear.substring(0, 4);
                year = Integer.parseInt(searchYear);
                searchYear2 = searchYear2.substring(5, 9);
                year2 = Integer.parseInt(searchYear2);
                    if ((searchCallNumber.equalsIgnoreCase(searchList.get(i).getCallNumber())) || searchCallNumber.isEmpty())
                        if (((Integer.parseInt(searchList.get(i).getYear())) >= year) && ((Integer.parseInt(searchList.get(i).getYear())) <= year2))
                                count++;
            }
            if (count > 0) {
                search = searchList.get(i).toString() + search;
                count = 0;
                check++;
            }
        }
        if (check == 0)
        search = "No book/journal matching your query were found.\n";
        
        return search;
    }
    
    public static String printAll() {
        String print = "";
            for(int i=0; i<library.size(); i++)
                print = library.get(i).toString() + print;
        return print;
    }
    public static String delete(String title, String searchCallNumber, String searchYear) {
        int count = 0;
        String rS = "";
        int year;
        int year2;
        String searchYear2;
        int check = 0;
        String[] titleArray;
        HashSet<Integer> search = new HashSet<>();
        int i,j;
        ArrayList<Reference> searchList = new ArrayList<>();
        int found = 0;
        
        if (library.isEmpty())
            return "Library is Empty.\n";
        
        if (title.length() > 0) {
            titleArray = title.split(" ");
            for (i=0; i<titleArray.length; i++) {
                titleArray[i] = titleArray[i].toLowerCase();
                if (titlesMap.containsKey(titleArray[i])) {
                    search = titlesMap.get(titleArray[i]);
                    break;
                }
            }
            for (j=i; j<titleArray.length; j++) {
                titleArray[j] = titleArray[j].toLowerCase();
                if (titlesMap.containsKey(titleArray[j]))
                    search.retainAll(titlesMap.get(titleArray[j]));
            }
            for (Integer myInt : search)
                searchList.add(library.get(myInt));
        }
        else if (title.length() <= 0)
            searchList = (ArrayList)library;
        
        for (int k=0; k<searchList.size(); k++) {
            if (searchYear.length() <= 4) {
                if (searchCallNumber.equalsIgnoreCase(searchList.get(k).getCallNumber()) || searchCallNumber.isEmpty())
                    if (searchYear.equalsIgnoreCase(searchList.get(k).getYear()) || searchYear.isEmpty())
                            count++;
            }
            else if (searchYear.startsWith("-") && searchYear.length() == 5) {
                searchYear = searchYear.substring(1);
                year = Integer.parseInt(searchYear);
                if (searchCallNumber.equalsIgnoreCase(searchList.get(k).getCallNumber()) || searchCallNumber.isEmpty())
                    if ((Integer.parseInt(searchList.get(k).getYear())) <= year)
                            count++;
            }
            else if (searchYear.endsWith("-") && searchYear.length() == 5) {
                searchYear = searchYear.substring(0, 4);
                year = Integer.parseInt(searchYear);
                    if (searchCallNumber.equalsIgnoreCase(searchList.get(k).getCallNumber()) || searchCallNumber.isEmpty())
                        if ((Integer.parseInt(searchList.get(k).getYear())) >= year)
                                count++;
            }
            else if (searchYear.length() == 9 && searchYear.charAt(4) == '-') {
                searchYear2 = searchYear;
                searchYear = searchYear.substring(0, 4);
                year = Integer.parseInt(searchYear);
                searchYear2 = searchYear2.substring(5, 9);
                year2 = Integer.parseInt(searchYear2);
                    if (searchCallNumber.equalsIgnoreCase(searchList.get(k).getCallNumber()) || searchCallNumber.isEmpty())
                        if (((Integer.parseInt(searchList.get(k).getYear())) >= year) && ((Integer.parseInt(searchList.get(k).getYear())) <= year2))
                                count++;
            }
            if (count > 0) {
                found = k;
                if (count > 1)
                    rS = "There was more than one entry found, please specify your search.\n";
                check++;
            }
        }
        if (check == 0)
        rS = "No entries matching your input were found. \nNothing was deleted.\n";
        
        if (count == 1) {
            rS = "The following entry was successfully deleted.\n" + library.get(found).toString();
            library.remove(found);
        }
        return rS;
    }
    
    public static void save() {
        FileIO fileIO = new FileIO(DATA_FILE_PATH);
        if (!library.isEmpty())
            fileIO.writeReferencesToFile(library);
    }
}