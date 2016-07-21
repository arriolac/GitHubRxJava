package com.codepath.githubrxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.List;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private GitHubRepoAdapter adapter = new GitHubRepoAdapter();
    private Subscription subscription;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = (ListView) findViewById(R.id.list_view_repos);
        listView.setAdapter(adapter);

        final EditText editTextUsername = (EditText) findViewById(R.id.edit_text_username);
        final Button buttonSearch = (Button) findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                final String username = editTextUsername.getText().toString();
                if (!TextUtils.isEmpty(username)) {
                    getStarredRepos(username);
                }
            }
        });
    }

    @Override protected void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }

    private void getStarredRepos(String username) {
        subscription = GitHubClient.getInstance()
                                   .getStarredRepos(username)
                                   .flatMap(new Func1<List<GitHubRepo>, Observable<GitHubRepo>>() {
                                       @Override public Observable<GitHubRepo> call(List<GitHubRepo> gitHubRepos) {
                                           return Observable.from(gitHubRepos);
                                       }
                                   })
                                   .filter(new Func1<GitHubRepo, Boolean>() {
                                       @Override public Boolean call(GitHubRepo gitHubRepo) {
                                           return "java".equalsIgnoreCase(gitHubRepo.language);
                                       }
                                   })
                                   .doOnError(new Action1<Throwable>() {
                                       @Override public void call(Throwable throwable) {

                                       }
                                   })
                                   .subscribeOn(Schedulers.io())
                                   .observeOn(AndroidSchedulers.mainThread())
                                   .subscribe(new Observer<GitHubRepo>() {
                                       @Override public void onCompleted() {
                                           Log.d(TAG, "In onCompleted()");
                                       }

                                       @Override public void onError(Throwable e) {
                                           e.printStackTrace();
                                           Log.d(TAG, "In onError()");
                                       }

                                       @Override public void onNext(GitHubRepo gitHubRepo) {
                                           Log.d(TAG, "In onNext()");
                                           //adapter.setGitHubRepos(gitHubRepos);
                                           adapter.add(gitHubRepo);
                                       }
                                   });
    }
}
