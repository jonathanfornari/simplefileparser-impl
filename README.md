# simplefileparser-impl

Maven project using java 11
Build with 'mvn clean install'
Run tests with 'mvn clean test'

Application was designed with 3 different modules in mind, in order to comply with the single responsability principle, open-closed principle, dependency inversion (for insstance, the parser depends on the abstractions of Channel and statistics extractors and not any concrete implementation) etc..

Channel: Monitorable channel, can be a folder or an s3 bucket for instance. In order to extend it to monitor/poll anything else than a folder you just need to create a new implementation of MonitorableChannel

Parser: Specific parsing rules, coordinates polling the items from monitorable channel and applying statistics extractor onto every item

Statistics: Implements statistics extraction and handle different types/extensions.

Instructions
* Run from Main class main method. If path argument is not provided, app will ask for input.
* The selected/provided folder path is monitored/polled every 60 seconds.

Known issues/Decisions/Assumptions
* Path/argument provided is not checked for existence or if it is a well-formed path
* Subfolders on the provided path are NOT monitored/polled
* Exceptions and logs are being printed on the console/standard out, needs proper longing for the future
* start method implementation on AbstractParser with the usage of while true and Thread.sleep is not the most elegant or suitable, but works for the reduced scope of this exercise, something like a consumer/observer/events pattern could be implemented here
* statistics are only outputed to the console, you can override outputStatistics method on a parser if you want to change and write them to another file for instance
* App is lacking a more reasonable amount of tests to properly test every module and edge cases

Roadmap/improvements
* Create a dockerfile with a proper springboot/quarkus build to containerize the application
* Make the polling period (Currenly every 1 minute) configurable / defined by user
* Implement proper logging and exception handling
