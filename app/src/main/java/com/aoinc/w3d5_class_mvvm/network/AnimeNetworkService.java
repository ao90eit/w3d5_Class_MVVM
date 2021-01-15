package com.aoinc.w3d5_class_mvvm.network;

import com.aoinc.w3d5_class_mvvm.model.JikanResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.aoinc.w3d5_class_mvvm.util.Constants.*;

public interface AnimeNetworkService {

    @GET(PATH)
    public Call<JikanResponse> searchAnime(@Query(SEARCH_QUERY) String searchQuery);
}
