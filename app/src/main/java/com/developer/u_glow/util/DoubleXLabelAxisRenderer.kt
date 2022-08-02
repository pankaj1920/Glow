package com.developer.u_glow.util

import android.content.Context
import android.graphics.Canvas
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.renderer.XAxisRenderer
import com.github.mikephil.charting.utils.MPPointF
import com.github.mikephil.charting.utils.Transformer
import com.github.mikephil.charting.utils.Utils
import com.github.mikephil.charting.utils.ViewPortHandler


class DoubleXLabelAxisRenderer(
    var context:Context,
    viewPortHandler: ViewPortHandler,
    xAxis: XAxis,
    transformer: Transformer,
    topValueFormatter: IndexAxisValueFormatter
) : XAxisRenderer(viewPortHandler, xAxis, transformer) {

    private var topValueFormatter: IndexAxisValueFormatter? = null

    init {

        this.topValueFormatter = topValueFormatter
    }

    override fun renderAxisLabels(c: Canvas) {
        if (!mXAxis.isEnabled || !mXAxis.isDrawLabelsEnabled) return
        val yOffSet = mXAxis.yOffset
        mAxisLabelPaint.typeface = mXAxis.typeface
        mAxisLabelPaint.textSize = mXAxis.textSize
        mAxisLabelPaint.color = mXAxis.textColor
        val pointF = MPPointF.getInstance(0f, 0f)
        if (mXAxis.position == XAxis.XAxisPosition.TOP){
            pointF.x = 0.5f
            pointF.y = 1.0f
            drawLabels(c, mViewPortHandler.contentTop() - yOffSet, pointF)
        } else if (mXAxis.position == XAxis.XAxisPosition.TOP_INSIDE) {
            pointF.x = 0.5f
            pointF.y = 1.0f
            drawLabels(
                c,
                mViewPortHandler.contentTop() + yOffSet + mXAxis.mLabelRotatedHeight,
                pointF
            )
        } else if (mXAxis.position == XAxis.XAxisPosition.BOTTOM) {
            pointF.x = 0.5f
            pointF.y = 0.0f
            drawLabels(c, mViewPortHandler.contentBottom() + yOffSet, pointF)
        } else if (mXAxis.position == XAxis.XAxisPosition.BOTTOM_INSIDE) {
            pointF.x = 0.5f
            pointF.y = 0.0f
            drawLabels(
                c,
                mViewPortHandler.contentBottom() - yOffSet - mXAxis.mLabelRotatedHeight,
                pointF
            )
        } else { // BOTH SIDED
            pointF.x = 0.5f
            pointF.y = 1.0f
            drawLabelsTop(c, mViewPortHandler.contentTop() - yOffSet, pointF)
            pointF.x = 0.5f
            pointF.y = 0.0f
            drawLabels(c, mViewPortHandler.contentBottom() + yOffSet, pointF)
        }
        MPPointF.recycleInstance(pointF)
    }

    private fun drawLabelsTop(c: Canvas, pos: Float, anchor: MPPointF) {
        val labelRotationAngleDegrees = mXAxis.labelRotationAngle
        val centeringEnabled = mXAxis.isCenterAxisLabelsEnabled
        val positions = FloatArray(mXAxis.mEntryCount * 2)
        run {
            var i = 0
            while (i < positions.size) {


                // only fill x values
                if (centeringEnabled) {
                    positions[i] = mXAxis.mCenteredEntries[i / 2]
                } else {
                    positions[i] = mXAxis.mEntries[i / 2]
                }
                i += 2
            }
        }
        mTrans.pointValuesToPixel(positions)
        var i = 0
        while (i < positions.size) {
            var x = positions[i]
            if (mViewPortHandler.isInBoundsX(x)) {
                val label = topValueFormatter!!.getAxisLabel(mXAxis.mEntries[i / 2], mXAxis)
                if (mXAxis.isAvoidFirstLastClippingEnabled) {

                    // avoid clipping of the last
                    if (i == mXAxis.mEntryCount - 1 && mXAxis.mEntryCount > 1) {
                        val width: Float = Utils.calcTextWidth(mAxisLabelPaint, label).toFloat()
                        if (width > mViewPortHandler.offsetRight() * 2
                            && x + width > mViewPortHandler.chartWidth
                        ) x -= width / 2

                        // avoid clipping of the first
                    } else if (i == 0) {
                        val width: Float = Utils.calcTextWidth(mAxisLabelPaint, label).toFloat()
                        x += width / 2
                    }
                }
                drawLabel(c, label, x, pos, anchor, labelRotationAngleDegrees)
            }
            i += 2
        }
    }
}