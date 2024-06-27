package com.example.myapplication.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.bumptech.glide.RequestManager
import com.example.myapplication.BuildConfig
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var moviePagingAdapter: MoviePagingAdapter
    private val binding get() = _binding!!

    @Inject
    lateinit var requestManager : RequestManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapter = MovieAdapter(listener = { movie ->
            Log.e("NKD","movie: $movie")
        })
        moviePagingAdapter = MoviePagingAdapter(listener = {
            Log.e("NKD","movie paging: $it")
        })
        homeViewModel.getMovies()
        binding.apply {
            rvMovie.adapter = moviePagingAdapter
        }
        moviePagingAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading){
                binding.progressCircular.visibility = View.VISIBLE
            } else {
                binding.progressCircular.visibility = View.GONE

                val error = when{
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                error?.let {
                    Toast.makeText(context, it.error.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.movies.observe(viewLifecycleOwner) { movies ->
            requestManager.load("${BuildConfig.BASE_URL_IMAGE}${movies.first().backdropPath}").into(binding.ivBottom)
            Log.e("NKD","$movies")
            movieAdapter.submitList(movies)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.moviesData.collectLatest {
                moviePagingAdapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}