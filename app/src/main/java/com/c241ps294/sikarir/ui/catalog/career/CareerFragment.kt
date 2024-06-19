package com.c241ps294.sikarir.ui.catalog.career

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.c241ps294.sikarir.data.database.CareerDatabase
import com.c241ps294.sikarir.data.remote.retrofit.ApiConfig
import com.c241ps294.sikarir.data.repository.CareerRepository
import com.c241ps294.sikarir.data.repository.QuizRepository
import com.c241ps294.sikarir.databinding.FragmentCareerBinding
import com.c241ps294.sikarir.ui.adapter.CareerListAdapter
import com.c241ps294.sikarir.ui.adapter.LoadingStateAdapter
import com.c241ps294.sikarir.ui.adapter.RecommendedCareerAdapter
import com.c241ps294.sikarir.ui.authentication.viewmodel.AuthenticationViewModel
import com.c241ps294.sikarir.ui.authentication.viewmodel.AuthenticationViewModelFactory
import com.c241ps294.sikarir.ui.catalog.viewmodel.CareerViewModel
import com.c241ps294.sikarir.ui.catalog.viewmodel.CareerViewModelFactory
import com.c241ps294.sikarir.ui.quiz.starter.QuizStarterActivity

class CareerFragment : Fragment() {
    private var _binding: FragmentCareerBinding? = null
    private val binding get() = _binding!!

    private lateinit var careerViewModel: CareerViewModel
    private lateinit var careerListAdapter: CareerListAdapter
    private lateinit var recommendedCareerAdapter: RecommendedCareerAdapter
    private val authenticationViewModel by viewModels<AuthenticationViewModel> {
        AuthenticationViewModelFactory.getInstance(context = requireContext())
    }

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
        recommendedCareerAdapter = RecommendedCareerAdapter()

        val layoutManagerCareerList = LinearLayoutManager(context)
        binding.rvCareer.layoutManager = layoutManagerCareerList

        binding.rvCareer.adapter = careerListAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                careerListAdapter.retry()
            }
        )

        binding.rvCareerRecommendation.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = recommendedCareerAdapter
        }

        val context = requireContext()
        val apiService = ApiConfig.getApiService()
        val database = CareerDatabase.getDatabase(context)
        val careerRepository = CareerRepository(database, apiService)
        val quizRepository = QuizRepository(apiService)
        val factory = CareerViewModelFactory(careerRepository, quizRepository)

        careerViewModel = ViewModelProvider(this, factory)[CareerViewModel::class.java]
        setupSearch()
        setupObservers()

        authenticationViewModel.getSession().observe(viewLifecycleOwner) { session ->
            if (!session.isTakenQuiz) {
                binding.quizLayout.visibility = View.VISIBLE
                binding.rvCareerRecommendation.visibility = View.GONE
            } else {
                binding.quizLayout.visibility = View.GONE
                binding.rvCareerRecommendation.visibility = View.VISIBLE
                careerViewModel.getQuizHistory(session.token)
            }
        }

        binding.btnTakeQuiz.setOnClickListener{ navToQuiz() }
    }

    private fun setupObservers() {
        careerViewModel.careers.observe(viewLifecycleOwner, Observer {
            careerListAdapter.submitData(lifecycle, it)
        })

        careerViewModel.searchResults.observe(viewLifecycleOwner, Observer {
            careerListAdapter.submitNonPaginatedList(it)
            binding.progressBar.visibility = View.GONE
        })

        careerViewModel.quizzes.observe(viewLifecycleOwner, Observer { quizzes ->
            if (quizzes.isNotEmpty()) {
                val latestQuiz = quizzes.last()
                recommendedCareerAdapter.submitList(latestQuiz.recommendation)
            }
        })

        careerViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
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
                careerViewModel.searchCareers(tempInput)
                false
            }
            searchView.editText.addTextChangedListener {
                searchBar.setText(it.toString())
            }
        }
    }

    private fun navToQuiz() {
        val intent = Intent(context, QuizStarterActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}