package com.chrisarriola.githubrxjava;

/**
 * Created by chris on 6/1/16.
 */
public class GitHubRepo {
    public final int id;
    public final String name;
    public final String htmlUrl;
    public final String description;
    public final String language;
    public final int stargazersCount;

    public GitHubRepo(int id, String name, String htmlUrl, String description, String language, int stargazersCount) {
        this.id = id;
        this.name = name;
        this.htmlUrl = htmlUrl;
        this.description = description;
        this.language = language;
        this.stargazersCount = stargazersCount;
    }
}
