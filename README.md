# CS307_project1

<details id=1>

<summary><h2>Task1: E-R Diagram(15%)</h2></summary>

- [ ] Make an E-R Diagram of your database design with any diagram software
 </details>

<details id=2>

<summary><h2>Task2: Database Design (25%) </h2></summary>


### **Target:**

- [ ] Design the tables and columns
- [ ] generate the database diagram via the “Show Visualization”feature of DataGrip and embed a snapshot or a vector graphics into your report
- [ ] describe the design of the tables and columns including (but not
limited to) the meanings of tables and columns. 
- [ ] submit an SQL file as an attachment that contains the
DDLs (create table statements) for all the tables you created



### **Detail:**

1. All data items should base on the file shipment_records.csv.
2. Your design needs to follow the requirements of the three normal forms.
3. Use a primary key and foreign key(s) to indicate important attributes
and relationships about your data.
4. Every row in each table should be uniquely identified by its primary
key. (You may use a simple or a composite primary key.)
5. Every table should be involved in a foreign key. Isolated table is NOT
allowed.
6. Your design shall NOT contain circular foreign key links. （表之间的外键方向不能成环。例如：A 表有外键关联 B 表，B 表有外健关联 C 表，
C 表有外健关联 A 表，这是不允许的。）
7. Each table should contain at least one mandatory (“Not Null”) column
(including the primary key but not the ID column).
8. Other than the system-generated self-increment ID column, there
should be at least one column with the “unique” constraint.
9. You should use appropriate data types for different fields.
10. Your design should be easy to expand if requirements changed.
    

</details>


<details id=3>

<summary><h2> Task 3: Data Import (25%=15%+10%)</h2></summary>

### **Target:**
- [ ] Write a script to import the data file
- [ ] Write a description in your report of how your script import data. You
should clearly state the steps, necessary prerequisites, and cautions to
run it and import data correctly.
- [ ] (Advanced)Find more than one way to import data and provide a comparative
analysis of the computational efficiencies between these ways.
- [ ] (Advanced)Try to optimize your script. Describe how you optimized it and analyze
how fast it is compared with your original script.
### **Detail:**
For the advanced tasks, please make sure to describe your test environment,
procedures, and actual time costs. You are required to write a paragraph or two
to analyze the experiment results. You may refer to the requirements for
reporting experimental results in Task 4 for details.
</details>

<details id=4>
<summary><h2>Task 4: Compare DBMS with File I/O (Basics:20%)</h2></summary>

### **Target:**
- [ ] Benchmarking with database APIs
- [ ] Benchmarking with file APIs
- [ ] Comparative analysis
### **Detail:**
1. Benchmarking with database APIs: Based on the database you created,
you are required to write a program in Java that accesses the database
via database APIs and contains a series of INSERT, DELETE, UPDATE,
and SELECT statements. You may specify the number of statements in
each type on your own and decide which data to be modified and read.
However, the operations of each statement type cannot be too small to fail illustrating the strength and weakness of database APIs over file
I/O (depending on the programming language, tens of thousands of
records each would usually be enough). Finally, you need to record the
running time of each statement type or each statement. Here, we provide
some typical test descriptions in database you can refer to:
a) INSERT: First, randomly drop out some rows of the csv file and
import it into your database. Then evaluate the time cost of
importing rest of the rows of the csv file.
b) DELETE: First, import all data of the csv file into your database.
Then, evaluate the time cost of deleting arbitrarily chosen rows of
your database’s delivery record table.
c) UPDATE: First, import all data of the csv file into your database.
Then, evaluate the time cost of update all the EMPTY values of
your database’s delivery record table to arbitrary values.
d) SELECT: First, import all data of the csv file into your database.
Then, evaluate the time cost of finding all delivery records that
have not finished or finding all the delivery records that had been
packed by an arbitrary container, etc. You may pay more attention
on SELECT statement tests since it is used more frequently than
other ones in many real-world scenarios.
2. Benchmarking with file APIs: This step is designed to replicate all your
operations in the first step, but via a generic programming language’s
standard file APIs. First, create file(s) that store(s) the same data as
you have in the tables in the DBMS. Then, write a program to insert,
delete, update, and find the data items as you did in the SQL statements
and queries. Be sure that the file operations (and the number of
operations) are identical to the SQL operations (and the number of the
statements). Finally, record the running time of each operation (type)
as in database API benchmarks.
3. Comparative analysis: Compare the recorded running time of the same
operation/statement from the DBMS and the file, respectively. You may
conduct comparisons from multiple levels, such as comparing statements
with corresponding operations (statement-level) or comparing the total
time of all statements in a specific type with the corresponding
operation type (type-level).
1. A description of your test environment, including (but not limited to):
a. Hardware specification, including the CPU model, size of memory,
whether you are using a solid-state disk (SSD) or hard disk drive
(HDD), how fast your disk is (sequential and random).
7 / 8
b. Software specification, including the version of your DBMS and
operating system, the programming language you choose, and the
development environment (the version of the language, the specific
version of the compilers and libraries, etc.).
c. When reporting the environment, you can think about this question:
if someone else is going to replicate your experiment, what
necessary information should be provided for him/her?
2. A specification of how you organize the test data in the DBMS and the
data file, including how do you generate the test SQL statements and
what data format/structure of the files are.
3. A description of your test SQL script and the source code of your
program. DO NOT copy and paste the entire script and the program
in the report. Instead, please submit source codes as attachments.
4. A comparative study of the running time for the corresponding
statements/operations. You are encouraged to use data visualization to
present the results. Be sure to use consistent style for graphics, tables,
etc. Besides a list/figure of the running time, you are required to
describe the major differences with respect to running performance,
what you find interesting in the results, what insights you may show to
other people in the experiments, etc.
Some notes on how to finish this task in a better way:
1. You can perform the above benchmarks with different orders of
magnitude of statements/operations (e.g., from hundreds to thousands
to ten(s) of thousand(s)).
2. You can choose or design any format you want to store data in the file,
such as plain text formats (CSV, JSON, XML, etc.) or a self-defined
binary format.
3. Please only stick to standard file APIs, i.e., the java.io in Java. The
only exception is that if you choose to use JSON and XML, you may
utilize third-party JSON/XML libraries if standard library does not
provide it, e.g., Gson.
2
4. We acknowledge that there are numerous libraries that can facilitate
the data manipulation works or even speed up the performance of
insertions and selections significantly (e.g., pandas in Python). You are
encouraged to also compare the performance of these libraries with
DBMS. However, you should conduct the analysis of DBMS vs.
standard file APIs beforehand
5. Some useful resources:
a. Advantage of database management system over file system
b. Advantages of Database Management System
c. Characteristics and benefits of a database






