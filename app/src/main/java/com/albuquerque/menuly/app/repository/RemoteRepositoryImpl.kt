package com.albuquerque.menuly.app.repository

import com.albuquerque.menuly.app.remote.MenulyAPI
import com.albuquerque.menuly.core.remote.Remote

class RemoteRepositoryImpl: Remote(), RemoteRepository {

    private val menulyApi by lazy { getRetrofitBuilder().create(MenulyAPI::class.java) }

}