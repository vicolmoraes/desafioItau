package com.vitoria.desafioitau

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.vitoria.desafioitau.data.MonthsEnum
import com.vitoria.desafioitau.presentation.transactions.TransactionsActivity
import com.vitoria.desafioitau.presentation.transactions.TransactionsAdapter
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test


class TransactionsInstrumentedTest {
    val FIRST_ITEM = 0

    @get:Rule
    var mActivityRule: ActivityTestRule<TransactionsActivity> =
        ActivityTestRule(TransactionsActivity::class.java, false, true)

    @Test
    fun openingOfTheDetailsActivity() {

        // Act  - Click on the first transaction
        Thread.sleep(2000);
        onView(ViewMatchers.withId(R.id.rv_activity_transactions_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<TransactionsAdapter.TransactionsViewHolder>(
                    FIRST_ITEM, ViewActions.click()
                )
            )

        // Assert - Open Detail Activity
        Thread.sleep(2000);
        onView(ViewMatchers.withId(R.id.cl_activity_detail_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    @Test
    fun checkTheMonthOfTheTransaction() {

        // Act - Click in January
        Thread.sleep(2000)
        onView(ViewMatchers.withId(R.id.rv_activity_transactions_months))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<TransactionsAdapter.TransactionsViewHolder>(
                    FIRST_ITEM, ViewActions.click()
                )
            )

        // Assert - Transactions are all in January
        Thread.sleep(2000)
        onView(withId(R.id.rv_activity_transactions_list))
            .check(
                matches(
                    atPositionOnView(
                        FIRST_ITEM,
                        withText(MonthsEnum.JANEIRO.month),
                        R.id.tv_item_transaction_month
                    )
                )
            );

    }

    fun atPositionOnView(
        position: Int, itemMatcher: Matcher<View?>,
        targetViewId: Int
    ): Matcher<View?>? {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has view id $itemMatcher at position $position")
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder =
                    recyclerView.findViewHolderForAdapterPosition(position)
                val targetView =
                    viewHolder!!.itemView.findViewById<View>(targetViewId)
                return itemMatcher.matches(targetView)
            }
        }
    }

}
