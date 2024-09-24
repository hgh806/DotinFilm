package com.borna.dotinfilm.sections.domain.use_cases

import com.borna.dotinfilm.core.data.remote.adapter.Failure
import com.borna.dotinfilm.core.data.remote.adapter.IsSucceed
import com.borna.dotinfilm.core.data.remote.adapter.Response
import com.borna.dotinfilm.core.data.remote.adapter.Success
import com.borna.dotinfilm.core.data.remote.adapter.ifSuccessful
import com.borna.dotinfilm.core.data.remote.adapter.onSuccessful
import com.borna.dotinfilm.core.data.remote.adapter.otherwise
import com.borna.dotinfilm.sections.data.mapper.toSection
import com.borna.dotinfilm.sections.domain.models.Section
import com.borna.dotinfilm.sections.domain.repository.FilmRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetSectionsUseCase(
    private val repository: FilmRepository
) {
    operator fun invoke(): Flow<Response<List<Section>>>  = flow {
        repository.getSections().onSuccessful { response ->
            val sections = response.sections.map { it.toSection() }
            IsSucceed(sections)
        }.ifSuccessful {
            emit(Success(it.data!!))
        }.otherwise {
            emit(Failure(it))
        }
    }.flowOn(IO)
}