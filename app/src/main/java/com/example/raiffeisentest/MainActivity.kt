package com.example.raiffeisentest

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.raiffeisentest.databinding.ActivityMainBinding
import org.koin.android.ext.android.get


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        setSupportActionBar(binding.toolbar)

        setContentView(ComposeView(this).apply {
            setContent {
                createNavGraph()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.main_manu, menu)
        return true
    }

    @Composable
    private fun createNavGraph() {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "userListScreen") {
            composable("userListScreen") { UserListScreen(repository = get()) }
        }
    }
}