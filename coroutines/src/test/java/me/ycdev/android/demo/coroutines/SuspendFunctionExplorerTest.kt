package me.ycdev.android.demo.coroutines

import kotlinx.coroutines.runBlocking
import org.junit.Test

class SuspendFunctionExplorerTest {
    @Test
    fun exploreCallStack() = runBlocking<Unit> {
        SuspendFunctionExplorer().fetchAndLoadData()
    }
}