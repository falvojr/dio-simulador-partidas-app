package me.dio.simulador.draft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
        binding.fab.setOnClickListener(view -> {

        });
    }

    private void setupMatchesRefresh() {
        binding.srlMatches.setOnRefreshListener(() -> {

        });
    }
}