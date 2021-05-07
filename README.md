# COMP62542: Pattern-Based Software Development
# Team-5 
# Online Booking System (Fly Away)

INTRODUCTION
------------

A Java-based e-commerce application using at least 4 design patterns, plus the MVC (Model View Controller) architectural pattern.

REQUIREMENTS
------------

* No special requirements are needed.


INSTALLATION
------------

* You will need an IDE (integrated development environment).

    - We recommend you use the IntelliJ IDEA (It's very powerful to support your spring web application development and integration of many technical frameworks).

* You will need to download JDK.

* You will need to download and install MySQL.

CONFIGURATION
-------------

* You will need to configure the database in MySQL.

    - You will need to create a new database called "Flights".

    - You will need set the database username: "root" and password: "12345678"

    - Run the "tables.sql" file to create three tables (Ticket, Customer, Booking).
      
    - Run the "allFlightInformation.sql" file to creat the "Flight" table and populate it with all the flight information (There are many flights/information so it might take some extra time to complete).
        
    - You should finally have one database(Flights) with four tables (Flight, Ticket, Customer, Booking) and the table Flight filled with flight information.

TROUBLESHOOTING
---------------

* If http://localhost:8080/ does not load, check the following:

    - Make sure your MySQL is up and running.

    - Make sure your MySQL username:root and password:12345678.

    - Reload page (Right click pom.xml, Select Maven and Reload project). 

FAQ
---

Q: I enabled "Aggregate and compress CSS files", but admin_menu.css is still
there. Is this normal?

A: Yes, this is the intended behavior. the administration menu module only loads
its stylesheet as needed (i.e., on page requests by logged-on, administrative
users).

MAINTAINERS
-----------

Current maintainers:
* Polina Fokicheva (ID: 10755545)
* Shubham Rana (ID: 10773467)
* Pouya Mohammadi (ID: 10663656)
* Christos Kardambikis (ID: 10800393)