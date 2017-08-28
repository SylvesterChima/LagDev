package com.example.sylvester.lagdev;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.sylvester.lagdev.model.developers;
import com.example.sylvester.lagdev.remote.APIService;
import com.example.sylvester.lagdev.remote.ApiUtils;

public class developer_details extends AppCompatActivity {

    ImageView img;
    Button btn;
    ProgressBar pbLoading;
    String share_url;
    private APIService mAPIService;
    developers devs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_details);

        TextView lblUserName = (TextView)findViewById(R.id.lblUserName);
        TextView lblGitUrl = (TextView)findViewById(R.id.lblGitUrl);

        devs = (developers) getIntent().getSerializableExtra("devExtra");
        img = (ImageView)findViewById(R.id.imgAvatar);
        btn = (Button)findViewById(R.id.btnShare);
        pbLoading = (ProgressBar)findViewById(R.id.pbLoading);
        mAPIService = ApiUtils.getAPIService();

        lblUserName.setText(devs.getLogin());
        lblGitUrl.setText(devs.getHtml_url());
        //Glide image loading
        if(devs.getAvatar_url() != null){
            pbLoading.setVisibility(View.VISIBLE);
            Glide.with(this)
                    .load(devs.getAvatar_url())
                    .placeholder(R.drawable.ic_person_black_24dp)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img);
            pbLoading.setVisibility(View.INVISIBLE);
        }
        else {
            pbLoading.setVisibility(View.INVISIBLE);
            img.setImageResource(R.drawable.ic_person_black_24dp);
        }
    }
    public void Share(View view){
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        /* Add data to the intent, the receiving app will decide
        // what to do with it.*/
        share.putExtra(Intent.EXTRA_SUBJECT, "Github Profile");
        share.putExtra(Intent.EXTRA_TEXT, "Check out this awesome developer" + devs.getLogin() + "," + devs.getHtml_url() +".");
        startActivity(Intent.createChooser(share, "Share user github profile"));
    }
}
