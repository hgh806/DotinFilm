package com.borna.dotinfilm.sections.presentation.film

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
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
class SharedViewModel @Inject constructor(
) : ViewModel() {
    val badgeState = MutableLiveData<Pair<Int, Boolean>>()
}