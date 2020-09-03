package com.albuquerque.menuly.app.data.dao

import androidx.room.Dao
import com.albuquerque.menuly.app.data.entity.MenuEntity

@Dao
interface MenuDao: BaseDao<MenuEntity> {
}