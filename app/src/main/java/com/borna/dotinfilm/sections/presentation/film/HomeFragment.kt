package com.borna.dotinfilm.sections.presentation.film

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
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.borna.dotinfilm.R
import com.borna.dotinfilm.databinding.FragmentHomeBinding
import com.borna.dotinfilm.sections.domain.models.Film
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment: Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private var listFragment: ListFragment? = null
    private lateinit var fragmentHomeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        observeState()
        viewModel.getSections()
    }

    private fun init() {
        if (listFragment != null) {
            return
        }

        listFragment = ListFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.list_fragment, listFragment!!)
        transaction.commit()

        listFragment!!.setOnContentSelectedListener {
            updateBanner(it)
        }

        listFragment!!.setOnItemClickListener { item ->
            findNavController(this).navigate(R.id.actionHomeToFilmDetails, Bundle().apply {
                putInt("filmId", item.id)
            })
        }
    }

    private fun updateBanner(film: Film) {
        fragmentHomeBinding.title.text = film.name
        fragmentHomeBinding.description.text = film.description
        Glide.with(this).load(film.bannerUrl).into(fragmentHomeBinding.imgBanner)
    }


    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel
                .uiState
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect { state -> handleUiState(uiState = state) }
        }
    }

    private fun handleUiState(uiState: HomeUiState) {
        // Handle loading state
        if (uiState.isLoading) {
            showLoadingIndicator()
        } else {
            hideLoadingIndicator()
        }

        // Handle sections
        if (uiState.sections.isNotEmpty()) {
            listFragment?.bindData(uiState.sections)
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