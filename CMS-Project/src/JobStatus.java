/**
 * JobStatus is an Enumeration class which provides information on the status 
 * of a given job. This helps methods and classes securely identify the current 
 * status of a given job   
 * 
 * @author Benjamin Scabbia
 * @version Task4
 */
public enum JobStatus
{
    WAITING(0), PRINTING(1), DONE(2);
    
    /** A field representing the status number*/
    private final int statusNumber;
    
    /**
     * Default constructor for JobStatus.
     * For each enum JobStatus, it also stores an int value which allows the system
     * to use either an int constant to representing the status (0 = Waiting, 1 = Printing,
     * 2 = Done), or directly use the enum (JobStatus.WAITING, JobStatus.PRINTING...) 
     * @param statusNumber represents the status of a job in terms of numbers, 
     */ 
    JobStatus(int statusNumber){
        this.statusNumber = statusNumber;
    }
    
    /**
     * Returns an number representation of the job status
     * @returns returns an int representation of the status of a given job 
     * 0 = WAITING, 1 = PRINTING, 2 = DONE
     */
    public int getStatusNumber(){
        return statusNumber;
    }
}