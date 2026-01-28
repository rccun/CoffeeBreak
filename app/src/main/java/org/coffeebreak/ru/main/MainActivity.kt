package org.coffeebreak.ru.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.coffeebreak.ru.Route
import org.coffeebreak.ru.barista.BaristaScreen
import org.coffeebreak.ru.cafemap.CafeMapScreen
import org.coffeebreak.ru.cart.CartScreen
import org.coffeebreak.ru.common.BottomNav
import org.coffeebreak.ru.country.CountryScreen
import org.coffeebreak.ru.create_order.CoffeeConstructorScreen
import org.coffeebreak.ru.create_order.CreateOrderScreen
import org.coffeebreak.ru.create_order.SharedOrderViewModel
import org.coffeebreak.ru.forgot.ForgotScreen
import org.coffeebreak.ru.login.LoginScreen
import org.coffeebreak.ru.menu.MenuScreen
import org.coffeebreak.ru.my_order.MyOrderScreen
import org.coffeebreak.ru.order.OrderScreen
import org.coffeebreak.ru.placed_order.PlacedOrderScreen
import org.coffeebreak.ru.profile.ProfileScreen
import org.coffeebreak.ru.qr.QRScreen
import org.coffeebreak.ru.reset.ResetScreen
import org.coffeebreak.ru.reward.RewardScreen
import org.coffeebreak.ru.signup.SignUpScreen
import org.coffeebreak.ru.sort.SortScreen
import org.coffeebreak.ru.splash.SplashScreen
import org.coffeebreak.ru.startup.StartUpScreen
import org.coffeebreak.ru.supplements.SupplementsScreen
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.MyCoffeeBreakTheme
import org.coffeebreak.ru.two_factor.TwoFactorScreen

@AndroidEntryPoint
class MainActivity() : ComponentActivity() {
    private var createOrderViewModel: SharedOrderViewModel? = null
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val isAuth = mainViewModel.isAuth.collectAsState()
            val navController = rememberNavController()
            val bottomBars = listOf(
                "menu",
                "gift",
                "order",
                "constructor_order",
                "reward",
                "cart",

            )
            Log.e("", ": $bottomBars");
            val entry = navController.currentBackStackEntryAsState().value
            val currentRoute = entry?.destination?.route

            val isBottomBar = currentRoute?.let { route ->
                bottomBars.any { route.startsWith(it) }
            } == true
            MyCoffeeBreakTheme {
                Surface(
                    modifier = Modifier.Companion.fillMaxSize(),
                    color = MainTheme.colorScheme.bg
                ) {
                    Scaffold(
                        modifier = Modifier.Companion.fillMaxSize(),
                        containerColor = Color.Companion.Transparent,

                    ) { innerPadding ->
                        Box(
                            modifier = Modifier.Companion.padding(innerPadding)

                        ) {
                            NavHost(
                                navController = navController,
                                startDestination =
                                    if (isAuth.value) {
                                        Route.Profile
                                    } else {
                                        Route.Splash
                                    }
                            )
                            {
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
                                    createOrderViewModel = createOrderViewModel ?: hiltViewModel()
//                                    val id = it.toRoute<Route.CreateOrder>().id
//                                        ?: "6db93909-45d5-47be-a16a-381e8c1d9d9d"
                                    CreateOrderScreen(
                                        navController,
                                        createOrderViewModel!!
                                    )//, createOrderViewModel)
                                }

                                composable<Route.Constructor> {
                                    createOrderViewModel = createOrderViewModel ?: hiltViewModel()
                                    CoffeeConstructorScreen(navController, createOrderViewModel!!)
                                }


                                composable<Route.Gift> {
                                    RewardScreen(navController)
                                }
                                composable<Route.Order> {
                                    OrderScreen(navController)
                                }
                                composable<Route.Barista> {
                                    BaristaScreen(navController)
                                }
                                composable<Route.Country> {
                                    CountryScreen(navController)
                                }
                                composable<Route.Sort> {
                                    SortScreen(navController)
                                }
                                composable<Route.Supplement> {
                                    SupplementsScreen(navController)
                                }
                                composable<Route.Placed> {
                                    PlacedOrderScreen(navController)
                                }
                                composable<Route.Profile> {
                                    ProfileScreen(navController)
                                }
                                composable<Route.QR> {
                                    QRScreen(navController)
                                }
                                composable<Route.Cart> {
                                    CartScreen(navController)
                                }
                                composable<Route.Reward> {
                                    RewardScreen(navController)
                                }
                                composable<Route.MyOrder> {
                                    MyOrderScreen(navController)
                                }
                                composable<Route.TwoFactor> {
                                    TwoFactorScreen(navController)
                                }
                                composable<Route.Forgot> {
                                    ForgotScreen(navController)
                                }
                                composable<Route.Reset> {
                                    ResetScreen(navController)
                                }
                            }
                            if (isBottomBar) {
                                BottomNav(
                                    modifier = Modifier.Companion.align(
                                        Alignment.Companion.BottomCenter
                                    ),
                                    navController, currentRoute
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}