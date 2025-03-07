package com.example.youtubevideotrimmer

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

    // State variables for hours, minutes, and seconds as Int
    var startHours by remember { mutableIntStateOf(0) }
    var startMinutes by remember { mutableIntStateOf(0) }
    var startSeconds by remember { mutableIntStateOf(0) }

    var endHours by remember { mutableIntStateOf(0) }
    var endMinutes by remember { mutableIntStateOf(0) }
    var endSeconds by remember { mutableIntStateOf(0) }

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
                    // Display video thumbnail
                    val thumbnailUrl = "https://img.youtube.com/vi/$it/hqdefault.jpg"
                    val painter: Painter = rememberImagePainter(thumbnailUrl)
                    Image(
                        painter = painter,
                        contentDescription = "Video Thumbnail",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )

                    // Duration input boxes
                    Spacer(modifier = Modifier.height(30.dp))

                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Start")

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("h")
                            // Hours input with numeric filter
                            BasicTextField(
                                value = startHours.toString(),
                                onValueChange = {
                                    if (it.all { int -> int.isDigit() }) {
                                        startHours = it.toInt()
                                    }
                                },
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                modifier = Modifier
                                    .weight(1f)
                                    .border(1.dp, MaterialTheme.colorScheme.primary)
                                    .padding(8.dp)
                            )

                            Text("m")

                            // Minutes input with numeric filter
                            BasicTextField(
                                value = startMinutes.toString(),
                                onValueChange = {
                                    if (it.all { int -> int.isDigit() }) {
                                        startMinutes = it.toInt()
                                    }
                                },
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                modifier = Modifier
                                    .weight(1f)
                                    .border(1.dp, MaterialTheme.colorScheme.primary)
                                    .padding(8.dp)
                            )

                            Text("s")
                            // Seconds input with numeric filter
                            BasicTextField(
                                value = startSeconds.toString(),
                                onValueChange = {
                                    if (it.all { int -> int.isDigit() }) {
                                        startSeconds = it.toInt()
                                    }
                                },
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                modifier = Modifier
                                    .weight(1f)
                                    .border(1.dp, MaterialTheme.colorScheme.primary)
                                    .padding(8.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(5.dp))
                        Text("End")

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("h")
                            // Hours input with numeric filter
                            BasicTextField(
                                value = endHours.toString(),
                                onValueChange = {
                                    if (it.all { int -> int.isDigit() }) {
                                        endHours = it.toInt()
                                    }
                                },
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                modifier = Modifier
                                    .weight(1f)
                                    .border(1.dp, MaterialTheme.colorScheme.primary)
                                    .padding(8.dp)
                            )

                            Text("m")
                            // Minutes input with numeric filter
                            BasicTextField(
                                value = endMinutes.toString(),
                                onValueChange = {
                                    if (it.all { int -> int.isDigit() }) {
                                        endMinutes = it.toInt()
                                    }
                                },
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                modifier = Modifier
                                    .weight(1f)
                                    .border(1.dp, MaterialTheme.colorScheme.primary)
                                    .padding(8.dp)
                            )

                            Text("s")
                            // Seconds input with numeric filter
                            BasicTextField(
                                value = endSeconds.toString(),
                                onValueChange = {
                                    if (it.all { int -> int.isDigit() }) {
                                        endSeconds = it.toInt()
                                    }
                                },
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                modifier = Modifier
                                    .weight(1f)
                                    .border(1.dp, MaterialTheme.colorScheme.primary)
                                    .padding(8.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(5.dp))

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
                            Text("Download Video")
                        }
                    }
                }
            }
        }
    }
}

// Function to extract YouTube video ID from a URL
fun extractVideoIdFromUrl(url: String): String? {
    val regex =
        Regex("(?:v=|youtu\\.be/|embed/|v/|watch\\?v=|/videos/|embed\\?clip_id=)([a-zA-Z0-9_-]{11})")
    val match = regex.find(url)
    return match?.groupValues?.get(1)
}