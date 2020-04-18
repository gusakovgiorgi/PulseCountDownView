package com.gusakov.library

import android.content.Context
import android.util.AttributeSet
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.appcompat.widget.AppCompatTextView


class PulseCountDown : AppCompatTextView {
    /**
     * class that holds values for custom attributes
     */
    private var customAttributes: CustomAttributes = CustomAttributes()
        set(value) {
            field = value
            currentCnt = value.startValue
        }

    private var currentCnt: Int = customAttributes.startValue
    /**
     * Scale animation which scales twofold from its original point
     */

    private val scaleAnimation = ScaleAnimation(
        1f, 2f,  // Start and end values for the X axis scaling
        1f, 2f,  // Start and end values for the Y axis scaling
        Animation.RELATIVE_TO_SELF, 0.5f,  // Pivot point of X scaling
        Animation.RELATIVE_TO_SELF, 0.5f
    ).apply { duration = 750 }

    /**
     * Disappear view with alpha value
     */
    private val alphaAnimation = AlphaAnimation(1f, 0f).apply { duration = 250; fillAfter = true }
    private var scaleAnimationStarted = false

    /**
     * This labda is invoked when animation is completed
     */
    private var animationCompleted: () -> Unit = {}

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )


    private fun init(context: Context, attrs: AttributeSet) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.PulseCountDown,
            0, 0
        ).apply {
            try {
                val startValue =
                    getInteger(R.styleable.PulseCountDown_pc_startValue, START_VALUE_DEFAULT)
                val endValue = getInteger(R.styleable.PulseCountDown_pc_endValue, END_VALUE_DEFAULT)
                if (startValue < endValue) throw IllegalStateException("Start value $startValue must be greater than $endValue")
                customAttributes = CustomAttributes(startValue, endValue)
            } finally {
                recycle()
            }
        }

    }

    fun start(completed: () -> Unit = {}) {
        animationCompleted = completed
        currentCnt = customAttributes.startValue
        handleAnimation()
    }


    override fun onAnimationEnd() {
        super.onAnimationEnd()
        handleAnimation()
    }

    /**
     * First Scale animation, then alpha animation
     */
    private fun handleAnimation() {
        if (scaleAnimationStarted) {
            scaleAnimationStarted = false
            startAnimation(alphaAnimation)
        } else if (currentCnt != customAttributes.endValue) {
            text = currentCnt.toString()
            currentCnt--
            scaleAnimationStarted = true
            startAnimation(scaleAnimation)
        } else animationCompleted()

    }


}