## About This Repository

This repository is required for preparing for L2 - Test Design Exercises. 
It also contains preparation and classroom instructions for L2. 

The provided Java source code will be updated the day of the lab. 

1. Download the repo using the [Download ZIP] link. **IMPORTANT**: DO NOT use the [Clone in Desktop] link. DO NOT fork this repo or try try to push your changes back to this repository.  
2. Import the repo into your Eclipse workspace (or your favorite IDE). This should work reasonably well with Eclipse. Make sure you have Eclipse Maven plugin installed. 
3. If the classpath configuration is messed up and dependencies don't work, see `TROUBLESHOOTING.md`. A `.project` and a `.classpath` file are provided, but these will not work with an IDE other than Eclipse and they might require some customization for your local setup. 

## What the Repository Contains

1. The Fitnesse wiki and framework in its entirety, including all of its directory structure and standalone server. 
2. The cobertura code coverage tool, including command-line scripts.
3. A working example that contains java sources, Fitnesse tests that use Fit fixtures, and cobertura 
setup for coverage analysis using both Maven and command-line scripts. 

Fitnesse is a functional testing framework that allows writing user-friendly tests. The tests are written as wiki pages in a table-like notation. To define tests, we will use Ward Cunninham's _Fit_ (Framework for Integration Testing) fixtures, which look very much like test-case tables used in class examples. Test pages are special wiki pages created in a markdown language and may contain documentation and notes... any text really. Fitnesse is run as a wiki web server and can hook up with your Java source code using _glue_ fixture code. 

## Preparation for L2 - Setup

