package com.example.mostpopular.popular.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mostpopular.core.ProgressState
import com.example.mostpopular.core.ResponseState
import com.example.mostpopular.popular.domain.usecase.GetUserPopularUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.mostpopular.popular.data.model.Result
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val getMostPopularUseCase: GetUserPopularUseCase
) : ViewModel() {

    private val _userMostArticleState: MutableStateFlow<ResponseState<*>> =
        MutableStateFlow(ResponseState.None)
    val userMostArticleState: StateFlow<ResponseState<*>> = _userMostArticleState

    var mostArticle: MutableList<Result> = mutableListOf()

    private val _progressState: MutableStateFlow<ProgressState> =
        MutableStateFlow(ProgressState.None)
    val progressState: StateFlow<ProgressState> = _progressState

    fun getMostArticle() {
        viewModelScope.launch {
            _progressState.emit(ProgressState.Show)
            try {
                val result = getMostPopularUseCase()
                if (result.isSuccessful) {
                    _userMostArticleState.emit(ResponseState.Success(result.body()))
                    result.body()?.results?.let {
                        mostArticle.addAll(it)
                    }
                } else {
                    _userMostArticleState.emit(ResponseState.Success(result.body()))
                }
                _progressState.emit(ProgressState.Hide)

            } catch (exception: Exception) {
                _userMostArticleState.emit(ResponseState.Error())
                exception.printStackTrace()
            } finally {
                _progressState.emit(ProgressState.Hide)
                _progressState.emit(ProgressState.None)
            }
        }
    }
}