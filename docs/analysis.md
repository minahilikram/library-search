## Limitations

- duplicate titles and/or publishers or organizations are accepted
- year searched will only contain numbers, search results will not be correct unless the year is either not entered or contains only numbers
- title searched entered will be spelt properly and separated by spaces, order does not matter
- all the title keywords searched for have to match an entry in the list in order to get a result, e.g. if “Mona is awesome” is entered then all titles that contain all three of the words will return positive
- search query will have to match all three inputs unless nothing is entered e.g. if all three do not match the results will be negative
- call number and year must be unique for each entry, e.g. a book and journal may not have an entry with identical call number and year
- File IO must be the format provided in order to read in references properly with a newline between entries,
> e.g. [reference]
type=journal
callNumber=3131s
title=book of computers
organization=""
year= 1996

- if the file provided has duplicates, this will be accepted and there will remain duplicates of those entries
- File IO writes and reads from the same file
- any known further assumption(s) and/or limitation(s) are reported in LibrarySearch > testing.pdf
