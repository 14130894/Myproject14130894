package com.example.threadhandlerwizard

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class ProgressFragment : Fragment(R.layout.frag_progress) {
    private val vm: WorkViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bar = view.findViewById<ProgressBar>(R.id.progressBar)
        val txtStatus = view.findViewById<TextView>(R.id.txtStatus)
        val txtPercent = view.findViewById<TextView>(R.id.txtPercent)

        bar.isIndeterminate = true

        vm.status.observe(viewLifecycleOwner) { s ->
            txtStatus.text = s
            if (s.startsWith("Working") || s.contains("工作中")) {
                bar.isIndeterminate = false
            } else if (s == "Preparing…" || s == "Idle") {
                bar.isIndeterminate = true
                txtPercent.text = ""
            } else {
                bar.isIndeterminate = false
                txtPercent.text = ""
            }
        }

        vm.progress.observe(viewLifecycleOwner) { p ->
            if (!bar.isIndeterminate) {
                bar.max = 100
                bar.progress = p
                txtPercent.text = "$p%"
            }
        }
    }
}