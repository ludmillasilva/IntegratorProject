package com.ludmilla.integratorproject.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ludmilla.integratorproject.data.model.Favorite
import com.ludmilla.integratorproject.data.repository.FavoriteRepositoryImpl
import com.ludmilla.integratorproject.data.response.GenreResp
import kotlinx.coroutines.launch

class FavoritesViewModel(private val favoriteRepository: FavoriteRepositoryImpl) :ViewModel() {
    var liveResponseMovie: LiveData<List<Favorite>> = MutableLiveData()
    var liveCheckFavorite: LiveData<Boolean> = MutableLiveData();
    val liveGenreResp: MutableLiveData<List<GenreResp>> = MutableLiveData<List<GenreResp>>()


    fun getAllFavorites(){
        liveResponseMovie = favoriteRepository.getAll()
    }

    fun saveFavorite(favorite: Favorite){
        viewModelScope.launch {
            favoriteRepository.save(favorite)
        }
    }

    fun deleteFavorite(favorite: Favorite){
        viewModelScope.launch {
            favoriteRepository.delete(favorite)
        }
    }


    suspend fun checkIfIsFavorite(id:Int){
        liveCheckFavorite = favoriteRepository.checkIfIsFavorite(id.toLong())
    }



}