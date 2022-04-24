package com.rajabd.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rajabd.core.domain.model.Movie
import com.rajabd.core.ui.MovieAdapter
import com.rajabd.core.utils.gone
import com.rajabd.core.utils.startActivity
import com.rajabd.core.utils.visible
import com.rajabd.di.favoriteModule
import com.rajabd.favorite.databinding.FragmentFavoriteBinding
import com.rajabd.mymoviesappmade.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules


class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val fragmentFavoriteBinding get() = _binding

    private val movieAdapter: MovieAdapter by lazy { MovieAdapter() }
    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(
            layoutInflater,
            container, false
        )
        return fragmentFavoriteBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteModule)

        fragmentFavoriteBinding?.progressSpinKitList?.visible()
        viewModel.getFavoriteMovie().observe(viewLifecycleOwner) { movies ->
            movieAdapter.setData(movies)
            showDataFavorite(movies)
        }

        movieAdapter.onItemClick = { selectedData ->
            requireActivity().startActivity<DetailActivity>(
                DetailActivity.EXTRA_DATA to selectedData
            )
        }

        with(fragmentFavoriteBinding?.rvMovieFavorite) {
            this?.setHasFixedSize(true)
            this?.adapter = movieAdapter
        }

        //rev
        unloadKoinModules(favoriteModule)
    }

    private fun showDataFavorite(movies: List<Movie>) {
        if (movies.isEmpty()) {
            fragmentFavoriteBinding?.apply {
                progressSpinKitList.gone()
                emptyLayout.visible()
                rvMovieFavorite.gone()
            }
        } else {
            fragmentFavoriteBinding?.apply {
                progressSpinKitList.gone()
                emptyLayout.gone()
                rvMovieFavorite.visible()
            }
        }
    }
}
