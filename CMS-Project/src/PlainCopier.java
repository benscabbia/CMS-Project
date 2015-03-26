
/**
 * PlainCopier is a class which inherits the base attributes and methods from the abstract class Copier.
 * PlainCopier provides the required methods and fields for a plain copier machine.
 * 
 * @author Benjamin Scabbia
 * @version Task4
 */
public class PlainCopier extends Copier 
{
    /** A field storing the setup cost (in pennies)*/
    private double costSetup;
    
    /** A field storing the the cost of a page in pence*/
    private double pageCost;
    
    /**
     * Default constructor for Plain Copier. 
     * Makes a call to the superclass constructor passing the id and pagecost.
     * Initialises the costSetup and copySpeed fields by passing it the relevant parameter. 
     * @param id represents the identifier of the copier 
     * @param  pageCost is the cost of a page in pence
     * @param costSetup is the set up cost for a job in pence 
     * @param copySpeed is the speed of the copier 
     */
    public PlainCopier(String id, double pageCost, double costSetup, int copySpeed)
    {
        super(id, copySpeed, CopierType.PLAINCOPIER);
        this.pageCost = pageCost;
        this.costSetup = costSetup;
    }
    
    /**
     * Returns a string reoresentation of a Plain Copier Machine.
     * @returns returns a string representation of a plain copier with 
     * fields space-separated and the id field first.
     */
    public String toString(){
        return "ID: " + getId() +
        	   "\nCopier Type: " + getType() +
               "\nPage Cost: " + pageCost + 
               "\nCost Setup: " + costSetup + 
               "\nCopy Speed: " + getCopySpeed() +
               "\nAvailability: " + getCopierStatus() + "\n";
    }
    
    /**
     * Returns the setup cost for a PlainCopier
     * @returns returns a double value representing the setup cost 
     */
	public double getCostSetup() {
		return costSetup;
	}
	
    /**
     * Returns the page cost for a PlainCopier
     * @returns returns a double value representing the page cost 
     */
	public double getPageCost() {
		return pageCost;
	}
}
