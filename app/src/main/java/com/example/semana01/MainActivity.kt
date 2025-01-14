package com.example.semana01

import RecuperarContrasenaForm
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.semana01.components.Contacto
import com.example.semana01.components.Home
import com.example.semana01.ui.theme.Semana01Theme
import com.example.semana01.components.Login
import com.example.semana01.components.Menu
import com.example.semana01.components.MisMensajes
import com.example.semana01.components.Perfil
import com.example.semana01.components.Registro
import com.example.semana01.utils.UserManager


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
        logRegisteredUsers(this)
    }
}

private fun logRegisteredUsers(context: Context) {
    // Usamos 'this' para obtener el contexto de la actividad actual


    // Obtener la lista de usuarios desde UserManager
    val users = UserManager.getUsersFromPrefs(context)

    // Verificar si hay usuarios y loguearlos
    if (users.isNotEmpty()) {
        users.forEach { user ->
            Log.d("MainActivity", "Usuario registrado: Email: ${user.email}, Nombre: ${user.firstName}, Apellido: ${user.lastName}, Password: ${user.password}")
        }
    } else {
        Log.d("MainActivity", "No hay usuarios registrados.")
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
    var selectedTab by remember { mutableStateOf(0) }

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            Login(
                onLoginClick = { navController.navigate("home") },
                onRegisterClick = { navController.navigate("register") },
                onForgotPasswordClick = { navController.navigate("forgotPassword") }
            )
        }
        composable("register") {
            Registro(
                navController = navController,
                onRegisterSuccess = { navController.popBackStack() }
            )
        }
        composable("forgotPassword") {
            // Aquí se pasa el callback onEmailSent que navegará hacia atrás al login
            RecuperarContrasenaForm(
                navController = navController,
                onCodeSent = { navController.popBackStack() } // Regresa a la pantalla de login
            )
        }
        composable("home") {
            Home( navController = navController)
        }

        composable("mensajes") {
            MisMensajes( navController = navController)
        }

        composable("perfil") {
            Perfil( navController = navController)
        }

        composable("menu") {
            Menu(
                navController = navController
            )
        }
        composable("contacto") {
            Contacto(
                navController = navController
            )
        }
    }
}