package com.example.bookhilt.ui

import com.example.bookhilt.model.models.Books


sealed class UiState {
    object Loading : UiState()
    data class Error (val error: UiState.Error): UiState()
    data class Success(val listado : List<Books> = listOf()): UiState()
}
