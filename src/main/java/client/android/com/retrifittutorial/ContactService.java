package client.android.com.retrifittutorial;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Akshay.Borgave on 01-06-2016.
 */
public interface ContactService {


    @GET("/contacts")
    Call<ContactJson> getContacts();

    @GET("/json/movies.json")
    Call<ArrayList<Movie>> getMovieList();
}
