package com.example.projectcompose.screens.mainpageScreens

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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.projectcompose.DataMadels.ProductData
import com.example.projectcompose.R
import com.example.projectcompose.Utils.ActionListner
import com.example.projectcompose.ViewModel.MyViewModel
import com.example.projectcompose.ViewModel.SharedViewModel
import com.example.projectcompose.navigation.NavigationScreen

@Composable
fun HeartScreen(actionListner: ActionListner,sharedViewModel: SharedViewModel) {
    ScreenUi(actionListner,sharedViewModel)
}

@Composable
private fun ScreenUi(actionListner: ActionListner, sharedViewModel: SharedViewModel){
    val myViewModel:MyViewModel  = hiltViewModel()
    val listlike = myViewModel.getLikeList().observeAsState(initial = Result.failure(Throwable()))
    var data  = remember{ mutableStateListOf<ProductData>() }

    listlike.value.onSuccess {
        data.clear()
        data.addAll(it)
    }.onFailure {
        data.clear()
        data.addAll(emptyList())
    }


    LazyColumn{
        items(count =data.size){
            ItemViewLazyColumn(data = data[it],data,sharedViewModel ,actionListner)
        }
        item {
            Spacer(modifier = Modifier.height(50.dp))
        }
    }

}

@Composable
private fun ItemViewLazyColumn(data: ProductData, list: SnapshotStateList<ProductData>,sharedViewModel :SharedViewModel,actionListner :ActionListner) {
    var viewModel:MyViewModel = hiltViewModel()
    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight().clickable {
            sharedViewModel.setData(data)
            actionListner.gotoActinNavigation(
                NavigationScreen.MainScreen.route,
                NavigationScreen.ItemScreen.route + "/${data.id}",
                false
            )
        }
        .padding(horizontal = 24.dp, vertical = 5.dp)){
        Card(
            Modifier
                .fillMaxWidth()
                .height(130.dp), shape = RoundedCornerShape(8), elevation = 8.dp){
            Row(Modifier.fillMaxSize()) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .weight(4f)
                    .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Card(shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxSize()) {
                        com.skydoves.landscapist.glide.GlideImage(imageModel = {data.photo})
                    }

                }
                Column(modifier = Modifier
                    .fillMaxSize()
                    .weight(5f)
                    .padding(top = 16.dp, start = 10.dp)
                    ) {
                    Text(text =data.product_name!!, color = Color.Black, fontSize = 14.sp, fontWeight = FontWeight.Bold, maxLines = 1)
                    Text(text =data.price!!, color = Color.Gray, fontSize = 14.sp, fontWeight = FontWeight.Bold, maxLines = 1)

                }
                Box(modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(top = 10.dp)){

                    Icon(painter = painterResource(id = R.drawable.delete_icon), contentDescription ="", tint = colorResource(
                        id = R.color.white_gray
                    ),modifier = Modifier
                        .height(24.dp)
                        .width(24.dp)
                        .align(
                            Alignment.TopCenter
                        )
                        .clickable {
                            viewModel.removeLike(data)
                            list.remove(data)
                        } )

                }

            }
        }
    }

}



@Preview
@Composable
private fun checkUi(){

}