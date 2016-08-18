package client.android.com.retrifittutorial;

import BaseModels.BaseModel;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Akshay.Borgave on 02-06-2016.
 */
public class Movie extends BaseModel{


    @SerializedName("title")
    String title;

    @SerializedName("image")
    String imageUrl;

    @SerializedName("rating")
    String movieRating;

    @SerializedName("releaseYear")
    String releaseYear;

    @SerializedName("genre")
    ArrayList<String> genre;


    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(String movieRating) {
        this.movieRating = movieRating;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }


}
