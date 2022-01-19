package com.example.wthr2255.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wthr2255.R
import com.example.wthr2255.data.ViewModel
import com.example.wthr2255.data.WthrObj
import dagger.hilt.android.qualifiers.ApplicationContext

@Composable
fun weather(wthrViewModel: ViewModel) {

    wthrViewModel.getWeather()
    if (!wthrViewModel.isLoading.value) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }

    if (wthrViewModel.isLoading.value) {
        val wthr = wthrViewModel.result

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = wthr?.name.toString(),
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Row {
                Spacer(modifier = Modifier.weight(1F))
                Text(
                    text = (wthr?.main?.temp?.toInt()?.minus(273)).toString() + "°C",
                    fontSize = MaterialTheme.typography.h3.fontSize,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1F)
                )
                Spacer(modifier = Modifier.weight(1F))
            }
            Row {
                Spacer(modifier = Modifier.weight(1F))
                Text(
                    text = "feels like: " + (wthr?.main?.feels_like?.toInt()?.minus(273)).toString(),
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    modifier = Modifier.weight(1F)
                )
                Spacer(modifier = Modifier.weight(1F))
            }
            Spacer(modifier = Modifier.height(30.dp))
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = 10.dp,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            ){
                Row {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .height(60.dp)
                            .weight(1F)
                    ){
                        Text(text = "Humidity")
                        Text(
                            text = wthr?.main?.humidity.toString() + "%",
                            fontSize = MaterialTheme.typography.h6.fontSize,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1F)
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .height(60.dp)
                            .weight(1F)
                    ){
                        Text(text = "Wind speed")
                        Text(
                            text = wthr?.wind?.speed.toString() + "m/s",
                            fontSize = MaterialTheme.typography.h6.fontSize,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1F)
                        )
                    }
                }
            }
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = 10.dp,
                modifier = Modifier
                    .padding(
                        horizontal = 20.dp,
                        vertical = 20.dp
                    )
            ){
                Column(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.Start
                ){
                    Text(
                        text = "Weather description:",
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        fontWeight = FontWeight.Bold,
                    )
                    LazyColumn{
                        items(wthr!!.weather) { weather ->
                            Text(
                                text = weather.description
                            )
                        }
                    }
                }
            }
        }
    }
}