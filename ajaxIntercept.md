Pada dasarnya tidak ada fungsi seperti dalam dokumentasi Android, namun kita di izinkan menggunakan fungsi penting yang bisa melancarkan agar aksi ini terjadi. Android menyediakan fungsi inject javascript dan membuat fungsi javascript dalam java, dengan kedua fungsi tersebut memungkinkan kita untuk memonitor lalu lintas AJAX, request maupun response.
Berikut langkah bagaimana melakukan intercept AJAX request pada Android WebView.

### Membuat Callback
Pertama kita membuat Javascript Interface menggunakan Java, dimana saat fungsi JS itu dipanggil, maka akan mentrigger fungsi di Java, ini berguna untuk mewadahi data yang sudah didapat dari hasil intercept.
```java
class MyJavaScriptInterface{
		@JavascriptInterface
		@SuppressWarnings("unused")
		public void now(String content){
			android.util.Log.d("xhr", content);
		}
	}
``` 
Tambahkan _MyJavaScriptInterface_ ke WebView
```java
webView.addJavascriptInterface(new MyJavaScriptInterface(), "callme");
``` 
Panggil dengan javascript
```javascript
callme.now("Hello Bro");
``` 

### Override XMLHttpRequest
Yap, dengan melakukan override pada fungsi XHR ini kita dapat melempar data yang kita inginkan ke JS Interface yang sudah kita buat. Bagaimana cara override request dan response dari XHR? Saya mendapatkan caranya disini
* Overriding XHR open http://stackoverflow.com/a/7778218
* Overriding XHR responseText http://stackoverflow.com/a/26447781

Inject JS untuk Override setiap halaman selesai dimuat, seperti ini
```java
@Override
				public void onPageFinished(WebView view, String url) {
// override reaponse
					view.loadUrl("javascript:!function(){var t=XMLHttpRequest.prototype.open;XMLHttpRequest.prototype.open=function(e,n,a){var s;return a&&(s=this.onreadystatechange,this.onreadystatechange=function(){if(true){var t=this,e={};return['statusText','status','readyState','responseType','responseText'].forEach(function(n){e[n]=t[n]}),callme.now(JSON.stringify(e)),s.call(e)}return s.apply(this,arguments)}),t.apply(this,arguments)}}();");
// override xhr open
					view.loadUrl("javascript:!function(){var t=window.XMLHttpRequest.prototype.open;window.XMLHttpRequest.prototype.open=function(){return callme.now(JSON.stringify(arguments)),t.apply(this,[].slice.call(arguments))}}();");
					super.onPageFinished(view, url);
				}
``` 
## What Next?
Banyak 
