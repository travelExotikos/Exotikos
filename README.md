# Group Project - *Travel Exotikos*

**Travel Exotikos** is an android app that allows a user to have a travel companion who helps you while traversing through confusing airports and travel stops. The app uses the airline api https://developer.flightstats.com/api-docs/ for getting airport and travel data.

Time spent: **4x** hours spent in total for wireframes

## User Stories

The following **required** functionality is completed:

* [ ] Login through Facebook [Owner: Jesus]
* [ ] Way to input information 
  * [ ] Bar code scanner [Owner: Ada]
    * [ ] APIs to use the bar code scanner - library https://pdf417.mobi/ [Owner: Ada]
    * [ ] What information is obtained from the QR code  [Owner: Ada]
  * [ ] Manually provide the information [Owner: Lakshmy] **Optional**
* [ ] Get the native clock and provide push notification for when the travel is imminent and in progress
  * [ ] Travel start [Owner: Jesus]
    * [ ] Upcoming flights list view [Owner: Jesus]
  * [ ] In Progress [Owner: Lakshmy]
    * [ ] In flight [Owner: Lakshmy]
    * [ ] In airport [Owner: Lakshmy]
  * [ ] Destination [Owner: Lakshmy]
* [ ] Provide users will additional help needed along the way
  * [ ] Include translation as needed [Owner: Ada]
 * [ ] 

The following **optional** features are implemented:

* [ ] Yelp with top restaurants in the airport 
* [ ] Manually allow users to input travel information [Owner: Lakshmy] 
* [ ] When a network request is sent, user sees an [indeterminate progress indicator](http://guides.codepath.com/android/Handling-ProgressBars#progress-within-actionbar)
* [ ] User can **"reply" to any tweet on their home timeline**
  * [ ] The user that wrote the original tweet is automatically "@" replied in compose
* [ ] User can click on a tweet to be **taken to a "detail view"** of that tweet
 * [ ] User can take favorite (and unfavorite) or retweet actions on a tweet
* [ ] Improve the user interface and theme the app to feel twitter branded
* [ ] User can **search for tweets matching a particular query** and see results
* [ ] Usernames and hashtags are styled and clickable within tweets [using clickable spans](http://guides.codepath.com/android/Working-with-the-TextView#creating-clickable-styled-spans)

The following **bonus** features are implemented:

* [ ] Use Parcelable instead of Serializable using the popular [Parceler library](http://guides.codepath.com/android/Using-Parceler).
* [ ] Leverages the [data binding support module](http://guides.codepath.com/android/Applying-Data-Binding-for-Views) to bind data into layout templates.
* [ ] On the profile screen, leverage the [CoordinatorLayout](http://guides.codepath.com/android/Handling-Scrolls-with-CoordinatorLayout#responding-to-scroll-events) to [apply scrolling behavior](https://hackmd.io/s/SJyDOCgU) as the user scrolls through the profile timeline.
* [ ] User can view their direct messages (or send new ones)

The following **additional** features are implemented:

* [ ] List anything else that you can get done to improve the app functionality!

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='http://i.imgur.com/link/to/your/gif/file.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app.

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android

## License

    Copyright [yyyy] [name of copyright owner]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
