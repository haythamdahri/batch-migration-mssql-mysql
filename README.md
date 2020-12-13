# batch-migration-mssql-mysql
Migrate Data From SQL Server To MySQL Database Using Spring Batch

# How To Run Project
1- Required tools:
`Java 11` `Maven`

2- Package project: 
```console
mvn clean package
```
3- Copy generated jar from target into Artifact directory: 
```console
cp target/*.jar Artifact/migration-batch.jar
```
4- Update databases connection credentials and hosts:
```console
vi application.properties 
```
5- Run batch processing: 
```console
cd Artifact
chmod +x runner.sh
./runner.sh
```

## Author
- [Haytham DAHRI](https://www.github.com/haythamdahri)
- [LinkedIn](https://www.linkedin.com/in/haytham-dahri/)
