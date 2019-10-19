# Java Assignment 2
## Booking System

This is a assignment written in Java using Java EE concepts.

### Program description 
This program is to be a boking system for a sky diving company. It will have a view for the customer and a view for the company. The customer should be able to see available session for a specific day and book one or more places in that session if available. The company should be able to see all the session for the day/week/next week and be able to change the places available if customer book in person. The company can get a report for the last week of sessions.  

### Requirement specifications  
To run the program, you need have a local server running and import the skydiving.slq file to a sql database. I used phpMyAdmin running on a local WAMP server.  The connection is setup for username: "root" and password: "" if different It needs to be changed in file Servant.java line 18 and 19.  The Company.java must be run first and shutdown last as this opens and closes the registry. 
