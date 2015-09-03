# TestRailJavaApi

This API is a wrapper arround TestRail Java Appi. This will help to automated your Test Results management when using test rail.

This project contains all the files nececsary dependencies to start working with it.


INSTRUCTIONS ON HOW TO USE IT:

There are several ways to use this API, this project can easily integrate with SoapUI Selenium Web driver.

---

### Here are the instructions SOAP UI

1) Clone the code:
git clone https://github.com/svelezsaffon/TestRailJavaApi.git

After cloning there will be two special things, all the code or the uilt JAR. For this tutorial we will use the JAR file, since it contains everything in the same package

2) Copy and paste the Jar file inside the SOAPUI folder \SoapUI-5.2.0\bin\ext .. This is the folder where libraries can be added

3)For this tutorial we will assume that you already have a SOAPUI project.

4)In your test-suite custom properties, add 3 new properties. This are the ones used to login to the TestRail server.

Username == The username used to login to the test rail server.
password == The Password used to login to the test rail server.
URL      == URL of the TestRail Server.

5) On your test case, create a new Script Case Step... Left click over the test case -> add step -> Groovy Script

https://drive.google.com/file/d/0B6LQ_Fb9bryrdkpLQjdJcHF0LVk/view?usp=sharing

6) The following code is one of the many scripts you can create.

```groovy

//Import the added jar

import core.*;
import core.test_rail_case.CaseCodes;
import core.test_rail_case.Case_Helper;

//Extract the previous created properties from the TestSuite Custom Variables
def username = testRunner.testCase.testSuite.getPropertyValue( "Username" );
def password = testRunner.testCase.testSuite.getPropertyValue( "Password" );
def url = testRunner.testCase.testSuite.getPropertyValue( "Url" )

//Create a API_CORE variable that will help you to interact with the library
API_CORE api=new API_CORE();

api.set_password(password);

api.set_url(url);

api.set_username(username);

api.connect();

def assertionsList = testRunner.getTestCase().getTestStepByName("GoogleMapsRequest").getAssertionList();

boolean result=true;

String failed_message="";

for( e in assertionsList){
    def status=e.status.toString();

    if(status=="FAILED"){

 		result &=false;

		def errors=e.getErrors();

		if(errors!=null){
			failed_message+="["+e.getName()+"]:="+errors[0].getMessage()+"\n";
		}

    }else if(status=="VALID"){

	result &=true;

    }
}


Case_Helper help= new Case_Helper().run_id("4").type(CaseCodes.ADD_RESULTS_TYPE).case_id("3");
Map data=new HashMap();

if(result==false){
	data.put(CaseCodes.STATUS_ID,CaseCodes.FAILED_STATUS_CODE);
	data.put(CaseCodes.COMMENT,failed_message);
}else{
	data.put(CaseCodes.STATUS_ID,CaseCodes.PASSED_STATUS_CODE);
	data.put(CaseCodes.COMMENT,"This test case passed correctly");
}


api.add_result_for_case(data,help);

log.info("Report has been succesfully sent to TestRail Server");


```