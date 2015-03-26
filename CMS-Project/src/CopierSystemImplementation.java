import java.util.*;
/**
 * CopierSystemImplementattion is the main class of the Copier System application. 
 * It implements the CopierSystem interface which provides the class with a range of methods. 
 * 
 * @author Benjamin Scabbia
 * @version Task4
 */
public class CopierSystemImplementation implements CopierSystem 
{   
    /** A list containing all copiers in the system */
    private List<Copier> allCopiers = new ArrayList<>();

    /** A list containing all jobs in the system in order of insertion*/
    private Map<Integer,Job> allJobs = new LinkedHashMap<>();    

    /** Allows a plain copier to be added to the system.
     * @param id represents the identifier of the copier 
     * @param  pageCost is the cost of a page in pence
     * @param costSetup is the set up cost for a job in pence 
     * @param copySpeed is the speed of the copier 
     */   
    @Override
    public void addPlainCopier(String id, double pageCost, double costSetup,
    int copySpeed) {
        allCopiers.add(new PlainCopier(id, pageCost, costSetup, copySpeed)); 
    }

    /** Allows a colour copier to be added to the system.
     * @param id represents the identifier of the copier
     * @param  pageCost is the cost of a page in pence
     * @param copySpeed is the speed of the copier 
     * @param maxJobSize is the maximum permitted job size in physical pages
     */   
    @Override
    public void addColourCopier(String id, double pageCost, int copySpeed,
    int maxJobSize) {
        allCopiers.add(new ColourCopier(id, pageCost, copySpeed, maxJobSize));

    }

    /** Allows a high-quality copier to be added to the system.
     * @param id represents the identifier of the copier
     * @param sheetCost is the cost of a single sheet in pence
     * @param monochromeCost is the cost supplement for a monochrome page
     * @param colourCost is the cost supplement for a colour page
     * @param hQPremium is the cost supplement for high-quality (per sheet)
     * @param costSetup is the set up cost for a job in pence 
     * @param copySpeed is the speed of the copier 
     */   
    @Override
    public void addHQCopier(String id, double sheetCost, double monochromeCost,
    double colourCost, double hQPremium, double costSetup, int copySpeed) {
        allCopiers.add(new HQCopier(id, sheetCost, monochromeCost, colourCost, hQPremium, costSetup, copySpeed));
    }

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
    @Override
    public int addJob(String cust, int copies, int pages, boolean isColour,
    boolean isHQuality, boolean isDSided) {
        Job job = new Job(cust, copies, pages, isColour, isHQuality, isDSided); 
        allJobs.put(job.getJobId(), job); 
        return job.getJobId();
    }

    /** Schedule jobs.
     *  This method inspects all the waiting jobs and sets them printing where 
     *    possible.
     */
    @Override
    public void scheduleJobs() {
        //Call the updateJobsAndCopiers() method which checks and updates the status if jobs are complete and copiers become available
        updateJobsAndCopiers();

        //A local variable storing the current time and date
        Date1 date = new Date1();

        //Loop through allJobs (oldest job first) and search for jobs which have a JobStatus.WAITING
        for(Map.Entry<Integer, Job> jobIteration : allJobs.entrySet()){
            Job job = jobIteration.getValue();
            if(job.getJobStatus() != JobStatus.WAITING){
                //continue iteration if the jobstatus is not equal to WAITING
                continue;
            }           
            //Ensure the costs for each compatible copier has been calculated, if not, call the getCompatipleCopiersAndCost() method
            if(job.getJobCostPerCopier() == null || job.getJobCostPerCopier().size() <= 0){
                //Pass in the job Id to the getJobCost method
                getCompatipleCopiersAndCost(jobIteration.getKey()); 
            }

            //Map storing the costs for each copier on the current job
            Map<Long, String> costPerCopier = job.getJobCostPerCopier(); 

            //Loop through costPerCopier map in cost ascending order  
            for(Map.Entry<Long, String> jobCost : costPerCopier.entrySet()){
                //get the copier by the id
                Copier compatipleCopier = getCopierById(jobCost.getValue()); 

                //Assign the cost for the current job for the given copier in a local variable (start from cheapest cost)
                double cost = jobCost.getKey(); 
                //System.out.println(cost);

                //Assign an initial job cost value (which is the cheapest)              
                if(job.getJobCost() == 0.0){
                    job.setJobCost(cost);
                }

                //if the cost of the current copier is greater than the lowest cost, then iterate onto next job
                if(cost > job.getJobCost()){
                    continue;
                }

                //If the cheapest copier is not available, leave job waiting and iterate onto next job
                if(compatipleCopier.getCopierStatus() != CopierAvailabilityStatus.AVAILABLE){
                    continue; 
                }
                else{                                                                    
                    //Map to store the estimated completion times and the costs for a given job
                    Map<String, Long> estTimePerCopier = jobCompletionTime(job.getJobId());
                    //check the time is not null
                    if(estTimePerCopier.get(compatipleCopier.getId()) != null){                       
                        //get the time for the job for the current copier
                        long timeForCurrentCopier = estTimePerCopier.get(compatipleCopier.getId());

                        //Change the copier status to IN_USE
                        compatipleCopier.changeCopierStatus();                        

                        //Call the startJob method
                        job.startJob(timeForCurrentCopier, compatipleCopier.getId());
                        //copier has been found for the job, break out of loop and onto the next job
                        break;  
                    }

                }
            }
        }
    }

