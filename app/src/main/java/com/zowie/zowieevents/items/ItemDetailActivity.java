package com.zowie.zowieevents.items;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.webkit.WebView;

import com.zowie.zowieevents.R;

public class ItemDetailActivity extends FragmentActivity {
    private static final String TAG = ItemDetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        String title = this.getIntent().getExtras().getString("title");
        String url = this.getIntent().getExtras().getString("url");

        setTitle(title);

        WebView mWebView = (WebView) findViewById(R.id.detail_web_view);
        mWebView.loadUrl(url);
    }
}
