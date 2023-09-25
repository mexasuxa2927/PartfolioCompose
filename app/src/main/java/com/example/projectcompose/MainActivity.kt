package com.example.projectcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.projectcompose.Utils.ActionListner
import com.example.projectcompose.ViewModel.MyViewModel
import com.example.projectcompose.navigation.NavigationGraph
import com.example.projectcompose.navigation.NavigationScreen
import com.example.projectcompose.ui.theme.ProjectComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay


@AndroidEntryPoint
class MainActivity : ComponentActivity(),ActionListner {
    lateinit var navigation:NavHostController
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProjectComposeTheme {
                navigation  = rememberNavController()
                NavigationGraph(navController =navigation,this)
                var viewModel:MyViewModel   = hiltViewModel()
                LaunchedEffect(key1 = true ){
                    delay(5000)
                    if(viewModel.firebaseGetAuth().currentUser==null){
                        gotoActinNavigation(NavigationScreen.SplashScreen.route,NavigationScreen.LoginScreen.route,true)
                    }
                    else{
                        gotoActinNavigation(NavigationScreen.SplashScreen.route,NavigationScreen.MainScreen.route,true)
                    }
                }
            }
        }
    }

    override fun gotoActinNavigation(fromScreen: String, toScreen: String, inclusiveScreen: Boolean) {
        navigation.navigate(toScreen){
            popUpTo(fromScreen){
                inclusive   = inclusiveScreen
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  
}


