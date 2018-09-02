package eeb3.anirudhhramesh.com.eeb3;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class AbsencesWeb extends AppCompatActivity {
    Snackbar snackbar;
    WebView webView ;
    String url;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    private String userPassword;
    private String userId;
    private String enabledOffice;

    private ProgressDialog progressx;
    Context context;
    String x="x";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.absenceswebactivity);

        sharedPref= getSharedPreferences("myPref", Context.MODE_PRIVATE);
        userPassword = sharedPref.getString("user_password","");
        userId = sharedPref.getString("user_id","");
        enabledOffice = sharedPref.getString("office_enabled","");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbartasks);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        progressx=new ProgressDialog(this);
        progressx.setMessage(getString(R.string.please_wait));
        progressx.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                .Lineara);
        snackbar = Snackbar
                .make(coordinatorLayout, R.string.no_internet, Snackbar.LENGTH_LONG)
                .setAction(R.string.retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.setClassName("com.android.phone", "com.android.phone.NetworkSetting");
                        startActivity(intent);

                    }
                });
        if (!isNetworkAvailable()) {
            snackbar.show();
        }
        progressx.show();

        Intent intent=getIntent();
        String id = intent.getStringExtra("id");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        webView = (WebView) findViewById(R.id.AbsencesWeb);


        webView.setWebViewClient(new AbsencesWeb.MyBrowser());
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAppCacheMaxSize(5 * 1024 * 1024); // 5MB
        webView.getSettings().setAppCachePath(getApplicationContext().getCacheDir().getAbsolutePath());
        webView.getSettings().setAllowFileAccess(true);
        webView.setWebChromeClient(new AbsencesWeb.MyWebViewClient());
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT); // load online by default

        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setJavaScriptEnabled(true);

        //webPage.getSettings().setPluginState(PluginState.ON);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDatabasePath("/data/data/" + "crm.agile.agilecrm" + "/databases/");
        webView.getSettings().setSaveFormData(true);
        webView.getSettings().setSavePassword(true);
        webView.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl(sharedPref.getString("office_enabled", "http://bit.ly/Office365SMS"));

        webView.loadUrl("javascript:document.getElementById('passwordInput').value = '"+userPassword+"';");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_xlweekly, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
        }
        if (id == R.id.action_help) {
            help();
        }
        else if (id == R.id.action_settings) {
            if (userId == ""){
                donate();
            }
            else {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Copied Username", userId);
                clipboard.setPrimaryClip(clip);
            }
        }
        else if(item.getItemId() == R.id.action_password) {
            if (userId == ""){
                donate();
            }
            else {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

                ClipData clip = ClipData.newPlainText("Copied Password", userPassword);
                clipboard.setPrimaryClip(clip);
            }
        }
        return super.onOptionsItemSelected(item);
    }


    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (!isNetworkAvailable()) {
                snackbar.show();
            }else {
                view.loadUrl(url);
                Log.e("SMS webpage", userPassword);
                webView.loadUrl("javascript: {" +
                        "document.getElementById('user_password').value = '"+userPassword +"';" +
                        "document.getElementById('user_email').value = '"+userId+"';" +
                        "var frms = document.getElementsByName('login_form');" +
                        "frms[0].submit(); };");

                if (url == "https://sms.eursc.eu/login.php"){
                    Log.e("SMS URL Check", "Url checking works properly!");
                }
                else {
                    Log.e("SMS Check not working", userId);
                }
            }


            return true;

        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            if (!isNetworkAvailable()) {
                snackbar.show();


            }
        }

        public void onPageFinished(WebView view, String url) {
            progressx.cancel();
            webView.loadUrl("javascript: {" +
                    "document.getElementById('passwordInput').value = '"+userPassword +"';" +
                    "document.getElementById('userNameInput').value = '"+userId+"';" +
                    "var frms = document.getElementsByName('loginForm');" +
                    "frms[0].submit(); };");

            //webView.loadUrl("javascript:document.getElementById('passwordInput').value = '"+userPassword+"';");
            Log.e("Website loaded", "Website has been loaded!");


            ////////When SMS page accessed/////////

            webView.loadUrl("javascript: {" +
                    "document.getElementById('user_password').value = '"+userPassword +"';" +
                    "document.getElementById('user_email').value = '"+userId+"';" +
                    "document.getElementById('login_form').click(); };");
        }

    }
    @Override
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
    private class MyWebViewClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (!isNetworkAvailable()) {
                snackbar.show();


            }else {
                AbsencesWeb.this.setValue(newProgress);
                super.onProgressChanged(view, newProgress);
            }
        }
    }

    public void setValue(int progress) {
        this.progressx.setProgress(progress);
    }



    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService( CONNECTIVITY_SERVICE );
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void donate(){
        AlertDialog.Builder builder = new AlertDialog.Builder(AbsencesWeb.this);
        builder.setTitle("Set credentials");
        builder.setIcon(R.drawable.ic_locksvg);
        builder.setMessage("If you would like to this feature, make sure you set your credentials in the password section");
        builder.setPositiveButton("Set Password", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Intent i = new Intent(AbsencesWeb.this, SMSCredentialsActivity.class);
                startActivity(i);
            }
        });
        builder.setNegativeButton(getString(R.string.maybe_later), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public void help(){
        AlertDialog.Builder builder = new AlertDialog.Builder(AbsencesWeb.this);
        builder.setTitle("Can't login?");
        builder.setIcon(R.drawable.ic_locksvg);
        builder.setMessage("If you're having trouble logging in to sms, make sure to uncheck the 'Login using Office365' option");
        builder.setPositiveButton("Go to settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Intent i = new Intent(AbsencesWeb.this, SMSCredentialsActivity.class);
                startActivity(i);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}

