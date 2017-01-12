package ai.agusibrahim.wvl;
import android.app.*;
import android.os.*;
import android.webkit.*;
import android.graphics.*;

public class Broser extends Activity
{
	private String EXT_LABEL="[EXTERNAL] ";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		WebView web=(WebView) findViewById(R.id.broser);
		String url=getIntent().getStringExtra("link");
		web.setWebViewClient(new WebViewClient() {
				@Override
				public void onPageStarted(WebView view, String url, Bitmap img) {
					Broser.this.setTitle(EXT_LABEL+"Loading...");
				}

				@Override
				public void onPageFinished(WebView view, String url) {
					Broser.this.setTitle(EXT_LABEL+view.getTitle());
				}
			});
		
		if(url!=null){
			setTitle("External Link");
			web.loadUrl(url);
		}
	}
}
