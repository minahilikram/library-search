package reference;

import java.util.ArrayList;

/**
 *
 * @author mikram
 */
public class Book extends Reference {
       
    /*
     * -------------------------
     * INSTANCE/MEMBER VARIABLES
     * -------------------------
     */
    
    /**
     * <p>Author(s) of the book.</p>
     */
    private ArrayList<String> authors;
    
    /**
     * <p>Publisher of the book.</p>
     */
    private String publisher;
     
    /*
     * ------------
     * CONSTRUCTORS
     * ------------
     */
    
    /**
     * <p>Default constructor creates an object that represents one book
     * that contains the author(s) and publisher.</p>
     */
    public Book () {
        authors = new ArrayList<>();
        this.publisher = "-";
    }
    
     /**
     * <p>Standard constructor creates an object according to the specified 
     * call number, author(s), title, publisher and year.</p>
     * 
     * @param num Call number of each book
     * @param auths Author(s) of each book
     * @param title Title of each book
     * @param pub Publisher of each book
     * @param year Year of each book
     */
    public Book (String num, ArrayList auths, String title, String pub, String year) {
        super("book", num, title, year);
        authors = auths;
        this.publisher = pub;
    }
    
    /*
     * --------
     * MUTATORS
     * --------
     */
    
    /**
     * <p>Sets the author(s) of each book for future addition to the library.</p>
     * 
     * @param author Author(s)
     * @return <code>true</code> if the new value was assigned; <code>false</code> if the assignment failed
     */
    public boolean addAuthor (String author) {
            authors.add(author);
            return true;
    }
    
    /**
     * <p>Sets the publisher of each book for future addition to the library.</p>
     * 
     * @param pub Publisher
     * @return <code>true</code> if the new value was assigned; <code>false</code> if the assignment failed
     */
    public boolean setPublisher (String pub) {
            this.publisher = pub;
            return true;
    }
    
    /*
     * ---------
     * ACCESSORS
     * ---------
     */

    /**
     * <p>Retrieves the current author(s) of each book.</p>
     * 
     * @return Author(s) of each book
     */
    public String getAuthor () {
        if (this.authors.isEmpty())
                return "";
        String author = "";
        for (int i=0; i<this.authors.size(); i++) {
            if (i != this.authors.size()-1)
                author += this.authors.get(i) + ", ";
        }
        author += this.authors.get(this.authors.size() - 1);
            return author;
    }
    
     /**
     * <p>Retrieves the current publisher of each book.</p>
     * 
     * @return Publisher of each book
     */ 
    public String getPublisher () {
        return this.publisher;
    }
    
    /*
     * -------------
     * OTHER METHODS
     * -------------
     */
    
    /**
     * <p>Creates a string for the current book.</p>
     * 
     * @return String
     */
    @Override
    public String toString() {
        
        String book = "\nTitle: " +this.getTitle()
                + "\nCall Number: " +this.getCallNumber()
                + "\nAuthor(s): " +this.getAuthor()
                +"\nYear: " +this.getYear()
                +"\nPublisher: " +this.getPublisher()
                +"\n";
        return book;   
    }
    
     /**
     * <p>Checks if two objects of type book are the same.</p>
     * 
     * @param book Object of type book
     * 
     * @return <code>true</code> if the objects match;
     * <code>false</code> if the objects do not match
     */
    public boolean equals(Book book) {
        boolean check = false;
        if ((book.getCallNumber().equalsIgnoreCase(this.getCallNumber()))
        && (book.getTitle().equalsIgnoreCase(this.getTitle()))
        && (book.getYear().equalsIgnoreCase(this.getYear()))
        && (book.getPublisher().equalsIgnoreCase(this.getPublisher())))
            check = true;
        for (int i=0; i<book.authors.size(); i++) {
            for (int k=0; k<this.authors.size(); k++)
                if (book.authors.toString().equalsIgnoreCase(this.authors.toString()))
                    check = true;
                else
                    check = false;
        }
        return check;
    }
}