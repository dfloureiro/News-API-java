News API Java wrapper

Gradle:
Step 1: Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
Step 2: Add the dependency

	dependencies {
	        compile 'com.github.dfloureiro:greves:-SNAPSHOT'
	}

Maven:
Step 1:
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
  
Step 2. Add the dependency

	<dependency>
	    <groupId>com.github.dfloureiro</groupId>
	    <artifactId>greves</artifactId>
	    <version>-SNAPSHOT</version>
	</dependency>
