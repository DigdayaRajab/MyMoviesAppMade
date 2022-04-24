package com.rajabd.mymoviesappmade.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.rajabd.mymoviesappmade.databinding.ActivityDetailBinding
import com.jakewharton.rxbinding2.view.clicks
import com.rajabd.core.domain.model.Movie
import com.rajabd.core.utils.loadImage
import com.rajabd.mymoviesappmade.R
import org.koin.android.viewmodel.ext.android.viewModel


class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val activityDetailBinding get() = _binding

    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(activityDetailBinding?.root)

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        if (detailMovie != null) {
            loadDataDetail(detailMovie)
        }
    }

    @SuppressLint("CheckResult")
    private fun loadDataDetail(movie: Movie) {
        activityDetailBinding?.apply {
            tvTitleDetail.text = movie.title
            tvLanguage.text = movie.originalLanguage
            tvPopularity.text = movie.popularity.toString()
            tvOverview.text = movie.overview
            tvReleaseDate.text = movie.releaseDate
            tvScoreDetail.text = movie.voteAverage.toString()
            loadImage(getString(R.string.url_poster, movie.posterPath), roundedPosterDetail)
            loadImage(getString(R.string.url_poster, movie.backdropPath), posterBg)

            var statusFavorite = movie.isFavorite
            setFavorite(statusFavorite)
            fabFavorite.clicks().subscribe {
                statusFavorite = !statusFavorite
                viewModel.setFavoriteMovie(movie, statusFavorite)
                setFavorite(statusFavorite)
            }
        }
    }

    private fun setFavorite(state: Boolean) {
        if (state) {
            activityDetailBinding?.fabFavorite?.setImageDrawable(
                ContextCompat.getDrawable(
                    this, R.drawable.ic_favorite
                )
            )
        } else {
            activityDetailBinding?.fabFavorite?.setImageDrawable(
                ContextCompat.getDrawable(
                    this, R.drawable.ic_favorite_border
                )
            )
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}
