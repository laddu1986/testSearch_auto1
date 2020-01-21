[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ekorab_qa_challenge&metric=alert_status)](https://sonarcloud.io/dashboard?id=ekorab_qa_challenge)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=ekorab_qa_challenge&metric=bugs)](https://sonarcloud.io/dashboard?id=ekorab_qa_challenge)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=ekorab_qa_challenge&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=ekorab_qa_challenge)

## Task 2: Create automation test for search functionality
##### Description: Search is a crucial part of all Auto1 platforms and Automation testing is essential part of software development in Auto Group. Please create automation test which will do the following.
Upload solution to github.com: (You are free to use any programming language or any
framework for this task. Please do not use any record-pay tools)

1. Open https://www.autohero.com/de/search/
2. Filter for First registration (Erstzulassung). Filter for FROM 2015
3. Apply Filter
4. Sort cars by Price Descending (Höchster Preis)
5. Verify all cars are filtered by first registration (2015+)
6. Verify all cars are sorted by price descending


![Demo](https://github.com/ekorab/qa_challenge/blob/master/demo.gif)

Automation done with Selenium JUnit, AssertJ 
Setup: 
1. Path to ChromeDriver can be found in TestBase
