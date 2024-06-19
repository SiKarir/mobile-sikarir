package com.c241ps294.sikarir.ui.catalog.major

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.c241ps294.sikarir.data.database.MajorDatabase
import com.c241ps294.sikarir.data.remote.retrofit.ApiConfig
import com.c241ps294.sikarir.data.repository.MajorRepository
import com.c241ps294.sikarir.databinding.FragmentMajorBinding
import com.c241ps294.sikarir.ui.adapter.LoadingStateAdapter
import com.c241ps294.sikarir.ui.adapter.MajorListAdapter
import com.c241ps294.sikarir.ui.catalog.viewmodel.MajorViewModel
import com.c241ps294.sikarir.ui.catalog.viewmodel.MajorViewModelFactory

class MajorFragment : Fragment() {
    private var _binding: FragmentMajorBinding? = null
    private val binding get() = _binding!!
    private lateinit var majorViewModel: MajorViewModel
    private lateinit var majorListAdapter: MajorListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMajorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        majorListAdapter = MajorListAdapter()

        val layoutManagerMajorList = LinearLayoutManager(context)
        binding.rvMajor.layoutManager = layoutManagerMajorList

        binding.rvMajor.adapter = majorListAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                majorListAdapter.retry()
            }
        )
        val context = requireContext()
        val apiService = ApiConfig.getApiService()
        val database = MajorDatabase.getDatabase(context)
        val majorRepository = MajorRepository(database, apiService)
        val factory = MajorViewModelFactory(majorRepository)

        majorViewModel = ViewModelProvider(this, factory)[MajorViewModel::class.java]
        setupSearch()
        getData()
    }

    private fun getData() {
        majorViewModel.majors.observe(viewLifecycleOwner, Observer {
            majorListAdapter.submitData(lifecycle, it)
        })
        majorViewModel.searchResults.observe(viewLifecycleOwner, Observer {
            majorListAdapter.submitNonPaginatedList(it)
            binding.progressBar.visibility = View.GONE
        })

        majorViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun setupSearch() {
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener { _, _, _ ->
                searchBar.setText(searchView.text)
                val tempInput = searchView.text.toString()
                searchView.hide()
                majorViewModel.searchMajors(tempInput)
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