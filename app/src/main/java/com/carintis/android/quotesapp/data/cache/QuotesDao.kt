package com.carintis.android.quotesapp.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuotesDao {

  @Query("SELECT * FROM Quotes")
  fun getAllQuotes(): Flow<List<CachedQuote>>

  @Query("SELECT * FROM Quotes WHERE id = :id")
  fun getQuote(id:Long): Flow<CachedQuote>

  @Query("SELECT * FROM Quotes WHERE isFavourite = 1")
  fun getFavoriteQuotes(): Flow<List<CachedQuote>>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(quotes: List<CachedQuote>)

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insertQuote(quotes: CachedQuote)

  @Query("UPDATE Quotes SET isFavourite = :isFavorite WHERE `key` = :quoteKey")
  fun update(quoteKey: Long, isFavorite: Boolean)

  @Query("SELECT * FROM Quotes WHERE id = :id")
  fun isQuoteExist(id: Long): Boolean

}