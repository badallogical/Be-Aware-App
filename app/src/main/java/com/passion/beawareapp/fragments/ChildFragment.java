package com.passion.beawareapp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.passion.beawareapp.R;
import com.passion.beawareapp.models.News;
import com.passion.beawareapp.utils.DownloadImageTask;

import org.w3c.dom.Text;

public class ChildFragment extends Fragment {

    TextView title;
    ImageView image;
    TextView desc;
    TextView read_more;

    News news;

    public ChildFragment( News news ){
        this.news = news;
    }


    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.news_api, container, false );

        // setup components
        title = (TextView) v.findViewById(R.id.title);
        title.setText( news.getTitle() );

        image = (ImageView) v.findViewById(R.id.image_id);
        new DownloadImageTask(image).execute(news.getUtl_to_img());

        desc = (TextView) v.findViewById(R.id.news_desc);
        desc.setText( news.getDesc() + news.getContent());

        read_more = (TextView) v.findViewById(R.id.read_more);

        final News tnews = news;
        read_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent( Intent.ACTION_VIEW , Uri.parse(tnews.getUrl_to_src()) );
                startActivity(browserIntent);
            }
        });

        return v;
    }
}
