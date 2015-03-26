import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 * TesterClass is a carefully assembled test driver providing a full-demostration on the functionality
 * of the system. For this class, comment/uncomment the required test method and run the main static method. 
 * Please only use one test method at a time. 
 * 
 * @author Benjamin Scabbia
 * @version Task4
 */
public class TesterClassConsole {
    public static void main(String[] args) {
        //Uncomment Required Test (Only run 1 test at a time)
        test1();
        //test2();
        //test3();
        //test4();
        //test5();
    }

    private static void test1(){            
        //Normal Test run
        System.out.println("Normal Test Run Demostrating System Functionality in normal conditions");

        CopierSystemImplementation c = new CopierSystemImplementation();    
        //Create 3 Copiers
        System.out.println("Adding 3 Copiers to System");           
        c.addPlainCopier("PC01", 1, 50, 2000); 
        c.addColourCopier("CC02", 1, 1300, 150);
        c.addHQCopier("HC03", 10, 0.5, 2, 1.25, 150, 1200);            

        //Create 3 Jobs
        System.out.println("Adding 3 Jobs to the system");
        c.addJob("Rick Grimes", 5, 10, true, false, false); 
        c.addJob("Dexter Morgan", 15,4 , false, false, false); 
        c.addJob("Hannah McKay", 16,2, true, false, true);

        //Before State
        System.out.println("\n#################### BEFORE STATE ####################");

        System.out.println("\nCurrent State of allCopiers");
        System.out.println("CopierID : Status");
        System.out.println("-------------------");
        for(String copier : c.getAllCopiers()){
            //getCopierStatus() - Temporary method to make test results clearer
            System.out.println("   " + copier + "  : " + c.getCopierStatus(copier));
        }

        System.out.println("\n\nCurrent State of allJobs");
        System.out.println("JobID : Status  : CopierID : Cost");
        System.out.println("------------------------------------");
        for(int i=0; i<c.getAllJobs().length; i++){
            //getJobById() - Temporary made 'public' to make test results clearer
            Job job = c.getJobById(i);                
            System.out.println("   " + job.getJobId() + "  :    " + c.getJobStatus(job.getJobId()) + "    :   " + job.getAllocatedCopierId() + "   :  " + job.getJobCost());
        }

        System.out.println("\n#################### SCHEDULE JOBS ####################");
        //Call schedule Jobs Method
        System.out.println("\n#####Calling scheduleJobs() method#####");
        c.scheduleJobs();

        System.out.println("\n#################### EXPECTED STATE ####################");
        //Expected State
        System.out.println("\nExpected State of allCopiers after scheduleJobs()");
        System.out.println("CopierID : Status");
        System.out.println("--------------------");
        System.out.println("  PC01   : AVAILABLE");
        System.out.println("  CC02   : IN_USE");
        System.out.println("  HC03   : IN_USE");

        System.out.println("\nExpected State of allJobs");
        System.out.println("JobID : Status : CopierID : Cost");
        System.out.println("--------------------");
        System.out.println("  0   :    1   :    CC02  :  50");
        System.out.println("  1   :    1   :    HC03  :  42");
        System.out.println("  2   :    0   :    null  :  224");            

        System.out.println("\n#################### ACTUAL STATE ####################");
        //After State
        System.out.println("\nAfter State of allCopiers");
        System.out.println("CopierID : Status");
        System.out.println("-------------------");
        for(String copier : c.getAllCopiers()){
            //getCopierStatus() - Temporary method to make test results clearer
            System.out.println("   " + copier + "  : " + c.getCopierStatus(copier));
        }

        System.out.println("\n\nAfter State of allJobs");
        System.out.println("JobID : Status  : CopierID : Cost");
        System.out.println("------------------------------------");
        for(int i=0; i<c.getAllJobs().length; i++){
            //getJobById() - Temporary made 'public' to make test results clearer
            Job job = c.getJobById(i);                
            System.out.println("   " + job.getJobId() + "  :    " + c.getJobStatus(job.getJobId()) + "    :   " + job.getAllocatedCopierId() + "   :  " + job.getJobCost());
        }
    }

