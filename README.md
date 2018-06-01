...TBD

```
mvn deploy:deploy-file -Durl=file:///c/dev/workspaces/my_sts_default/editor/repo -Dfile=/c/Users/Rick/.m2/repository/com/mgarin/weblaf/1.27/weblaf-1.27.jar -DgroupId=com.mgarin -DartifactId=weblaf -Dpackaging=jar -Dversion=1.27 
Because I was using a file path that was inside my local repo, Maven would not allow it...
... so I copied it to a local directory and ran the following:
cp /c/Users/Rick/.m2/repository/com/mgarin/weblaf/1.27/weblaf-1.27.jar .
cp /c/Users/Rick/.m2/repository/net/sourceforge/weblaf/1.2/naplaf-1.2.jar .
[A]
mvn deploy:deploy-file -Durl=file://c/dev/workspaces/my_sts_default/editor/repo -Dfile=weblaf-1.27.jar -DgroupId=com.mgarin -DartifactId=weblaf -Dpackaging=jar -Dversion=1.27 
mvn deploy:deploy-file -Durl=file://c/dev/workspaces/my_sts_default/editor/repo -Dfile=napkinlaf-1.2.jar -DgroupId=net.sourceforge.napkinlaf -DartifactId=napkinlaf -Dpackaging=jar -Dversion=1.2 
```

Add these to pom.xml if necessary:
```
	<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core (2.9.5 is the latest) -->
	<dependency>
	    <groupId>com.fasterxml.jackson.core</groupId>
	    <artifactId>jackson-core</artifactId>
	    <version>2.6.3</version>
	</dependency>

	<dependency>
	    <groupId>com.fasterxml.jackson.core</groupId>
	    <artifactId>jackson-databind</artifactId>
	    <version>2.6.3</version>
	</dependency>
```
