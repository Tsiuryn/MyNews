package com.ts.alex.mynews.domain.usecase

interface IJobUseCase {

    fun startJob(mls: Long)

    fun cancelJob()
}