    private static void test2(){
        //Test run with 0 Copiers
        System.out.println("Test Run Demostrating System Functionality with 0 copiers");

        CopierSystemImplementation c = new CopierSystemImplementation();             

        //Create 3 Jobs
        System.out.println("Adding 3 Jobs to the system");
        c.addJob("Rick Grimes", 5, 10, true, false, false); 
        c.addJob("Dexter Morgan", 15,4 , false, false, false); 
        c.addJob("Hannah McKay", 16,2, true, false, true);

        //Before State
        System.out.println("\n#################### BEFORE STATE ####################");
        System.out.println("\nCurrent State of allCopiers");
        System.out.println("CopierID : Status");
        System.out.println("-------------------");
        for(String copier : c.getAllCopiers()){
            //getCopierStatus() - Temporary method to make test results clearer
            System.out.println("   " + copier + "  : " + c.getCopierStatus(copier));
        }

        System.out.println("\n\nCurrent State of allJobs");
        System.out.println("JobID : Status  : CopierID : Cost");
        System.out.println("------------------------------------");
        for(int i=0; i<c.getAllJobs().length; i++){
            //getJobById() - Temporary made 'public' to make test results clearer
            Job job = c.getJobById(i);                
            System.out.println("   " + job.getJobId() + "  :    " + c.getJobStatus(job.getJobId()) + "    :   " + job.getAllocatedCopierId() + "   :  " + job.getJobCost());
        }

        System.out.println("\n#################### SCHEDULE JOBS ####################");
        //Call schedule Jobs Method
        System.out.println("\n#####Calling scheduleJobs() method#####");
        c.scheduleJobs();

        System.out.println("\n#################### EXPECTED STATE ####################");
        //Expected State
        System.out.println("\nExpected State of allCopiers after scheduleJobs()");
        System.out.println("CopierID : Status");
        System.out.println("--------------------");

        System.out.println("\nExpected State of allJobs");
        System.out.println("JobID : Status : CopierID : Cost");
        System.out.println("--------------------");
        System.out.println("  0   :    0   :    null  :  0");
        System.out.println("  1   :    0   :    null  :  0");
        System.out.println("  2   :    0   :    null  :  0");            

        System.out.println("\n#################### ACTUAL STATE ####################");
        //After State
        System.out.println("\nAfter State of allCopiers");
        System.out.println("CopierID : Status");
        System.out.println("-------------------");
        for(String copier : c.getAllCopiers()){
            //getCopierStatus() - Temporary method to make test results clearer
            System.out.println("   " + copier + "  : " + c.getCopierStatus(copier));
        }

        System.out.println("\n\nAfter State of allJobs");
        System.out.println("JobID : Status  : CopierID : Cost");
        System.out.println("------------------------------------");
        for(int i=0; i<c.getAllJobs().length; i++){
            //getJobById() - Temporary made 'public' to make test results clearer
            Job job = c.getJobById(i);                
            System.out.println("   " + job.getJobId() + "  :    " + c.getJobStatus(job.getJobId()) + "    :   " + job.getAllocatedCopierId() + "   :  " + job.getJobCost());
        }
    }

    private static void test3(){            
        //Test run with 0 jobs
        System.out.println("Test Run Demostrating System Functionality with 0 jobs");

        CopierSystemImplementation c = new CopierSystemImplementation();    

        //Create 3 Copiers
        System.out.println("Adding 3 Copiers to System");           
        c.addPlainCopier("PC01", 1, 50, 2000); 
        c.addColourCopier("CC02", 1, 1300, 150);
        c.addHQCopier("HC03", 10, 0.5, 2, 1.25, 150, 1200);            

        //Before State
        System.out.println("\n#################### BEFORE STATE ####################");
        System.out.println("\nCurrent State of allCopiers");
        System.out.println("CopierID : Status");
        System.out.println("-------------------");
        for(String copier : c.getAllCopiers()){
            //getCopierStatus() - Temporary method to make test results clearer
            System.out.println("   " + copier + "  : " + c.getCopierStatus(copier));
        }

        System.out.println("\n\nCurrent State of allJobs");
        System.out.println("JobID : Status  : CopierID : Cost");
        System.out.println("------------------------------------");
        for(int i=0; i<c.getAllJobs().length; i++){
            //getJobById() - Temporary made 'public' to make test results clearer
            Job job = c.getJobById(i);                
            System.out.println("   " + job.getJobId() + "  :    " + c.getJobStatus(job.getJobId()) + "    :   " + job.getAllocatedCopierId() + "   :  " + job.getJobCost());
        }

        System.out.println("\n#################### SCHEDULE JOBS ####################");
        //Call schedule Jobs Method
        System.out.println("\n#####Calling scheduleJobs() method#####");
        c.scheduleJobs();

        System.out.println("\n#################### EXPECTED STATE ####################");
        //Expected State
        System.out.println("\nExpected State of allCopiers after scheduleJobs()");
        System.out.println("CopierID : Status");
        System.out.println("--------------------");
        System.out.println("  PC01   : AVAILABLE");
        System.out.println("  CC02   : AVAILABLE");
        System.out.println("  HC03   : AVAILABLE");

        System.out.println("\nExpected State of allJobs");
        System.out.println("JobID : Status : CopierID : Cost");
        System.out.println("--------------------");      

        System.out.println("\n#################### ACTUAL STATE ####################");
        //After State
        System.out.println("\nAfter State of allCopiers");
        System.out.println("CopierID : Status");
        System.out.println("-------------------");
        for(String copier : c.getAllCopiers()){
            //getCopierStatus() - Temporary method to make test results clearer
            System.out.println("   " + copier + "  : " + c.getCopierStatus(copier));
        }

        System.out.println("\n\nAfter State of allJobs");
        System.out.println("JobID : Status  : CopierID : Cost");
        System.out.println("------------------------------------");
        for(int i=0; i<c.getAllJobs().length; i++){
            //getJobById() - Temporary made 'public' to make test results clearer
            Job job = c.getJobById(i);                
            System.out.println("   " + job.getJobId() + "  :    " + c.getJobStatus(job.getJobId()) + "    :   " + job.getAllocatedCopierId() + "   :  " + job.getJobCost());
        }                       
    }

