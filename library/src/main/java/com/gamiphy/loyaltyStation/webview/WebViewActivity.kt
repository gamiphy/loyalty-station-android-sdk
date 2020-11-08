package com.gamiphy.loyaltyStation.webview

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.gamiphy.loyaltyStation.Config
import com.gamiphy.loyaltyStation.R
import com.gamiphy.loyaltyStation.models.User

class WebViewActivity : AppCompatActivity(), WebViewActions {
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private var firstLogin: Boolean = true
    private val jsSdk = JsSdkImp()

    private fun getPath(): String {
        return when (Config.instance.agent) {
            "floward" -> "/sdk/custom/floward/index.html"

            else -> "/sdk/custom/floward/index.html"
        }
    }

    private fun getDomain(): String {
        return when (Config.instance.sandbox) {
            true -> "https://static-staging.gamiphy.co"
            else -> "https://static.gamiphy.co"
        }
    }

    private fun getUrl(): String {
        return this.getDomain() + this.getPath()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        checkFirstStart()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamiphy_web_view)

        initViews()
        Config.instance.actionsList.add(this)
    }

    private fun checkFirstStart() {
        if (firstLogin) {
            moveTaskToBack(true)
            firstLogin = false
        }
    }

    override fun login(user: User) {
        Config.instance.user = user
        refresh()
    }

    override fun logout() {
        Config.instance.user = null
        WebStorage.getInstance().deleteAllData()
        refresh()
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    override fun close() {
        onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        Config.instance.actionsList.clear()
    }

    override fun refresh() {
        initWebView()
    }

    private fun initViews() {
        webView = findViewById(R.id.webView)
        progressBar = findViewById(R.id.progressBar)
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

        webView.loadUrl(getUrl())
        webView.addJavascriptInterface(jsSdk, jsSdk.JAVASCRIPT_OBJ)

        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                showLoading()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                hideLoading()
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
            }

            override fun onReceivedHttpError(
                view: WebView?,
                request: WebResourceRequest?,
                errorResponse: WebResourceResponse?
            ) {
                hideLoading()
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                hideLoading()
            }
        }
    }

    private fun executeJavaScript(script: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.post { webView.evaluateJavascript(script, null) }
        } else {
            webView.post { webView.loadUrl(script) }
        }
    }

    private fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun share(text: String, link: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "$text \n $link")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}