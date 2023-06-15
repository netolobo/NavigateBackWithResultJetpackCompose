package com.netoloboapps.navigatebackwithresult

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.netoloboapps.navigatebackwithresult.ui.theme.NavigateBackWithResultTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigateBackWithResultTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "Screen1"
                ) {
                    composable("Screen1") { navBackResult ->
                        Column {
                            //get result from screen 2
                            //you need to use the same key ("book") you used to set the value on screen 2
                            navBackResult.savedStateHandle.get<Book>("book")?.let {
                                Text(text= it.title)
                            }
                            Button(
                                onClick = {
                                    //navigate to screen 2
                                    navController.navigate("Screen2")
                                }
                            ) {
                                Text(
                                    text = "Navigate to Screen 2"
                                )
                            }
                        }
                    }
                    composable("Screen2") {
                        Column {
                            var text by remember {
                                mutableStateOf("")
                            }
                            OutlinedTextField(
                                value = text,
                                onValueChange = { text = it }
                            )
                            Button(
                                onClick = {
                                    //navigate back to screen 2
                                    navController.popBackStack()
                                    //set "book" as key and Book as value
                                    navController.currentBackStackEntry
                                        ?.savedStateHandle
                                        ?.set("book", Book(title = text))
                                }
                            ) {
                                Text(
                                    text = "OK"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
