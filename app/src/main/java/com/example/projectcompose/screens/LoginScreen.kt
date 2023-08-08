package com.example.projectcompose.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import com.example.projectcompose.R
import com.example.projectcompose.Utils.ActionListner
import com.example.projectcompose.ViewModel.MyViewModel
import com.example.projectcompose.navigation.NavigationScreen

@Composable
fun LoginScreen(actionListner: ActionListner) {
    var viewmodel:MyViewModel  = hiltViewModel()
    ScreenUI(actionListner,viewmodel)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenUI(actionListner: ActionListner?,viewmodel:MyViewModel?) {

    var email by remember{ mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var context  = LocalContext.current
    var lifecycleOwner = LocalLifecycleOwner.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(weight = 9f)
            .padding(horizontal = 25.dp), horizontalAlignment = Alignment.CenterHorizontally) {




            Spacer(modifier = Modifier.height(80.dp))
            Column(
                modifier = Modifier
                    .height(80.dp)
                    .width(80.dp)
                    .background(
                        colorResource(id = R.color.orange),
                        shape = RoundedCornerShape(40.dp)
                    ),

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "E",
                    color = Color.White,
                    fontSize = 38.sp,
                    fontFamily = FontFamily(
                        Font(
                            R.font.spartan,
                            weight = FontWeight.Bold,
                            style = FontStyle.Italic
                        )
                    )
                )
            }

            Spacer(modifier = Modifier.height(120.dp))
            Text(text = "Login to e-Shop",
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = FontFamily(
                    Font(
                        R.font.spartan,
                        weight = FontWeight.Bold,
                        style = FontStyle.Italic
                    )
                )

            )
            Spacer(modifier = Modifier.height(20.dp))




            //Username 
            TextField(value =email , onValueChange ={
                email=it
            }, modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .border(
                    2.dp,
                    colorResource(id = R.color.white_gray),
                    shape = RoundedCornerShape(25)
                )
                .clickable {

                },
                placeholder = { Text(text = "Email", color = colorResource(id = R.color.white_gray), fontSize = 22.sp, fontFamily = FontFamily(Font(R.font.spartan)), fontWeight = FontWeight.Bold) },
                leadingIcon = {
                    Icon(painter = painterResource(id = R.drawable.email_icon), contentDescription =""
                        , modifier = Modifier
                            .height(24.dp)
                            .width(24.dp),
                        tint = colorResource(id = R.color.white_gray)
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(color = Color.Black,
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.spartan)),
                    fontWeight = FontWeight.Bold
                    ),
                singleLine = true


            )
            Spacer(modifier = Modifier.height(20.dp))
            ///Password
            TextField(value =password , onValueChange ={
                password=it
            }, modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .border(
                    2.dp,
                    colorResource(id = R.color.white_gray),
                    shape = RoundedCornerShape(25)
                ),
                placeholder = { Text(text = "Password", color = colorResource(id = R.color.white_gray), fontSize = 22.sp, fontFamily = FontFamily(Font(R.font.spartan)), fontWeight = FontWeight.Bold) },
                leadingIcon = {
                    Icon(painter = painterResource(id = R.drawable.password_icon), contentDescription =""
                        , modifier = Modifier
                            .height(24.dp)
                            .width(24.dp),
                        tint = colorResource(id = R.color.white_gray)
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(color = Color.Black,
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.spartan)),
                    fontWeight = FontWeight.Bold,


                ),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()

            )

            val context  =  LocalContext.current
            Spacer(modifier = Modifier.height(20.dp))
            Text(modifier = Modifier.clickable { Toast.makeText(context,"asdawdadw",Toast.LENGTH_LONG).show() }  ,text = "Forgot Password?", fontFamily = FontFamily(Font(R.font.spartan)), color = Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))

            Column(modifier = Modifier
                .height(64.dp)
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.orange),
                    shape = RoundedCornerShape(25)
                )
                .clickable {
                    if (email.text
                            .trim()
                            .isNotEmpty() && password.text
                            .trim()
                            .isNotEmpty()
                    ) {
                        if (checkEmail(email.text)) {
                            viewmodel!!
                                .logInUser(email.text, password.text)
                                .observe(lifecycleOwner,
                                    Observer {
                                        if (it.isSuccessful) {
                                            actionListner!!.gotoActinNavigation(
                                                NavigationScreen.LoginScreen.route,
                                                NavigationScreen.MainScreen.route,
                                                true
                                            )
                                        } else {
                                            Log.d("@@@@", "ScreenUI:${it.exception}")
                                            Toast
                                                .makeText(context, "email or password  is wrong", Toast.LENGTH_SHORT)
                                                .show()
                                        }
                                    })
                        } else {

                        }
                    } else {
                        Toast
                            .makeText(context, "fill all field", Toast.LENGTH_SHORT)
                            .show()
                    }


                }, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Text(text ="Logind", color = Color.White, fontSize = 22.sp, fontFamily = FontFamily(Font(R.font.spartan)), fontWeight = FontWeight.Bold )
            }



            
        }
        
        Row(modifier = Modifier
            .fillMaxSize()
            .weight(weight = 1f), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            
            Text(text = "Don’t have an account?", fontSize =16.sp, color = colorResource(id = R.color.white_gray) )
            Text(text = "Sign Up", fontSize = 18.sp, color = colorResource(id = R.color.orange), modifier = Modifier.clickable { actionListner!!.gotoActinNavigation(NavigationScreen.LoginScreen.route,NavigationScreen.SignUpScreen.route,true) })
            
        }
        

    }



}



@Preview(showBackground = true)
@Composable
private fun showUI(){
    ScreenUI(null,null)
}