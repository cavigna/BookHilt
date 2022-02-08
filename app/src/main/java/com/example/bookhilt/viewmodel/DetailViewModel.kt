package com.example.bookhilt.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.bookhilt.model.Repositorio
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repositorio: Repositorio) : ViewModel(){



    fun selectBookDetailFlow(id:Int) = repositorio.selectBookDetailFlow(id).asLiveData()


    fun insertBookDetail(bookId: Int) {

        viewModelScope.launch(Dispatchers.IO) {
            val book = repositorio.fetchBookDetail(bookId)
            repositorio.insertDetailBook(book)
        }
    }
}