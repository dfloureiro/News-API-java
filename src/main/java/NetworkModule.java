import io.reactivex.annotations.NonNull;
import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

class NetworkModule {

    private final static String BASE_URL = "https://newsapi.org/";
    public final static int DEFAULT_CACHE_MAX_SIZE = 10 * 1024 * 1024;  // 10 MiB
    public final static int DEFAULT_CACHE_MAX_AGE = 120;
    public final static int DEFAULT_READ_TIMEOUT_SECONDS = 45;
    public final static int DEFAULT_WRITE_TIMEOUT_SECONDS = 45;

    private NewsApi newsApi;
    private int cacheMaxSize;
    private int cacheMaxAgeSeconds;
    private int readTimeoutSeconds;
    private int writeTimeoutSeconds;

    private static String API_KEY;

    NetworkModule(@NonNull String apiKey) {
        API_KEY = apiKey;
        this.cacheMaxSize = DEFAULT_CACHE_MAX_SIZE;
        this.cacheMaxAgeSeconds = DEFAULT_CACHE_MAX_AGE;
        this.readTimeoutSeconds = DEFAULT_READ_TIMEOUT_SECONDS;
        this.writeTimeoutSeconds = DEFAULT_WRITE_TIMEOUT_SECONDS;
        setRetrofit();
    }

    NetworkModule(String apiKey, int cacheMaxSize, int cacheMaxAgeSeconds, int readTimeoutSeconds, int writeTimeoutSeconds) {
        API_KEY = apiKey;
        this.cacheMaxSize = cacheMaxSize;
        this.cacheMaxAgeSeconds = cacheMaxAgeSeconds;
        this.readTimeoutSeconds = readTimeoutSeconds;
        this.writeTimeoutSeconds = writeTimeoutSeconds;
        setRetrofit();
    }

    private Interceptor provideCacheInterceptor() {
        return chain -> {
            Response response = chain.proceed(chain.request());
            CacheControl cacheControl = new CacheControl.Builder().maxAge(cacheMaxAgeSeconds, TimeUnit.SECONDS)
                    .build();
            return response.newBuilder()
                    .header("Cache-Control", cacheControl.toString())
                    .build();
        };
    }

    private Interceptor provideApiKeyInterceptor() {
        return chain -> {
            Request request = chain.request().newBuilder().addHeader("X-Api-Key", API_KEY).build();
            return chain.proceed(request);
        };
    }

    private void setRetrofit() {
        newsApi = new Retrofit.Builder().baseUrl(BASE_URL)
                .client(getDefaultClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(NewsApi.class);
    }

    private OkHttpClient getDefaultClient() {
        final OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.addNetworkInterceptor(provideCacheInterceptor());
        okHttpClientBuilder.addInterceptor(provideApiKeyInterceptor());
        okHttpClientBuilder.readTimeout(readTimeoutSeconds, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(writeTimeoutSeconds, TimeUnit.SECONDS);
        okHttpClientBuilder.cache(new Cache(new File("/"), cacheMaxSize));
        return okHttpClientBuilder.build();
    }

    NewsApi getNewsApi() {
        return newsApi;
    }
}
