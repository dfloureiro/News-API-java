# News API SDK for Java
This is a Java API Wrapper SDK for the https://newsapi.org. Go to the website to get your apiKey.

## How to import:
### Gradle:
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
Add the dependency:

	dependencies {
	        compile 'com.github.dfloureiro:News-API-java:v1.2'
	}

### Maven:

	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
  
Add the dependency:

	<dependency>
	    <groupId>com.github.dfloureiro</groupId>
	    <artifactId>News-API-java</artifactId>
	    <version>v1.2</version>
	</dependency>


### How to use examples:
Create a instance of NewsApiRequestFactory with default values:

        NewsApiRequestFactory newsApiRequestFactory = new NewsApiRequestFactory("apiKey");

or with you custom values:

        NewsApiRequestFactory newsApiRequestFactory = new NewsApiRequestFactory("apiKey",NetworkModule.DEFAULT_CACHE_MAX_SIZE, NetworkModule.DEFAULT_CACHE_MAX_AGE, NetworkModule.DEFAULT_READ_TIMEOUT_SECONDS, NetworkModule.DEFAULT_WRITE_TIMEOUT_SECONDS);

Get top headlines by category/country/query:

            newsApiRequestFactory.getTopHeadlinesRequest(Categories.general, Countries.pt, "trump", 20, 0)
                    .subscribeOn(Schedulers.io())
                    .map(ArticlesResponse::getArticles)
                    .subscribe(articles -> {
                                for (Article article : articles)
                                    System.out.print(article.getTitle() + "\n");
                            }
                            , throwable -> System.out.print(throwable.getMessage()));
		
Get top headlines by sources/query:

            newsApiRequestFactory.getTopHeadlinesRequest("bbc-news", "trump", 20, 0)
                    .subscribeOn(Schedulers.io())
                    .map(ArticlesResponse::getArticles)
                    .subscribe(articles -> {
                                for (Article article : articles)
                                    System.out.print(article.getTitle() + "\n");
                            }
                            , throwable -> System.out.print(throwable.getMessage()));

Search articles from the full repo with everything:

            newsApiRequestFactory.getEverythingRequest("trump","bbc-news",null,"2018-01-01", "2018-02-11", Languages.en, SortBy.popularity,20,0)
                    .subscribeOn(Schedulers.io())
                    .map(ArticlesResponse::getArticles)
                    .subscribe(articles -> {
                                for (Article article : articles)
                                    System.out.print(article.getTitle() + "\n");
                            }
                            , throwable -> System.out.print(throwable.getMessage()));

Get available sources:

            newsApiRequestFactory.getSourcesRequest(Categories.technology,Languages.pt,Countries.pt)
                    .subscribeOn(Schedulers.io())
                    .map(SourcesResponse::getSources)
                    .subscribe(sources -> {
                                for (Source source : sources)
                                    System.out.print(source.getName() + "\n");
                            }
                            , throwable -> System.out.print(throwable.getMessage()));
			

### Used techologies:
#### RETROFIT 2
https://github.com/square/retrofit/tree/master/retrofit-adapters/rxjava2
#### GSON
https://github.com/square/retrofit/tree/master/retrofit-converters/gson
#### RXJAVA 2
https://github.com/ReactiveX/RxJava
