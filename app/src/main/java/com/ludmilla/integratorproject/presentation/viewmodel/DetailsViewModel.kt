package com.ludmilla.integratorproject.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ludmilla.integratorproject.data.factory.Network
import com.ludmilla.integratorproject.data.repository.MovieRepository
import com.ludmilla.integratorproject.data.repository.MovieRepositoryImpl
import com.ludmilla.integratorproject.data.response.GenreResp
import com.ludmilla.integratorproject.data.response.ReleaseDateResponse
import com.ludmilla.integratorproject.data.response.ResponseCast
import com.ludmilla.integratorproject.data.response.ResponseMovie
import com.ludmilla.integratorproject.domain.MovieDetail
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class DetailsViewModel(private val movieRepository: MovieRepositoryImpl): ViewModel() {


    private val disposable = CompositeDisposable()
    val liveResponseMovie: MutableLiveData<List<ResponseMovie>> = MutableLiveData<List<ResponseMovie>>()
    val liveGenreResp: MutableLiveData<List<GenreResp>> = MutableLiveData<List<GenreResp>>()
    val liveResponseSearch: MutableLiveData<List<ResponseMovie>> = MutableLiveData<List<ResponseMovie>>()
    val liveResponseCast: MutableLiveData<ResponseCast> = MutableLiveData<ResponseCast>()
    val liveReleaseDate : MutableLiveData<ReleaseDateResponse> = MutableLiveData<ReleaseDateResponse>()

    fun getDetailMovie(movieId: Int){
        movieRepository.getPopularMovies()
            .compose(Network.applySingleTransformer())
            .subscribe({
                liveResponseMovie.value = it
            },{
                print(it.message)
            }).addToDispose()

    }

    fun getGenres(){
        movieRepository.getAllGenres()
            .compose(Network.applySingleTransformer())
            .subscribe({
                liveGenreResp.value = it
            },{
                print(it.message)
            }).addToDispose()
    }

    fun getSearch(movieSearch: Uri){
        movieRepository.getSearchMovie(movieSearch)
            .compose(Network.applySingleTransformer())
            .subscribe({
                liveResponseSearch.value = it
            },{
                print(it.message)
            }).addToDispose()
    }

    fun getCast(movieId: Int){
        movieRepository.getCast(movieId)
            .compose(Network.applySingleTransformer())
            .subscribe({
                liveResponseCast.value = it
            },{
                print(it.message)
            }).addToDispose()
    }

    fun getParentalGuide  (movieId: Int){
        movieRepository.getParentalGuide(movieId)
            .compose(Network.applySingleTransformer())
            .subscribe({
                liveReleaseDate.value = it
            },{
                print(it.message)
            }).addToDispose()
    }

    /*fun getMovieByGenre(genreId: String){
        movieRepository.getMoviesByGenre(genreId)
            .compose(Network.applySingleTransformer())
            .subscribe({
                liveResponseMovieByGenre.value = it
            },{
                print(it.message)
            }).addToDispose()
    }*/

    // fun getFavoriteMovie(movie: Movie){
    //    movieRepository.

    //   }


    private fun Disposable.addToDispose(): Disposable = apply { disposable.add(this) }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}
