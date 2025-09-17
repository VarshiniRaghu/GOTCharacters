package com.example.gotcharactersapp.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest

@ExperimentalCoroutinesApi
public class ViewModelFlowCollector<S, E>(
    private val stateFlow: Flow<S>,
    private val eventFlow: Flow<E>,
) {
    public fun test(test: suspend TestScope.(List<S>, List<E>) -> Unit): Unit = runTest {
        val states = mutableListOf<S>()
        val stateJob = launch { stateFlow.toList(states) }
        val events = mutableListOf<E>()
        val eventJob = launch { eventFlow.toList(events) }
        test(states, events)
        stateJob.cancel()
        eventJob.cancel()
    }
}