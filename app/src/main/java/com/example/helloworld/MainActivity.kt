package com.example.helloworld

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.helloworld.ui.theme.HelloWorldTheme
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloWorldTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppContent(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

fun showHelloWorld(context: Context) {
    val alertDialogBuilder = AlertDialog.Builder(context)
    alertDialogBuilder.setTitle("Уведомление")
    alertDialogBuilder.setMessage("Вы нажали на кнопку!")
    alertDialogBuilder.setPositiveButton("OK") { _, _ ->
        // Ваш обработчик события для положительного ответа
    }
    alertDialogBuilder.setNegativeButton("Отмена") { dialog, _ ->
        dialog.cancel()
    }
    alertDialogBuilder.create().show()
}

@Composable
fun AppContent(modifier: Modifier = Modifier) {
    var isTextVisible by remember { mutableStateOf(false) }
    var isHelloWorldStepEnabled by remember { mutableStateOf(true) }
    var isAlertStepEnabled by remember { mutableStateOf(false) }
    var isAlertShowed by remember { mutableStateOf(false) }
    var isImageStepEnabled by remember { mutableStateOf(false) }
    var isImageShowed by remember { mutableStateOf(false) }

    Surface(color = Color.LightGray, modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
        ) {

            if (isHelloWorldStepEnabled) {
                Row {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                isTextVisible = !isTextVisible;
                                isAlertStepEnabled = true;
                            },
                            modifier = Modifier
                                .padding(16.dp),
                        ) {
                            Text(text = if (!isTextVisible) "Показать надпись" else "Скрыть надпись")
                        }

                        if (isTextVisible) {
                            Text(text = "Hello, World!")
                        }
                    }
                }
            }

            if (isAlertStepEnabled) {
                Row {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = { isAlertShowed = !isAlertShowed },
                            modifier = Modifier
                                .padding(16.dp),
                        ) {
                            Text(text = "Показать модальное окно")
                        }

                        if (isAlertShowed) {
                            AlertDialog(
                                text = {
                                    Text(text = "Моя модалочка")
                                },
                                onDismissRequest = { isAlertShowed = false; isImageStepEnabled = true; },
                                confirmButton = {
                                    Button(
                                        onClick = { isAlertShowed = false; isImageStepEnabled = true; },
                                        modifier = Modifier
                                            .padding(16.dp),
                                    ) {
                                        Text(text = "Закрыть мою модалочку!")
                                    }
                                }
                            )
                        }
                    }
                }
            }

            if (isImageStepEnabled) {
                Row {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        if (isHelloWorldStepEnabled) {
                            Button(
                                onClick = {
                                    isImageShowed = !isImageShowed;
                                    isAlertStepEnabled = false;
                                    isHelloWorldStepEnabled = false
                                },
                                modifier = Modifier
                                    .padding(16.dp),
                            ) {
                                Text(text = "Показать картиночку")
                            }
                        }

                        if (isImageShowed) {
                            Image(
                                painter = painterResource(id = R.drawable.android_chrome_512x512),
                                contentDescription = stringResource(id = R.string.app_name)
                            )
                        }
                    }
                }
            }
        }
    }
}

fun handleButtonClick() {
    println("button clicked");
}

@Preview(showBackground = true)
@Composable
fun AppContentPreview() {
    HelloWorldTheme {
        AppContent()
    }
}