package com.albuquerque.menuly.app.repository

import com.albuquerque.menuly.app.data.dao.MenuDao

class LocalRepositoryImpl(
    private val menuDao: MenuDao
): LocalRepository {

}