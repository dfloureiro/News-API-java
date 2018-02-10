import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import datamodels.ArticlesResponse;
import datamodels.SourcesResponse;
import enums.Categories;
import enums.Countries;
import enums.Languages;
import enums.SortBy;
import io.reactivex.Flowable;

/**
 * News Api Request Factory, and API wrapper for https://newsapi.org
 *
 * @author Diogo Loureiro
 */
public class NewsApiRequestFactory {

    private NetworkModule networkModule;

    /**
     * NewsApiRequestFactory constructor with default values
     *
     * @param apiKey your news api key
     */
    public NewsApiRequestFactory(@NotNull String apiKey) {
        networkModule = new NetworkModule(apiKey);
    }

    /**
     * NewsApiRequestFactory constructor with customized values
     *
     * @param apiKey              your news api key
     * @param cacheMaxSize        max size for cache
     * @param cacheMaxAgeSeconds  max age for cache in seconds
     * @param readTimeoutSeconds  max read timeout in seconds
     * @param writeTimeoutSeconds max write timeout in seconds
     */
    public NewsApiRequestFactory(@NotNull String apiKey, int cacheMaxSize, int cacheMaxAgeSeconds, int readTimeoutSeconds, int writeTimeoutSeconds) {
        networkModule = new NetworkModule(apiKey, cacheMaxSize, cacheMaxAgeSeconds, readTimeoutSeconds, writeTimeoutSeconds);
    }

    /**
     * Top Headlines Request by country, category and/or query string
     *
     * @param category category
     * @param country  country
     * @param q        query
     * @param pageSize page size
     * @param page     page to read
     * @return top headlines request flowable to subscribe
     */
    public Flowable<ArticlesResponse> getTopHeadlinesRequest(@Nullable Categories category, @Nullable Countries country, @Nullable String q, int pageSize, int page) {
        return networkModule.getNewsApi().getTopHeadlines(category == null ? null : category.name(), country == null ? null : country.name(), null, q, pageSize, page);
    }

    /**
     * Top Headlines Request by sources and/or query string
     *
     * @param sources  comma-separated string of identifiers for the sources
     * @param q        query
     * @param pageSize page size
     * @param page     page to read
     * @return top headlines request flowable to subscribe
     */
    public Flowable<ArticlesResponse> getTopHeadlinesRequest(@Nullable String sources, @Nullable String q, int pageSize, int page) {
        return networkModule.getNewsApi().getTopHeadlines(null, null, sources, q, pageSize, page);
    }

    /**
     * Everything search Request, sortable, and queried by sources, domains, date, language and/or query string
     *
     * @param q        query URL-encoded
     * @param sources  comma-separated string of identifiers for the sources
     * @param domains  A comma-separated string of domains
     * @param from     initial date to filter ISO 8601 format
     * @param to       end date to filter ISO 8601 format
     * @param language language
     * @param sortBy   sort by
     * @param pageSize page size
     * @param page     page to read
     * @return everything request flowable to subscribe
     */
    public Flowable<ArticlesResponse> getEverythingRequest(@Nullable String q, @Nullable String sources, @Nullable String domains, @Nullable String from, @Nullable String to, @Nullable
            Languages language, @Nullable SortBy sortBy, int pageSize, int page) {
        return networkModule.getNewsApi().getEverything(q, sources, domains, from, to, language == null ? null : language.name(), sortBy == null ? null : sortBy.name(), pageSize, page);
    }

    /**
     * Sources Request by category, country and/or language
     *
     * @param category category (default = all)
     * @param language language (default = all)
     * @param country  country (default = all)
     * @return sources request flowable to subscribe
     */
    public Flowable<SourcesResponse> getSourcesRequest(@Nullable Categories category, @Nullable Languages language, @Nullable Countries country) {
        return networkModule.getNewsApi().getSources(category == null ? null : category.name(), language == null ? null : language.name(), country == null ? null : country.name());
    }

}
