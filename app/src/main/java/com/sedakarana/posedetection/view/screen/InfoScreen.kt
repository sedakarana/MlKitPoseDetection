package com.sedakarana.posedetection.view.screen

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.asImageBitmap
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
import com.sedakarana.navigation.navigation.Screen
import com.sedakarana.posedetection.R
import com.sedakarana.posedetection.ui.theme.ColorMain1
import com.sedakarana.posedetection.ui.theme.ColorMain2
import com.sedakarana.posedetection.ui.theme.ColorMain3
import com.sedakarana.posedetection.ui.theme.ColorMain4
import com.sedakarana.posedetection.ui.theme.ColorSpacer
import com.sedakarana.posedetection.utils.ImageUtils
import com.sedakarana.posedetection.viewModel.HomeViewModel
import java.io.InputStream
import java.lang.Exception

@Composable
fun InfoScreen(
    navController: NavController,
    viewModel: HomeViewModel
) {
    val context = LocalContext.current
    var bitmap by viewModel.newBitmap
    var newAngle by viewModel.newAngle
    var showType by viewModel.showType



    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                    if(showType.contentEquals("image")){
                        bitmap?.let {
                            Image(
                                bitmap = it.asImageBitmap(), // Bitmap'i ImageBitmap'e çeviriyoruz
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .fillMaxSize(),
                                contentScale = ContentScale.FillWidth
                            )
                        }
                    }else
                    {
                        Text(
                            text = newAngle.toString(),
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Serif,
                                color = Color.Black,
                                shadow = Shadow( // Gölge ekliyoruz
                                    color = Color.Black.copy(alpha = 0.3f),
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
                            navController.popBackStack()
                        }
                        .padding(2.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = "Back Icon",
                        modifier = Modifier.size(24.dp),
                        colorFilter = ColorFilter.tint(Color.Black)
                    )
                    Text(
                        modifier = Modifier.wrapContentSize(),
                        text = stringResource(id = R.string.s_back),
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
                            viewModel.showType.value="text"
                        }
                        .padding(2.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.angle),
                        contentDescription = "Text Info Icon",
                        modifier = Modifier.size(24.dp),
                        colorFilter = if(showType.contentEquals("text")) ColorFilter.tint(
                            ColorSpacer) else ColorFilter.tint(Color.Black)
                    )
                    Text(
                        modifier = Modifier.wrapContentSize(),
                        text = stringResource(id = R.string.s_info),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = if(showType.contentEquals("text")) ColorSpacer else Color.Black
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
                            viewModel.showType.value="image"
                        }
                        .padding(2.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.gallery),
                        contentDescription = "Text Info Icon",
                        modifier = Modifier.size(24.dp),
                        colorFilter = if(showType.contentEquals("image")) ColorFilter.tint(
                            ColorSpacer) else ColorFilter.tint(Color.Black)
                    )
                    Text(
                        modifier = Modifier.wrapContentSize(),
                        text = stringResource(id = R.string.s_info1),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = if(showType.contentEquals("image")) ColorSpacer else Color.Black
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
                            ImageUtils.saveImage(bitmap!!, context)
                        }
                        .padding(2.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.download),
                        contentDescription = "Download Icon",
                        modifier = Modifier.size(24.dp),
                        colorFilter = ColorFilter.tint(Color.Black)
                    )
                    Text(
                        modifier = Modifier.wrapContentSize(),
                        text = stringResource(id = R.string.s_download),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                }
            }

        }
    }
}

fun uriToBitmap(context: Context, uri: Uri): Bitmap? {
    return try {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        BitmapFactory.decodeStream(inputStream)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}