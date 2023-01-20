package com.google.maps.android.compose

import android.content.Context
import android.graphics.*
import android.util.TypedValue
import kotlin.math.roundToInt

internal class MarkerIconFactory(private val context: Context) {

    private val clusterOuterCirclePaint = Paint().apply { color = Color.parseColor("#26000000") }
    private val clusterInnerCirclePaint = Paint().apply { color = Color.parseColor("#FF000000") }
    private val clusterTextPaint = Paint().apply {
        color = Color.parseColor("#FFFFFFFF")
        textAlign = Paint.Align.LEFT
        textSize = 16.toPx()
    }
    private val clusterCanvasRect = Rect()
    private val itemPaint = Paint().apply { color = Color.parseColor("#0000FF") }

    fun createClusterIcon(text: String?): Bitmap {
        val side = 56.toPx()
        val center = side / 2
        val outerRadius = side / 2
        val innerRadius = 32.toPx() / 2
        val bitmap = Bitmap.createBitmap(side.roundToInt(), side.roundToInt(), Bitmap.Config.ARGB_8888)

        Canvas(bitmap).run {
            drawCircle(center, center, outerRadius, clusterOuterCirclePaint)
            drawCircle(center, center, innerRadius, clusterInnerCirclePaint)

            if (text != null) {
                clusterTextPaint.getTextBounds(text, 0, text.length, clusterCanvasRect)
                val x = side / 2 - clusterCanvasRect.width() / 2 - clusterCanvasRect.left
                val y = side / 2 + clusterCanvasRect.height() / 2f - clusterCanvasRect.bottom
                drawText(text, x, y, clusterTextPaint)
            }
        }
        return bitmap
    }

    fun createItemIcon(item: MyItem, selected: Boolean): Bitmap {
        val padding = if (selected) 12.toPx() else 4.toPx()

        val width = padding * 2 + 24.toPx()
        val height = padding * 2 + 24.toPx()

        val bitmap = Bitmap.createBitmap(width.roundToInt(), height.roundToInt(), Bitmap.Config.ARGB_8888)
        Canvas(bitmap).run {
            drawRoundRect(0f, 0f, width, height, 16.toPx(), 16.toPx(), itemPaint)
        }
        return bitmap
    }

    private fun Int.toPx() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics)
}