    /** Returns an array of id's of all of the copiers. 
     * @return returns an array of the id's of all of the copiers 
     */
    @Override
    public String[] getAllCopiers() {
        String copierId[] = new String[allCopiers.size()];
        for(int i=0; i<=allCopiers.size()-1; i++){ 
            copierId[i] = allCopiers.get(i).getId();
        }
        return copierId;
    }

    /** Returns true if the copier with the identifier 
     * can be found in the system, false otherwise. 
     * @param id represents the identifier of the copier
     * @return returns true if and only if the copier with the identifier 
     * can be found, false otherwise.
     */
    @Override
    public boolean isCopier(String id) {
        return Arrays.asList(getAllCopiers()).contains(id);        
    }

    /** Gets the copier details with the specified id.
     * @param id represents the identifier of the copier
     * @return returns a string representation of the copier with fields 
     *    space-separated and the id field first
     */
    @Override
    public String getCopierDetails(String id) {
        if(isCopier(id)){
            return getCopierById(id).toString();            
        }
        return null;
    }

    /** Remove a copier.
     * @param id is the copier id
     */
    @Override
    public void removeCopier(String id) {
        if(isCopier(id)){
            allCopiers.remove(getCopierById(id));
        }
    }

    /** Returns the cost of job specified by the parameter value ONCE it
     * has been printed.
     * @param jNo is the number of the job
     * @return the cost of a job in pence
     */
    @Override
    public int getJobCost(int jNo) {   
        return getJobById(jNo).getJobCost();
    }

    /** Provides an array of String representation of all jobs.
     *  Job fields within records are space delimited and the id field is given first
     * @return returns an array of String representation of all jobs
     */
    @Override
    public String[] getAllJobs() {
        String jobsArray[] = new String[allJobs.size()];

        //create a List containing only the map values for allJobs
        List<Job> job = new ArrayList<>(allJobs.values());

        //Loop through allJobs
        for(int i=0; i<allJobs.size(); i++){
            //add job.toString at index i to jobsArray
            jobsArray[i] = job.get(i).toString(); 
        }       
        return jobsArray;
    }

    /** Provides an array of String representation of all jobs which are waiting 
     *    for printing.
     *  Job fields within records are space delimited and the id field is given first.
     * @return returns an array of String representation of all jobs which are 
     *   waiting for printing
     */
    @Override
    public String[] getJobsWaiting() {
        //create a List containing only the map values for allJobs
        List<Job> jobList = new ArrayList<>(allJobs.values());

        //create a list to store WAITING jobs
        List<String> waitingJobList = new ArrayList<>();

        //loop through allJobs map values
        for(int i=0; i<jobList.size(); i++){
            //true if the job status at index i equals to JobStatus.WAITING
            if(jobList.get(i).getJobStatus() == JobStatus.WAITING){             
                waitingJobList.add(jobList.get(i).toString());
            }
        }           
        //Return an array of type String
        return waitingJobList.toArray(new String[waitingJobList.size()]);
    }

    /** Get the status of the specified job.
     * @param jNo is the number of the job
     * @return returns the status of the job as 
    CopierSystem.WAITING = 0, CopierSystem.PRINTING = 1, or CopierSystem.DONE = 2
     */
    @Override
    public int getJobStatus(int jNo) {
        Job job = getJobById(jNo);
        return job.getJobStatus().getStatusNumber();
    }

