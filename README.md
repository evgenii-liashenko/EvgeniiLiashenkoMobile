# How to run tests



### 1. Add your EPAM Mobile Cloud access token to the project

1. Run ```mkdir credentials && touch mobile_cloud.properties```
2. In the created file, type ```token=``` and paste your token immediately after


### 2. Specify the devices you want to use
1. Write ```udid``` or ```deviceName``` parameter values into TestNG suite files located in ```src/test/resources```


### 3. Run a Maven command

EPAM Mobile Cloud tests
* ```mvn test -P androidCloudNative```
* ```mvn test -P androidCloudWeb```
* ```mvn test -P iOSCloudNative```
* ```mvn test -P iOSCloudWeb```

Local appium server tests
* ```mvn test -P androidLocalNative```
* ```mvn test -P androidLocalWeb```



