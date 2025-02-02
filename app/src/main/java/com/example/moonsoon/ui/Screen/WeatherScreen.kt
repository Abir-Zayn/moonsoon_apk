package com.example.moonsoon.ui.Screen

import android.health.connect.datatypes.units.Temperature
import android.widget.Space
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.moonsoon.data.entity.WeatherResponse

@Composable
fun weatherContent(weather: WeatherResponse){
        Column(
            modifier = Modifier.padding(16.dp)
                .fillMaxWidth() .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WeatherMainCard(weather = weather)
            Spacer(
                modifier = Modifier.height(24.dp)
            )
            WeatherGridCard(weather = weather)
        }
}

@Composable
fun WeatherMainCard(weather: WeatherResponse){

    //Weather Main Card Background
    Surface( modifier = Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(24.dp)),
            color = MaterialTheme.colorScheme.primaryContainer,
            tonalElevation = 4.dp
    ) {

        //Weather Main Card Content
            Column (
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                WeatherIcon(weather.weather.firstOrNull()?.main?:"Clear")
                    Spacer(modifier = Modifier.height(16.dp))

                Text( text ="${weather.main.temp.toInt()}°C",
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.primaryContainer)

                Text(
                    text = weather.weather.firstOrNull()?.description?:"",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primaryContainer
                )

                Spacer( modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ){
                        TemperatureInfo(label = "Feels Like", value = "${weather.main.feelsLike.toInt()}°C")
                        TemperatureInfo(label = "Low", value = "${weather.main.tempMin.toInt()}°C")
                        TemperatureInfo(label = "High", value = "${weather.main.tempMax.toInt()}°C")

                    }


            }
    }
}

@Composable
fun WeatherGridCard(weather: WeatherResponse){

}

@Composable
fun WeatherIcon(weather_condition: String){

}

@Composable
fun TemperatureInfo(label: String, value:String){

}

@Composable
fun WeatherDetailedCard(icon:ImageVector, value: String, label:String){

}

@Composable
fun SearchBar(value: String, onValueChange:(String)->Unit){

}

@Composable
fun LoadingIndicator(){

}

@Composable
fun ErrorView(){

}

@Composable
fun WeatherScreen(){

}

