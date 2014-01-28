package reference;

/**
 *
 * @author mikram
 */
public class Journal extends Reference {
    
    /*
     * -------------------------
     * INSTANCE/MEMBER VARIABLES
     * -------------------------
     */
    
    /**
     * <p>Organization of the journal.</p>
     */
    private String organization;
    
    /*
     * ------------
     * CONSTRUCTORS
     * ------------
     */
    
   /**
     * <p>Default constructor creates an object that represents one journal
     * that only contains organization.</p>
     */
    public Journal () {
        this("-", "-", "-", "0000");
        this.organization = "-";
    }
    
    /**
     * <p>Standard constructor creates an object according to the specified 
     * call number, title, organization and year.</p>
     * 
     * @param num Call number of each journal
     * @param title Title of each journal
     * @param org Organization of each journal
     * @param year Year of each journal
     */
    public Journal (String num, String title, String org, String year) {
        super("journal", num, title, year);
        this.organization = org;
    }
    
    /**
     * --------
     * MUTATORS
     * --------
     */
    
    /**
     * <p>Sets the organization of each journal for future addition to the library.</p>
     * 
     * @param org Organization
     * @return <code>true</code> if the new value was assigned; <code>false</code> if the assignment failed
     */
    public boolean setOrganization (String org) {
            this.organization = org;
            return true;
    }
    
    /*
     * ---------
     * ACCESSORS
     * ---------
     */

    /**
     * <p>Retrieves the current organization of each journal.</p>
     * 
     * @return Organization of each journal
     */
    public String getOrganization () {
        return this.organization;
    }
    
     /*
     * -------------
     * OTHER METHODS
     * -------------
     */
    
    /**
     * <p>Creates a string for the current journal.</p>
     * 
     * @return String
     */
    @Override
    public String toString() {
        String journal = "\nTitle: " +this.getTitle()
                + "\nCall Number: " +this.getCallNumber()
                +"\nYear: " +this.getYear()
                +"\nOrganization: " +this.getOrganization()
                +"\n";
        return journal;
    }
    
     /**
     * <p>Checks if two objects of type journal are the same.</p>
     * 
     * @param journal Object of type journal
     * 
     * @return <code>true</code> if the objects match;
     * <code>false</code> if the objects do not match
     */
    public boolean equals(Journal journal) {
        if ((journal.getCallNumber().equalsIgnoreCase(this.getCallNumber()))
        && (journal.getTitle().equalsIgnoreCase(this.getTitle()))
        && (journal.getYear().equalsIgnoreCase(this.getYear()))
        && (journal.getOrganization().equalsIgnoreCase(this.getOrganization())))
            return true;
        return false;
    }
}