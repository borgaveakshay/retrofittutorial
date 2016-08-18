package client.android.com.retrifittutorial;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Akshay.Borgave on 01-06-2016.
 */

public class Phone {

    @SerializedName("mobile")
    String mobile;

    @SerializedName("home")
    String home;

    @SerializedName("office")
    String office;
}