    /** Remove a job which has not yet started printing.
     * @param jNo is the number of the job
     */
    @Override
    public void removeJob(int jNo) {
        Job job = getJobById(jNo);
        //if job status is waiting, then remove
        if(job.getJobStatus() == JobStatus.WAITING){
            allJobs.remove(jNo);
        }
    }

    //Helper Methods
    /** Get the job by the id
     * @param id is the id for a given job
     * @return returns the job object of the given id 
     */
    private Job getJobById(int id){
        return allJobs.get(id);             
    }

    /** Get the copier by the id
     * @param id is the id for a given copier
     * @return returns the copier object of the given id 
     */
    private Copier getCopierById(String id){
        for(Copier copier : allCopiers){
            if(copier.getId().equals(id)){
                return copier;
            }            
        }
        return null;
    }

    /** Returns a map containing the compatible copiers and the estimated time 
     * for a given job id
     * @param id is the id for a given job
     * @return returns a Map containing the id's of the copiers and the est copy time
     */
    private Map<String, Long> jobCompletionTime(int jobId){
        //Map containing details on completion per copier
        Map<String, Long> completionTimes = new HashMap<>(); //copier ID and time

        //job information
        Job job = getJobById(jobId);
        int pages = job.getPages();
        int copies = job.getCopies();
        boolean isColor = job.isColour();
        boolean isDSided = job.isDSided();
        boolean isHQuality = job.isHQuality();

        //Plaincopier can be utilised
        if(!isColor && !isDSided && !isHQuality){
            //Get all plain copiers in system
            List<PlainCopier> allPlainCopiers = getCopierType(CopierType.PLAINCOPIER);
            for(PlainCopier copier : allPlainCopiers){
                int copySpeed = copier.getCopySpeed();  
                double copyPerSecond = copySpeed / 60.0; 
                double estimatedTime = (pages * copies) / copyPerSecond;
                long estMilliSeconds = (long)(estimatedTime * 1000);
                completionTimes.put(copier.getId(), estMilliSeconds);               
            }
        }
        //Colorcopier can be utilised
        if(isColor && !isDSided && !isHQuality){
            //Get all colour copiers in system
            List<ColourCopier> allColourCopiers = getCopierType(CopierType.COLOURCOPIER);

            for(ColourCopier copier : allColourCopiers){
                int copySpeed = copier.getCopySpeed();          
                int maxJobSize = copier.getMaxJobSize(); 
                //make sure copier can handle it
                if((pages * copies) <= maxJobSize){
                    double copyPerSecond = copySpeed / 60.0; //copier copy speed per second
                    double estimatedTime = (pages * copies) / copyPerSecond;
                    long estMilliSeconds = (long)(estimatedTime * 1000);                
                    completionTimes.put(copier.getId(), estMilliSeconds);    
                }
            }
        }

        //get all hq copiers in system
        List<HQCopier> allHQCopiers = getCopierType(CopierType.HQCOPIER);

        for(HQCopier copier : allHQCopiers){
            int copySpeed = copier.getCopySpeed();
            double copyPerSecond = copySpeed / 60.0; 
            double estimatedTime = (pages * copies) / copyPerSecond;
            long estMilliSeconds = (long)(estimatedTime * 1000);            
            completionTimes.put(copier.getId(), estMilliSeconds);    
        }
        return completionTimes;
    }

    /** Returns a list containing all the copiers of the specified type
     * @param type is the type of copier (CopierType.PLAINCOPIER, CopierType.COLOURCOPIER, CopierType.HQCOPIER)
     * @return returns a List containing the copiers of the specified type
     */
    private List getCopierType(CopierType type){
        List<Copier> copiers = new ArrayList<>();
        for(Copier copier : allCopiers){
            if(copier.getType() == type){
                copiers.add(copier);
            }
        }
        return copiers;
    }

