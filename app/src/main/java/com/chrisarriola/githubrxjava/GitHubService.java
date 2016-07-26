package com.chrisarriola.githubrxjava;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by chris on 6/1/16.
 */
public interface GitHubService {
    @GET("users/{user}/starred") Observable<List<GitHubRepo>> getStarredRepositories(@Path("user") String userName);
}
