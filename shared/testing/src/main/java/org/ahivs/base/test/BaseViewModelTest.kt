package org.ahivs.base.test

import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension


@ExtendWith(TaskExecutorRule::class)
abstract class BaseViewModelTest {
    @RegisterExtension
    @JvmField
    val coroutinesTestRule = CoroutinesTestRule()

    @UseExperimental
    fun runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) {
        coroutinesTestRule.testDispatcher.runBlockingTest(block)
    }
}