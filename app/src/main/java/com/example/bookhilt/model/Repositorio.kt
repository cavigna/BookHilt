package com.example.bookhilt.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.bookhilt.model.db.BookDao
import com.example.bookhilt.model.models.BookDetail
import com.example.bookhilt.model.models.Books
import com.example.bookhilt.model.models.Resource
import com.example.bookhilt.model.remote.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

//@Singleton
class Repositorio @Inject constructor(
    private val api: ApiService,
    private val dao: BookDao
) {

    val isOkDb = MutableLiveData(false)
    suspend fun fetchBooksApi() = api.fetchBooksListApi()
    suspend fun fetchBookDetail(id:Int) = api.fetchDetails(id)



    suspend fun fetchBooksListApiResouce():Resource<List<Books>>{
        val response = try {
                 api.fetchBooksListApi()
            }catch (e:Exception){
                return Resource.Error(e.message.toString())
            }
        return Resource.Success(response)
    }


    suspend fun insertBooksDB(){

        when(fetchBooksListApiResouce()){
            is Resource.Success ->{

                dao.insertBooks(fetchBooksApi())
                isOkDb.postValue(true)
            }

            is Resource.Error -> Resource.Error(message = "Hubo un error", data = null)

            else -> {

            }
        }


    }

     fun selectAllBooks() = dao.selectAllBooks()

    fun insertDetailBook(bookDetail: BookDetail) = dao.insertDetailBook(bookDetail)
    fun selectBookDetail(id : Int) =dao.selectBookDetail(id)

    fun selectBookDetailFlow(id: Int) = dao.selectBookDetailFlow(id)


    /*
     fun selectAllBooks() :Resource<List<Books>>{
         val response = try {
             dao.selectAllBooks()
         }catch (e:Exception){
             return Resource.Error(e.message.toString())
         }
         return Resource.Success(response)
     }

     */
}

/*
    suspend fun insertBooksDB(){
        val listadoLibros = fetchBooksApi()
        dao.insertBooks(listadoLibros)
    }
 */