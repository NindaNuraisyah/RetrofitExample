package com.catnip.retrofitexample.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.catnip.retrofitexample.presentation.main.adapter.ProductListAdapter
import com.catnip.retrofitexample.databinding.ActivityMainBinding
import com.catnip.retrofitexample.utils.proceedWhen

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupList()
        observeData()
    }

    private fun observeData() {
        viewModel.productList.observe(this){
            it.proceedWhen (
                doOnSuccess = {
                    binding.rvProducts.isVisible = true
                    binding.layoutState.root.isVisible = false
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = false
                    binding.rvProducts.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = ProductListAdapter()
                    }
                    it.payload?.let {
                        ProductListAdapter().setData(it.products)
                    }
                }
            )
        }
    }

    private fun setupList() {
        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}