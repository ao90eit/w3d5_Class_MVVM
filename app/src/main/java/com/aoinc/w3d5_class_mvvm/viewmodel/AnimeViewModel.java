package com.aoinc.w3d5_class_mvvm.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ReportFragment;
import androidx.lifecycle.ViewModel;

import com.aoinc.w3d5_class_mvvm.model.AnimeResult;
import com.aoinc.w3d5_class_mvvm.model.JikanResponse;
import com.aoinc.w3d5_class_mvvm.network.AnimeRetrofit;
import com.aoinc.w3d5_class_mvvm.util.DebugLogger;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.aoinc.w3d5_class_mvvm.util.DebugLogger.logDebug;

public class AnimeViewModel extends ViewModel {

    private MutableLiveData<List<AnimeResult>> animeSearchResult = new MutableLiveData<>();
    public MutableLiveData<Boolean> progressState = new MutableLiveData<Boolean>();

    public LiveData<List<AnimeResult>> searchForAnime(String searchQuery) {
        new Thread() {
            @Override
            public void run() {
                super.run();

                //use Retrofit Call object to read data
                AnimeRetrofit.getAnimeRetrofitSingleton().getSearchResults(searchQuery)
                        .enqueue(new Callback<JikanResponse>() {

                            @Override
                            public void onResponse(Call<JikanResponse> call, Response<JikanResponse> response) {

                                if (response.isSuccessful() && response.body() != null &&
                                        !response.body().getResults().isEmpty()) {

                                    animeSearchResult.postValue(response.body().getResults());
                                    progressState.postValue(false);

                                } else {
                                    logDebug("Empty list...");
                                    progressState.postValue(false);

                                }
                            }

                            @Override
                            public void onFailure(Call<JikanResponse> call, Throwable t) {
                                logDebug(t.getMessage());
                                progressState.postValue(false);

                            }
                        });
            }
        }.start();
        return animeSearchResult;
    }
}
