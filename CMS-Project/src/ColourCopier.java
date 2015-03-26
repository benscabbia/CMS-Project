/**
 * PlainCopier is a class which inherits the base attributes and methods from the abstract class Copier.
 * PlainCopier provides the required methods and fields for a plain copier machine.
 * 
 * @author Benjamin Scabbia
 * @version Task4
 */
public class ColourCopier extends Copier 
{
    /** A field storing the cost of a page in pence */
    private double pageCost;
    
    /** A field storing the maximum permitted job size in physical pages */
    private int maxJobSize;
    
    /**
     * Default constructor for Color Copier. 
     * Makes a call to the superclass constructor passing the id and pageCost.
     * Initialises the copySpeed and maxJobSize fields by passing it the relevant parameter. 
     * @param id represents the identifier of the copier
     * @param  pageCost is the cost of a page in pence
     * @param copySpeed is the speed of the copier 
     * @param maxJobSize is the maximum permitted job size in physical pages
     */ 
    public ColourCopier(String id, double pageCost, int copySpeed,int maxJobSize)
    {
        super(id, copySpeed, CopierType.COLOURCOPIER);
        this.pageCost = pageCost;
        this.maxJobSize = maxJobSize;
    }
    
    /**
     * Returns a string representation of a Colour Copier Machine.
     * @returns returns a string representation of a Colour copier with 
     * fields space-separated and the id field first.
     */
    public String toString(){
        return "ID: " + getId() + 
        	   "\nCopier Type: " + getType() +
               "\nPage Cost: " + pageCost + 
               "\nCopy Speed: " + getCopySpeed() +
               "\nMax Job Size: " + maxJobSize +  
        	   "\nAvailability: " + getCopierStatus() + "\n";
    }

    /**
     * Returns the maximum job sizet for a ColourCopier
     * @returns returns a int value representing the maximum job size the ColourCopier can handle 
     */
	public int getMaxJobSize() {
		return maxJobSize;
	}
	
    /**
     * Returns the setup cost for a ColourCopier
     * @returns returns a double value representing the page cost 
     */
	public double getPageCost() {
		return pageCost;
	}
}