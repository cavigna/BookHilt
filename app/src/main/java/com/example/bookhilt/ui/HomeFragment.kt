package com.example.bookhilt.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookhilt.databinding.FragmentHomeBinding
import com.example.bookhilt.model.models.Resource
import com.example.bookhilt.viewmodel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), BooksListAdapter.ExtraerId {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel by activityViewModels<BookViewModel>()

    private lateinit var recyclerView: RecyclerView
    private val listAdapter by lazy { BooksListAdapter(this) }

    private var idBook: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        recyclerView = binding.recyclerView

        recyclerView.let{
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = listAdapter
        }

//        recyclerView.apply {
//            this.layoutManager = LinearLayoutManager(requireContext())
//            this.adapter = listAdapter
//        }

        viewModel.response.observe(viewLifecycleOwner, { respuesta ->
            when (respuesta) {

                is Resource.Loading -> {
                    isLoading(true)

                }
                is Resource.Success -> {
                    viewModel.selectAllBooks().observe(viewLifecycleOwner,{
                        isLoading(false)
                        listAdapter.submitList(it)
                    })
                }
                is Resource.Error -> {
                    Toast.makeText(
                        requireContext(),
                        respuesta.message, Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

        return binding.root
    }

    private fun isLoading(isLoading:Boolean) {
        when(isLoading){
            true ->{
                recyclerView.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }
            false->{
                recyclerView.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }
        }

    }

    override fun alHacerClick(id: Int) {
        viewModel.bookId.value = id
    }

}

/*
        viewModel.response.observe(viewLifecycleOwner, { respuesta ->
            when (respuesta) {

                is Resource.Loading -> {
                    isLoading(true)

                }
                is Resource.Success -> {
                    respuesta.data?.let {
                        isLoading(false)
                        adapter1.submitList(it)

                    }
                }
                is Resource.Error -> {
                    Toast.makeText(
                        requireContext(),
                        respuesta.toString(), Toast.LENGTH_SHORT
                    ).show()
                }


            }


        })
 */