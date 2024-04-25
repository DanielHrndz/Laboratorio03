package com.alexisflop.labo03.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexisflop.labo03.data.objectList
import com.alexisflop.labo03.model.ObjectClass
import kotlinx.coroutines.launch

class DataViewModel: ViewModel() {
    fun setData(myList: MutableList<ObjectClass>){
        viewModelScope.launch {
            objectList.value = myList
        }
    }
    fun getData(): MutableList<ObjectClass>{
        return objectList.value
    }
}