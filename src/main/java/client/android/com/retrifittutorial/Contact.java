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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }


}
