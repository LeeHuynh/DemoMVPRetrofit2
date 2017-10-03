package codes.trongtin.h.demomvpretrofit2.Presenter;

/**
 * Created by MadPierrot on 10/3/2017.
 */
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import codes.trongtin.h.demomvpretrofit2.Adapter.PostAdapter;
import codes.trongtin.h.demomvpretrofit2.Interface.APIService;
import codes.trongtin.h.demomvpretrofit2.R;
import codes.trongtin.h.demomvpretrofit2.Remotes.RetrofitClient;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PostsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private APIService api;
    private PostAdapter adapter;
    private ProgressDialog progress;
    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        progress = new ProgressDialog(this);
        progress.setMessage("Loading...");

        adapter = new PostAdapter();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        api = RetrofitClient.get().create(APIService.class);

        //get all posts
        subscription = api.getPosts().subscribeOn(Schedulers.io())       //setting up worker thread
                .observeOn(AndroidSchedulers.mainThread())               //setting up thread where result will be delivered
                .doOnSubscribe(() -> progress.show())                    //show progress bar on subscribe
                .doOnCompleted(() -> progress.dismiss())                 //dismiss progress bar on complete
                .subscribe(posts -> {                                    //result will be delivered here
                    adapter.setData(posts);                              //sending api result to recycler adapter
                }, err -> {                                              //error from api comes here
                    err.printStackTrace();                               //printing stack trace in case of err
                    progress.dismiss();                                  //hiding progressbar as onComplete is not called in case of error
                });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }
}

