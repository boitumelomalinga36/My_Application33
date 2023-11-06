package com.example.myapplication33
import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import android.view.View

class ItemDecorator(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = spaceHeight // Adjust the spaceHeight as needed
    }
}
