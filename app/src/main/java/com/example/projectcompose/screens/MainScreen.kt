package com.example.projectcompose.screens
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Text
import androidx.compose.material.rememberDrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.projectcompose.BottomNavigation.BottomNavigationMainScreen
import com.example.projectcompose.NavigationDrawer.NavigationDrowerItem
import com.example.projectcompose.R
import com.example.projectcompose.Utils.ActionListner
import kotlinx.coroutines.launch

@Composable
fun MainScreen(actionListner: ActionListner) {
ScreenUI(actionListner = actionListner)
}
@Composable
private fun  ScreenUI(actionListner: ActionListner?){
    val mdrowerstate  = rememberDrawerState(DrawerValue.Closed)
    val scope  = rememberCoroutineScope()
    
    Column(modifier = Modifier.fillMaxSize()) {

        ModalDrawer(

            drawerState = mdrowerstate,
            drawerContent = {
                DrowerContentUi()
            },
            content = {
                Column(Modifier.fillMaxSize()) {
                    Column(Modifier.weight(0.5f)) {
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(horizontal = 25.dp)) {
                            Image(painter = painterResource(id = R.drawable.menu_icon), contentDescription ="",modifier = Modifier
                                .align(Alignment.CenterStart)
                                .width(24.dp)
                                .height(24.dp)
                                .clickable {
                                    scope.launch {
                                        mdrowerstate.open()
                                    }
                                })
                            Text(text = "E-Shop", modifier = Modifier.align(Alignment.Center), fontSize = 22.sp, fontFamily = FontFamily(Font(R.font.spartan)), fontWeight = FontWeight.Bold)
                            Image(painter = painterResource(id = R.drawable.shopping_icon), contentDescription ="", modifier = Modifier
                                .align(
                                    Alignment.CenterEnd
                                )
                                .width(24.dp)
                                .height(24.dp) )
                        }
                    }
                    Column(Modifier.weight(9f)) {
                        BottomNavigationMainScreen()
                    }
                }
            }
        )



    }
}

@Composable
private fun DrowerContentUi() {
    val brush   =Brush.verticalGradient(listOf(colorResource(id = R.color.orange),Color.White))
    Column(modifier = Modifier.fillMaxWidth()) {
        Column(
            Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(brush = brush), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text(text = "E-SHOP", fontFamily = FontFamily(Font(R.font.spartan)), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 50.sp)
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            drawerItem(navigationDrowerItem = NavigationDrowerItem.Profile)
            Spacer(modifier = Modifier.height(10.dp))
            drawerItem(navigationDrowerItem = NavigationDrowerItem.Settings)
            Spacer(modifier = Modifier.height(10.dp))
            drawerItem(navigationDrowerItem = NavigationDrowerItem.Info)
        }
    }
}

@Composable
fun drawerItem(navigationDrowerItem: NavigationDrowerItem) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
        .fillMaxWidth()
        .padding(start = 20.dp)) {
        Icon(painter = painterResource(id = navigationDrowerItem.icon) , contentDescription ="", modifier = Modifier
            .width(32.dp)
            .height(32.dp), tint = Color.Black )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = navigationDrowerItem.title, fontFamily = FontFamily(Font(R.font.spartan)), fontSize = 28.sp, color = Color.Black, fontWeight = FontWeight.Bold , textAlign = TextAlign.Center)
        
    }
}



@Preview(showBackground = true)
@Composable
private fun showUI(){
    ScreenUI(actionListner = null)
}