package com.jack.www.retrofit2_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofit_get();
    }

    private void retrofit_get()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Repo>> repos = service.listRepos("octocat");
        repos.enqueue(new Callback<List<Repo>>(){

            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response){

                System.out.println(response.message());
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t){

            }
        });

    }
}
