package com.chrisarriola.githubrxjava;

import com.manaschaudhari.android_mvvm.ViewModel;

public class GithubRepoVM implements ViewModel {
    public final String name;
    public final String description;
    public final String language;
    public final String stars;

    public GithubRepoVM(GitHubRepo repo) {
        name = repo.name;
        description = repo.description;
        language = "Language: " + repo.language;
        stars = "Stars: " + repo.stargazersCount;
    }

}
