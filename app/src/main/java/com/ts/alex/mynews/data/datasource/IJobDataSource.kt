package com.ts.alex.mynews.data.datasource

interface IJobDataSource {

    fun startJob(mls: Long)

    fun cancelJob()
}