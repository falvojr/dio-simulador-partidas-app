package me.dio.simulador.draft.data;

import java.util.List;

import me.dio.simulador.draft.domain.Match;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MatchesApi {
    @GET("partidas.json")
    Call<List<Match>> getMatches();
}
