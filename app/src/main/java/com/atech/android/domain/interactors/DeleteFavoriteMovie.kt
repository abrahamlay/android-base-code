package com.atech.domain.interactors

import com.atech.domain.FlowableUseCase
import com.atech.domain.PostExecutionThread
import com.atech.domain.entities.MovieModel
import com.atech.domain.repositories.MovieRepository
import io.reactivex.Flowable

/**
 * Created by Abraham Lay on 13/06/20.
 */
class DeleteFavoriteMovie constructor(
    private val repository: MovieRepository,
    postExecutionThread: PostExecutionThread
) : FlowableUseCase<Int, DeleteFavoriteMovie.Params>(postExecutionThread) {
    override fun build(params: Params): Flowable<Int> {
        return Flowable.just(
            repository.deleteFavoriteMovie(params.movieModel)
        )
    }

    data class Params(val movieModel: MovieModel)
}

