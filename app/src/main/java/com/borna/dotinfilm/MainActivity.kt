package com.borna.dotinfilm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.borna.dotinfilm.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar?.hide()
        setSupportActionBar(null)
    }
}