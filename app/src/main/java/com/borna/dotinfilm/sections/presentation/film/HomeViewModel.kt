package com.borna.dotinfilm.sections.presentation.film

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borna.dotinfilm.core.data.remote.adapter.Failure
import com.borna.dotinfilm.core.data.remote.adapter.Success
import com.borna.dotinfilm.core.data.remote.adapter.handleError
import com.borna.dotinfilm.sections.domain.use_cases.GetSectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val getSectionsUseCase: GetSectionsUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    fun getSections() {
        _uiState.update {
            it.copy(
                isLoading = true,
                errorMessage = null,
                errorMessageId = null,
            )
        }

        getSectionsUseCase().onEach { result ->
            when (result) {
                is Failure -> {
                    Log.e(TAG, "getSections: ${result.error}", )
                    result.error.handleError { message, messageId, _ ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                sections = emptyList(),
                                errorMessage = message,
                                errorMessageId = messageId,
                            )
                        }
                    }
                }

                is Success -> _uiState.update {
                    it.copy(
                        isLoading = false,
                        sections = result.value,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }


    fun onHandledError() {
        _uiState.value = uiState.value.copy(
            errorMessage = null,
            errorMessageId = null,
        )
    }
}