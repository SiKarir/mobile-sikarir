package com.c241ps294.sikarir.ui.catalog.career

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.c241ps294.sikarir.R
import com.c241ps294.sikarir.data.database.CareerDatabase
import com.c241ps294.sikarir.data.remote.retrofit.ApiConfig
import com.c241ps294.sikarir.data.repository.CareerRepository
import com.c241ps294.sikarir.databinding.FragmentCareerBinding
import com.c241ps294.sikarir.ui.adapter.CareerListAdapter
import com.c241ps294.sikarir.ui.catalog.viewmodel.CareerViewModel
import com.c241ps294.sikarir.ui.catalog.viewmodel.CareerViewModelFactory

class CareerFragment : Fragment() {
    private var _binding: FragmentCareerBinding? = null
    private val binding get() = _binding!!

    private lateinit var careerViewModel: CareerViewModel
    private lateinit var careerListAdapter: CareerListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCareerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        careerListAdapter = CareerListAdapter()

        binding.rvCareer.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = careerListAdapter
        }

        val context = requireContext()
        val apiService = ApiConfig.getApiService()
        val database = CareerDatabase.getDatabase(context)
        val careerRepository = CareerRepository(database, apiService)
        val factory = CareerViewModelFactory(careerRepository)

        careerViewModel = ViewModelProvider(this, factory).get(CareerViewModel::class.java)
        setupSearch()
        setupObservers()
    }

    private fun setupObservers() {
        careerViewModel.careers.observe(viewLifecycleOwner, Observer {
            careerListAdapter.submitData(lifecycle, it)
        })

        careerViewModel.searchResults.observe(viewLifecycleOwner, Observer {
            careerListAdapter.submitNonPaginatedList(it)
            binding.progressBar.visibility = View.GONE
        })
    }

    private fun setupSearch() {
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener { _, _, _ ->
                searchBar.setText(searchView.text)
                val tempInput = searchView.text.toString()
                searchView.hide()
                careerViewModel.searchCareers(tempInput)
                false
            }
            searchView.editText.addTextChangedListener {
                searchBar.setText(it.toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}