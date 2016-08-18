package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import FragmentBaseClasses.BaseFragmentManager;

import java.util.ArrayList;

import client.android.com.retrifittutorial.Movie;
import client.android.com.retrifittutorial.R;

/**
 * Created by Akshay.Borgave on 03-06-2016.
 */
public class MovieRecyclerView extends FixedRecyclerView.Adapter<MovieRecyclerView.MovieHolder> {

    LayoutInflater inflater;
    ArrayList<Movie> dataList;
    BaseFragmentManager context;
    RequestManager requestManager;

    public MovieRecyclerView(BaseFragmentManager context, ArrayList<Movie> data){

        this.context = context;
        dataList = data;
        inflater = (LayoutInflater) context.getAppListMenuedActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public MovieRecyclerView(BaseFragmentManager context, ArrayList<Movie> data , RequestManager requestManager1){

        this.context = context;
        dataList = data;
        inflater = (LayoutInflater) context.getAppListMenuedActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        requestManager = requestManager1;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.movie_layout_item ,  null);

        MovieHolder holder = new MovieHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {

        Movie movie = dataList.get(position);

        try {
             Glide.with(context)
                    .load(movie.getImageUrl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.mipmap.ic_default_image).into(holder.movieImage);
        }
        catch (Exception e){

        }
        holder.movieTitle.setText(movie.getTitle());
        holder.releaseYear.setText(movie.getReleaseYear());
        holder.movieRating.setText(context.getResources().getString(R.string.rating)+" "+movie.getMovieRating());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class MovieHolder extends FixedRecyclerView.ViewHolder{

        ImageView movieImage;
        TextView movieTitle;
        TextView movieRating;
        TextView releaseYear;

        public MovieHolder(View itemView) {
            super(itemView);

            movieImage = (ImageView) itemView.findViewById(R.id.movieImage);
            movieTitle = (TextView) itemView.findViewById(R.id.moviewTitle);
            movieRating = (TextView) itemView.findViewById(R.id.rating);
            releaseYear = (TextView) itemView.findViewById(R.id.releaseYear);
        }
    }
}
