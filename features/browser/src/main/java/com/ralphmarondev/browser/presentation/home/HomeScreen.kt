package com.ralphmarondev.browser.presentation.home

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberAsyncImagePainter
import com.ralphmarondev.browser.R
import com.ralphmarondev.browser.presentation.home.components.HomeBottomBar
import com.ralphmarondev.browser.presentation.home.components.HomeTopBar

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun HomeScreen() {
    val baseUrl = "https://duckduckgo.com/"
    var url by remember { mutableStateOf("") }
    val webView = remember { mutableStateOf<WebView?>(null) }

    // states to track navigation
    var canGoBack by remember { mutableStateOf(false) }
    var canGoForward by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            HomeTopBar(
                value = url,
                onValueChanged = { url = it }
            )
        },
        bottomBar = {
            HomeBottomBar(
                canGoBack = canGoBack,
                canGoForward = canGoForward,
                onBackClick = { webView.value?.goBack() },
                onForwardClick = { webView.value?.goForward() }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AnimatedVisibility(url.isNotEmpty()) {
                AndroidView(
                    factory = { context ->
                        WebView(context).apply {
                            settings.javaScriptEnabled = true
                            settings.loadWithOverviewMode = true
                            settings.useWideViewPort = true
                            settings.setSupportZoom(true)
                            settings.builtInZoomControls = true
                            settings.displayZoomControls = false

                            settings.userAgentString =
                                settings.userAgentString.replace("wv", "") + "Mobile Safari/537.36"

                            webViewClient = object : WebViewClient() {
                                override fun onPageFinished(view: WebView?, url: String?) {
                                    super.onPageFinished(view, url)

                                    // update navigation states
                                    if (view != null) {
                                        canGoBack = view.canGoBack()
                                        canGoForward = view.canGoForward()
                                    }
                                }
                            }

                        }
                    },
                    update = { view ->
                        view.loadUrl("$baseUrl$url")
                        webView.value = view
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
            AnimatedVisibility(url.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(R.drawable.browser),
                        contentDescription = "Browser Image",
                        modifier = Modifier.size(100.dp),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text =
                        "Hello there, this browser is developed, designed, and being maintained by Ralph Maron A. Eda.",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W500,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}