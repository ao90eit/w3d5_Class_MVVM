package com.aoinc.w3d5_class_mvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aoinc.w3d5_class_mvvm.R;
import com.aoinc.w3d5_class_mvvm.model.AnimeResult;
import com.aoinc.w3d5_class_mvvm.viewmodel.AnimeViewModel;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.aoinc.w3d5_class_mvvm.util.DebugLogger.logDebug;

public class MainActivity extends AppCompatActivity {

    private AnimeViewModel animeViewModel;

    @BindView(R.id.circular_progress_indicator)
    CircularProgressIndicator circularProgressIndicator;

    @BindView(R.id.test_textView)
    TextView testTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        animeViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(AnimeViewModel.class);

        initiateQuery("Goku");
    }

    private void initiateQuery(String searchQuery){
        showProgressBar(true);

        animeViewModel.searchForAnime(searchQuery).observe(this, new Observer<List<AnimeResult>>() {
            @Override
            public void onChanged(List<AnimeResult> animeResults) {
//                logDebug(animeResults.size() + "");
                testTextView.setText(animeResults.get(0).getTitle());
                showProgressBar(false);
            }
        });
    }

    public void showProgressBar(boolean visible) {
        int visibility;
        if (visible)
            visibility = View.VISIBLE;
        else
            visibility = View.INVISIBLE;

        circularProgressIndicator.setVisibility(visibility);
    }
}