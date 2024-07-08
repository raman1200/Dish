package com.raman.project.dish.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raman.project.dish.model.DishData
import com.raman.project.dish.repositories.DishRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DishViewModel @Inject constructor(private val repository: DishRepository) : ViewModel() {

    private val _dishes = MutableLiveData<List<DishData>>()
    val dishes: LiveData<List<DishData>> = _dishes

    fun getDishes() {
        viewModelScope.launch {
            val fetchedDishes = repository.fetchDishes()
            _dishes.postValue(fetchedDishes) // Update LiveData with fetched dishes
        }
    }
}