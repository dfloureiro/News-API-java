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
	        compile 'com.github.dfloureiro:News-API-java:v1.2'
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
	    <artifactId>News-API-java</artifactId>
	    <version>v1.2</version>
	</dependency>


Usage example:

       	new NewsApiRequestFactory("myApiKey")
                .getTopHeadlinesRequest(Categories.general, Countries.pt,null,20,0)
                .subscribeOn(Schedulers.io())
        	.map(ArticlesResponse::getArticles)
                .subscribe(articles -> {
                    for(Article article : articles)
                            System.out.print(article.getTitle()+"\n");
                        }
                ,throwable -> System.out.print(throwable.getMessage()));
