package com.borna.dotinfilm.sections.presentation.film

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.borna.dotinfilm.R
import com.borna.dotinfilm.databinding.FragmentHomeBinding
import com.borna.dotinfilm.sections.domain.models.Film
import com.borna.dotinfilm.sections.presentation.detail.FilmDetailsFragment
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment: Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private var listFragment: ListFragment? = null
    private lateinit var binding: FragmentHomeBinding

    override fun onResume() {
        super.onResume()

        sharedViewModel.badgeState.observe(viewLifecycleOwner) { badgeState ->
            if (badgeState?.second == true) {
                viewModel.changeBadgeState(badgeState.first, true)
                sharedViewModel.badgeState.value = Pair(badgeState.first, false)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.refresh.setOnClickListener {
            hideError()
            viewModel.getSections()
        }

        init()
        observeState()
        if (viewModel.uiState.value.sections.isEmpty()) {
            viewModel.getSections()
        }
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

        listFragment!!.setOnLikeChangeListener { item ->
            viewModel.changeBadgeState(item.id, false)
        }
    }

    private fun updateBanner(film: Film) {
        binding.title.text = film.name
        binding.description.text = film.description
        Glide.with(this).load(film.bannerUrl).into(binding.imgBanner)
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
        binding.progress.visibility = View.VISIBLE
    }

    private fun hideLoadingIndicator() {
        binding.progress.visibility = View.GONE
    }

    private fun showError(message: String) {
        binding.error.text = message
        binding.error.visibility = View.VISIBLE
        binding.refresh.visibility = View.VISIBLE
    }

    private fun hideError() {
        binding.error.text = ""
        binding.error.visibility = View.GONE
        binding.refresh.visibility = View.GONE
    }
}