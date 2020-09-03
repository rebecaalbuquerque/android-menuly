package com.albuquerque.menuly.app.repository

class RepositoryImpl(
    val remote: RemoteRepository,
    val local: LocalRepository
): Repository {

}