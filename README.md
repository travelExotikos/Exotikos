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
    * [ ] Use https://shaun.net/whats-contained-in-a-boarding-pass-barcode/ to obtain QR code scanning
       * [ ] Name
       * [ ] booking  reference???,
       * [ ] from-to-airline need dictionary/service to read this. ATLMEMDL=from Atlanta to Memphis, Delta
       * [ ] Flight #
       * [ ] Departure day 006 = 06JAN Julian format
       * [ ] Cabin Y-economy, F-first, J-business
       * [ ] Seat # 
  * [ ] Manually provide the information [Owner: Lakshmy] **Optional**
* [ ] Get the native clock and provide push notification for when the travel is imminent and in progress
  * [ ] Travel start [Owner: Jesus]
    * [ ] Upcoming flights list view [Owner: Jesus]
    * [ ] Flight detail activity
       * [ ] Fragment - You will flight in X days
       * [ ] Fragment - Your flight will depart in XX hours, head to {AirportName}
       * [ ] [Actions] Give me directions
       * [ ] [Checks section] Before you leave
       * [ ] Luggage
       * [ ] Visas
       * [ ] Medication
  * [ ] In Progress [Owner: Lakshmy]
    * [ ] In flight [Owner: Lakshmy]
      * [ ] Fragment - details on the next leg of the journey
       * [ ] In flightCountry
       * [ ] Time zone
       * [ ] Language
       * [ ] Details of the next airport (shops, etc) [Stretch]
     * [ ] Fragment - inflight exercises
       * [ ] Leg exercises
       * [ ] Back exercises
       * [ ] In airport [Owner: Lakshmy]
  * [ ] Destination [Owner: Lakshmy]
    * [ ] In airport
       * [ ] Provide weather information 
       * [ ] Provide next gate information
       * [ ] Time gap between landing to next gate
       * [ ] provide weather information, 
       * [ ] provide baggage information
  * [ ] Provide users will additional help needed along the way[Owner: Ada]
    * [ ] Include translation as needed [Owner: Ada]
   

The following **optional** features are implemented:
  * [ ] Barometric pressure changes - provide exercises to pop ears [stretch]
  * [ ] Manually allow users to input travel information [Owner: Lakshmy] 

The following **bonus** features are implemented:

* [ ] Yelp with top restaurants in the airport 

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
