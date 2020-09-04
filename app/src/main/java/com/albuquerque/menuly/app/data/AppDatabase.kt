package com.albuquerque.menuly.app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.albuquerque.menuly.app.data.dao.CategoryDao
import com.albuquerque.menuly.app.data.dao.FoodDao
import com.albuquerque.menuly.app.data.entity.CategoryEntity
import com.albuquerque.menuly.app.data.entity.FoodEntity
import com.albuquerque.menuly.app.data.entity.MenuEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [CategoryEntity::class, FoodEntity::class, MenuEntity::class]
)
abstract class AppDatabase: RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "menuly"
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: synchronized(this) {buildDatabase(context)}
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context, AppDatabase::class.java,
                DATABASE_NAME
            )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                    }
                })
                .fallbackToDestructiveMigration()
                .build()
        }

    }

    abstract val categoryDAO: CategoryDao
    abstract val foodDAO: FoodDao

}