# Hospital Tech test

Time taken about 3hrs

The test had four area's of interest.
With more time and going for production quality code I would be looking  at caching the hospital list locally in sharedprefs or a sqlDatabase. For  this I used the lifetime of a ViewModel to store the information in memory. Normally I am more used to fetching information via Json. In this case it was in a CSV file (not on HTTPS which also causes problems) with an odd value for the seperator. This was load just using OKHTTP3 (no Retrofit)  
Kotlin allows concise code to read from a stream and transform.

### UI
This is list/detail type screen. I used the AS wizard but was unhappy with the use of multiple activities. As the app grows I might want to use Jetpack Navigation with works best with one Activity, many Fragments. When we have two fragments on the screen we can use a shared ViewModel to share and coordinate data between the controlling Activity and the fragments. Please note that this will present both screens on a tablet.

The `ItemListFragment` will change the `HospitalViewModel.hospital` when an Item is selected. This will change what is seen in the `ItemDetailFragment` if visible on a tablet. On a phone the underlying activity will change the fragment to the `ItemDetailFragment` and add it to the backstack.

The UI appearance was not a priority so little effort was taken to make the app visually appealing. Good designs and time can accomplish this.

### DI
I used Koin instead of Dagger2. Koin is very easy to use and makes it easier to then write unit tests.

### Filtering
I implemented the the model code and repository/view model code for filtering, but did not get to adding it to the UI. The UI would add a series of buttons or menu options based on the Unique Hospital sectors found when retrieving the data. This way we don't have to update the code when the data changes. The List can then be changed by `HospitalViewModel.setSector()`

### Async
The UI relies on observing `LiveData` in the `ViewModel`. The `ViewModel` is used to take the Network call off the UI thread. I have used Kotlin coroutines for this.

## What I didn't do
* Write enough tests.
* Use sealed classes as my `LiveData` types. I prefer to use this because error and loading can then also be represented.
* Transform the Hospital data into more useful types. Everything was kept as non null strings.
* Use Jetpack Navigation library. I think is works well and makes Deep links much easier, but this is a small project and I had little time.
* More strings to string.xml
* Make sure release builds work.
* Run ktlint to make my code look better (and maintain standards)
* Error checking/recovery
* Remove dead/unused resources.
* Separate the URL to not be just hardcoded into one of the classes (we might have different URLS for production/development)
* Rename the classes from the Wizard code to be more consistent.
