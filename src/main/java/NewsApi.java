import datamodels.ArticlesResponse;
import datamodels.SourcesResponse;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
    @GET("v2/sources")
    Flowable<SourcesResponse> getSources(@Query("category") String category, @Query("language") String language, @Query("country") String country);

    @GET("/v2/everything")
    Flowable<ArticlesResponse> getEverything(@Query("q") String q, @Query("sources") String sources, @Query("domains") String domains, @Query("from") String from,
                                             @Query("to") String to, @Query("language") String language, @Query("sortBy") String sortBy,
                                             @Query("pageSize") int pageSize, @Query("page") int page);

    @GET("/v2/top-headlines")
    Flowable<ArticlesResponse> getTopHeadlines(@Query("category") String category, @Query("country") String country,
                                               @Query("sources") String sources, @Query("q") String q, @Query("pagesize") int pageSize,
                                               @Query("page") int page);
}
