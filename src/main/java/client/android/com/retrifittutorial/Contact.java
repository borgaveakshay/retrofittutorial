package client.android.com.retrifittutorial;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Akshay.Borgave on 01-06-2016.
 */
public class Contact {

    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    @SerializedName("address")
    String address;

    @SerializedName("email")
    String email;

    @SerializedName("gender")
    String gender;

    @SerializedName("phone")
    Phone phone;

}
