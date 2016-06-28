# FindAHotel

A Project Emphasizing on Data Load/Warehousing from different sources to different sink.

For the simplicity, the project aims at reading some data points using some custom query from a source (Mysql) and dumping it to any specific Storage/Persistance(currently Mysql but could be extended to various possible databases).

Simple pass the configuration in the app.properties ->

db.url= source mysql url to read data from
db.username = credentials
db.password = credentials
temp.path = path to store temporary files
source.query.file = source file to query data from

mysql.db.url = destination mysql url
mysql.db.username = credentials
mysql.db.password = credentials



[source.query.file] should have following format 

1) OutputDatabaseName "."(without quotes) OutputTableName
2) Output Destination Type(currently "mysql")
3) Query to source data

Precisely something,

test.findthem
mysql
select h.place_id...
...
...
...
testN.findthemN
mysql
select h.place_idN...


To run the Project, simply run com.findhotel.dwh.Invoker with the correct configuration in the app.properties file
