package codes.trongtin.h.demomvpretrofit2.Interface;

import java.util.List;

import codes.trongtin.h.demomvpretrofit2.Models.Post;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by MadPierrot on 10/3/2017.
 */

public interface APIService {
    @GET("posts")
    Observable<List<Post>> getPosts();
}
