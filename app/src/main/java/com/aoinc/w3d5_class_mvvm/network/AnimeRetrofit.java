package com.aoinc.w3d5_class_mvvm.network;

import com.aoinc.w3d5_class_mvvm.model.JikanResponse;
import com.aoinc.w3d5_class_mvvm.util.Constants;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnimeRetrofit {

    /* Singleton 101 (Java)
    1 - make constructor private
    2 - create an instance variable (private static)
    3 - create a getter that checks if the instance is null and only instantiates it if null
     */

    private static AnimeRetrofit animeRetrofitSingleton = null;
    private AnimeNetworkService animeNetworkService;

    public static AnimeRetrofit getAnimeRetrofitSingleton() {
        if (animeRetrofitSingleton == null) {
            animeRetrofitSingleton = new AnimeRetrofit();
        }

        return animeRetrofitSingleton;
    }

    private AnimeRetrofit() {
        animeNetworkService = createAnimeService(createRetrofit());
    }

    private AnimeNetworkService createAnimeService(Retrofit retrofit) {
        return retrofit.create(AnimeNetworkService.class);
    }

    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Call<JikanResponse> getSearchResults(String searchQuery) {
        return animeNetworkService.searchAnime(searchQuery);
    }
}
