# How to run tests



### 1. Setup EPAM Mobile Cloud access

1. Run ```mkdir credentials && touch mobile_cloud.properties```
2. In the created file, type ```token=``` and paste your token immediately after;
3. Add a new line and paste ```base_url=https://mobilecloud.epam.com/automation/api/```



### 2. Specify the devices you want to use
1. Write ```udid```  parameter values into TestNG suite files located in ```src/test/resources```


### 3. Run a Maven command

EPAM Mobile Cloud tests
* ```mvn test -P androidCloudNative```
* ```mvn test -P androidCloudWeb```
* ```mvn test -P iOSCloudNative```
* ```mvn test -P iOSCloudWeb```

Local appium server tests
* ```mvn test -P androidLocalNative```
* ```mvn test -P androidLocalWeb```



