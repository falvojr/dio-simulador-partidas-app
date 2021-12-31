package me.dio.simulador.draft.ui;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import me.dio.simulador.draft.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupFloatActionButton();
        setupMatchesRefresh();
    }

    private void setupFloatActionButton() {
        binding.fabSimulate.setOnClickListener(view -> {
            view.animate().rotationBy(360).setDuration(1000).start();
        });
    }

    private void setupMatchesRefresh() {
        binding.srlMatches.setOnRefreshListener(() -> {
            new Handler().postDelayed(() -> {
                binding.srlMatches.setRefreshing(false);
            }, 1000);
        });
    }
}