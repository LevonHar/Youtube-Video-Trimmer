/*package com.example.youtubevideotrimmer

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VideoTrimApp()
        }
    }
}

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoTrimApp() {
    var youtubeUrl by remember { mutableStateOf(TextFieldValue("")) }
    var videoId by remember { mutableStateOf<String?>(null) }
    var errorMessage by remember { mutableStateOf("") }
    var isVideoPlaying by remember { mutableStateOf(false) }
    var videoDuration by remember { mutableStateOf(137f) } // Example duration in seconds
    var startTime by remember { mutableStateOf(0f) }
    var endTime by remember { mutableStateOf(videoDuration) }

    // LocalKeyboardController to control the software keyboard
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("YouTube Video Downloader") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text("Enter YouTube URL:")
            Spacer(modifier = Modifier.height(8.dp))
            BasicTextField(
                value = youtubeUrl,
                onValueChange = { youtubeUrl = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, MaterialTheme.colorScheme.primary)
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val url = youtubeUrl.text
                    videoId = extractVideoIdFromUrl(url)
                    if (videoId == null) {
                        errorMessage = "Invalid YouTube URL"
                    } else {
                        errorMessage = ""
                        isVideoPlaying = false // Reset the video state
                        keyboardController?.hide() // Hide the keyboard
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Find Video")
            }
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            // Show video thumbnail (cover) if videoId is available and not yet playing
            videoId?.let {
                Spacer(modifier = Modifier.height(16.dp))
                if (!isVideoPlaying) {
                    val thumbnailUrl = "https://img.youtube.com/vi/$it/hqdefault.jpg"
                    val painter: Painter = rememberImagePainter(thumbnailUrl)
                    Image(
                        painter = painter,
                        contentDescription = "Video Thumbnail",
                        modifier = Modifier.fillMaxWidth().height(200.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Select interval")
                    Slider(
                        value = startTime,
                        onValueChange = { startTime = it.coerceIn(0f, endTime) },
                        valueRange = 0f..videoDuration,
                        modifier = Modifier.fillMaxWidth(),
                        colors = SliderDefaults.colors(thumbColor = MaterialTheme.colorScheme.primary)
                    )
                    Slider(
                        value = endTime,
                        onValueChange = { endTime = it.coerceIn(startTime, videoDuration) },
                        valueRange = 0f..videoDuration,
                        modifier = Modifier.fillMaxWidth(),
                        colors = SliderDefaults.colors(thumbColor = MaterialTheme.colorScheme.secondary)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Start: ${formatTime(startTime.toInt())}")
                        Text("End: ${formatTime(endTime.toInt())}")
                    }

                    Spacer(modifier = Modifier.height(30.dp))
                    Button(
                        onClick = {
                            // Handle download with interval
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Download Video")
                    }
                }
            }
        }
    }
}

fun extractVideoIdFromUrl(url: String): String? {
    val regex = Regex("(?:v=|youtu\\.be/|embed/|v/|watch\\?v=|/videos/|embed\\?clip_id=)([a-zA-Z0-9_-]{11})")
    val match = regex.find(url)
    return match?.groupValues?.get(1)
}

@SuppressLint("DefaultLocale")
fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return String.format("%d:%02d", minutes, remainingSeconds)
}*/

