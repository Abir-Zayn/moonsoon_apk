package com.example.moonsoon.ui.Screen


import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Air
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.material.icons.outlined.Compress
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moonsoon.data.entity.WeatherResponse
import com.example.moonsoon.data.resource.ResourceState
import com.example.moonsoon.ui.ViewModel.WeatherViewModel
import kotlin.math.roundToInt


@Composable
fun WeatherContent(weather: WeatherResponse){
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
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .shadow(8.dp, shape = RoundedCornerShape(16.dp)),
        color = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
    )  {

        //Weather Main Card Content
            Column (
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){

                WeatherIcon(weather.weather.firstOrNull()?.main?:"Clear")
                    Spacer(modifier = Modifier.height(16.dp))

                Text( text ="${weather.main.temp}°C",
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer)

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
                       TemperatureInfo(
                            label = "Min",
                            value = "${weather.main.tempMin}°C",
                            icon = Icons.Outlined.Warning
                          )
                            TemperatureInfo(
                             label = "Max",
                             value = "${weather.main.tempMax.roundToInt()}°C",
                             icon = Icons.Outlined.Warning
                       )

                    }


            }
    }
}

@Composable
fun WeatherGridCard(weather: WeatherResponse){

    Column (verticalArrangement = Arrangement.spacedBy(16.dp)
       ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            WeatherDetailedCard(icon = Icons.Outlined.WaterDrop,
                value = "${weather.main.humidity}%",
                label = "Humidity")
            WeatherDetailedCard(icon = Icons.Outlined.Air,
                value = "${weather.wind.speed}m/s",
                label = "Wind")
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){
            WeatherDetailedCard(icon = Icons.Outlined.Compress,
                value = "${weather.main.pressure}%",
                label = "Pressure")
            WeatherDetailedCard(icon = Icons.Outlined.Visibility,
                value = "${weather.main.humidity}m/s",
                label = "Visibility")
        }
     }
}

@Composable
fun WeatherIcon(weather_condition: String){
    val icon = when(weather_condition.lowercase()){
        "clear" ->Icons.Outlined.WbSunny
        "clouds" -> Icons.Outlined.Cloud
        "rain" ->Icons.Outlined.WaterDrop
        "snow" ->Icons.Outlined.Warning
        else -> Icons.Outlined.WbSunny
    }
    Icon(
        //converting the icon to image_vector.
        imageVector = icon,
        contentDescription = weather_condition,
        modifier = Modifier.size(64.dp),
        tint = MaterialTheme.colorScheme.onPrimaryContainer
    )
}

@Composable
fun TemperatureInfo(label: String, value:String, icon:ImageVector){
    Column (
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun WeatherDetailedCard(
    icon:ImageVector,
    value: String,
    label:String,
    modifier: Modifier = Modifier
    ){

    Surface(
        modifier = Modifier.clip(RoundedCornerShape(16.dp)),
        color =  MaterialTheme.colorScheme.secondaryContainer,
        tonalElevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f)
            )
        }
    }

}

@Composable
fun SearchBar(
    value: String,
    onValueChange:(String)->Unit,
    modifier: Modifier = Modifier,
    placeholder: String ="Search City"
    ){
    val interactionSource = remember { MutableInteractionSource() }

    Surface(
        modifier = Modifier.fillMaxWidth() .clip(RoundedCornerShape(16.dp)),

    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = "Enter City Name")},
            textStyle = MaterialTheme.typography.bodyMedium,
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            },
            interactionSource = interactionSource,
            modifier = Modifier.padding(16.dp) .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),

        )

    }
}

@Composable
fun LoadingIndicator(){

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator(
            modifier = Modifier.size(48.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }


}

@Composable
fun ErrorView(message:String){
    Surface(
        modifier =  Modifier.clip(RoundedCornerShape(16.dp)) .fillMaxWidth(),
        color =  MaterialTheme.colorScheme.errorContainer,
        tonalElevation = 2.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Warning,
                contentDescription = "Error",
                tint = MaterialTheme.colorScheme.onError
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onError
            )
        }
    }
}



@Composable
fun WeatherScreen(
    viewModel : WeatherViewModel = hiltViewModel()
){
    //This allows the UI to reactively update whenever the weatherState changes.
    val weatherState by viewModel.weatherState.collectAsState()
    val cityName by viewModel.cityName.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()

            .background(brush = Brush.verticalGradient(
                colors = listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.outline)
            ))
    ){
        Column(
            modifier =  Modifier.fillMaxSize() .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Search Bar -> Upon on the user input on search bar the city will be placed on the search bar
            // this happens on the onValueChange function
            SearchBar(
                value = cityName,
                onValueChange = {viewModel.setCityName(it)}
            )

            Spacer(modifier = Modifier.height(16.dp))

            AnimatedContent(
                targetState = weatherState,
                transitionSpec = {
                    fadeIn() + slideInVertically() togetherWith fadeOut() + slideOutVertically()
                }, label = ""
            ) { state->
                when(state){
                    is ResourceState.Loading -> LoadingIndicator()
                    is ResourceState.Success -> WeatherContent(state.data)
                    is ResourceState.Error -> ErrorView(state.error.toString())
                }

            }

        }
    }


}

