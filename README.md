...TBD


mvn deploy:deploy-file -Durl=file:///c/dev/workspaces/my_sts_default/editor/repo -Dfile=/c/Users/Rick/.m2/repository/com/mgarin/weblaf/1.27/weblaf-1.27.jar -DgroupId=com.mgarin -DartifactId=weblaf -Dpackaging=jar -Dversion=1.27 
Because I was using a file path that was inside my local repo, Maven would not allow it...
... so I copied it to a local directory and ran the following:
cp /c/Users/Rick/.m2/repository/com/mgarin/weblaf/1.27/weblaf-1.27.jar .
cp /c/Users/Rick/.m2/repository/net/sourceforge/weblaf/1.2/naplaf-1.2.jar .
[A]
mvn deploy:deploy-file -Durl=file://c/dev/workspaces/my_sts_default/editor/repo -Dfile=weblaf-1.27.jar -DgroupId=com.mgarin -DartifactId=weblaf -Dpackaging=jar -Dversion=1.27 
mvn deploy:deploy-file -Durl=file://c/dev/workspaces/my_sts_default/editor/repo -Dfile=napkinlaf-1.2.jar -DgroupId=net.sourceforge.napkinlaf -DartifactId=napkinlaf -Dpackaging=jar -Dversion=1.2 

