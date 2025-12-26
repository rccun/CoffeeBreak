package org.coffeebreak.ru

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dagger.hilt.android.AndroidEntryPoint
import org.coffeebreak.ru.cafemap.CafeMapScreen
import org.coffeebreak.ru.common.BottomNav
import org.coffeebreak.ru.login.LoginScreen
import org.coffeebreak.ru.main.MainScreen
import org.coffeebreak.ru.menu.MenuScreen
import org.coffeebreak.ru.create_order.CreateOrderScreen
import org.coffeebreak.ru.gift.GiftScreen
import org.coffeebreak.ru.order.OrderScreen
import org.coffeebreak.ru.signup.SignUpScreen
import org.coffeebreak.ru.splash.SplashScreen
import org.coffeebreak.ru.startup.StartUpScreen
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.MyCoffeeBreakTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val bottomBars = listOf(
                "menu",
                "gift",
                "order"
            )
            Log.e("", ": $bottomBars");
            val entry = navController.currentBackStackEntryAsState().value
            val currentRoute = entry?.destination?.route

            val isBottomBar = currentRoute?.let { route ->
                bottomBars.any { route.startsWith(it) }
            } == true
//            val isBottomBar = currentRoute in bottomBars


            MyCoffeeBreakTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MainTheme.colorScheme.bg
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        containerColor = Color.Transparent,
                    ) { innerPadding ->
                        Box(
                            modifier = Modifier.padding(innerPadding)

                        ) {


                            NavHost(
                                navController = navController,
                                startDestination = Route.Cafe,
                            )
                            {
                                composable<Route.Main> {
                                    MainScreen(navController)
                                }
                                composable<Route.Splash> {
                                    SplashScreen(navController)
                                }
                                composable<Route.Login> {
                                    LoginScreen(navController)
                                }
                                composable<Route.SignUp> {
                                    SignUpScreen(navController)
                                }
                                composable<Route.StartUp> {
                                    StartUpScreen(navController)
                                }
                                composable<Route.Cafe> {
                                    CafeMapScreen(navController)
                                }
                                composable<Route.Menu> {
                                    MenuScreen(navController)
                                }
                                composable<Route.CreateOrder> {
                                    val id = it.toRoute<Route.CreateOrder>().id
                                    CreateOrderScreen(navController, id)
                                }
                                composable<Route.Gift> {
                                    GiftScreen(navController)
                                }
                                composable<Route.Order> {
                                    OrderScreen(navController)
                                }
                            }
                            if (isBottomBar) {
                                BottomNav(modifier = Modifier.align(
                                    Alignment.BottomCenter
                                ),
                                    navController, currentRoute)
                            }
                        }
                    }
                }
            }
        }
    }
}