    private static void test4(){            
        //Normal Test run
        System.out.println("Test Run Demostrating System Functionality with 1 Copier and MANY Jobs");

        CopierSystemImplementation c = new CopierSystemImplementation();    

        //Create 1 Plain Copier
        System.out.println("Adding 1 Copier to System");           
        c.addPlainCopier("PC01", 1, 50, 2000);            

        //Create all Jobs Combination, including Jobs the Plain Copier can't handle
        System.out.println("Adding 8 Jobs to the system");
        c.addJob("Rick Grimes", 5, 5, true, true, true); 
        c.addJob("Harry Kane", 15, 4, true, true, false);         
        c.addJob("Lisa Smith", 51, 6, true, false, true); 
        c.addJob("Katy Green", 2, 4, true, false, false); 
        c.addJob("James Reynolds", 3, 110, false, true, true); 
        c.addJob("Stepanie Watlins", 1, 10, false, true, false); 
        c.addJob("Peter Griffin", 2, 5, false, false, true); 
        c.addJob("Homer Simpson", 5, 10, false, false, false); 

        //Before State
        System.out.println("\n#################### BEFORE STATE ####################");
        System.out.println("\nCurrent State of allCopiers");
        System.out.println("CopierID : Status");
        System.out.println("-------------------");
        for(String copier : c.getAllCopiers()){
            //getCopierStatus() - Temporary method to make test results clearer
            System.out.println("   " + copier + "  : " + c.getCopierStatus(copier));
        }

        System.out.println("\n\nCurrent State of allJobs");
        System.out.println("JobID : Status  : CopierID : Cost");
        System.out.println("------------------------------------");
        for(int i=0; i<c.getAllJobs().length; i++){
            //getJobById() - Temporary made 'public' to make test results clearer
            Job job = c.getJobById(i);                
            System.out.println("   " + job.getJobId() + "  :    " + c.getJobStatus(job.getJobId()) + "    :   " + job.getAllocatedCopierId() + "   :  " + job.getJobCost());
        }

        System.out.println("\n#################### SCHEDULE JOBS ####################");
        //Call schedule Jobs Method
        System.out.println("\n#####Calling scheduleJobs() method#####");
        c.scheduleJobs();

        System.out.println("\n#################### EXPECTED STATE ####################");
        //Expected State
        System.out.println("\nExpected State of allCopiers after scheduleJobs()");
        System.out.println("CopierID : Status");
        System.out.println("--------------------");
        System.out.println("  PC01   : IN_USE");

        System.out.println("\nExpected State of allJobs");
        System.out.println("JobID : Status : CopierID : Cost");
        System.out.println("--------------------");
        System.out.println("  0   :    0   :    null  :  0");
        System.out.println("  1   :    0   :    null  :  0");
        System.out.println("  2   :    0   :    null  :  0");            
        System.out.println("  3   :    0   :    null  :  0");   
        System.out.println("  4   :    0   :    null  :  0");   
        System.out.println("  5   :    0   :    null  :  0");   
        System.out.println("  6   :    0   :    null  :  0");   
        System.out.println("  7   :    1   :    PC01  :  100");   

        System.out.println("\n#################### ACTUAL STATE ####################");
        //After State
        System.out.println("\nAfter State of allCopiers");
        System.out.println("CopierID : Status");
        System.out.println("-------------------");
        for(String copier : c.getAllCopiers()){
            //getCopierStatus() - Temporary method to make test results clearer
            System.out.println("   " + copier + "  : " + c.getCopierStatus(copier));
        }

        System.out.println("\n\nAfter State of allJobs");
        System.out.println("JobID : Status  : CopierID : Cost");
        System.out.println("------------------------------------");
        for(int i=0; i<c.getAllJobs().length; i++){
            //getJobById() - Temporary made 'public' to make test results clearer
            Job job = c.getJobById(i);                
            System.out.println("   " + job.getJobId() + "  :    " + c.getJobStatus(job.getJobId()) + "    :   " + job.getAllocatedCopierId() + "   :  " + job.getJobCost());
        }

    }

