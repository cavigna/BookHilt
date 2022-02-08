package com.example.bookhilt.app



import android.content.Context
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import com.example.bookhilt.model.Repositorio
import com.example.bookhilt.model.db.BookDao
import com.example.bookhilt.model.db.BooksDatabase
import com.example.bookhilt.model.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {


    @Singleton
    @Provides
    fun providesRetrofit(): ApiService{
        return Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/Himuravidal/anchorBooks/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesBookDB(@ApplicationContext context: Context) =
         databaseBuilder(context, BooksDatabase::class.java, "books.db").build()


    @Singleton
    @Provides
    fun providesBookDao(db:BooksDatabase) = db.dao()


    @Singleton
    @Provides
    fun provideRepositorio(apiService: ApiService, dao: BookDao) = Repositorio(apiService, dao)




}