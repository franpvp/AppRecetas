package com.example.semana01

import RecuperarContrasenaForm
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.semana01.ui.theme.Semana01Theme
import com.example.semana01.Home


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Semana01Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        MyApp()
                    }
                }

            }
        }
    }
}


@Composable
fun Formulario() {
    var texto by remember { mutableStateOf("") }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp)
    ){
        Text( text = "Ingrese su nombre: "
            , modifier = Modifier
                .background(Color.Blue)
                .fillMaxWidth()
                .padding(16.dp)
                .border(2.dp, Color.Black)
                .shadow(4.dp)
        )
        TextField(
            value = texto,
            onValueChange = { texto = it },
            label = { Text("Input Text") },
            placeholder = { Text("Escribe aqui ....")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)

        )
        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
        ) { Text(text = "Enviar") }

    }
}

@Preview(showBackground = true)
@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            // Pasamos navController al Login Composable
            Login(
                onLoginClick = { navController.navigate("home") },
                onRegisterClick = { navController.navigate("register") },
                onForgotPasswordClick = { navController.navigate("forgotPassword") }
            )
        }
        composable("register") {
            Registro(
                onRegisterSuccess = { navController.popBackStack() }
            )
        }
        composable("forgotPassword") {
            RecuperarContrasenaForm(
                onEmailSent = { navController.popBackStack() } // Regresa a la pantalla de login
            )
        }
        // Pantalla "home" con menú lateral habilitado
        composable("home") {
            Home()
        }

        // Ejemplo de pantalla adicional, sin menú lateral
        composable("profile") {
            Perfil(
                "Francisca",
                "Valdivia",
                "franpvp.98@gmail.com",
                "949199315",
                "30/03/1998"
            )
        }

    }
}