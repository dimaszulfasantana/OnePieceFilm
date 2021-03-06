package com.dimas.onepiecefilm.core.source.local
import androidx.room.Database
import androidx.room.RoomDatabase
import com.dimas.onepiecefilm.core.source.local.dao.OnePieceDao
import com.dimas.onepiecefilm.core.source.local.entity.OnePieceEntity


@Database(
    entities = [
    OnePieceEntity::class],
    version = 1,
    exportSchema = false
)
abstract class OnePieceDatabase : RoomDatabase() {
    abstract fun onePieceDao(): OnePieceDao
}
