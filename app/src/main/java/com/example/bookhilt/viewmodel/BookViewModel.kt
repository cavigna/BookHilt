package com.example.bookhilt.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.bookhilt.model.Repositorio
import com.example.bookhilt.model.models.BookDetail
import com.example.bookhilt.model.models.Books
import com.example.bookhilt.model.models.Resource
import com.example.bookhilt.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(private val repo: Repositorio) : ViewModel() {
    val listadoApi = MutableLiveData<List<Books>>()
    //val listadoDB = MutableLiveData<List<Books>>()
    val listadoDB = MutableLiveData<Resource<List<Books>>>()

    val uiState = MutableLiveData<UiState>()


    var response = MutableLiveData<Resource<List<Books>>>()
    val bookId = MutableLiveData<Int>()

    val isOkDb = repo.isOkDb

    init {

        response.postValue(Resource.Loading())
        viewModelScope.launch(IO) {
            response.postValue(repo.fetchBooksListApiResouce())
            repo.insertBooksDB()
        }

    }
    fun selectAllBooks() = repo.selectAllBooks()
    fun insertBookDetail(bookId: Int) {

        viewModelScope.launch(IO) {
            val book = repo.fetchBookDetail(bookId)
            repo.insertDetailBook(book)
        }
    }

    fun selectBookDetail(id:Int) =  repo.selectBookDetail(id)

    fun listaApiFlow() = flow {
        emit(repo.fetchBooksListApiResouce())

    }



    private suspend fun fetchLitstApiResource() {
        response.postValue(Resource.Loading())
        try {
            //val listado = repo.fetchBooksListApiResouce().data!!
            val listado = repo.fetchBooksApi()
            response.postValue(Resource.Success(listado))
            //response.postValue(listado?.let { Resource.Success(it) })

        } catch (e: Exception) {
            response.postValue(Resource.Error(e.message!!))
        }

    }



}

/*
    init {
        viewModelScope.launch(IO){
            listadoApi.postValue(repo.fetchBooksApi())
            repo.insertBooksDB()
            listadoDB.postValue(repo.selectAllBooks())
        }

    }
 */