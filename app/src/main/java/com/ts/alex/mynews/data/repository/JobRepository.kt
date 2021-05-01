package com.ts.alex.mynews.data.repository

import com.ts.alex.mynews.data.datasource.IJobDataSource
import com.ts.alex.mynews.domain.usecase.IJobUseCase

class JobRepository (private val source: IJobDataSource): IJobUseCase {
    override fun startJob(mls: Long) {
        source.startJob(mls)
    }

    override fun cancelJob() {
        source.cancelJob()
    }
}