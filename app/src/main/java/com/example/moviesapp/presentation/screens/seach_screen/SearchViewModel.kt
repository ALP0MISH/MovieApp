package com.example.moviesapp.presentation.screens.seach_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.domain.use_case.SearchMoviesByQueryUseCase
import com.example.moviesapp.presentation.mappers.toDomain
import com.example.moviesapp.presentation.models.MovieUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SearchUiState(
    val query: String = "",
    val movies: List<MovieUi> = emptyList(),
    val isLoading: Boolean = false
)

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMoviesByQueryUseCase: SearchMoviesByQueryUseCase,
) : ViewModel() {
    private val searchQueryFlow = MutableStateFlow("")

    private val _uiStateFlow = MutableStateFlow(SearchUiState())
    val uiStateFlow: StateFlow<SearchUiState> = _uiStateFlow.asStateFlow()

    init {
        searchQueryFlow
            .onEach { query ->
                _uiStateFlow.tryEmit(
                    _uiStateFlow.value.copy(
                        query = query,
                        isLoading = true
                    )
                )
            }
            .debounce(300)
            .onEach(::startSearch)
            .launchIn(viewModelScope)
    }

    private fun startSearch(query: String) {
        viewModelScope.launch {
            val movies = searchMoviesByQueryUseCase.fetchSearchMovie(query)
            _uiStateFlow.tryEmit(
                _uiStateFlow.value.copy(
                    movies = movies.toDomain(),
                    isLoading = false
                )
            )
        }
    }

    fun onValueChange(value: String) {
        searchQueryFlow.tryEmit(value)
    }
}