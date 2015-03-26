/**
 * Copier is an abstract class providing default behaviour for all copiers. 
 * Copier abstract class also provides default methods and fields for a copier machine.
 * 
 * @author Benjamin Scabbia
 * @version Task4
 */
public abstract class Copier
{    
    /** A field storing the identifier of the copier  */
    private String id;
    
    /** A field storing the speed of the copier */
    private int copySpeed;
    
    /** A field storing the type of copier */
    private CopierType type;
    
    /** A field storing the availability status of the copier */
    private CopierAvailabilityStatus copierStatus;

    /**
     * Default constructor for Copier. 
     * Initialises the id and pagecost fields by passing it the relevant parameter. 
     * @param id represents the identifier of the copier 
     * @param  copySpeed is the speed of the copier 
     */
    public Copier(String id, int copySpeed, CopierType type)
    {
        this.id = id;
        this.copySpeed = copySpeed;
        this.type = type;
        //set all new copiers to available
        this.copierStatus = CopierAvailabilityStatus.AVAILABLE;
    }
    
    /**
     * Returns the id of the copier
     * @returns returns a string containing the id for the copier 
     */
    public String getId(){
        return id;
    }
    
    /**
     * Returns the speed of the copier
     * @returns returns an int value representing the speed of the copier 
     */
    public int getCopySpeed(){
        return copySpeed;
    }
    
    /**
     * Returns the type of the copier
     * @returns returns the CopierType enum which represents the type of copier 
     */
    public CopierType getType(){
    	return type;
    }
    
    /**
     * Used to togged the copier status
     * If the copier status is AVAILABLE, it will change it to IN_USE 
     * If the copier status is IN_USE, it will change it do AVAILABLE
     */
    public void changeCopierStatus(){
    	copierStatus = copierStatus == CopierAvailabilityStatus.AVAILABLE ? CopierAvailabilityStatus.IN_USE : CopierAvailabilityStatus.AVAILABLE;    	
    }
    
    /**
     * Returns the status of the copier
     * @returns returns an enum representing the status of the copier
     */
    public CopierAvailabilityStatus getCopierStatus(){
    	return copierStatus;
    }
    
    public abstract String toString();  
}
