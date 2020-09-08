# Menuly

<p align="center">
  <img src="https://user-images.githubusercontent.com/41158713/92423584-fef2df80-f157-11ea-881e-8247874a7015.png" width="660">
</p>

<br/>
<br/>

This is a simple app that simulates the purchase of food at a restaurant. You can see the food list, grouped by categories and you can add then to the app cart.

# Architectural Design Pattern
In this project the MVVM architecture was used along with the Clean Architecture. This choice was made because MVVM + Clean Architecture makes it easier to test, makes the code even more decoupled and makes the project easier to maintain.

# Android Architecture Components
Some components of the Android Architecture Components library collection were also used in this project, such as:
- Room
- LiveData
- ViewModel
- Lifecycle-aware components
- ViewBinding


# Other libraries
- Koin: for dependency injection
- Glide: for load images from the app.
- Retrofit: for consume API services
- Gson: handles the serialization/deserialization from the API
- OkHttp3: HTTP client
- Coroutines: to execute asynchronously
- LeakCanary: for memory leak detection

# Test libraries
- Hamcrest
- Robolectric
- JUnit
- Coroutines-test
- OkHttp Mockwebserver


# GIFs
You can see the restaurant menu and add items to the cart.

<img src="https://user-images.githubusercontent.com/41158713/92423772-9eb06d80-f158-11ea-9ee0-ea1211ab1f64.gif" width="350">
<br/>
<br/>

The items you add to the cart are saved in the app's database.

<img src="https://user-images.githubusercontent.com/41158713/92423777-a3752180-f158-11ea-8169-7ab67f762dc4.gif" width="350">
<br/>
<br/>

The app also handles view status like loading, error and empty.

<img src="https://user-images.githubusercontent.com/41158713/92423784-ab34c600-f158-11ea-9a01-6d0d1b679414.gif" width="350">
