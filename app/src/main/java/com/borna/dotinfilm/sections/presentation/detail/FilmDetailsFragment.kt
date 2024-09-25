package com.borna.dotinfilm.sections.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.borna.dotinfilm.databinding.FragmentFilmDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FilmDetailsFragment: Fragment() {
    private val viewModel: FilmDetailsViewModel by viewModels()
    private lateinit var binding: FragmentFilmDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilmDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        observeState()
        viewModel.getSections()
    }

    private fun init() {

    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel
                .uiState
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect { state -> handleUiState(uiState = state) }
        }
    }

    private fun handleUiState(uiState: FilmUiState) {
        // Handle loading state
        if (uiState.isLoading) {
            showLoadingIndicator()
        } else {
            hideLoadingIndicator()
        }

        // Handle sections
        if (uiState.sections.isNotEmpty()) {

        }

        // Handle errors
        uiState.errorMessage?.let { message ->
            showError(message)
        }
        uiState.errorMessageId?.let { messageId ->
            showError(getString(messageId))
        }
    }

    private fun showLoadingIndicator() {
        // Show a loading spinner or progress bar
    }

    private fun hideLoadingIndicator() {
        // Hide the loading spinner or progress bar
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        viewModel.onHandledError()
    }
}