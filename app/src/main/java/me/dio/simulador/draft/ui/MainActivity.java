package me.dio.simulador.draft.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Random;

import me.dio.simulador.draft.R;
import me.dio.simulador.draft.data.MatchesApi;
import me.dio.simulador.draft.databinding.ActivityMainBinding;
import me.dio.simulador.draft.domain.Match;
import me.dio.simulador.draft.ui.adapter.MatchesAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MatchesApi matchesApi;
    private MatchesAdapter matchesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupHttpClient();
        setupMatchesList();
        setupMatchesRefresh();
        setupFloatActionButton();
    }

    private void setupHttpClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://falvojr.github.io/dio-simulador-partidas-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        matchesApi = retrofit.create(MatchesApi.class);
    }

    private void setupFloatActionButton() {
        binding.fabSimulate.setOnClickListener(view -> {
                view.animate().rotationBy(360).setDuration(500).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        for (int i = 0; i < matchesAdapter.getItemCount(); i++) {
                            Match match = matchesAdapter.getMatches().get(i);
                            Random random = new Random();
                            match.getHomeTeam().setScore(random.nextInt(match.getHomeTeam().getStars() + 1));
                            match.getAwayTeam().setScore(random.nextInt(match.getAwayTeam().getStars() + 1));
                            matchesAdapter.notifyItemChanged(i);
                        }
                    }
                }).start();
        });
    }

    private void setupMatchesList() {
        binding.rvMatches.setHasFixedSize(true);
        binding.rvMatches.setLayoutManager(new LinearLayoutManager(this));
        findMatchesFromApi();
    }

    private void setupMatchesRefresh() {
        binding.srlMatches.setOnRefreshListener(this::findMatchesFromApi);
    }

    private void findMatchesFromApi() {
        binding.srlMatches.setRefreshing(true);
        matchesApi.getMatches().enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(@NonNull Call<List<Match>> call, @NonNull Response<List<Match>> response) {
                if (response.isSuccessful()) {
                    matchesAdapter = new MatchesAdapter(response.body());
                    binding.rvMatches.setAdapter(matchesAdapter);
                } else {
                    showErrorMessage();
                }
                binding.srlMatches.setRefreshing(false);
            }

            @Override
            public void onFailure(@NonNull Call<List<Match>> call, @NonNull Throwable t) {
                showErrorMessage();
                binding.srlMatches.setRefreshing(false);
            }

        });
    }

    private void showErrorMessage() {
        Snackbar.make(binding.rvMatches, R.string.error_api, Snackbar.LENGTH_SHORT).show();
    }
}