    /** Method used by scheduleJobs(). updateJobsAndCopiers iterates through every job 
     * and checks whether the current job has the JobStatus PRINTING. If so, it then checks if 
     * the job has finished. If the job has finished, then it changes the CopierAvailabilityStatus to AVAILABLE
     * and calls the job.finished() method which changes the JobStatus to DONE.  
     */
    private void updateJobsAndCopiers() {
        //Iterate through allJobs
        for(Map.Entry<Integer, Job> jobIteration : allJobs.entrySet()){ //check all jobs
            Job job = jobIteration.getValue();
            //skip jobs that are not printing
            if(job.getJobStatus() != JobStatus.PRINTING){
                continue;
            }
            Date1 currentTime = new Date1();
            long jobStartTime = job.getJobStartTime().getTime();
            long jobRequiredTime = job.getJobRequiredTime();
            long jobFinishTime = jobStartTime + jobRequiredTime;

            //if current time is greater or equal to finishTime, job is complete
            if(jobFinishTime <= currentTime.getTime()){
                //Change job state
                job.finished();
                //change the copier status to available
                getCopierById(job.getAllocatedCopierId()).changeCopierStatus(); 
            }
        }
    }

    /** Method used by scheduleJobs(). getCompatipleCopiersAndCost looks up the job details 
     * and determines which copiers the job is compatible with. It then adds the compatible
     * copier and the cost of running the job on that copier to the list which   
     */
    private void getCompatipleCopiersAndCost(int jNo){
        //get all existing plain copiers
        List<PlainCopier> plainCopiers = getCopierType(CopierType.PLAINCOPIER);

        //get all existing colour copiers
        List<ColourCopier> colourCopiers = getCopierType(CopierType.COLOURCOPIER);

        //get all existing hq copiers
        List<HQCopier> hqCopiers = getCopierType(CopierType.HQCOPIER);

        Map<String, Integer> jobCostPerCopier = new HashMap<>();

        Job job = getJobById(jNo);
        int copies = job.getCopies();
        int pages = job.getPages();
        boolean isColour = job.isColour();
        boolean isHQuality = job.isHQuality();
        boolean isDSided = job.isDSided();

        //check if plaincopier can be used AND whether there are any plainCopiers
        if(!isColour && !isHQuality && !isDSided && plainCopiers.size() > 0){
            for(PlainCopier plainCopier : plainCopiers){
                double singleSheetCost = plainCopier.getPageCost();
                double totalSheetCost = singleSheetCost * pages * copies;
                //System.out.println("@Singlesheetcost: " + singleSheetCost + ", pages: " + pages + ", copies: " + copies);
                double setupCost = plainCopier.getCostSetup();
                int cost = (int)(totalSheetCost + setupCost);
                //System.out.println("@Cost noncast inside: " + totalSheetCost);
                //System.out.println("@Cost cast inside: " + cost);
                jobCostPerCopier.put(plainCopier.getId(), cost);
                //so the job itself holds details on potential copiers and its cost
                job.addCopierAndCost(plainCopier.getId(), cost);
            }
        }
        //check if colourcopier can be used AND whether there are any colourcopiers
        if(isColour && !isHQuality && !isDSided && colourCopiers.size() > 0){
            for(ColourCopier colourCopier : colourCopiers){
                double pageCost = colourCopier.getPageCost();
                int cost = (int)(pageCost * pages * copies);
                jobCostPerCopier.put(colourCopier.getId(), cost);
                //so the job itself holds details on potential copier       s and its cost
                job.addCopierAndCost(colourCopier.getId(), cost);
            }
        }
        //checks if there are any hqCopiers in system
        if(hqCopiers.size() > 0){
            for(HQCopier hqCopier : hqCopiers){
                double singleSheetCost = hqCopier.getSheetCost();               
                double inkCost = isColour ? hqCopier.getColourCost() : hqCopier.getMonochromeCost();
                double hqCost = isHQuality? hqCopier.gethQPremium() : 0;
                int numberOfPages = pages;              
                if(isDSided){
                    numberOfPages = numberOfPages > 1 ? (numberOfPages / 2) * copies : 1 * copies; //takes in account copies
                    //System.out.println("@Num of pages inside: " + numberOfPages);
                    inkCost *= 2;
                }
                int cost = (int) ((singleSheetCost * numberOfPages) + (inkCost * numberOfPages) + (hqCost * numberOfPages));
                jobCostPerCopier.put(hqCopier.getId(), cost);
                //so the job itself holds details on potential copiers and its cost

                job.addCopierAndCost(hqCopier.getId(), cost);
            }
        }
    }
}

