import com.sun.istack.internal.NotNull;
import datamodels.ArticlesResponse;
import datamodels.SourcesResponse;
import enums.Categories;
import enums.Countries;
import enums.Languages;
import enums.SortBy;
import io.reactivex.Flowable;


public class NewsApiRequestFactory {

    private NetworkModule networkModule;

    /**
     *
     * @param apiKey
     */
    public NewsApiRequestFactory(@NotNull String apiKey){
        networkModule = new NetworkModule(apiKey);
    }

    /**
     *
     * @param apiKey
     * @param cacheMaxSize
     * @param cacheMaxAgeSeconds
     * @param readTimeoutSeconds
     * @param writeTimeoutSeconds
     */
    public NewsApiRequestFactory(@NotNull String apiKey, int cacheMaxSize, int cacheMaxAgeSeconds, int readTimeoutSeconds, int writeTimeoutSeconds){
        networkModule = new NetworkModule(apiKey,cacheMaxSize,cacheMaxAgeSeconds,readTimeoutSeconds,writeTimeoutSeconds);
    }

    /**
     *
     * @param category
     * @param country
     * @param q
     * @param pageSize
     * @param page
     * @return
     */
    public Flowable<ArticlesResponse> getTopHeadlinesRequest(Categories category, Countries country, String q, int pageSize, int page) {
        return networkModule.getNewsApi().getTopHeadlines(category == null ? null : category.name(), country==null ? null : country.name(), null, q, pageSize, page);
    }

    /**
     *
     * @param sources
     * @param q
     * @param pageSize
     * @param page
     * @return
     */
    public Flowable<ArticlesResponse> getTopHeadlinesRequest(String sources, String q, int pageSize, int page) {
        return networkModule.getNewsApi().getTopHeadlines(null, null, sources, q, pageSize, page);
    }

    /**
     *
     * @param q
     * @param sources
     * @param domains
     * @param from
     * @param to
     * @param language
     * @param sortBy
     * @param pageSize
     * @param page
     * @return
     */
    public Flowable<ArticlesResponse> getEverythingRequest(String q, String sources, String domains, String from, String to, Languages language, SortBy sortBy, int pageSize, int page){
        return networkModule.getNewsApi().getEverything(q,sources,domains,from,to,language==null ? null : language.name(),sortBy == null ? null : sortBy.name(),pageSize,page);
    }

    /**
     *
     * @param category
     * @param language
     * @param country
     * @return
     */
    public Flowable<SourcesResponse> getSourcesRequest(Categories category, Languages language, Countries country) {
        return networkModule.getNewsApi().getSources(category == null ? null : category.name(), language==null ? null : language.name(), country==null ? null : country.name());
    }

}
