package com.example.mycleanarchitecture.presentation.util.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.mycleanarchitecture.R
import kotlinx.android.synthetic.main.error_view_layout.view.*

class ErrorView : ConstraintLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, attributeSetId: Int) : super(
        context,
        attrs,
        attributeSetId
    )

    private var view: View = inflate(context, R.layout.error_view_layout, this)

    fun setError(errorImage: Int, errorMessage: String) {
        view.error_image.setImageResource(errorImage)
        view.error_text.text = errorMessage
    }

}