package com.carintis.quotesapp.repository

import android.app.Application
import com.carintis.quotesapp.data.Quote
import com.carintis.quotesapp.db.FavoritesDao
import com.carintis.quotesapp.db.FavoritesDatabase
import org.jetbrains.anko.doAsync

class FavoritesRepository(app: Application) {

    private val favoritesDao: FavoritesDao = FavoritesDatabase.create(app).favoritesDao()

    fun getFavorites() = favoritesDao.getFavorites()

    fun getFavorite(id: String) = favoritesDao.getFavorite(id)

    fun getFavoriteCount() = favoritesDao.getFavoriteCount()

    fun addFavorite(quote: Quote) {
        doAsync {
            favoritesDao.addFavorite(quote)
        }
    }

    fun removeFavorite(quote: Quote) {
        doAsync {
            favoritesDao.removeFavorite(quote)
        }
    }
}
