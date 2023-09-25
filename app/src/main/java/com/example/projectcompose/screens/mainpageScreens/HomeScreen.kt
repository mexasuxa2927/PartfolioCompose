package com.example.projectcompose.screens.mainpageScreens


import android.annotation.SuppressLint
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectcompose.DataMadels.ProductData
import com.example.projectcompose.R
import com.example.projectcompose.Utils.ActionListner
import com.example.projectcompose.ViewModel.MyViewModel
import com.example.projectcompose.ViewModel.SharedViewModel
import com.example.projectcompose.navigation.NavigationScreen
import com.skydoves.landscapist.glide.GlideImage
import androidx.hilt.navigation.compose.hiltViewModel as hiltViewModel1

@Composable
fun HomeScreen(actionListner: ActionListner,sharedViewModel: SharedViewModel) {
    ScreenUi(actionListner,sharedViewModel)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
private fun ScreenUi(actionListner: ActionListner,sharedViewModel: SharedViewModel){

    val myViewmodel : MyViewModel  = hiltViewModel1()

    val listdata  = remember{ mutableStateListOf<ProductData>() }
    var searchfield  by remember { mutableStateOf(TextFieldValue("")) }
    val data  by myViewmodel.getAllProduct(if(searchfield.text.trim().length>0)searchfield.text.trim() else "").observeAsState(initial = Result.failure(
        Throwable()
    ))
    data .onSuccess {
        listdata.clear()
        listdata.addAll(it)
    }.onFailure {
        listdata.clear()
        listdata.addAll(emptyList())

    }


    LazyColumn(modifier = Modifier){
        item {
            Column(modifier = Modifier.padding(top = 5.dp, start = 20.dp, end = 20.dp)) {

                    TextField(value = searchfield, onValueChange ={
                        searchfield=it
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .clip(RoundedCornerShape(25))
                        .background(
                            shape = RoundedCornerShape(25),
                            color = colorResource(id = R.color.white_gray)
                        )

                        .clickable {

                        },
                        placeholder = { Text(text = "Search", color = colorResource(id = R.color.white_gray), fontSize = 22.sp, fontFamily = FontFamily(
                            Font(R.font.spartan)
                        ), fontWeight = FontWeight.Bold) },
                        leadingIcon = {
                            Icon(painter = painterResource(id = R.drawable.search_icon), contentDescription =""
                                , modifier = Modifier
                                    .height(24.dp)
                                    .width(24.dp),
                                tint = colorResource(id = R.color.white_gray)
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
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
                    Spacer(modifier = Modifier.height(10.dp))


            }

        }
        
        item {
            HorizontalRecycler()
        }
        items(listdata.size) {

            ItemViewList(data = listdata[it],actionListner,sharedViewModel)
        }
        item {
            Spacer(modifier = Modifier.height(50.dp))
        }

    }

}




@SuppressLint("SuspiciousIndentation", "UnrememberedMutableState")
@Composable
fun ItemViewList(data: ProductData,actionListner: ActionListner,sharedViewModel: SharedViewModel) {
    val viewModel:MyViewModel  = hiltViewModel1()
    val checklike by viewModel.checkLike(data).observeAsState(initial = false)

    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            sharedViewModel.setData(data)
            actionListner.gotoActinNavigation(
                NavigationScreen.MainScreen.route,
                NavigationScreen.ItemScreen.route + "/${data.id}",
                false
            )
            viewModel.setLIke(productData = data)
        }
        .padding(horizontal = 20.dp)
        .height(250.dp), shape = RoundedCornerShape(5), elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)) {
            Box(modifier = Modifier.fillMaxSize()){
                GlideImage(imageModel = {  data.photo})
                Column(modifier = Modifier.align(Alignment.TopEnd).padding(top = 20.dp, end = 20.dp).background(color = Color.White, shape = RoundedCornerShape(50))
                    .clip(RoundedCornerShape(50))
                    .height(40.dp).clickable {
                        if(checklike){
                            viewModel.removeLike(data)
                        }else{
                            viewModel.setLIke(data)
                        }
                    }
                    .width(40.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(painter = painterResource(id = R.drawable.heart_icon), contentDescription = "",
                        tint = if(checklike) colorResource(id = R.color.orange)else Color.Gray,
                        modifier =Modifier.height(24.dp).width(24.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = data.product_name!!, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp), color = Color.Black)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = data.price+"summ", modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp), color = Color.Gray)
        Spacer(modifier = Modifier.height(10.dp))



}


@Composable
fun HorizontalRecycler() {
    LazyRow{
        item{

            Box{
                Card(modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .width(320.dp)
                    .height(180.dp)
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                    , shape = RoundedCornerShape(5) ) {
                    Row(modifier = Modifier
                        .fillMaxSize()
                        .background(color = colorResource(id = R.color.orange))) {
                        Text(text = "Woman", fontSize = 30.sp , color = Color.White, modifier = Modifier.padding(top = 30.dp, start = 20.dp), fontWeight = FontWeight.Bold)
                    }

                }

                Image(painter = painterResource(id = R.drawable.woman_image), contentDescription ="" , modifier = Modifier
                    .height(250.dp)
                    .width(180.dp)
                    .padding(bottom = 10.dp, end = 10.dp)
                    .align(Alignment.BottomEnd), contentScale = ContentScale.Crop)

            }


        }
        item{

            Box{
                Card(modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .width(320.dp)
                    .height(180.dp)
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                    , shape = RoundedCornerShape(5) ) {
                    Row(modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Gray)) {
                        Text(text = "Man", fontSize = 30.sp , color = Color.White, modifier = Modifier.padding(top = 30.dp, start = 20.dp), fontWeight = FontWeight.Bold)
                    }

                }

                Image(painter = painterResource(id = R.drawable.man_image), contentDescription ="" , modifier = Modifier
                    .height(250.dp)
                    .width(180.dp)
                    .padding(bottom = 10.dp, end = 10.dp)
                    .align(Alignment.BottomEnd), contentScale = ContentScale.Crop)

            }


        }
        item{

            Box(modifier = Modifier.height(250.dp)){
                Card(modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .width(320.dp)
                    .height(180.dp)
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                    , shape = RoundedCornerShape(5) ) {
                    Row(modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Blue)) {
                        Text(text = "CHildren", fontSize = 30.sp , color = Color.White, modifier = Modifier.padding(top = 30.dp, start = 20.dp), fontWeight = FontWeight.Bold)
                    }

                }

                Image(painter = painterResource(id = R.drawable.child_image), contentDescription ="" , modifier = Modifier
                    .height(200.dp)
                    .width(90.dp)
                    .padding(bottom = 10.dp, end = 10.dp)
                    .align(Alignment.BottomEnd), contentScale = ContentScale.Crop)

            }


        }


    }



}




@Preview(showBackground = true)
@Composable
private fun ShowUI(){

}