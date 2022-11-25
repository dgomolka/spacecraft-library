package com.psycodeinteractive.spacecraftlibrary.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp

@Composable
fun MeasureUnconstrainedViewHeight(
    viewToMeasure: @Composable () -> Unit,
    content: @Composable (measuredHeight: Dp) -> Unit
) {
    SubcomposeLayout { constraints ->
        val measuredHeight = subcompose("viewToMeasure", viewToMeasure)[0]
            .measure(Constraints()).height.toDp()

        val contentPlaceable = subcompose("content") {
            content(measuredHeight)
        }

        if (contentPlaceable.isNotEmpty()) {
            val measure = contentPlaceable[0].measure(constraints)
            layout(measure.height, measure.height) {
                measure.place(0, 0)
            }
        } else {
            layout(0, 0) {}
        }
    }
}
