## DESCRIPTION:

	SMART WEBCRAWLER (depth based crawling)
	This Smart crawler API is designed for web crawling purpose. 
	Receiving a valid URL and an optional depth value as input parameters it gives the following attributes as JSON objects:
	*	Number of hypermedia links
	*	Links grouped as Internal and External
	*	Protocol of the link (http or https)
	*	Accessibility / reachability flag: true or false
	*	Redirection
	*	Depth of the link – gives the level of the link from the parent url

## PREREQUISITES:

	JAVA 8,9,10 (Mandatory)
	MAVEN / GRADLE
	SPRING BOOT STS
	Web browser / Post man app to test the API
	
## Git path of the solution 

   [SmartCrawler](https://github.com/naveenmutharasan/smartCrawler.git )

## HOW TO BUILD AND RUN THE SMART CRAWLER LOCALLY

	First step is to run your spring boot app/server in one of the following ways

	Way 1 Using Spring boot jar generated:
	1. Copy the smartCrawl-0.0.1-SNAPSHOT.jar to your box
	2. In terminal go to the location where the jar is copied
	3. Run the command: java -jar smartCrawl-0.0.1-SNAPSHOT.jar 

	Way 2 If STS (Spring Tool Suite) is installed:
	1. Launch the STS app from your machine
	2. Import the cloned/copied project smartCrawler as an existing project into workspace and give finish.
	File -> Import -> General -> Existing Projects into Workspace
	3. Once the import is successful, right click on the project and Run as -> Spring Boot App

	Once the server is started , Either using postman or a web-browser hit the folliwing url with your parameters:

	http://localhost:8080/crawl?uri=URLYouWantToPass&depth=DepthOfTheUrl
	uri - required parameter
	depth- optional parameter with default value as 0

	*While using browser , by default the ouput will be displayed in JSON format in the page section.
	*While using the postman select the method as GET and output as JSON.

## EXAMPLES

	1. http://localhost:8080/crawl?uri=http://www.tnstc.in&depth=0  is same as  http://localhost:8080/crawl?uri=http://www.tnstc.in
	2. http://localhost:8080/crawl?uri=http://www.tnstc.in&depth=1
	
   Output sample:
	{
    "url": "http://www.tnstc.in",
    "totalCrawledPages": 45,
    "internalLinks": {
        "http://www.tnstc.in/SETCCashRmtWeb/": {
            "protocol": "http",
            "reachable": false,
            "unreachableReason": "HTTP error fetching URL",
            "internal": true,
            "redirectedURI": "",
            "uriDepth": 0,
            "redirected": false,
            "uri": "http://www.tnstc.in/SETCCashRmtWeb/"
        },........
		
	"externalLinks": {
        "https://www.facebook.com/sivagangainews/?ref=py_c": {
            "protocol": "https",
            "reachable": true,
            "unreachableReason": "",
            "internal": false,
            "redirectedURI": "",
            "uriDepth": 1,
            "redirected": false,
            "uri": "https://www.facebook.com/sivagangainews/?ref=py_c"
        },........
    
## ASSUMPTIONS MADE
	* User knows how to access a webpage and test run an API using postman
	* Depth is considered as 0 initially (provides crawling report about the same page) , but additional feature given to 
	run tests for further depth levels as well.
	* Threshold for URLs processed is kept as 100 for now
	* Time out limit for a URL is kept as 3000ms 

## DESIGN CHOICE MADE
   
   * Since the objective seems to be a uni-morphic solution , I have used the basic MVC style of design 
   of splitting the pojo's into model layer , Controller layer for dispatching the request to the service 
   layer, processing in service layer with factory design based interface and implementation.

## CONSTRAINTS and LIMITATIONS  
	* The crawl report varies from page to page depends upon the number of hypermedial links it has , 
	so the processing time may vary depends upon the same.For performance reasons the threshold for 
	number of urls processed is kept as 100 for now.

	* I have additional feature developed depth based crawling for a deeper crawl report. However
	if the depth level goes beyond 1 it takes more time in most of the cases since it takes longer processing 
	time for larger number of urls. So please be informed to wait for some time when processing higher depths.

	* J-Unit test was not fully completed because of the time constraints , It may not be a perfectly 
	working unit test.

	* Idea of multithreading was to be implemented to reduce processing time through parallel exectution
	But the semi-working solution was reverted back due to code quality and time constraints.  


## RUNNING J-UNIT TESTS
   Go to the java class SmartCrawlerServiceTest.java , Right click and Run as Junit test to see the test results


## BUILT WITH

	* [Spring Tool Suite](https://spring.io/tools3/sts/all) - The spring boot web framework used
	* [Maven](https://maven.apache.org/) - Dependency Management


## AUTHORS

	*Naveen Mutharasan** - [SmartCrawler](https://github.com/naveenmutharasan/smartCrawler.git )


## ACKNOWLEDGMENTS

	* Took inspiration from online materials but final coding is done by Self with personal design choice.


