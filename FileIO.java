package librarysearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import reference.Book;
import reference.Journal;
import reference.Reference;

/**
 * Handles the reading/writing of data from/to files.
 * @author mikram
 * 
 */
public class FileIO {
    
    /*
     * -------------------------
     * INSTANCE/MEMBER VARIABLES
     * -------------------------
     */
    
    /**
     * File path for the input file
     */
    private String inputFilePath;
    
    /**
     * File path for the output file
     */
    private String outputFilePath;
    
    /**
     * Used for reading from the input file
     */
    private BufferedReader reader;
    
    /**
     * Used for writing to the input file using buffers
     */
    private BufferedWriter writer;   
    
    /*
     * ------------
     * CONSTRUCTORS
     * ------------
     */
                
    /**
     * Default constructor: sets the default input and output stream buffers 
     * to the keyboard and the screen, respectively.
     */
    public FileIO () {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new PrintWriter(System.out));
    }    
       
    /**
     * Creates a new object that performs reading and writing to the same file.
     * @param filePath Path to the data file to be used for reading and writing
     */
    public FileIO (String filePath) {
        this.inputFilePath = filePath;
        this.outputFilePath = filePath;         
    }        
    
    /**
     * Creates a new object that performs reading and writing to separate files.
     * @param inputFilePath Path to the input file for reading
     * @param outputFilePath Path to the output file for writing
     */
    public FileIO (String inputFilePath, String outputFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;        
    }
    
     /*
     * --------------
     * HELPER METHODS
     * --------------
     */
    
    /**
     * Attempts to open the file specified by inputFilePath for reading.
     */
    void openFileForReading () {         
        try {
            this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.inputFilePath)));
        }
        catch (FileNotFoundException exception) {
            System.err.println("(FileIO): " + exception.toString());
            System.err.println("(FileIO): Error opening file " + this.inputFilePath);
            System.err.println("(FileIO): Defaulting to System.in");
            this.reader = new BufferedReader(new InputStreamReader(System.in));
        }
    }
    
    
    /**
     * Attempts to open the file specified by outputFilePath for writing.
     */
    void openFileForWriting () {        
        try {
            this.writer = new BufferedWriter(new PrintWriter(new File(this.outputFilePath)));
        }
        catch (FileNotFoundException exception) {
            System.err.println("(FileIO): " + exception.toString());
            System.err.println("(FileIO): Error opening file " + this.outputFilePath);
            System.err.println("(FileIO): Defaulting to System.out");
            this.writer = new BufferedWriter(new PrintWriter(System.out));
        }
    }
    
    /**
     * Safely close both the reading and writing stream buffers. (This method 
     * may be redundant)    
     */
    public void closeStreams () {
        try {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
        catch (IOException exception) {
            System.err.println("(FileIO): " + exception);
            System.err.println("(FileIO): Error closing I/O streams");
        }
    }
    
    /*
     * ---------------------
     * FUNCTIONALITY METHODS
     * ---------------------
     */
    
    /**
     * Create a list of references from a file.
     * @return List of references if file reading was successful, else <code>null</code>
     */
    public List<Reference> readReferencesFromFile () {
        // Open the input file for reading
        this.openFileForReading();
        
        List<Reference> referenceList = null;
        try {            
            // Read new lines until we reach a blank line or EOF            
            String line;            
            while ((line = this.reader.readLine()) != null) {                
                if (line.equals("[reference]")) {
                    Reference reference = readReferenceFromFile(this.reader);
                    if (reference != null) {
                        if (referenceList == null) {
                            referenceList = new ArrayList<>();
                        }                        
                        referenceList.add(reference);
                    }
                }
            }            
        }
        catch (IOException exception) {
            referenceList = null;
            System.err.println("(FileIO): " + exception);            
        }
        // Close the file when we have finished reading or if an error occurs
        finally {
            try {
                this.reader.close();                
            } catch (IOException exception) {
                System.err.println("(FileIO): " + exception);
            }
        }
                
        return referenceList;
    }
    
     /**
     * Create a single Reference object based from a file.
     * @param br The buffered reader pointing to the specified input stream/file
     * @return An Reference object if the file reading was successful, else <code>null</code>
     */
    private Reference readReferenceFromFile (BufferedReader br) {                        
        String referenceClass = null, callNumber = "-", year = "-";
        String title = "-", publisher = "-", organization = "-";
        Reference reference = null;
        StringTokenizer tokenizeAuthor;
        boolean auth;
        String value, key = "";
        
        ArrayList<String> authorsArray = new ArrayList<>();
        
        try {                        
            // Read new lines until we reach a blank line or EOF            
            String line;            
            while ((line = br.readLine()) != null) {                 
                if (line.length() == 0) {
                    break;
                }                
                String[] tokens = line.split("=");
                if (tokens.length == 1)
                    value = "-";
                else {
                    key = tokens[0];
                    value = tokens[1];
                }
                if (key.equals("type")) {
                    referenceClass = value;
                }
                else if (key.equals("callNumber")) {
                    callNumber = value;
                }
                else if (key.equals("author(s)")) {
                    tokenizeAuthor = new StringTokenizer(value, ",");
                    while (tokenizeAuthor.hasMoreTokens()) {
                        auth = authorsArray.add(tokenizeAuthor.nextToken().trim());
                        if (auth == false)
                            break;
                    }
                }
                else if (key.equals("title")) {
                    title = value;                    
                }
                else if (key.equals("publisher")) {
                    publisher = value;                    
                }
                else if (key.equals("organization")) {
                    organization = value;                    
                }
                else if (key.equals("year")) {
                    year = value;                    
                }
            }
                        
            // Enforces mandatory fields
            if (referenceClass != null &&
                    callNumber != null &&
                    year != null) { 
                if (referenceClass.equalsIgnoreCase("book")) {
                    reference = new Book(callNumber, authorsArray, title, publisher, year);
                }
                else if (referenceClass.equalsIgnoreCase("journal")) {
                    reference = new Journal(callNumber, title, organization, year);
                }
            }        
        }                    
        catch (IOException exception) {
            reference = null;
            System.err.println("(FileIO): " + exception);                        
        }

        return reference;
    }
    
     /**
     * Save the given list of references to a file.
     * @param referenceList List of references to be saved
     * @return <code>true</code> if the operation was successful, else <code>false</code>
     */
    public boolean writeReferencesToFile (List<Reference> referenceList) {                
        // Open the output file for wriitng
        this.openFileForWriting();
        
        
        try {
            for (Reference reference : referenceList) {
                if (reference.getReferenceClass().equalsIgnoreCase("book")) {
                    String line = "[reference]";
                    this.writer.write(line);
                    this.writer.newLine(); // Platform-independent newline   
                    if (!this.writeBookReferenceToFile(this.writer, (Book)reference)) {
                        throw new IOException();
                    }
                    this.writer.newLine(); 
                    this.writer.flush(); // Do not forget this or nothing will be written to the file
                }
                else if (reference.getReferenceClass().equalsIgnoreCase("journal")) {
                    String line = "[reference]";
                    this.writer.write(line);
                    this.writer.newLine(); // Platform-independent newline   
                    if (!this.writeJournalReferenceToFile(this.writer, (Journal)reference)) {
                        throw new IOException();
                    }
                    this.writer.newLine(); 
                    this.writer.flush(); // Do not forget this or nothing will be written to the file
                }
            }
        }
        catch (IOException exception) {
            System.err.println("(FileIO): " + exception);
            System.err.println("(FileIO): Failed to save references to file.");
            return false;
        }
        // Close the file when we have finished writing or if an error occurs
        finally {            
            try {
                writer.close();
            } catch (IOException exception) {
                System.err.println("(FileIO): " + exception);
            }
        }
        
        
        return true;
    }
    
    /**
     * Perform a series of writes to the specified output file that represent an Reference object.
     * @param bw The buffered writer pointing to the specified output stream/file
     * @param reference The Reference object to be saved
     * @return <code>true</code> if the operation was successful, else <code>false</code>
     */
    private boolean writeBookReferenceToFile (BufferedWriter bw, Book reference) {
        
        try {                                    
            bw.write("type" + "=" + reference.getReferenceClass());
            bw.newLine();
            bw.write("callNumber" + "=" + reference.getCallNumber());
            bw.newLine();
            bw.write("author(s)" + "=" + reference.getAuthor());
            bw.newLine();
            bw.write("title" + "=" + reference.getTitle());
            bw.newLine();
            bw.write("publisher" + "=" + reference.getPublisher());
            bw.newLine();
            bw.write("year" + "=" + reference.getYear());
            bw.newLine();
        }                    
        catch (IOException exception) {
            System.err.println("(FileIO): " + exception);
            System.err.println("(FileIO): Failed to save reference " +
                    reference.toString() + " to file.");
            return false;
        }
        finally {
            return true;
        }
    }
    
    /**
     * Perform a series of writes to the specified output file that represent an Reference object.
     * @param bw The buffered writer pointing to the specified output stream/file
     * @param reference The Reference object to be saved
     * @return <code>true</code> if the operation was successful, else <code>false</code>
     */
    private boolean writeJournalReferenceToFile (BufferedWriter bw, Journal reference) {
        
        try {
            bw.write("type" + "=" + reference.getReferenceClass());
            bw.newLine();
            bw.write("callNumber" + "=" + reference.getCallNumber());
            bw.newLine();
            bw.write("title" + "=" + reference.getTitle());
            bw.newLine();
            bw.write("organization" + "=" + reference.getOrganization());
            bw.newLine();
            bw.write("year" + "=" + reference.getYear());
            bw.newLine();
        }                    
        catch (IOException exception) {
            System.err.println("(FileIO): " + exception);
            System.err.println("(FileIO): Failed to save reference " +
                    reference.toString() + " to file.");
            return false;
        }
        finally {
            return true;
        }
    }
}