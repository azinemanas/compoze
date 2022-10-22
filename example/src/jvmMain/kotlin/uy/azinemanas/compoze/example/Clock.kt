package uy.azinemanas.compoze.example

import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import uy.azinemanas.compoze.components.*

@Composable
fun Clock() {
    val scope = rememberCoroutineScope()
    var clock by remember { mutableStateOf(Date()) }

    LaunchedEffect(Unit) {
        scope.launch {
            while(true) {
                delay(1_000)
                clock = Date()
            }
        }
    }

    Text(SimpleDateFormat("hh:mm:ss dd/MM/yyyy").format(clock))
}