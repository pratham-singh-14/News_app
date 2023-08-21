package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Model.Articles;
import com.example.myapplication.Model.Headlines;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    final String API_KEY="61574516149049d596c38c300f5a999a";
    Adapter adapter;
    List<Articles> articles=new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;
   // EditText searchedt;
//    AppCompatButton btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout=findViewById(R.id.swipe);
        recyclerView=findViewById(R.id.recyclerView);
        //searchedt=findViewById(R.id.edittext);
      //  btn=findViewById(R.id.appButton);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String country = getCounty();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reterivedata("",country,API_KEY);
            }
        });
        reterivedata("",country,API_KEY);
//   btn.setOnClickListener(new View.OnClickListener() {
//       @Override
//       public void onClick(View v) {
//              if (!searchedt.getText().toString().equals(""))
//              {
//                  swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//                      @Override
//                      public void onRefresh() {
//                          reterivedata("",country,API_KEY);
//                      }
//                  });
//                  reterivedata(searchedt.getText().toString(),country,API_KEY);
//              }else {
//                  swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//                      @Override
//                      public void onRefresh() {
//                          reterivedata("",country,API_KEY);
//                      }
//                  });
//                  reterivedata("",country,API_KEY);
//              }
//       }
//   });

    }

    public void reterivedata(String query,String country,String apiKey)
    {
        swipeRefreshLayout.setRefreshing(true);
        Call<Headlines> call;
//        if(!searchedt.getText().equals(""))
//        {
//            call=ApiClient.getInstance().getApi().getSpecific(query,apiKey);
//        }else {
            //call=ApiClient.getInstance().getApi().getHeadlines(country,apiKey);
//        }
         call=ApiClient.getInstance().getApi().getHeadlines(country,apiKey);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if(response.isSuccessful() && response.body().getArticle() != null)
                {
                    swipeRefreshLayout.setRefreshing(false);
                    articles.clear();
                    articles = response.body().getArticle();
                    adapter = new Adapter(MainActivity.this,articles);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    {

    }
    public String getCounty()
    {
        Locale locale=Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }
}