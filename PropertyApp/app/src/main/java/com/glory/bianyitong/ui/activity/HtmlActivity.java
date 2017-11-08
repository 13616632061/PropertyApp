package com.glory.bianyitong.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.http.HttpURL;

/**
 * 1.在要Activity中实例化WebView组件：WebView webView = new WebView(this);
 * 2.调用WebView的loadUrl()方法，设置WevView要显示的网页：
 * 互联网用：webView.loadUrl("http://www.***.com");
 * 本地文件用：webView.loadUrl(file:///android_asset/XX.html); 本地文件存放在：assets 文件中
 * 3.调用Activity的setContentView()方法来显示网页视图
 * 4.用WebView点链接看了很多页以后为了让WebView支持回退功能，需要覆盖覆盖Activity类的onKeyDown
 * ()方法，如果不做任何处理，点击系统回退剪键，整个浏览器会调用finish()而结束自身，而不是回退到上一页面
 * 5.需要在AndroidManifest.xml文件中添加权限，否则会出现Web page not available错误。
 * <uses-permission android:name="android.permission.INTERNET" />
 * 缺点：如果是载入的是普通网页，没有什么问题，但如果是html5，封装后，在android2.3以上才能正常访问，android2.2及以下，
 * SDK中的WebView还没完全支持HTML5
 * <p/>
 * 还可以直接载入html的字符串，如： String htmlString = "<h1>Title</h1>
 * <p>
 * This is HTML text<br />
 * <i>Formatted in italics</i><br />
 * Anothor Line
 * </p>
 * "; // 载入这个html页面 myWebView.loadData(htmlString, "text/html", "utf-8");
 */

public class HtmlActivity extends BaseActivity {
    private RelativeLayout left_return_btn; //返回按钮

    private RelativeLayout rootLayout;
    private WebView webview;
    private ProgressBar my_progress;
    private String from = "";
    private String title = "";
    private String url = "";

    @Override
    protected int getContentId() {
        return R.layout.html_layout;
    }

    @Override
    protected void init() {
        super.init();
        from = getIntent().getStringExtra("from");
        if (from != null && from.equals("contract")) {
            title = getString(R.string.user_agreement);//用户协议
            url = HttpURL.HTTP_LOGIN_AREA + "/home/license";
        } else if (from != null && from.equals("about")) {
            title = getString(R.string.about_us);//关于我们
            url = HttpURL.HTTP_LOGIN_AREA + "/About/About.html?versionName=" + Constant.VERSIONNAME;
        } else if (from != null && from.equals("webview")) {
            title = "广告";//关于我们
            url = getIntent().getStringExtra("url");
        }else {
            HtmlActivity.this.finish();
            return;
        }
//        Log.i("resultString", "from------------"+from);
//        Log.i("resultString", "title------------"+title);
//        Log.i("resultString", "url------------"+url);

        inintTitle(title, true, "");
        left_return_btn = (RelativeLayout) findViewById(R.id.left_return_btn);
        rootLayout = (RelativeLayout) findViewById(R.id.html_layout);
        my_progress = (ProgressBar) findViewById(R.id.my_progress);
        //实例化WebView对象
        webview = (WebView) findViewById(R.id.webView);

        left_return_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webview.canGoBack()) {
                    webview.goBack(); //goBack()表示返回WebView的上一页面
                    return;
                } else {
                    HtmlActivity.this.finish();
                    // 清理cache 和历史记录:
                    webview.clearHistory();
                    webview.clearFormData();
                    webview.clearCache(true);

                    webview.loadUrl("about:blank");
                }
            }
        });
        load(url);
    }

    private void load(String html) {
        //加载需要显示的网页
        webview.loadUrl(html);
        //设置WebView属性，能够执行Javascript脚本
        webview.getSettings().setPluginState(WebSettings.PluginState.ON); //支持插件
        WebSettings webSettings = webview.getSettings();  //android 5.0以上
//        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        //Android webview 从Lollipop(5.0)开始webview默认不允许混合模式，https当中不能加载http资源，需要设置开启。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setSupportZoom(true);
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setBuiltInZoomControls(true); //设置支持缩放
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setAllowFileAccess(true);//设置可以访问文件
        webSettings.setUseWideViewPort(false); //将图片调整到适合webview的大小
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
        webSettings.supportMultipleWindows(); //多窗口
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口

        // 在Android中点击一个链接，默认是调用应用程序来启动，因此WebView需要代为处理这个动作 通过WebViewClient
        // 设置WebViewClient
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO Auto-generated method stub
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) { //https
                handler.proceed(); //接受证书
            }
        });

        webview.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    my_progress.setVisibility(View.GONE);
                } else {
                    if (View.GONE == my_progress.getVisibility()) {
                        my_progress.setVisibility(View.VISIBLE);
                    }
                    my_progress.setProgress(newProgress);
                    int x = (int) (newProgress / 2);
                    my_progress.setSecondaryProgress(newProgress + x);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        if (webview != null) {
            // 销毁webview:
            rootLayout.removeView(webview);
            webview.destroy();
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        if (webview != null) {
        }
        super.onPause();

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        // SDK已经禁用了基于Activity 的页面统计，所以需要再次重新统计页面
    }

    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (webview.canGoBack()) {
                webview.goBack(); //goBack()表示返回WebView的上一页面
                return true;
            } else {
                HtmlActivity.this.finish();
            }
            // 清理cache 和历史记录:
            webview.clearHistory();
            webview.clearFormData();
            webview.clearCache(true);

            webview.loadUrl("about:blank");
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            // 音量键的控制，调出控制台
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            // 音量键的控制，调出控制台
        } else {
            HtmlActivity.this.finish();
        }

        return false;
    }

    private class MyWebViewDownLoadListener implements DownloadListener {

        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,
                                    long contentLength) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

    }

}
