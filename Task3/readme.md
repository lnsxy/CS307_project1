# way1
First use copy to copy the whole table, like **copy test from '/home/kali/Desktop/fuck.csv' csv**;

In this case, you must ensure that you have the access permission of the csv file.
Also, the column and the data type should be the same with the csv file.
Remember to delete the first line!

Then use sql sentence like **insert into test2 (select c1,c2 from test) is ok**;

# way2
use java to generate some insert sql sentence and execute.

## optimize 
use copy to execute insert,
add foreign key and constraint later
多值insert
https://blog.csdn.net/pg_hgdb/article/details/79077852