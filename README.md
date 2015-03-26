# CMS-Project
Masters project for the module "Programming and Programming Design"

Overall grade: 94/100

#Copier Management System
#Introduction
For this assignment you are to prototype a simulation of a software system to manage a copying service for employees of a company with a range of copying devices. The prototype you are asked to produce should handle the part of a system mainly concerned with handling jobs and copiers.

The system should maintain a list of copiers together with information on whether they are available for jobs. Each copier has a unique identifier which consists of letters and digits e.g. CP12. The characteristics of the copiers supported are expected to vary during the life of project with new copier types being incorporated into the system. The known copier types are as described below. In these descriptions a physical piece of paper is referred to as a sheet, and a logical page of a document is referred to as a page. For a copier that only prints "single sided", sheets and pages are the same thing, whereas for a copier printing "double sided", each sheet will have a page of the document on each side (except, possibly, the last sheet).

Plain copiers can only copy in monochrome, do not support double-sided or high-quality copying, and have no limit on the job size they can handle. They have a copy tariff per page plus a job "set up" cost.

Colour copiers can only copy in colour, do not support double-sided or high-quality copying, and have a limit on the job size they can handle (in sheets/pages). They have a copy tariff per page but no job "set up" cost.

High-quality copiers can copy in monochrome and colour, support single, double-sided, low and high-quality copying, and have no limit on the job size they can handle. They have a copy tariff per sheet. This is supplemented by additional amounts for monochrome (per page), colour (per page) and high-quality copying (per sheet). They also have a job "set up" cost. 

All copiers are also given a speed rating of number of sheets printed (single or double sided) per minute. 

Each copier in the system has an availability status, which will be one of the following:
•	available  		(available for jobs)
•	in use        		(in use and unavailable for other jobs)
•	unavailable     		(unavailable for jobs e.g. broken down)

When a copier is added to the system, its availability status is "available".

The system stores the following information about a job: a unique job number allocated sequentially by the system starting from zero, the customer name, the number of copies required, the number of pages in the document, whether the job requires high quality, colour or double sided printing. When a job has been added to the system the state of a job is "waiting".

A job is set running during a call to the scheduleJobs method; the state of the job is set to "printing" and the copier's state is set to "in use".

When a job has been done, the state of the job is set to "done" and the state of its copier is set to "available". 

At any moment in time there may be more jobs to be run than suitable copiers available. The order in which the jobs are run is described in the task-specific information given below.

You may assume for this coursework that there is always at least one suitable copier for every job.

#Usage

Compile and run TesterClass.java for a gui (which demostrates only some of the functionality)
Compile and run TesterClassConsole.java to test all functionality of program
