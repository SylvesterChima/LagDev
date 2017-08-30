package com.example.sylvester.lagdev;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sylvester.lagdev.model.developers;
import com.example.sylvester.lagdev.model.mDevs;
import com.example.sylvester.lagdev.remote.APIService;
import com.example.sylvester.lagdev.remote.ApiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class java_developers extends AppCompatActivity {

    ListView listView = null;
    private APIService mAPIService;
    private mDevs results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_developers);
        listView = (ListView)findViewById(R.id.listStudents);
        mAPIService = ApiUtils.getAPIService();
        getDevelopers("language:java location:lagos",100,1);
        //getDevelopers(Integer.toString(1));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                developers pt = results.getItems().get(position);
                Intent intent = new Intent(java_developers.this, developer_details.class);
                intent.putExtra("devExtra", pt);
                startActivity(intent);
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int bufferItemCount = 50;
            private int currentPage = 0;
            private int itemCount = 0;
            private boolean isLoading = true;
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
            {
                if (totalItemCount < itemCount) {
                    this.itemCount = totalItemCount;
                    if (totalItemCount == 0) {
                        this.isLoading = true;
                    }
                }

                if (isLoading && (totalItemCount > itemCount)) {
                    isLoading = false;
                    itemCount = totalItemCount;
                    currentPage++;
                }
                if (!isLoading && (totalItemCount - visibleItemCount)<=(firstVisibleItem + bufferItemCount)) {
                    getDevelopers("language:java location:lagos",bufferItemCount,currentPage + 1);
                    isLoading = true;
                }
            }
        });
    }


    public void getDevelopers(String q, int per_page, int page){
        final ProgressDialog loading = ProgressDialog.show(this," ","Please wait...",false,false);
        //Retrofit method that fetches record from the github api
        mAPIService.getDevs(q,per_page,page).enqueue(new Callback<mDevs>() {
            @Override
            public void onResponse(Call<mDevs> call, Response<mDevs> response) {
                if(response.isSuccessful()){
                    loading.dismiss();
                    results = response.body();
                    devAdapter customAdapter = new devAdapter(java_developers.this, R.layout.java_developers,results.getItems());
                    listView.setAdapter(customAdapter);
                    customAdapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(java_developers.this, response.message(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<mDevs> call, Throwable t) {
                Toast.makeText(java_developers.this, t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }


}


