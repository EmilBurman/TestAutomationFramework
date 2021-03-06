# Test automation framework
This respository is a simple template project that allows for API and frontend testing. Either executed through the Junit plattform, or through Cucumber based features.

It's based on:
 - Gradle 
 - Junit 5 
 - Selenide
 - Cucumber
  
It is possible to split this project into a pure frontend or a pure API testing template.The project also supports Cucumber and BDD, but it is optional and the other parts of the project can be used without the BDD integration.

## Getting Started
The project does not have any external dependencies, it is simply a collection of methods and classes to get a test automation framework of the ground. 

Gradle and java 8 is expected though. Download the repo and check the underlying chapters for more info.

### Currently supported APIs
Since this is a template project there are currently two supported APIs, chosen due to their ease of use and their inherent difference.
- https://cat-fact.herokuapp.com/#/
- http://www.omdbapi.com/

The omdb API uses a key that is contained within the secrets in this repository. Running these tests localy requires a API key that you need to order yourself. Currently there is no way to fetch the secret from within this repository.

## Running the tests

Tests can run inside of IntelliJ, but can also be run through gradle tasks. See build.gradle for more information regarding the tasks.
All tests can be executed through:
- gradle testWithTag -Ptestwith.tag=[NAME OF YOUR TAG HERE]

Its important to understand that the tags are specific, and can be found in either utils/tags for junit tags, or in the features themselves for Cucumber and BDD tests.

It it also possible to use predefined tasks specified for the types of testing the framework offers:
- gradle testAllFrontEnd/testAllAPI/testAllBDD

To run a complete test suite simply type gradle test into your terminal.

### Secrets and API keys

While all tests can be run, some are dependent on API keys that are only available from github (currently). In order to run the OMDB tests without them failing a API key needs to be added to property file aptly named "api.secrets.properties". The specific key name is "omdb.api.key" in order to avoid any confussion.

Currently there is no way to fetch the key from within github and place it locally, instead the key is currently known by only one person, Emil Burman. If you wish to run the tests yourself it is recommended that you fetch your own key to the omdb API.

## Built With

* See gradle.properties or build.gradle for an overview of the different libraries used for the framework.

## Extending the framework
Currently there are no hard rules within the framework, but there are several patterns used that is recommended to continue using:
- Every API test is currently using a builder pattern to create a URI and then use that for further tests towards their API management class.
- Every API management class currently implemenents the API management interface. This is to ensure consistency between classes.
- The Selenide framework uses a factory pattern to determine the needed browser. It is recommended to maintain this pattern since it eases management of the webdriver itself.
- The implementation of Selenide is built upon the Page Object Model, which decouples tests and the selenide framework itself. It is highly recommended that every extension to the selenide test suite maintains this implementation.

### Possible improvements
The current BDD implementation is barebones, it is more of a proof of concept than anything else. If anyone would like to further this implementation please feel free to do so. 

An improvement to the logging within the framewrok is needed, where different levels can be used to trace, info and debugg certain areas.

The XML implementation is mostly a proof of concept due to the difficulty to working with XML.

## Authors

* **Emil Burman** - *Initial work* - [Github](https://github.com/EmilBurman)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
