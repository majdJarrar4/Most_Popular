package com.example.mostpopular.popular.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mostpopular.core.ProgressState
import com.example.mostpopular.R
import com.example.mostpopular.core.ResponseState
import com.example.mostpopular.databinding.FragmentHomeBinding
import com.example.mostpopular.popular.data.model.PopularArticalResponse
import com.example.mostpopular.popular.data.model.Result
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), TextWatcher {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    lateinit var adapter: MostArticleAdapter

    lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.getMostArticle()
        setHasOptionsMenu(true)
        binding.searchTextView.addTextChangedListener(this)
        adapter = MostArticleAdapter(requireContext())
        collectSwipeRefresh()
        collectMostAriclesState()
        collectProgressBar()
    }

    private fun collectProgressBar() {
        lifecycleScope.launch {
            viewModel.progressState.collect {
                when (it) {
                    is ProgressState.Show -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is ProgressState.Hide -> {
                        binding.progressBar.visibility = View.GONE
                    }

                    is ProgressState.None -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }

    }

    private fun collectSwipeRefresh() {
        binding?.srRefreshLayout?.setOnRefreshListener {
            binding?.srRefreshLayout?.isRefreshing = false
            viewModel.getMostArticle()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.new_to_old -> {
                val sortedItems = viewModel.mostArticle.sortedBy { it.numberOfDay }
                adapter.differ.submitList(sortedItems)
                false
            }

            R.id.old_to_new -> {
                val sortItem = viewModel.mostArticle.sortedByDescending { it.numberOfDay }
                adapter.differ.submitList(sortItem)
                false
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun collectMostAriclesState() {

        lifecycleScope.launch {
            viewModel.userMostArticleState.collect {
                when (it) {
                    is ResponseState.Success -> {
                        if (it.data != null) {
                            it.data as PopularArticalResponse
                            initialAdapter()
                        }
                    }
                    is ResponseState.Error -> {

                    }
                    else -> {

                    }
                }
            }
        }
    }

    private fun initialAdapter() {
        adapter.differ.submitList(viewModel.mostArticle)
        binding.popularMostPopularRecyclerView.adapter = adapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (s?.isNotEmpty() == true) {
            getSearchOnArticle(s)
        } else {
            initialAdapter()
        }
    }

    override fun afterTextChanged(s: Editable?) {

    }

    fun getSearchOnArticle(s: CharSequence?) {
        val searchList: MutableList<Result> = mutableListOf()
        viewModel.mostArticle.forEach {
            if (it.title?.lowercase()?.contains(s.toString().lowercase()) == true) {
                searchList.add(it)
            }
        }
        adapter.differ.submitList(searchList)

    }
}