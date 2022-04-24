package com.rajabd.mymoviesappmade.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import com.rajabd.core.ui.MovieAdapter
import com.rajabd.core.utils.gone
import com.rajabd.core.utils.startActivity
import com.rajabd.core.utils.visible
import com.rajabd.mymoviesappmade.databinding.FragmentSearchBinding
import com.rajabd.mymoviesappmade.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val fragmentSearchBinding get() = _binding

    private val movieAdapter: MovieAdapter by lazy { MovieAdapter() }
    private val viewModel: SearchViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentSearchBinding.inflate(
            layoutInflater, container, false
        )
        return fragmentSearchBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentSearchBinding?.rvMovie?.adapter = movieAdapter

        fragmentSearchBinding?.svMovie?.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    getItemFromDb(query.orEmpty())
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    getItemFromDb(newText.orEmpty())
                    return true
                }
            })
        }

        movieAdapter.onItemClick = { selectedData ->
            requireActivity().startActivity<DetailActivity>(
                DetailActivity.EXTRA_DATA to selectedData
            )
        }
    }

    private fun getItemFromDb(searchText: String) {
        var text = searchText
        text = "%$text%"

        viewModel.searchForItems(text).observe(this) { list ->
            movieAdapter.setData(list)
            if (list.isNullOrEmpty()) {
                fragmentSearchBinding?.apply {
                    noDataLayout.visible()
                    layoutSearch.gone()
                    rvMovie.gone()
                }
            } else {
                fragmentSearchBinding?.apply {
                    rvMovie.visible()
                    layoutSearch.gone()
                    noDataLayout.gone()
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
