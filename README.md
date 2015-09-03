# TestRailJavaApi

This API is a wrapper arround TestRail Java Appi. This will help to automated your Test Results management when using test rail.

This project contains all the files nececsary dependencies to start working with it.


INSTRUCTIONS ON HOW TO USE IT:

There are several ways to use this API, this project can easily integrate with SoapUI Selenium Web driver.

## Here are the instructions SOAP UI

1) Clone the code:
git clone https://github.com/svelezsaffon/TestRailJavaApi.git

After cloning there will be two special things, all the code or the uilt JAR. For this tutorial we will use the JAR file, since it contains everything in the same package

2) Copy and paste the Jar file inside the SOAPUI folder \SoapUI-5.2.0\bin\ext .. This is the folder where libraries can be added

3)For this tutorial we will assume that you already have a SOAPUI project.

4)In your test-suite custom properties, add 3 new properties. This are the ones used to login to the TestRail server.

Username == The username used to login to the test rail server.

password == The Password used to login to the test rail server.

URL      == URL of the TestRail Server.

![alt text](https://github.com/adam-p/markdown-here/raw/master/src/common/images/icon48.png "Logo Title Text 1")
