# Group Project - *Travel Exotikos*

**Travel Exotikos** is an android app that allows a user to have a travel companion who helps you while traversing through confusing airports and travel stops. The app uses the airline api https://developer.flightstats.com/api-docs/ for getting airport and travel data.

Time spent: **4x** hours spent in total for wireframes

## User Stories

The following **required** functionality is completed:

* [x] Way to input information 
  * [x] Bar code scanner [Owner: Ada]
    * [x] APIs to use the bar code scanner - library https://pdf417.mobi/ [Owner: Ada]
    * [x] What information is obtained from the QR code  [Owner: Ada]
    * [x] Use https://shaun.net/whats-contained-in-a-boarding-pass-barcode/ to obtain QR code scanning
       * [x] Name
       * [x] booking  reference???,
       * [x] from-to-airline need dictionary/service to read this. ATLMEMDL=from Atlanta to Memphis, Delta
       * [x] Flight #
       * [x] Departure day 006 = 06JAN Julian format
       * [x] Cabin Y-economy, F-first, J-business
       * [x] Seat # 
  * [X] Manually provide the information [Owner: Lakshmy] **Optional**
* [x] Get the native clock and provide push notification for when the travel is imminent and in progress
  * [x] Travel start [Owner: Jesus]
    * [x] Upcoming flights list view [Owner: Jesus]
    * [x] Flight detail activity
       * [x] Fragment - You will flight in X days
       * [x] Fragment - Your flight will depart in XX hours, head to {AirportName}
       * [x] [Actions] Give me directions
       * [x] [Checks section] Before you leave
       * [x] Luggage
       * [ ] Visas ** Stretch **
       * [ ] Medication ** Stretch **
  * [x] In Progress [Owner: Lakshmy]
    * [x] In flight [Owner: Lakshmy]
      * [x] Fragment - details on the next leg of the journey
       * [ ] In flightCountry
       * [ ] Time zone
       * [ ] Language
       * [ ] Details of the next airport (shops, etc) [Stretch]
     * [x] Fragment - inflight exercises
       * [x] Leg exercises
       * [x] Back exercises
       * [x] In airport [Owner: Ada]
  * [X] Security Checkin [Owner: Lakshmy]
    * [X] In airport
       * [X] Provide video on checkin
       * [X] Provide information on prohibited items
       * [X] Provide information on what needs to be done for security
       * [X] Provide webpage on both TSA security and prohibited items
  * [X] Destination [Owner: Lakshmy]
    * [X] In airport
       * [X] Provide next gate information
       * [X] Time gap between landing to next gate
       * [X] provide baggage information
  * [x] Provide users will additional help needed along the way[Owner: Ada]
    * [x] Include translation as needed [Owner: Ada]
   

The following **optional** features are implemented:
  * [ ] Barometric pressure changes - provide exercises to pop ears [stretch]
  * [x] Manually allow users to input travel information [Owner: Lakshmy] 


The following **additional** features are implemented:

* [ ] List anything else that you can get done to improve the app functionality!

## Video Walkthrough

Here's a walkthrough of implemented user stories:

![Video Walkthrough](https://github.com/travelExotikos/Exotikos/blob/master/wireframes/bullheadNBD90Wada11212016155408.gif?raw=true)

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
