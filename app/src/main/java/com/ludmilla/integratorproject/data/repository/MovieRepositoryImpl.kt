package com.ludmilla.integratorproject.data.repository
import android.net.Uri
import com.ludmilla.integratorproject.data.factory.Network
import com.ludmilla.integratorproject.data.remotesource.RemoteSource
import com.ludmilla.integratorproject.data.response.GenreResp
import com.ludmilla.integratorproject.data.response.ResponseGenre
import com.ludmilla.integratorproject.data.response.ResponseMovie
import io.reactivex.Single

class MovieRepositoryImpl:MovieRepository {

    private val remoteSource: RemoteSource = Network.getRemoteSource()

    override fun getPopularMovies(): Single<List<ResponseMovie>> {
        return remoteSource.getPopularMovies()
            .map { it.results }
    }

    override fun getAllGenres(): Single<List<GenreResp>> {
        return remoteSource.getAllGenres()
            .map { it.genres }
    }

    override fun getSearchMovie(movieSearch:Uri): Single<List<ResponseMovie>> {
        return remoteSource.getSearchMovie(movieSearch)
            .map { it.results }
    }
    override fun getGenreByMovie(genreId:String): Single<List<ResponseMovie>> {
        return remoteSource.getMoviesByGenre(genreId)
            .map { it.results }
    }

}