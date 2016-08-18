package client.android.com.retrifittutorial;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Akshay.Borgave on 01-06-2016.
 */

public class Phone {

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    @SerializedName("mobile")
    String mobile;

    @SerializedName("home")
    String home;

    @SerializedName("office")
    String office;
}
