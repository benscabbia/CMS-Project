/**
 * PlainCopier is a class which inherits the base attributes and methods from the abstract class Copier.
 * PlainCopier provides the required methods and fields for a plain copier machine.
 * 
 * @author Benjamin Scabbia
 * @version Task4
 */
public class HQCopier extends Copier 
{
    /** A field storing the cost of a single sheet in pence  */
    private double sheetCost;
    
    /** A field storing the cost supplement for a monochrome page */
    private double monochromeCost;
    
    /** A field storing the cost supplement for a colour page */
    private double colourCost;    
    
    /** A field storing the cost supplement for high-quality (per sheet) */
    private double hQPremium;
    
    /** A field storing the set up cost for a job in pence  */
    private double costSetup;
    
    /**
     * Default constructor for HQ Copier. 
     * Makes a call to the superclass constructor passing the id and pageCost.
     * Initialises the monochromeCost, colorCost, hQPremius, costSetup and copy speed fields
     * by passing it the relevant parameter. 
     * @param id represents the identifier of the copier
     * @param sheetCost is the cost of a single sheet in pence
     * @param monochromeCost is the cost supplement for a monochrome page
     * @param colourCost is the cost supplement for a colour page
     * @param hQPremium is the cost supplement for high-quality (per sheet)
     * @param costSetup is the set up cost for a job in pence 
     * @param copySpeed is the speed of the copier 
     */  
    public HQCopier(String id, double sheetCost, double monochromeCost,
    double colourCost, double hQPremium, double costSetup, int copySpeed)
    {
        super(id, copySpeed, CopierType.HQCOPIER); 
        this.sheetCost = sheetCost;
        this.monochromeCost = monochromeCost;
        this.colourCost = colourCost;
        this.hQPremium = hQPremium;
        this.costSetup = costSetup;
    }
    
    /**
     * Returns a string reoresentation of a HQ Copier Machine.
     * @returns returns a string representation of a HQ copier with 
     * fields space-separated and the id field first.
     */
    public String toString(){
        return "ID: " + getId() + 
        	   "\nCopier Type: " + getType() +
               "\nSheet Cost: " + sheetCost + 
               "\nMonochrome Cost: " + monochromeCost +
               "\nColour Cost: " + colourCost +
               "\nHQPremium Cost: " + hQPremium +
               "\nCopy Speed: " + getCopySpeed() + 
        	   "\nAvailability: " + getCopierStatus() + "\n";
    }
    
    /**
     * Returns the sheet cost for a HQCopier
     * @returns returns a double value representing the sheet cost 
     */
	public double getSheetCost() {
		return sheetCost;
	}

    /**
     * Returns the monochrome cost for a HQCopier
     * @returns returns a double value representing the monochrome cost 
     */
	public double getMonochromeCost() {
		return monochromeCost;
	}
	
    /**
     * Returns the colour cost for a HQCopier
     * @returns returns a double value representing the colour cost 
     */
	public double getColourCost() {
		return colourCost;
	}

    /**
     * Returns the high-quality premium cost for a HQCopier
     * @returns returns a double value representing the premium cost 
     */
	public double gethQPremium() {
		return hQPremium;
	}

    /**
     * Returns the setup cost for a HQCopier
     * @returns returns a double value representing the setup cost 
     */
	public double getCostSetup() {
		return costSetup;
	}
}