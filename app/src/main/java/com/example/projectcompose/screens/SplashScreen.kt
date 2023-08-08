package com.example.projectcompose.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projectcompose.R


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(){
    ScreenUI()
}

@Composable
private fun ScreenUI(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.orange)), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

        Image(painter = painterResource(id = R.drawable.component_1__2_), contentDescription = "")
        Text(text = "E-SHOP", fontSize =25.sp, color = Color.White )
    }
}

@Preview(showBackground = true)
@Composable
private fun showUI(){
    ScreenUI()
}