package com.example.youtubevideotrimmer

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VideoTrimApp()
        }
    }
}

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoTrimApp() {
    var youtubeUrl by remember { mutableStateOf(TextFieldValue("")) }
    var videoId by remember { mutableStateOf<String?>(null) }
    var errorMessage by remember { mutableStateOf("") }
    var isVideoPlaying by remember { mutableStateOf(false) }
    var videoDuration by remember { mutableStateOf(0f) }
    var startTime by remember { mutableStateOf(0f) }
    var endTime by remember { mutableStateOf(0f) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("YouTube Video Downloader") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text("Enter YouTube URL:")
            Spacer(modifier = Modifier.height(8.dp))
            BasicTextField(
                value = youtubeUrl,
                onValueChange = { youtubeUrl = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, MaterialTheme.colorScheme.primary)
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val url = youtubeUrl.text
                    videoId = extractVideoIdFromUrl(url)
                    if (videoId == null) {
                        errorMessage = "Invalid YouTube URL"
                    } else {
                        errorMessage = ""
                        isVideoPlaying = false
                        keyboardController?.hide()
                        fetchVideoDuration(videoId!!) { duration ->
                            videoDuration = duration
                            endTime = duration
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Find Video")
            }

            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 8.dp))
            }

            videoId?.let {
                Spacer(modifier = Modifier.height(16.dp))
                if (!isVideoPlaying) {
                    val thumbnailUrl = "https://img.youtube.com/vi/$it/hqdefault.jpg"
                    val painter: Painter = rememberImagePainter(thumbnailUrl)
                    Image(painter = painter, contentDescription = "Video Thumbnail", modifier = Modifier.fillMaxWidth().height(200.dp))

                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Select interval")
                    Slider(
                        value = startTime,
                        onValueChange = { startTime = it.coerceIn(0f, endTime) },
                        valueRange = 0f..videoDuration,
                        modifier = Modifier.fillMaxWidth(),
                        colors = SliderDefaults.colors(thumbColor = MaterialTheme.colorScheme.primary)
                    )
                    Slider(
                        value = endTime,
                        onValueChange = { endTime = it.coerceIn(startTime, videoDuration) },
                        valueRange = 0f..videoDuration,
                        modifier = Modifier.fillMaxWidth(),
                        colors = SliderDefaults.colors(thumbColor = MaterialTheme.colorScheme.secondary)
                    )
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Start: ${formatTime(startTime.toInt())}")
                        Text("End: ${formatTime(endTime.toInt())}")
                    }

                    Spacer(modifier = Modifier.height(30.dp))
                    Button(onClick = { /* Handle download with interval */ }, modifier = Modifier.fillMaxWidth()) {
                        Text("Download Video")
                    }
                }
            }
        }
    }
}

fun fetchVideoDuration(videoId: String, onResult: (Float) -> Unit) {
    val apiKey = "AIzaSyBB44AqMT9inre6BaDW4oluvw0y2U9zYA0"
    val url = "https://www.googleapis.com/youtube/v3/videos?id=$videoId&part=contentDetails&key=$apiKey"

    val request = okhttp3.Request.Builder().url(url).build()
    val client = okhttp3.OkHttpClient()

    client.newCall(request).enqueue(object : okhttp3.Callback {
        override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
            response.body?.string()?.let { jsonResponse ->
                val duration = parseYouTubeDuration(jsonResponse)
                onResult(duration)
            }
        }
    })
}

fun parseYouTubeDuration(json: String): Float {
    val regex = Regex("""PT(\d+H)?(\d+M)?(\d+S)?""")
    val match = regex.find(json) ?: return 0f

    var totalSeconds = 0
    match.groups.filterNotNull().forEach { group ->
        when {
            group.value.endsWith("H") -> totalSeconds += group.value.dropLast(1).toInt() * 3600
            group.value.endsWith("M") -> totalSeconds += group.value.dropLast(1).toInt() * 60
            group.value.endsWith("S") -> totalSeconds += group.value.dropLast(1).toInt()
        }
    }
    return totalSeconds.toFloat()
}


fun extractVideoIdFromUrl(url: String): String? {
    val regex = Regex("(?:v=|youtu\\.be/|embed/|v/|watch\\?v=|/videos/|embed\\?clip_id=)([a-zA-Z0-9_-]{11})")
    val match = regex.find(url)
    return match?.groupValues?.get(1)
}

@SuppressLint("DefaultLocale")
fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return String.format("%d:%02d", minutes, remainingSeconds)
}