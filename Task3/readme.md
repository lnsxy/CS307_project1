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

快递员的年龄在变，应为年龄+（当前年份-递送年份) 但估计会有年龄+1-1的情况
因此当前年龄挪到retrival/delivery里面

# Using way1 
create_table.sql following task2.

load.sql create a temporary table named test, then copy the data in csv to test, and select the data in test to insert into the tables.

You should delete the first line in the csv file and substitute the path in load.sql('/home/kali/data.csv') to your actual path(absolute path).