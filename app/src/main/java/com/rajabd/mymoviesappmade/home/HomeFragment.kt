package com.rajabd.mymoviesappmade.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.jakewharton.rxbinding2.view.clicks
import com.rajabd.core.data.source.Resource
import com.rajabd.core.domain.model.Movie
import com.rajabd.core.ui.MovieAdapter
import com.rajabd.core.utils.*
import com.rajabd.mymoviesappmade.R
import com.rajabd.mymoviesappmade.databinding.FragmentHomeBinding
import com.rajabd.mymoviesappmade.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val fragmentHomeBinding get() = _binding

    private val viewModel: HomeViewModel by viewModel()
    private val moviesAdapter: MovieAdapter by lazy { MovieAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(
            layoutInflater, container, false
        )
        return fragmentHomeBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setList(SortUtils.NEWEST)

        moviesAdapter.onItemClick = { selectedData ->
            requireActivity().startActivity<DetailActivity>(
                DetailActivity.EXTRA_DATA to selectedData
            )
        }

        fragmentHomeBinding?.progressSpinKitList?.visible()
        with(fragmentHomeBinding?.rvMovie) {
            this?.setHasFixedSize(true)
            this?.adapter = moviesAdapter
        }

        onClickFAB()
    }

    @SuppressLint("CheckResult")
    private fun onClickFAB() {
        fragmentHomeBinding?.apply {
            fabNewest.clicks().subscribe { setList(SortUtils.NEWEST) }
            fabOldest.clicks().subscribe { setList(SortUtils.OLDEST) }
            fabPopularity.clicks().subscribe { setList(SortUtils.POPULARITY) }
        }
    }

    private fun setList(sort: String) {
        viewModel.getMovies(sort).observe(viewLifecycleOwner, movieObserver)
    }

    private val movieObserver = Observer<Resource<List<Movie>>> { movies ->
        if (movies != null) {
            when (movies) {
                is Resource.Loading -> fragmentHomeBinding?.progressSpinKitList?.visible()
                is Resource.Success -> {
                    fragmentHomeBinding?.progressSpinKitList?.gone()
                    moviesAdapter.setData(movies.data)
                }
                is Resource.Error -> {
                    fragmentHomeBinding?.progressSpinKitList?.gone()
                    requireActivity().showToastShort(getString(R.string.there_is_an_error))
                }
            }
        }
    }

    override fun onDestroy() {
        fragmentHomeBinding?.rvMovie?.adapter = null
        _binding = null
        super.onDestroy()
    }
}
