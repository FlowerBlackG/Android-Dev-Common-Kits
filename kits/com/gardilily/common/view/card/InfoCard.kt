package com.gardilily.common.view.card

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView

class InfoCard(context: Context) : RelativeLayout(context) {
	var spMultiply = 1f
	fun setSpMultiply(value: Float): InfoCard {
		spMultiply = value
		return this
	}

	var cardBackground: Drawable? = null
	fun setCardBackground(value: Drawable): InfoCard {
		cardBackground = value
		return this
	}

	var outerMarginBottomSp = 12f
	fun setOuterMarginBottomSp(value: Float): InfoCard {
		outerMarginBottomSp = value
		return this
	}

	var outerMarginTopSp = 0f
	fun setOuterMarginTopSp(value: Float): InfoCard {
		outerMarginTopSp = value
		return this
	}

	var outerMarginStartSp = 0f
	fun setOuterMarginStartSp(value: Float): InfoCard {
		outerMarginStartSp = value
		return this
	}

	var outerMarginEndSp = 0f
	fun setOuterMarginEndSp(value: Float): InfoCard {
		outerMarginEndSp = value
		return this
	}

	var innerMarginBetweenSp = 12f
	var innerMarginTopSp = 12f
	var innerMarginBottomSp = 12f
	var innerMarginStartSp = 12f
	var innerMarginEndSp = 12f

	var textLineSpaceSp = 1f

	var layoutWidth = LayoutParams.MATCH_PARENT
	var layoutHeight = LayoutParams.WRAP_CONTENT

	var hasIcon = true
	fun setHasIcon(value: Boolean): InfoCard {
		hasIcon = value
		return this
	}

	var icon = "🍓"
	fun setIcon(value: String): InfoCard {
		icon = value
		return this
	}

	var iconTextSizeDp = 48f

	var hasEndMark = false
	fun setHasEndMark(value: Boolean): InfoCard {
		hasEndMark = value
		return this
	}

	var endMark = "A"
	fun setEndMark(value: String): InfoCard {
		endMark = value
		return this
	}

	var endMarkTextSizeDp = 52

	var title = "标题"
	fun setTitle(value: String): InfoCard {
		title = value
		return this
	}

	var titleTextSizeDp = 24f

	var titleMaxEms = 12

	var titleMaxLines = 1

	var titleEllipsize = TextUtils.TruncateAt.END

	var infoTextSizeDp = 14f

	class Info(var title: String, var text: String)

	private val c = context
	private val infoList = ArrayList<Info>()

	fun addInfo(info: Info): InfoCard {
		infoList.add(info)
		return this
	}

	fun build(): InfoCard {
		val params = LayoutParams(
			layoutWidth,
			layoutHeight
		)
		params.marginStart = floatSp2intPx(outerMarginStartSp)
		params.marginEnd = floatSp2intPx(outerMarginEndSp)
		params.topMargin = floatSp2intPx(outerMarginTopSp)
		params.bottomMargin = floatSp2intPx(outerMarginBottomSp)
		this.layoutParams = params

		if (cardBackground != null) {
			this.background = cardBackground
		}

		this.isClickable = true

		val iconView = TextView(c)
		iconView.text = icon
		iconView.textSize = iconTextSizeDp
		iconView.setTextColor(Color.BLACK)

		iconView.visibility =
			if (hasIcon) {
				View.VISIBLE
			} else {
				View.GONE
			}

		val iconViewParams = LayoutParams(
			LayoutParams.WRAP_CONTENT,
			LayoutParams.WRAP_CONTENT
		)
		iconViewParams.marginStart = floatSp2intPx(innerMarginStartSp)
		iconViewParams.addRule(CENTER_VERTICAL)

		iconView.layoutParams = iconViewParams

		this.addView(iconView)

		val infoLinearLayout = LinearLayout(c)
		infoLinearLayout.orientation = LinearLayout.VERTICAL
		infoLinearLayout.gravity = Gravity.CENTER_VERTICAL

		val infoLinearLayoutParams = LayoutParams(
			LayoutParams.MATCH_PARENT,
			LayoutParams.MATCH_PARENT
		)
		infoLinearLayoutParams.marginStart = floatSp2intPx(
			innerMarginStartSp +
					if (hasIcon) {
						innerMarginBetweenSp + iconTextSizeDp
					} else {
						0f
					}
		)

		infoLinearLayoutParams.marginEnd = floatSp2intPx(innerMarginEndSp)
		infoLinearLayoutParams.topMargin = floatSp2intPx(innerMarginTopSp)
		infoLinearLayoutParams.bottomMargin = floatSp2intPx(innerMarginBottomSp)

		infoLinearLayout.layoutParams = infoLinearLayoutParams

		val titleTV = TextView(c)
		titleTV.text = title
		titleTV.textSize = titleTextSizeDp
		titleTV.maxEms = titleMaxEms
		titleTV.maxLines = titleMaxLines
		titleTV.ellipsize = titleEllipsize

		infoLinearLayout.addView(titleTV)

		infoList.forEach {
			val row = LinearLayout(c)
			row.orientation = LinearLayout.HORIZONTAL
			val rowParams = LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT
			)
			rowParams.topMargin = floatSp2intPx(1f)
			row.layoutParams = rowParams

			val tvParams = LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT
			)
			val tvTitle = TextView(c)
			tvTitle.layoutParams = tvParams
			tvTitle.textSize = infoTextSizeDp
			tvTitle.text = "${it.title}："
			val tvText = TextView(c)
			tvText.layoutParams = tvParams
			tvText.textSize = infoTextSizeDp
			tvText.text = it.text

			row.addView(tvTitle)
			row.addView(tvText)

			infoLinearLayout.addView(row)
		}

		this.addView(infoLinearLayout)

		return this
	}

	private fun floatSp2intPx(value: Float): Int {
		return (value * spMultiply).toInt()
	}
}
