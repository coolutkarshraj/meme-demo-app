package com.io.utkarsh.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import com.io.utkarsh.data.api.NetworkService
import com.io.utkarsh.data.model.MemeApiResponseModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemeRepository @Inject constructor(private val networkService: NetworkService) {

    fun getMemeApiResponse(): Flow<MemeApiResponseModel> {
        return flow {
            emit(networkService.getMemeApi())
        }.map {
            it
        }
    }

}