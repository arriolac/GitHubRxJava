package com.codepath.githubrxjava;

import android.support.annotation.NonNull;
import java.util.List;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by chris on 5/22/16.
 */
public class GitHubClient {

    private static final String GITHUB_BASE_URL = "https://api.github.com/";

    private static GitHubClient instance;
    private GitHubService gitHubService;

    private GitHubClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(GITHUB_BASE_URL).build();
        gitHubService = retrofit.create(GitHubService.class);
    }

    public static GitHubClient getInstance() {
        if (instance == null) {
            instance = new GitHubClient();
        }
        return instance;
    }

    public Observable<List<GitHubRepo>> getStarredRepos(@NonNull String userName) {
        return gitHubService.getStarredRepositories(userName);
    }
}
