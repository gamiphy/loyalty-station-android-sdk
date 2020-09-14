package com.gamiphy.loyaltyStation.webview

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.gamiphy.loyaltyStation.R
import com.gamiphy.loyaltyStation.jsSdk.JsSdk
import com.gamiphy.loyaltyStation.jsSdk.JsSdkImp
import com.gamiphy.loyaltyStation.jsSdk.models.JsSdkConfig
import com.gamiphy.loyaltyStation.jsSdk.models.JsSdkInitConfig
import com.gamiphy.loyaltyStation.models.AuthListener
import com.gamiphy.loyaltyStation.models.Environments
import com.gamiphy.loyaltyStation.models.User

class WebViewActivity : AppCompatActivity(), WebViewActions {
    private lateinit var webView: WebView
    private lateinit var closeBtn: ImageButton
    private lateinit var progressBar: ProgressBar
    private var firstLogin: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        checkFirstStart()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamiphy_web_view);

        initViews()
        actionsList.add(this)
    }

    private fun checkFirstStart() {
        if (firstLogin) {
            moveTaskToBack(true);
            firstLogin = false
        }
    }

    override fun login(user: User) {
        config.user = user;
        refresh()
    }

    override fun logout() {
        config.user = null;
        WebStorage.getInstance().deleteAllData();
        refresh()
    }

    override fun onBackPressed() {
        moveTaskToBack(true);
    }

    override fun close() {
        onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        actionsList.clear()
    }

    override fun refresh() {
        initWebView()
    }

    private fun initViews() {
        webView = findViewById(R.id.webView)
        progressBar = findViewById(R.id.progressBar)
        closeBtn = findViewById(R.id.close_btn);
        closeBtn.setOnClickListener { onBackPressed() }
        initWebView()
    }

    @SuppressLint("SetJavaScriptEnabled", "AddJavascriptInterface")
    private fun initWebView() {
        val webSettings = webView.settings
        webView.webChromeClient = WebChromeClient()
        with(webSettings) {
            javaScriptEnabled = true
            domStorageEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            builtInZoomControls = false
            blockNetworkImage = false
            loadsImagesAutomatically = true
            supportMultipleWindows()

        }

        webView.loadUrl(jsSdk.getUrl())
        webView.addJavascriptInterface(jsSdk, JsSdk.JAVASCRIPT_OBJ)

        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                showLoading()
                showCloseBtn()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                hideLoading()
                hideCloseBtn();
                executeJavaScript(jsSdk.getInitScript())
            }

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val requestUrl = request?.url.toString()
                view?.loadUrl(requestUrl)
                return false
            }

            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                hideLoading()
                hideCloseBtn()
            }

            override fun onReceivedHttpError(
                view: WebView?,
                request: WebResourceRequest?,
                errorResponse: WebResourceResponse?
            ) {
                hideLoading()
                hideCloseBtn()
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                hideLoading()
                hideCloseBtn()
            }
        }
    }

    private fun executeJavaScript(script: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.post { webView.evaluateJavascript(script, null) }
        } else {
            webView.post { webView.loadUrl(script, null) }
        }
    }

    private fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    private fun showCloseBtn() {
        closeBtn.visibility = View.GONE
    }

    private fun hideCloseBtn() {
        closeBtn.visibility = View.VISIBLE
    }

    companion object {
        private lateinit var config: WebViewConfig
        private lateinit var jsSdk: JsSdk
        var actionsList = mutableListOf<WebViewActions>()

        fun init(config: WebViewConfig) {
            WebViewActivity.config = config;
            jsSdk = JsSdkImp(
                JsSdkConfig(
                    JsSdkInitConfig(config.app, config.user),
                    Environments.DEV
                )
            )
        }

        fun setAuthTrigger(authListener: AuthListener) {
            jsSdk.authListener = authListener;
        }

        @JvmStatic
        fun newIntent(context: Context) =
            Intent(context, WebViewActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
    }
}