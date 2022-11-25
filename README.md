# framework-hometask
### Description
Completed practical task on the training module **(Test Automation. Level 2)**:
- [X] ***Framework***

mvn -Dbrowser=firefox -Denviroment=staging -DsuiteXmlFile=src\test\resources\testng-smoke.xml -Denvironment=dev clean test
mvn -Dbrowser=firefox -Denviroment=staging -Dsurefire.suiteXmlFiles=src\test\resources\testng-smoke.xml -Denvironment=dev clean test

mvn -Dbrowser=firefox -Denviroment=staging -Denvironment=dev clean test
mvn -Dbrowser=chrome -Denviroment=staging -Denvironment=dev clean test