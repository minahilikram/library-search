package reference;

/**
 *
 * @author mikram
 */
public class Reference {
    
    /*
     * -------------------------
     * INSTANCE/MEMBER VARIABLES
     * -------------------------
     */
    
    /**
     * <p>Call number of the book/journal.</p>
     */
    private String callNumber;
    
    /**
     * <p>Title of the book/journal.</p>
     */
    private String title;
    
    /**
     * <p>Year of the book/journal.</p>
     */
    private String year;
    
    /**
     * <p>Type, book or journal.</p>
     */
    private final String referenceClass;
    
    /*
     * ------------
     * CONSTRUCTORS
     * ------------
     */
    
    /**
     * <p>Default constructor creates an object that represents one book/journal
     * that only contains call number, title and year.</p>
     */
    public Reference () {
        this("-", "-", "-", "0000");
    }
    
    /**
     * <p>Standard constructor creates an object according to the specified 
     * call number, title and year.</p>
     * 
     * @param num Call number of each book/journal
     * @param title Title of each book/journal
     * @param year Year of each book/journal
     */
    public Reference (String referenceClass, String num, String title, String year) {
        this.referenceClass = referenceClass;
        this.callNumber = num;
        this.title = title;
        this.year = year;
    }
    
    /**
     * --------
     * MUTATORS
     * --------
     */
    
    /**
     * <p>Sets the call number of each book/journal for future addition to the library.</p>
     * 
     * @param num Call number
     * @return <code>true</code> if the new value was assigned; <code>false</code> if the assignment failed
     */  
    public boolean setCallNumber (String num) {
            this.callNumber = num;
            return true;
    }
    
    /**
     * <p>Sets the title of each book/journal for future addition to the library.</p>
     * 
     * @param title Title
     * @return <code>true</code> if the new value was assigned; <code>false</code> if the assignment failed
     */ 
    public boolean setTitle (String title) {
            this.title = title;
            return true;
    }
    
    /**
     * <p>Sets the year of each book/journal for future addition to the library.</p>
     * 
     * @param year Year
     * @return <code>true</code> if the new value was assigned; <code>false</code> if the assignment failed
     */  
    public boolean setYear (String year) {
            this.year = year;
            return true;
    }
    
    /*
     * ---------
     * ACCESSORS
     * ---------
     */
    
    /**
     * <p>Retrieves the current call number of each book/journal.</p>
     * 
     * @return Call number of each book/journal
     */
    public String getCallNumber () {
            return this.callNumber;
    }
    
    /**
     * <p>Retrieves the current title of each book/journal.</p>
     * 
     * @return Title of each book/journal
     */
    public String getTitle () {
            return this.title;
    }
    
    /**
     * <p>Retrieves the current year of each book/journal.</p>
     * 
     * @return Year of each book/journal
     */
    public String getYear () {
            return this.year;
    }
    
    /**
     * <p>Retrieves the type of each book/journal.</p>
     * 
     * @return Type of each book/journal
     */
    public String getReferenceClass (){
        return this.referenceClass;
    }
}