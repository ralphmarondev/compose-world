package com.ralphmarondev.browser

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.ralphmarondev.browser.components.BrowserTopBar
import com.ralphmarondev.browser.utils.Constants

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun BrowserMainScreen() {
    var webView by remember { mutableStateOf<WebView?>(null) }
    var searchQuery by remember { mutableStateOf("") }
    var searchUrl by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            BrowserTopBar(
                value = searchQuery,
                onValueChanged = { searchQuery = it },
                onSearch = {
                    val query = searchQuery
                    searchUrl = "${Constants.BASE_URL}$query&t=ffab&ia=web"
                    webView?.loadUrl(searchUrl)
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AndroidView(

                modifier = Modifier
                    .fillMaxSize(),
                factory = { context ->
                    WebView(context).apply {
                        webViewClient = WebViewClient()
                        settings.javaScriptEnabled = true
                        loadUrl(searchUrl)
                        webView = this
                    }
                },
                update = { webView ->
                    if (searchUrl.isNotEmpty()) {
                        webView.loadUrl(searchUrl)
                        // Inject JavaScript to hide the search bar after the page loads
                        webView?.evaluateJavascript(
                            """
                            (function() {
                                var searchBar = document.querySelector('#search_form_homepage');
                                if (searchBar) {
                                    searchBar.style.display = 'none';
                                }
                            })();
                            """, null
                        )
                    }
                }
            )
        }
    }
}