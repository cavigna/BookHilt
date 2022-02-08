package com.example.bookhilt.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.example.bookhilt.R
import com.example.bookhilt.databinding.FragmentDetailsBinding
import com.example.bookhilt.viewmodel.BookViewModel
import com.example.bookhilt.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import java.lang.NullPointerException

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private val bookViewModel by activityViewModels<BookViewModel>()
    private val detailViewModel by viewModels<DetailViewModel> ()
    private lateinit var binding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)

        val idBook = bookViewModel.bookId.value!!

        try {
            detailViewModel.insertBookDetail(1)

        }catch (e:java.lang.Exception){

        }

        detailViewModel.selectBookDetailFlow(idBook).observe(viewLifecycleOwner, {
            try {
                with(binding) {
                    imageViewCover.load(it.imageLink)
                }
            }catch (e : NullPointerException){
                detailViewModel.insertBookDetail(idBook)
            }
        })
        return binding.root


}}

/*
        idBook?.let { viewModel.insertBookDetail(it) }


        binding= FragmentDetailsBinding.inflate(layoutInflater, container, false)

        idBook.let {
                viewModel.selectBookDetail(it).observe(viewLifecycleOwner, {
                    try {
                        with(binding) {
                            imageViewCover.load(it.imageLink)
                        }
                    } catch (e: Exception) {
                        Toast.makeText(requireContext(), "Nulo", Toast.LENGTH_LONG).show()
                    }
                })

        }
 */

/*
        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailViewModel.selectBookDetailFlow(idBook).collect {
                    try {
                        with(binding) {
                            imageViewCover.load(it.imageLink)
                        }
                    } catch (e: Exception) {
                        Toast.makeText(requireContext(), "Nulo", Toast.LENGTH_LONG).show()
                    }
                }
        }
 */