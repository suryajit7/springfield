**Springboot based Full-Fledged Enterprise Level Automation Framework**

Work In Progress*

The key objective behind this project is to design a robust Automation Test Framework that would support test automation at all levels, including, but not limited to UI (Presentation Layer), APIs & Integration (Service Layer), and Unit/Components, etc. The framework supports various Test Data, Test Environment, Configuration Management, and Test Automation features which are needed to run any modern automated tests.

**Features:**
1. Context and Dependency Injection using Spring.
2. Supports data insertion in all types of DB's like MongoDB, SQL DB, H2 etc. using Spring JPA.
3. Inbuilt Test Data Generation features using Faker & Fixture libraries for supplying Test Data in respective tests.
4. Common File Reader for deserializing data from various file types (json, txt, csv, xml, excel etc.).
5. Rest Assured support for API testing and PACT for running Contract tests.
6. Supports all kinds of projects including Data & Keyword-driven along with Page Object Model with PageFactory.
7. Infrastructure support for Dockerized Selenium Grid containers.
8. Support for Parallel testing with Cross-Browser and Cross-Platform support. (Used Browserstack as an example, but the same example can be used for any other type of Cloud Testing platforms like LambdaTest or Applitools)
9. Custom scopes for running WebDriver thread and easy configuration for different Run modes. (To run local tests change -> **runmode=local** in application.properties, for Dockerized Grid containers -> **runmode=remote**, and for cloud platforms -> **runmode=cloud**)
10. Visual assertions support using Shutterbug library.	