    private static void test5(){            
        //Normal Test run
        System.out.println("Test Run Demostrating System Functionality with 1 Copier and MANY Jobs");

        CopierSystemImplementation c = new CopierSystemImplementation();    

        //Create 1 Plain Copier
        System.out.println("Adding 1 Copier to System");           
        c.addPlainCopier("PC01", 1, 50, 2000);      
        c.addColourCopier("CC02", 1, 1300, 100);
        c.addHQCopier("HC03", 10, 0.5, 2, 1.25, 250, 1200);
        c.addPlainCopier("PC04", 1, 150, 2500);      
        c.addColourCopier("CC05", 1, 700, 100);
        c.addHQCopier("HC06", 10, 1.5, 3, 1.25, 250, 1000);

        //Create all Jobs Combination, including Jobs the Plain Copier can't handle
        System.out.println("Adding 1 Job to the system");
        c.addJob("Rick Grimes", 5, 5, true, false, false); 

        //Before State
        System.out.println("\n#################### BEFORE STATE ####################");
        System.out.println("\nCurrent State of allCopiers");
        System.out.println("CopierID : Status");
        System.out.println("-------------------");
        for(String copier : c.getAllCopiers()){
            //getCopierStatus() - Temporary method to make test results clearer
            System.out.println("   " + copier + "  : " + c.getCopierStatus(copier));
        }

        System.out.println("\n\nCurrent State of allJobs");
        System.out.println("JobID : Status  : CopierID : Cost");
        System.out.println("------------------------------------");
        for(int i=0; i<c.getAllJobs().length; i++){
            //getJobById() - Temporary made 'public' to make test results clearer
            Job job = c.getJobById(i);                
            System.out.println("   " + job.getJobId() + "  :    " + c.getJobStatus(job.getJobId()) + "    :   " + job.getAllocatedCopierId() + "   :  " + job.getJobCost());
        }

        System.out.println("\n#################### SCHEDULE JOBS ####################");
        //Call schedule Jobs Method
        System.out.println("\n#####Calling scheduleJobs() method#####");
        c.scheduleJobs();

        System.out.println("\n#################### EXPECTED STATE ####################");
        //Expected State
        System.out.println("\nExpected State of allCopiers after scheduleJobs()");
        System.out.println("CopierID : Status");
        System.out.println("--------------------");
        System.out.println("  PC01   : AVAILABLE");
        System.out.println("  CC02   : AVAILABLE");
        System.out.println("  HC03   : AVAILABLE");
        System.out.println("  PC04   : AVAILABLE");
        System.out.println("  CC05   : IN_USE");
        System.out.println("  HC06   : AVAILABLE");

        System.out.println("\nExpected State of allJobs");
        System.out.println("JobID : Status : CopierID : Cost");
        System.out.println("--------------------");
        System.out.println("  0   :    1   :    CC05  :  25");

        System.out.println("\n#################### ACTUAL STATE ####################");
        //After State
        System.out.println("\nAfter State of allCopiers");
        System.out.println("CopierID : Status");
        System.out.println("-------------------");
        for(String copier : c.getAllCopiers()){
            //getCopierStatus() - Temporary method to make test results clearer
            System.out.println("   " + copier + "  : " + c.getCopierStatus(copier));
        }

        System.out.println("\n\nAfter State of allJobs");
        System.out.println("JobID : Status  : CopierID : Cost");
        System.out.println("------------------------------------");
        for(int i=0; i<c.getAllJobs().length; i++){
            //getJobById() - Temporary made 'public' to make test results clearer
            Job job = c.getJobById(i);                
            System.out.println("   " + job.getJobId() + "  :    " + c.getJobStatus(job.getJobId()) + "    :   " + job.getAllocatedCopierId() + "   :  " + job.getJobCost());
        }
    }
}