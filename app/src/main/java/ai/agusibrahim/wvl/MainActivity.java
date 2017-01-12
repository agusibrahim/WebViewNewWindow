package ai.agusibrahim.wvl;

import android.app.*;
import android.os.*;
import android.webkit.*;
import android.graphics.*;
import android.widget.*;
import android.content.*;

public class MainActivity extends Activity 
{
	WebView broser;
	private String FB_URL="https://mbasic.facebook.com/";
	private String EXT_LINK_PREFIX="facebook.com/l.php?u=";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		broser=(WebView) findViewById(R.id.broser);
		broser.setWebViewClient(new WebViewClient() {
				@Override
				public void onPageStarted(WebView view, String url, Bitmap img) {
					MainActivity.this.setTitle("Loading...");
					if(url.contains(EXT_LINK_PREFIX)){
						view.stopLoading();
						Toast.makeText(view.getContext(), "Membuka Tautan External...", 1).show();
						Intent ten=new Intent(view.getContext(), Broser.class);
						ten.putExtra("link", url);
						view.getContext().startActivity(ten);
					}
				}

				@Override
				public void onPageFinished(WebView view, String url) {
					MainActivity.this.setTitle(view.getTitle());
				}
			});
		broser.loadUrl(FB_URL);
    }
}
