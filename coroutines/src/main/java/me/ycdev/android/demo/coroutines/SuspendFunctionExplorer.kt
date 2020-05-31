package me.ycdev.android.demo.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import me.ycdev.android.demo.coroutines.model.LocalData
import me.ycdev.android.demo.coroutines.model.RemoteData

class SuspendFunctionExplorer {
    suspend fun fetchAndLoadData(): LocalData {
        return doFetchAndLoadData()
    }

    private suspend fun doFetchAndLoadData(): LocalData {
        logCallStack("in #doFetchAndLoadData(), START & before #fetchDataFromServer()")
        val remoteData = fetchDataFromServer("111")

        logCallStack("in #doFetchAndLoadData(), before #saveData()")
        val localData = saveData(remoteData)

        logCallStack("in #doFetchAndLoadData(), END")
        return localData
    }

    private suspend fun fetchDataFromServer(id: String): RemoteData {
        withContext(Dispatchers.IO) {
            logCallStack("in #fetchDataFromServer(), I/O dispatcher")
            delay(1000) // simulate network delay
        }
        return RemoteData("Remote data for [$id]")
    }

    private suspend fun saveData(data: RemoteData): LocalData {
        withContext(Dispatchers.Default) {
            logCallStack("in #saveData(), in default dispatcher")
            delay(1000) // simulate data processing
        }
        return LocalData("Local data for [${data.data}]")
    }

    private fun logCallStack(msg: String) {
        println("${Thread.currentThread()}: " + msg)
        Thread.dumpStack()
    }
}