# space-invaders

A Java application to detect if a specific invader pattern is present in a radar image. 

### Technologies

- Java 8
- Maven
- Lombok Plugin
- Log4j
- JUnit 

### How to start

Once the application is fetched from git it can be built with maven
~~~~
mvn clean install
~~~~
This will fetch dependencies and run all tests

To run the app execute:
~~~~
mvn exec:java
~~~~

### Functionallity

The program will read the different invaders from resource folder and try to detect them in the radar image. This was done using brute force matching. The algorithm reads each coordinate from radar image and then will extract a sub image with the same dimensions than the current invader. In case that both images are similar, the application mark that invader as detected. 
The result will be a list of invaders detected with their specific position in the radar.

### Known space invaders
~~~~
--o-----o--
---o---o---
--ooooooo--
-oo-ooo-oo-
ooooooooooo
o-ooooooo-o
o-o-----o-o
---oo-oo---
~~~~

~~~~
---oo---
--oooo--
-oooooo-
oo-oo-oo
oooooooo
--o--o--
-o-oo-o-
o-o--o-o
~~~~

### Considerations

- Assuming that the noise in the channel (interference) cant change any value in the radar image. Otherwise, it will be necessary count the number of coincidences and use an accuracy as a filter
- Invaders can share coordinates in the same radar image
- Some testing images were created for unit testing
