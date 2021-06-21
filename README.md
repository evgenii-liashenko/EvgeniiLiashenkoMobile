# How to launch tests from Terminal

1. In order to run tests on EPAM Mobile Cloud, paste your access token into the token field of properties section of pom.xml
2. Choose the device you want to use and write its udid or deviceName into an appropriate testNG suite file located in in src/test/resources
3. Run one of the commands below to execute a specific test

Tests on EPAM Mobile Cloud
* mvn test -P androidCloudNative
* mvn test -P androidCloudWeb
* mvn test -P iOSCloudNative
* mvn test -P iOSCloudWeb

Tests on a local appium server
* mvn test -P androidLocalNative
* mvn test -P androidLocalWeb