1. Browse to `FitnesseDir` directory. 
3. Start the Fitnesse server on localhost: `java -jar fitnesse-standalone.jar -p 8080` (if port 8080 is used, substitute an usused port).
4. Open your browser and go to  `localhost:8080`. 
5. Read about Fitnesse: read the _One-Minute Description_; try the _Two-Minute Example_.
6. Browse the _User Guide_: review _Introduction_, _Working with the Fitnesse Wiki_, _Writing Acceptance Tests (with Fit)_, and then jump straight to _Fit_. Or if you're impatient, proceed to try out other examples and come back to this step later. 
7. Go to FitNessse root page (path: _/FitNesse/_, or click on _Welcome to FitNesse!_). Then navigate to _My Fit Examples_. Find _Weekly Timesheet_ -> _Timesheet Suite_. This is a suite that contains only one test case. Inspect this suite page. Then run the suite by hitting `Suite`.
8. Navigate to _Weekly Timesheet_ -> _Timesheet Suite_ -> _Weekly Compensation_. Inspect this test case page. Then run the test by hitting `Test`. See rows of the table turn green (passing tests) and yellow (tests giving runtime errors). The yellow cells will have `FunnyHoursException` stack trace. 
9. Experiment with editing existing the page. Click on Edit tab, change row 2 of both `pay?` and `totalHours?` column values to `error`. Save and re-execute the page by hitting Test. See all rows turn green. 
10. Now create a new test page under `TimeSheetSuite`. Copy contents of `WeeklyCompensation` mark-up to clipboard. Go back to `TimeSheetSuite` page. Click on Add->Test Page. For Page Name, enter: `MoreWeeklyCompensation`. Paste copied contents into page. Change data row 1 of Column Fixture to:` |  1 | 1 | 1 | 1 | 1 |`. Save. Click on Test. See red row showing a failing test with expected and actual values. 
11. Browse the Java source to see how the Fitnesse test case is connected to the source code through a fixture class. Look under`src/main/java`. The source code is in  `timesheet` package. The Fit fixture code is located under  `fit.timesheet` package.
12. Look under `src/test/java` to see how to run the Fitnesse tests from JUnit using the`FitNesseRunner` test runner. Regular unit tests are located in `junit.timesheet` package. Fitnesse test suites are under the  `junit.fitnesse.timesheet` package. 
13. Navigate to  _FitNesse_ -> _My Fit Examples_ -> _Triangle Example_. Check the test pages and code for a more complicated example. 
14. Navigate to _FitNesse_ -> _My Fit Examples_ -> _Common Header_. Read this wiki page. Edit the  `CommonHeader` page by hitting the Edit tab (top navigation bar). In `CommonHeader`, change the value of the `net.sourceforge.cobertura.datafile` system property so that this will point to the correct file in your local directory structure. Run a Maven build with goal `compile` to install necessary dependencies (if using Eclipse, your project must have Maven nature). If necessary, select the project folder in Eclipse and try Project->Clean to remove any stale build state.  If this does not work, add JUnit (as a reference library), as well as Fitnesse jar file, cobertura jar file and other required dependencies (as external jars) explicitly to your project classpath. You'll find the jar files under `FitnesseDir` and its subfolder`cobertura-X.X.X` (where `X.X.X` is the latest version). 
15. Now try the Maven setup (your project must have Maven nature). Run a Maven build using the `test` goal to execute both regular unit tests and Fitnesse tests (from console, this is done by: `mvn test`). The fist time this goal is executed, all plugin dependencies will be installed into your project. Inspect the `pom.xml` file for the Maven setup. This setup uses the `maven-sure-fire-plugin` to run both JUnit and Fitnesse tests. For now, running the regular JUnit tests are disabled in the pom file inside the sure-fire configuration. Fitnesse and sure-fire html reports will be generated and stored in subfolders `/target/fitnesse-reports` and `/target/surefire-reports `, respectively. You may browse and render them within your IDE's web browser. If you don't see the report folders and files, refresh the project (Eclipse-Project->Refresh).
16. Use the Maven setup to perform coverage analysis. For this run Maven with the `cobertura:cobertura` goal, which uses the `cobartura-maven-plugin`. In Eclipse, this is done by right-clicking the project and selecting _Run As Maven..._ with proper goal from the drop-down menu. The `cobertura:cobertura`  Maven goal will create instrumented Java source files for coverage analysis, then run Fitnesse tests, and generate html coverage reports. Cobertura coverage data and reports will be accessible in the subfolders `/target/cobertura` and `/target/coverage-reports`, respectively. If you don't see the report folders and files, refresh the project (right-click project, then select Refresh). Open the `index.html` file and navigate to the coverage report for `WeeklyTimesheet`; check the coverage metrics. The first column of the report is  line number, the second column is number of tests that executed that line. Then check coverage metrics for `Triangle`. You should see both green and red lines. If you see only red lines, there is something wrong. Check TROUBLESHOOTING.md. 
17. Problems with Maven builds? Make sure that your IDE has a Maven plugin installed. You may run coverage analysis manually too. Open a console shell. Browse to  `FitnesseDir/cobertura-X.X.X`. Instrument the classes (if they have already been instrumented and haven't changed since last instrumentation, no need to re-instrument) by running the `cobertura-instrument` script with the proper arguments. Run the required Fitnesse tests from the Fitnesse web server. Then run the `cobertura-report.sh` script with proper arguments to generate the coverage reports. Check the reports under the `/target/coverage-reports` subfolder. For more information on how to specify the arguments, see _TimesheetHeader_ page. Attention Windows users: the `.bat` scripts must still be updated to fix the old dependencies as described in _TimesheetHeader_. I have already updated the `.sh` shell scripts for Linux/OSX/Unix users. 
18. You may delete the reports folders under the `target` directory by executing a Maven build with `clean` goal (from console: `mvn clean`). The `.pom ` file has a `maven-clean-plugin` configuration to delete the right folders when the `clean ` goal is executed. If the build state gets corrupted after a `mvn clean`, select project folder and try Eclipse-Project->Clean. 
19. Come to class with your setup ready and working. There won't be any time to fix dependencies and install missing components. 

## Important: Tips for Creating FitNesse Test Tables

You must familiarize yourself a bit with FitNesse markup language before coming to class. Otherwise you'll waste precious time struggling with the syntax of FitNesse pages. 

You can import and export tables from Excel into FitNesse test pages to avoid the complicated markup.
Check this page out to find out how (search for "Excel" in the page):
http://fitnesse.org/FitNesse.UserGuide.WritingAcceptanceTests

## Setup Checklist

__ You have installed FitNesse.  
__ You can run FitNesse server and try above examples from a browser.   
__ You are sufficiently familiar with FitNesse markup language. You know at least how to define comments, section titles, lists, comment tables, executable fixture tables. You know how to include and reference other pages.   
__ You have written a Java `Calculator` class with methods `int square(int)` and `int power(int, int)` (`power(2, 3)` returns 8).  
__ You can create a new FitNesse test page with a Fit column fixture for testing `Calculator`.  
__ You can write Java fixture code for the FitNesse test page and link the FitNesse test page to the fixture.  
__ You can run the FitNesse test and see the tests pass or fail.   
__ You can compute code coverage for FitNesse tests.  
__ You can view code coverage reports for FitNesse tests.  
__ You can change FitNesse tests and see different results.  
__ You can set up Maven to run both JUnit and FitNesse tests.  
__ You can run cobertura for FitNesse and JUnit tests from command line.  


## END OF PREP - STOP HERE! 

## Class Demo (THIS IS NOT PART OF THE LAB - WILL BE DEMOED BY THE INSTRUCTOR)

A `Triangle` is represented by three integers `a`, `b`, `c`, each corresponding to the length of one of the three sides. The `classify()` method of a `Triangle` object classifies the triangle as either `Triangle.SCALENE` (all sides are of different lengths), `Triangle.EQUILATERAL` (all sides are of equal length), or `Triangle.ISOSCELES` (two sides are of equal length). The `isRightAngled()` method returns `true` if the triangle is right-angled; `false` otherwise. For a valid triangle, the sum of any two sides must exceed the third side. 

--1. Design an input space model for the _Triangle_ spec (including both interface methods `classify()` and `isRightAngled()`).  
---1.1. Identify attributes.   
---1.2. Identify characteristics.    
---1.3. Partition the characteristics into blocks that are equivalence classes (each block needs one representative test case).   
---1.4. Identify the constraints and dependencies among blocks from different characteristics.  
 
--2. Derive test case specs from the input space model.    
---2.1. Choose a proper combinatorial strategy. Form feasible combinations of blocks into test cases.    
---2.2. Choose representative values for attributes for each combination, identifying the oracle when required.     
---2.3. Organize your test cases into a table with columns representing attributes and rows representing test cases.       

-- 3. Create the necessary page hierarchy in the Fitnesse wiki. Create an entry page _Triangle Example_ under _My Fit Examples_.    _Triangle Header_, _Triangle Suite_, and _Triangle Test_ (and any other test pages you may need under _Triangle Suite_) should mirror the hierarchy and structure in the _Weekly Timesheet_ example. Document your input space model (task 1) and test cases (task 2) directly on the _Triange Test_ page. Note that nonCamelCase names are not valid FitNesse page names (`Triangle` is not valid, `TriangleTest` is). 

-- 4. Create and run Fitnesse tests.   
--- 4.1 Convert test case specs to test cases expressed as Fitnesse test pages using the _Column Fixture_. For each test case, you need to choose values for all attributes and determine the oracle. Add these test cases to the appropriate test page.      
--- 4.2 Add the required fixture code to your Java source. This should go under `/src/main/java` in a package `fit.triangle`.     
--- 4.3 Run your Fitnesse tests (or test suite). Inspect the results. Don't modify the original source code. Note any faults found to list in your lab report.  Open the coverage report for `Triangle` and check the coverage metrics. Hover over the red line (line 19 -- first statement of `valid()`) to check condition coverage values for that line. The first column of the report is line number, the second column is number of tests that executed that line.       

-- 5. Calculate the coverage of your tests. Visualize and interpret the resulting reports. 


## In-Class Instructions: Systematic Spec-Based Testing of _Date_

You are given a Java implementation (classes `Date` and `InvalidDateException`) for the following spec. In the `FitNesseLab` project structure, you'll find the sources in the package named `date` under `/src/main/java`.

A `Date` object represents a date using three integers: `day`, `month`, and `year`. The `nextDate()` methods returns a new `Date` object that gives the date of the next day. The `isLeapYear()` method returns `true` if the `year` of the `Date` object  is a leap year; `false` otherwise. The `year` must be between 1900 and 2200 (1899 and 2201 are not valid years). A leap year is a year that is divisible by 4; but if it's a century year, it should also be divisible by 400.

Perform the following tasks during the lab. 

--1. Design an input space model for the _Date_ spec (including both interface methods `nextDate()` and `isLeapYear()`).  
---1.1. Identify attributes.   
---1.2. Identify characteristics. Leap year must be included as a characteristic related to the year attribute.    
---1.3. Partition the characteristics into blocks that are equivalence classes (each block needs one representative test case).   
---1.4. Identify the constraints and dependencies among blocks from different characteristics.      

--2. Decide on a test design based on the input space model and a mix of combinatorial strategies.   
---2.1. List the steps in your test design. Each step should deal with a subset of the characteristics. Choose and name the combinatorial strategies used in the step (Base Choice, All Choice, All Pairs, boundary value considerations, etc.) 
---2.2. Comment on constraints involved in each step, if applicable. 
---2.3. Check that your input space model and test design are complete: anyone should be able to create test case specs from your input space model and test design without further instructions, assumptions, or information, and will end up with the same test case specs as you.

--3. Derive test case specs from the input space model and test design.    
---3.1. For each step of your test design, form feasible combinations of blocks of the involved characteristics into test cases.      
---3.2. Organize your test cases into comment tables (one for each test design step) with columns representing attributes and rows representing test cases.       

You are already provided the necessary page hierarchy in the FitNesse wiki. See the entry page _Date Example_ under _My Fit Examples_. The _Date Header_, _Date Suite_, and _Date Test_ pages mirror the hierarchy and structure in the _Weekly Timesheet_ and _Triangle Example_ examples. Document your input space model (task 1), test design (task 2), test case specs (task 3) directly on the _Date Test_ page. Note that non-`CamelCase` names are not valid FitNesse page names (`Date` is not valid, but `DateTest` is. If you want to use `camelCase` notation for a word that is not a page reference, use this markup: `!--CamelCaseWordThatIsNotAPage--!`. 

-- 4. Create and run FitNesse tests.   
--- 4.1 Convert your test case specs to test cases expressed as FitNesse tests using the _Column Fixture_. For each test case, you need to choose values for all attributes and determine the oracle. Add these test cases to the `DateTest` page.    
--- 4.2.  Include isLeapYear() in the oracle as well as nextDate().      
--- 4.3 Add the required fixture code to your Java source. This goes under `/src/main/java` in a package `fit.date`. A fixture stub `NextDate.java` is already provided.    
--- 4.4 Run your FitNesse tests (or test suite). Inspect the results. Modify the original Java source code only to remove faults that may mask other faults. Once you have found all faults from running tests, don't modify the Java source further. Note any faults found to list in your lab report.       

-- 5. Calculate the coverage of your tests. Visualize the resulting reports. Notice the uncovered red lines. Hover over them to see condition coverage values. The first column of the report is line number, the second column is number of tests that executed that line. Take notes for your lab report.

Note: use _Comment Fixture_ tables to document your test design (attributes, characteristics, decision tables, etc.) - comment fixtures are not executed if included in a test or suite page, but will improve the readability of your test pages.

## Creating and Submitting Your Lab Report

Use template below. Add your lab report directly to your FitNesse page under "Results and Interpretation" (remove all comment lines). Run tests to show the results, print the rendered page to PDF, and submit it to Canvas. 

-------

LAB REPORT - Test Design Exercises  
// Simply append this report to the end of your FitNesse test page        
// Remove these comment lines with instructions  

Name: ..........    
Andrew ID: .........   

// IMPORTANT: Replace/remove these comment lines in your report.     
 
-- Faults Found

// List all faults found by your test cases. Indicate for each fault which test cases revealed the fault. 

-- Coverage

// Discuss the coverage of your tests. Relate code coverage to the effectiveness of your test cases.     

-- Insights

// What lessons did you learn from this lab?   
// What worked well?   
// What challenges (other than setup and not being familiar with Java or FitNesse) did you face?  
// How effective were your testing strategy and test cases? 
// If your test cases didn't reveal any faults (or any important faults), why didn't they? Relate this to your testing strategy and test design.     

-- Suggestions (Optional)

// List any suggestions for improving this lab.    
// How comfortable are you with modeling input spaces (identifying attributes, defining characteristics, partitioning characteristics, specifying constraints, and applying compinatorial strategies, creating actual test cases)? If you are not comfortable any of these elements, state what you find difficult or what you do not fully understand? 

  









