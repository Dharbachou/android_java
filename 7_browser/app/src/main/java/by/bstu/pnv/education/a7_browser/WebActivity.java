package by.bstu.pnv.education.a7_browser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends Activity {

    WebView webView;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        intent = getIntent();
        webView = (WebView) findViewById(R.id.wv);
        Bundle bundle = intent.getExtras();

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings set =  webView.getSettings();
        set.setJavaScriptEnabled(true);
        if(bundle != null){
            String str = bundle.getString("url");
            Log.d("Hello",str);
            webView.loadUrl(str);
        }

    }
}
