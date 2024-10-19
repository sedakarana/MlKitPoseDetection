package com.sedakarana.posedetection.view.screen

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Handler
import android.os.Looper
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.sedakarana.navigation.navigation.Screen
import com.sedakarana.posedetection.R
import com.sedakarana.posedetection.ui.theme.ColorMain1
import com.sedakarana.posedetection.ui.theme.ColorMain2
import com.sedakarana.posedetection.ui.theme.ColorMain3
import com.sedakarana.posedetection.ui.theme.ColorMain4
import com.sedakarana.posedetection.ui.theme.ColorSpacer
import com.sedakarana.posedetection.viewModel.HomeViewModel
import java.io.InputStream

@ExperimentalPermissionsApi
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel
) {
    val context = LocalContext.current
    var imageUri by viewModel.imageUri
    var hasImage by viewModel.hasImage
    var isLoading by remember { mutableStateOf(false) }


    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            viewModel.hasImage.value = uri != null
            viewModel.imageUri.value = uri
        })
    val gradientBrush = Brush.linearGradient(
        colors = listOf(ColorMain1, ColorMain2, ColorMain3, ColorMain4)
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        }
        Row(
            Modifier
                .fillMaxWidth()
                .height(50.dp), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.wrapContentSize(),
                text = stringResource(id = R.string.app_name),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(ColorSpacer)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize(),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    if (hasImage) {
                        AsyncImage(
                            model = imageUri,
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .fillMaxSize(),
                            contentScale = ContentScale.FillWidth
                        )
                    } else {
                        Text(
                            text = stringResource(id = R.string.s_welcome),
                            style = TextStyle(
                                fontSize = 36.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Serif,
                                brush = gradientBrush, // Text'e gradient ekliyoruz
                                shadow = Shadow( // Gölge ekliyoruz
                                    color = Color.Black.copy(alpha = 0.7f),
                                    offset = Offset(4f, 4f),
                                    blurRadius = 8f
                                )
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }
                }
            }


        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxHeight()
                    .weight(1f),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            viewModel.hasImage.value = false
                            viewModel.imageUri.value = null
                        }
                        .padding(2.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.close),
                        contentDescription = "Close Icon",
                        modifier = Modifier.size(24.dp),
                        colorFilter = ColorFilter.tint(Color.Black)
                    )
                    Text(
                        modifier = Modifier.wrapContentSize(),
                        text = stringResource(id = R.string.s_copy),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                }
            }



            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxHeight()
                    .weight(1f),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            if (hasImage) {
                                val bitmap: Bitmap? =viewModel.uriToBitmap(context, imageUri)
                                if (bitmap != null) {
                                    viewModel.pose(context = context, bitmap = bitmap)
                                    viewModel.calculatePose(bitmap = bitmap)

                                    Handler(Looper.getMainLooper()).postDelayed({
                                        navController.navigate(
                                            Screen.InfoScreen.route
                                        )
                                    }, 2000)
                                    isLoading = false

                                }
                            }
                        }
                        .padding(2.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.send),
                        contentDescription = "Send Icon",
                        modifier = Modifier.size(24.dp),
                        colorFilter = ColorFilter.tint(Color.Black)
                    )
                    Text(
                        modifier = Modifier.wrapContentSize(),
                        text = stringResource(id = R.string.s_send),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                }
            }

            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxHeight()
                    .weight(1f),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            imagePicker.launch("image/*")
                        }
                        .padding(2.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.image_gallery),
                        contentDescription = "Gallery Icon",
                        modifier = Modifier.size(24.dp),
                        colorFilter = ColorFilter.tint(Color.Black)
                    )
                    Text(
                        modifier = Modifier.wrapContentSize(),
                        text = stringResource(id = R.string.s_gallery),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                }
            }

        }
    }
}

