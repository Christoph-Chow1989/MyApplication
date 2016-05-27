package com.jack.www.retrofit2_demo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by pc on 2016/5/27.
 */
public interface GitHubService
{
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}
