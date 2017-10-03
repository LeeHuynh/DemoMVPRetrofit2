package codes.trongtin.h.demomvpretrofit2.Adapter;

/**
 * Created by MadPierrot on 10/3/2017.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import codes.trongtin.h.demomvpretrofit2.Models.Post;
import codes.trongtin.h.demomvpretrofit2.R;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    private List<Post> posts = new ArrayList<>();

    public void setData(List<Post> posts) {
        this.posts = posts;
        //telling adapter that data has been changed and redraw recyclerView
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_post, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Post post = posts.get(position);
        //set data to view
        holder.title.setText(post.getTitle());
        holder.body.setText(post.getBody());
    }

    @Override
    public int getItemCount() {
        //return size of list
        return posts.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        //declare variables here
        private TextView title;
        private TextView body;

        private MyViewHolder(View view) {
            super(view);
            //reference declared object here.
            title = (TextView) view.findViewById(R.id.tvTitle);
            body = (TextView) view.findViewById(R.id.tvBody);
        }
    }
}