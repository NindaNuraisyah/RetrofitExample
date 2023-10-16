package com.catnip.retrofitexample.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catnip.retrofitexample.data.model.ProductsResponse
import com.catnip.retrofitexample.data.repository.ProductRepository
import com.catnip.retrofitexample.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repo : ProductRepository) : ViewModel() {
    val productList = MutableLiveData<ResultWrapper<ProductsResponse>>()

    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getProducts().collect{
                productList.postValue(it)
            }
        }
    }
}