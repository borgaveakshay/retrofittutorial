package client.android.com.retrifittutorial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import FragmentBaseClasses.BaseInfiniteListFragmentManager;
import FragmentBaseClasses.BaseListFragmentManager;
import com.example.genericactivity.BaseActivities.GenericFragmentListMenuedActivity;
import com.example.genericactivity.BaseActivities.GenericFragmentMenuedActivity;

import java.util.ArrayList;

import FragmentBaseClasses.BaseListMultiSelectFragmentManager;
import RecycleViewBaseClasses.BaseMultiselectRecycleView;
import WebService.BaseWebServiceCall;
import adapters.MovieNewRecycler;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Akshay.Borgave on 08-06-2016.
 */
public class MainFragment extends BaseListMultiSelectFragmentManager<Movie, MovieNewRecycler> {

    Retrofit retrofit;
    SwipeRefreshLayout swipe;
    RequestManager requestManager;
    static View view;

    @Override
    public View onViewCreated(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(savedInstanceState == null) {
            view = inflater.inflate(R.layout.activity_main, null);

        }
        initFragment();
        getAppListMenuedActivity().setToolBarHideOnScroll(true);

//        getAppListMenuedActivity().setNavigationDrawerResourceId(R.id.drawer_layout);
//        getAppListMenuedActivity().setNavigationViewResourceId(R.id.nav_view);
//        getAppListMenuedActivity().enableNavigationDrawer();
        requestManager = Glide.with(getActivity());


        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.androidhive.info")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recyclerView = (RecyclerView) view.findViewById(R.id.moviewList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getAppListMenuedActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setVisibility(View.INVISIBLE);

        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                makeCall();
            }
        });


        return view;
    }

    public void makeCall() {

       ContactService service = retrofit.create(ContactService.class);
       Call call = service.getMovieList();

       webServiceCall = new BaseWebServiceCall<Call<ArrayList<Movie>>, ArrayList<Movie>>(getActivity(), call , this) {

            @Override
            public void onResponseReceived(ArrayList<Movie> data) {

                recyclerView.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "Data received for Movie Service", Toast.LENGTH_LONG).show();
                dataList = data;

                baseListRecyclerViewAdapter = new MovieNewRecycler(getAppListMenuedActivity(), dataList, MainFragment.this);
               // baseListRecyclerViewAdapter.setOnLoadMoreListener(MainFragment.this);

                recyclerView.setAdapter(baseListRecyclerViewAdapter);
                swipe.setRefreshing(false);


            }
            @Override
            public void onCallFailure(Call<ArrayList<Movie>> call, Throwable t) {

            }
        };
        webServiceCall.setProgressBarEnabled(true);
        webServiceCall.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Glide.get(getActivity()).clearMemory();
    }

    @Override
    public void handleMenuClick(MenuItem menuItem) {

        if(menuItem.getItemId() == R.id.sync){
            recyclerView.setVisibility(View.INVISIBLE);
            if(baseListRecyclerViewAdapter != null){

                   dataList.clear();
                   baseListRecyclerViewAdapter.notifyDataSetChanged();

            }
            makeCall();
        }
    }

    @Override
    public int setMenuResourceId() {
        return R.menu.main_menu;
    }

    @Override
    public View setFragmentView() {
        return view;
    }

    @Override
    public GenericFragmentListMenuedActivity setMenuedListActivity() {
        return (GenericFragmentListMenuedActivity) getActivity();
    }

    @Override
    public int setProgressBarResourceId() {
        return R.id.progressBar;
    }

    @Override
    public boolean setMultiSelectFlag() {
        return true;
    }
}
