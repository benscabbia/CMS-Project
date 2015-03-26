import java.util.*;
/**
 * Job is a class providing an implemenation for all the required details of a job. 
 * The class auto-generates the jobId for every new job. It also provides accessor methods to 
 * provide access to the required information.
 * 
 * @author Benjamin Scabbia
 * @version Task4
 */
public class Job
{   
    /** A class field to keep track of the job number */
    private static int jobNumber = 0;
    
    /** A field storing an unchangeable jobId */
    private final int jobId;
    
    /** A field storing the name of the customer */
    private String customerName;
    
    /** A field storing the number of copies required */
    private int copies;
    
    /** A field storing the number of pages in the document */
    private int pages;
    
    /** A boolean field indicating whether colour is required or not */
    private boolean isColour;
    
    /** A boolean field indicating whether high quality is required or not */
    private boolean isHQuality;
    
    /** A boolean indicating whether double-sided is required or not */
    private boolean isDSided;
    
    /** A field storing an enum representing on the status of the job */
    private JobStatus jobStatus;
    
    /** A field storing a Date1 object representing the start time of the job */
    private Date1 jobStartTime;
    
    /** A field storing the expected required time of for the job */
    private Long jobRequiredTime;
    
    /** A field storing a Map object with information on compatible copiers and their costs */
    private Map<Long, String> jobCostPerCopier; 
    
    /** A field storing the allocated copier Id */
    private String allocatedCopierId;
    
    /** A field storing a Date1 object representing the end time of the job */
    private Date1 finishedTime; 
    
    /** A field storing a Date1 object representing the end time of the job */
    private int jobCost;
    
    /**
     * Default Constructor for objects of class Jobs
     * Each Job is given a unique final jobId. Final to ensure the value remains unchanged. 
     * @param cust is the name of the customer
     * @param copies is the number of copies required
     * @param pages is the number of pages in the document
     * @param isColour indicates whether colour is required or not
     * @param isHQuality indicates whether high quality is required or not
     * @param isDSided indicates whether double-sided is required or not
     */
    public Job(String cust, int copies, int pages, boolean isColour,
    boolean isHQuality, boolean isDSided)
    {      
        jobId = jobNumber++;
        this.customerName = cust;
        this.copies = copies;
        this.pages = pages;
        this.isColour = isColour;
        this.isHQuality = isHQuality;
        this.isDSided = isDSided;
        //set new job status to waiting
        jobStatus = JobStatus.WAITING;
        //ensures costs are sorted in natural order
        jobCostPerCopier = new TreeMap<>(); 
    }
    
    /**
     * Returns an int value representing the job id
     * @return returns an int value representing the job id. 
     */
    public int getJobId(){
        return jobId;
    }
    
    /**
     * Returns a string representation of a Job.
     * @returns returns a string representation of a job with 
     * fields space-separated and the id field first.
     */
    public String toString(){
        return "ID: " + jobId +
               "\nCustomer Name: " + customerName +
               "\nNumber of Copies: " + copies +
               "\nNumber of Pages: " + pages +
               "\nColour: " + isColour +
               "\nHigh Quality: " + isHQuality + 
               "\nDouble Sided: " + isDSided + 
               "\nJob Status: " + jobStatus + 
               "\nAssigned Copier: " + allocatedCopierId + "\n";
    }

    /**
     * Returns the number of copies for a job.
     * @returns returns an int value representing the number of copies
     */
	public int getCopies() {
		return copies;
	}
	
    /**
     * Returns the number of pages for a job.
     * @returns returns an int value representing the number of pages
     */
	public int getPages() {
		return pages;
	}

    /**
     * Returns true if the job requires colour, false otherwise
     * @returns returns a boolean value on whether the job requires colour
     */
	public boolean isColour() {
		return isColour;
	}
	
    /**
     * Returns true if the job requires high-quality, false otherwise
     * @returns returns a boolean value on whether the job requires high-quality
     */
	public boolean isHQuality() {
		return isHQuality;
	}

    /**
     * Returns true if the job requires double-sided, false otherwise
     * @returns returns a boolean value on whether the job requires double sided
     */
	public boolean isDSided() {
		return isDSided;
	}
	
    /**
     * Returns the status of a job
     * @returns returns a JobStatus enum on the status of a job
     * WAITING, PRINTING OR DONE
     */
	public JobStatus getJobStatus(){
		 return jobStatus;		
	}
	
    /**
     * Toggle the current job status
     * if the jobstatus is currently WAITING, it will set it to PRINTING 
     * If the jobstatus is either PRINTING or DONE, then it will set it to DONE
     */
	public void changeJobStatus(){
		jobStatus = jobStatus == JobStatus.WAITING ? JobStatus.PRINTING : JobStatus.DONE;
	}
	
    /**
     * Used when the job is complete. 
     * Changes the job status to DONE and records the finish time for the job 
     */
	public void finished(){
		changeJobStatus();
		finishedTime = new Date1(); 
	}
	
	/** 
	 * Set the start time for the job
     * @param date is the date the job starts
     */
	public void setJobStartTime(Date1 date){
		jobStartTime = date;
	}
	
	/** 
	 * Get the start time of the job
     * @return returns the date the job was started 
     */
	public Date1 getJobStartTime(){
		return jobStartTime;
	}
	
    /**
     * Set the expected required time for the job  
     * @param milliseconds is the time in milliseconds required to complete the job
     */
	public void setJobRequiredTime(Long milliseconds){
		jobRequiredTime = milliseconds;
	}
	
	/** 
	 * Get the required time for the job
     * @return returns a long value representing the required time in milliseconds 
     */
	public long getJobRequiredTime(){
		return jobRequiredTime;
	}
	
	/** 
	 * Get the cost of a job for a compatible copier
     * @return returns a map ordered by the cheapest cost with the copier id 
     */
	public Map<Long, String> getJobCostPerCopier() {
		return jobCostPerCopier;
	}
	
	/** 
	 * Get the id of the allocated copier for the job
     * @return the allocated coper id for the job 
     */
	public String getAllocatedCopierId(){
		return allocatedCopierId;
	}
	
	/** 
	 * Set the allocated copier for the job
     * @param id set the allocated copier id for the job
     */
	public void setAllocatedCopierId(String id){
		allocatedCopierId = id;
	}
	
	/** 
	 * Add a compatible copier for the job and the price to the
	 * jobCostPerCopier map
     * @param copierId The id for the compatible copier
     * @param cost The cost of the job for the given copier
     */
	public void addCopierAndCost(String copierId, long cost) {
		//System.out.println("From addCopier Cost of copier: " + cost);
		jobCostPerCopier.put(cost, copierId);
	}
	
	public void startJob(Long estCompletionTime, String copierId){
		jobStartTime = new Date1();
		jobRequiredTime = estCompletionTime;
		allocatedCopierId = copierId;
		changeJobStatus();
	}

	public int getJobCost() {
		return jobCost;
	}

	public void setJobCost(double cost) {
		jobCost = (int)cost;
	}
}
