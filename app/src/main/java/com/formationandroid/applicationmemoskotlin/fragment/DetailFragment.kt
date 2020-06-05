package com.formationandroid.applicationmemoskotlin.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.formationandroid.applicationmemoskotlin.R


class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        // init :
        super.onActivityCreated(savedInstanceState)
        if (view != null && context != null) {
            // vues :
            val textViewMemo = view!!.findViewById<TextView>(R.id.detail_memo)
            if (arguments != null) {
                textViewMemo.text = arguments!!.getString(EXTRA_MEMO)
            }
        }
    }

    companion object {
        // Constantes :
        const val EXTRA_MEMO = "EXTRA_MEMO"
    }
}
