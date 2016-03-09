# Overview

The program has a menu of options to choose from; add, search and/or quit. I added elements, and hand walked through the nested loops in the search method to make sure the algorithm works the way it is suppose to. After entries were added I checked to see if all were present by printing the array. File IO reads and write to the same file and does not accept command line.

## Tests

| TESTS |RESULTS | FIX |
| ------------------ |--------------------| -------------------|
| search method; if characters are entered instead of numbers for year | accepts input, returns no results found | check year string if the input entered contains numbers only |
| search method; if numbers and characters are entered e.g. 9000a or 9000­9999a or ­9000a or 9000a | program crashes | validate input |
| add method; if characters such as “!@#$%^&*()_+­={}[]|\:”<>?;’,./” are added | accepts input and saves into corresponding field | validate input | 
| add method; authors are added to arraylist with separated by “,”, if anything else entered | takes everything as one author | validate input |
| If a file contains entries that are specific as mentioned in the README | program crashes | validate |

## Limitations

- duplicate titles and/or publishers or organizations are accepted
- year searched will only contain numbers, search results will not be correct unless the year is either not entered or contains only numbers
- title searched entered will be spelt properly and separated by spaces, order does not matter
- all the title keywords searched for have to match an entry in the list in order to get a result, e.g. if “Mona is awesome” is entered then all titles that contain all three of the words will return positive
- search query will have to match all three inputs unless nothing is entered e.g. if all three do not match the results will be negative
- call number and year must be unique for each entry, e.g. a book and journal may not have an entry with identical call number and year
- File IO must be the format provided in order to read in references properly with a newline between entries,

| example |
| :------------------: |
| [reference]  |
| type=journal |
| callNumber=3131s  |
| title=book of computer |
| organization="" |
| year= 1996 |

- if the file provided has duplicates, this will be accepted and there will remain duplicates of those entries
- File IO writes and reads from the same file
