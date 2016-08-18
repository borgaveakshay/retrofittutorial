package adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import FragmentBaseClasses.BaseFragmentManager;
import FragmentBaseClasses.BaseListFragmentManager;
import FragmentBaseClasses.BaseListMultiSelectFragmentManager;
import RecycleViewBaseClasses.BaseInfiniteDataLoadRecycleView;
import RecycleViewBaseClasses.BaseMultiselectRecycleView;
import RecycleViewBaseClasses.BaseRecyclerView;
import UtilityBaseClasses.MultiSelectImageView;

import java.util.ArrayList;

import UtilityBaseClasses.NotificationBuilder;
import client.android.com.retrifittutorial.Movie;
import client.android.com.retrifittutorial.R;

/**
 * Created by Akshay.Borgave on 01-08-2016.
 */
public class MovieNewRecycler extends BaseMultiselectRecycleView< Movie , MovieNewRecycler.ViewHolder > {

    Bitmap imagenotificationBitmap;
   public MovieNewRecycler(Context context, ArrayList<Movie> data , BaseListMultiSelectFragmentManager manager)
    {
        super(true,R.id.movieImage);
        baseFragmentManager = manager;
        dataList = data;
        con = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public ViewHolder onCreateView(ViewGroup parent, int viewType) {

         View view = inflater.inflate(R.layout.movie_layout_item ,  null);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBind(final ViewHolder holder, int pos) {

        final Movie movie = dataList.get(pos);

        try {
            Glide.with(baseFragmentManager)
                    .load(movie.getImageUrl())
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.mipmap.ic_default_image)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            if(!movie.isSelected())
                             holder.movieImage.setImageBitmap(resource);

                        }
                    });
        }
        catch (Exception e){

        }
        holder.movieTitle.setText(movie.getTitle());
        holder.releaseYear.setText(movie.getReleaseYear());
        holder.movieRating.setText(con.getResources().getString(R.string.rating)+" "+movie.getMovieRating());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView parentView;
        MultiSelectImageView movieImage;
        TextView movieTitle;
        TextView movieRating;
        TextView releaseYear;
        public ViewHolder(View itemView) {
            super(itemView);
            parentView = (CardView) itemView.findViewById(R.id.parentId);
            movieImage = (MultiSelectImageView) itemView.findViewById(R.id.movieImage);
            movieTitle = (TextView) itemView.findViewById(R.id.moviewTitle);
            movieRating = (TextView) itemView.findViewById(R.id.rating);
            releaseYear = (TextView) itemView.findViewById(R.id.releaseYear);

        }
    }

    @Override
    public void onItemClickListener(ViewHolder viewHolder, int position) {
        if(isMultipleItemSelected) {
            super.onItemClickListener(viewHolder, position);
        }
        else {

            Movie movie = dataList.get(position);
            Glide
                    .with(baseFragmentManager)
                    .load(movie.getImageUrl())
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.mipmap.ic_default_image)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            imagenotificationBitmap = resource;
                        }
                    });
            NotificationBuilder notificationBuilder = new NotificationBuilder.Builder(con)
                    .setBuilderContentTitile(movie.getTitle())
                    .setBuilderContentText(movie.getMovieRating())
                    .setBuilderNotificationDrawableResource(R.mipmap.ic_tick)
                    .setBuilderBigStyleNotificationBitmap(imagenotificationBitmap)
                    .build();

            notificationBuilder.sendNotification();
        }
    }

}
