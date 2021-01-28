# Modular Architecture with MVVM

This is an attempt to create a modular architecture in Android following MVVM pattern.

#### Supporting Libraries
* Dagger 2 for Dependency Injection
* Jetpack Android Library
* Retrofit for network communication
* Junit5 for Unit testing


#### Modules

![Main Modules](https://raw.githubusercontent.com/laaptu/uploads/master/pics/modular_app.png)


The module division is as follows

* Shared or Base Module
* Feature Module
* Main Module

##### Shared/Base Module
 Contains all the shared classes that will be used entirely by all the modules. Currenty this contains base files for dependency injection for Dagger along with helper classes for ViewModels
 
##### Feature Modules
  All the features that is required by the app will lie in this feature module. Currently there is only login feature and later on more can be added here. This module depends upon the base module.
  
##### Main Module
   This is the app module or the main app will use feature modules and base modules as it's dependency.
   

#### Login Module
 Uses MVVM architecture for simple login. The valid username and password for login is `test@test.com` and `123456`. Currently using Firebase email login for test. This is the only working module for now.