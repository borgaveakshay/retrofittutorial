package client.android.com.retrifittutorial;

import android.os.Bundle;

import com.example.genericactivity.BaseActivities.GenericFragmentListMenuedActivity;

public class MainActivity extends GenericFragmentListMenuedActivity<MainFragment,Movie> {


    @Override
    public boolean isListMultiselectEnableInd() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onCreate(savedInstanceState,MainFragment.class,R.layout.activity_generic_menued,R.string.app_name);


    }
}
