package com.developer.u_glow.view.fragment.analytics


import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.databinding.FragmentAnalyticsBinding
import com.developer.u_glow.util.DoubleXLabelAxisRenderer
import com.developer.u_glow.viewmodel.analytics.AnalyticsViewModel
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.fragment_analytics.view.*


class AnalyticsFragment : BaseFragment<AnalyticsViewModel, FragmentAnalyticsBinding>() {


    override val mViewModel: AnalyticsViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_analytics

    override fun subscribeObservers() {

    }

    override fun onFragmentCreated() {

        mViewBinding.view = this
        val mLineChart = mViewBinding.lineChart
        val eLineChart = mViewBinding.lineChartEarnings


        mLineChart.isDragEnabled = true
//        remove the below text
        mLineChart.legend.isEnabled = false
        eLineChart.legend.isEnabled = false

        mLineChart.setScaleEnabled(false)
        eLineChart.setScaleEnabled(false)
        val yValue = ArrayList<Entry>()

        yValue.add(Entry(0f, 15f))
        yValue.add(Entry(1f, 20f))
        yValue.add(Entry(2f, 18f))
        yValue.add(Entry(3f, 17f))
        yValue.add(Entry(4f, 20f))
        yValue.add(Entry(5f, 22f))

        val set1 = LineDataSet(yValue, "Data set1")

        set1.fillAlpha = 200

        set1.color = ContextCompat.getColor(requireContext(), R.color.chartColor)
        set1.setCircleColor(ContextCompat.getColor(requireContext(), R.color.chartColor))
        set1.circleHoleColor = ContextCompat.getColor(requireContext(), R.color.chartColor)
        set1.lineWidth = 2f
        set1.valueTextSize = 0f
        set1.circleRadius = 8f
        mLineChart.lineChart.axisRight.gridLineWidth = 5f
        mLineChart.axisRight.gridLineWidth = 5f

        val dataSet = ArrayList<ILineDataSet>()
        dataSet.add(set1)

        val data = LineData(dataSet)
        data.setDrawValues(false)
        mLineChart.data = data
        mLineChart.axisRight.isEnabled = false
        mLineChart.axisLeft.isEnabled = false
        val yAxis = mLineChart.axisLeft
        yAxis.granularity = 2f


        eLineChart.axisRight.isEnabled = false
        eLineChart.axisLeft.isEnabled = false
        eLineChart.data = data

        val values = arrayOf("JAN", "FEB", "MAR", "APR", "MAY", "JUN")
        val numValues = arrayOf("1", "3", "2", "1", "2", "4")
        val xAxis = mLineChart.xAxis
        val xAxisE = eLineChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(values)
        xAxis.position = XAxis.XAxisPosition.BOTH_SIDED
        xAxis.setDrawAxisLine(false)
        xAxisE.valueFormatter = IndexAxisValueFormatter(values)

        xAxisE.position = XAxis.XAxisPosition.BOTH_SIDED
        mLineChart.xAxis.axisLineColor =
            ContextCompat.getColor(requireContext(), R.color.chartColor)
//        set the label label color
        mLineChart.xAxis.textColor= ContextCompat.getColor(requireContext(), R.color.textBlack)



//        set the top label
        mLineChart.setXAxisRenderer(
            DoubleXLabelAxisRenderer(
                requireContext(),
                mLineChart.viewPortHandler,
                mLineChart.xAxis,
                mLineChart.getTransformer(YAxis.AxisDependency.LEFT),
                IndexAxisValueFormatter(numValues)
            )
        )
        eLineChart.setXAxisRenderer(
            DoubleXLabelAxisRenderer(
                requireContext(),
                mLineChart.viewPortHandler,
                mLineChart.xAxis,
                mLineChart.getTransformer(YAxis.AxisDependency.LEFT),
                IndexAxisValueFormatter(numValues)
            )
        )
//        x axis line width
        xAxis.axisLineWidth = 3f


//      set the size of the dataLine
        xAxis.granularity = 1f
        xAxisE.granularity = 1f


//        remove the description
        val description = mLineChart.description
        description.isEnabled = false
        description.textColor=Color.BLUE
        val descriptionE = eLineChart.description
        descriptionE.isEnabled = false

//         make the line dotted
        xAxis.enableGridDashedLine(10f, 10f, 0f)
        xAxisE.enableGridDashedLine(10f, 10f, 0f)

//        grid width
        xAxis.gridLineWidth=1f

//        refresh chart on loading
        mLineChart.invalidate()
        mLineChart.refreshDrawableState()
        eLineChart.invalidate()
        eLineChart.refreshDrawableState()

    }

    fun onClickNavigation() {
//        val action = AnalyticsFragmentDirections.actionAnalyticsFragmentToNotificationFragment()
//        findNavController().navigate(action)
    }


}