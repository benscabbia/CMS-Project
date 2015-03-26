/**
 * This interface specifies the behaviour expected from the copier management
 * system as required in Object-oriented Programming coursework
 * 
 * @author Benjamin Scabbia
 * @version Task4
 */
public interface CopierSystem
{    
   /** Allows a plain copier to be added to the system.
    * @param id represents the identifier of the copier 
    * @param  pageCost is the cost of a page in pence
    * @param costSetup is the set up cost for a job in pence 
    * @param copySpeed is the speed of the copier 
    */   
   public void addPlainCopier(String id, double pageCost, double costSetup, 
     int copySpeed);

   /** Allows a colour copier to be added to the system.
    * @param id represents the identifier of the copier
    * @param  pageCost is the cost of a page in pence
    * @param copySpeed is the speed of the copier 
    * @param maxJobSize is the maximum permitted job size in physical pages
    */   
   public void addColourCopier(String id, double pageCost, int copySpeed, 
     int maxJobSize);

   /** Allows a high-quality copier to be added to the system.
    * @param id represents the identifier of the copier
    * @param sheetCost is the cost of a single sheet in pence
    * @param monochromeCost is the cost supplement for a monochrome page
    * @param colourCost is the cost supplement for a colour page
    * @param hQPremium is the cost supplement for high-quality (per sheet)
    * @param costSetup is the set up cost for a job in pence 
    * @param copySpeed is the speed of the copier 
    */   
   public void addHQCopier(String id, double sheetCost, double monochromeCost,
     double colourCost, double hQPremium, double costSetup, int copySpeed);

    
   /** Adds a job to the job list.
    * This method assumes that all jobs submitted are runnable (i.e. that at last
    *   one suitable copier exists. The added job is given the state "waiting".
    * @param cust is the name of the customer
    * @param copies is the number of copies required
    * @param pages is the number of pages in the document
    * @param isColour indicates whether colour is required or not
    * @param isHQuality indicates whether high quality is required or not
    * @param isDSided indicates whether double-sided is required or not
    * @return returns the id assigned to the job
    */ 
   public int addJob(String cust, int copies, int pages, 
     boolean isColour, boolean isHQuality, boolean isDSided);

   /** Schedule jobs.
    *  This method inspects all the waiting jobs and sets them printing where 
    *    possible.
    */
   public void scheduleJobs();

   /** Returns an array of id's of all of the copiers. 
    * @return returns an array of the id's of all of the copiers 
    */
   public String[] getAllCopiers();

   /** Returns true if the copier with the identifier 
    * can be found in the system, false otherwise. 
    * @param id represents the identifier of the copier
    * @return returns true if and only if the copier with the identifier 
    * can be found, false otherwise.
    */
   public boolean isCopier(String id);
    
   /** Gets the copier details with the specified id.
    * @param id represents the identifier of the copier
    * @return returns a string representation of the copier with fields 
    *    space-separated and the id field first
    */
   public String getCopierDetails(String id);

   /** Remove a copier.
    * @param id is the copier id
    */
   public void removeCopier(String id);

   /** Returns the cost of job specified by the parameter value once it
    * has been printed.
    * @param jNo is the number of the job
    * @return the cost of a job in pence
    */
   public int getJobCost(int jNo);
 
   /** Provides an array of String representation of all jobs.
    *  Job fields within records are space delimited and the id field is given first
    * @return returns an array of String representation of all jobs
    */
   public String[] getAllJobs();
    
   /** Provides an array of String representation of all jobs which are waiting 
    *    for printing.
    *  Job fields within records are space delimited and the id field is given first.
    * @return returns an array of String representation of all jobs which are 
    *   waiting for printing
    */
   public String[] getJobsWaiting();
    
         
   /** Get the status of the specified job.
    * @param jNo is the number of the job
    * @return returns the status of the job as 
        CopierSystem.WAITING, CopierSystem.PRINTING, or CopierSystem.DONE
    */
   public int getJobStatus(int jNo);

   /** Remove a job which has not yet started printing.
    * @param jNo is the number of the job
    */
   public void removeJob(int jNo);
}


