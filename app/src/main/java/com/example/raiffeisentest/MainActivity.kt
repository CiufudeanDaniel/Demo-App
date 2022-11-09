package com.example.raiffeisentest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.raiffeisentest.view_models.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private val viewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.users.observe(this) { u ->
            Log.v(TAG, "users: $u")
        }
    }
}