package com.erman.fourgame.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.erman.fourgame.R
import com.erman.fourgame.databinding.DialogGameOverBinding
import com.erman.fourgame.game.ui.GameViewModel

class GameOverDialog(private val viewModel: GameViewModel) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater

            val binding: DialogGameOverBinding =
                DataBindingUtil.inflate(inflater, R.layout.dialog_game_over, null, false)
            binding.lifecycleOwner = this
            binding.viewModel = viewModel

            builder.setPositiveButton(R.string.restart, DialogInterface.OnClickListener { _, _ ->
                viewModel.onResetPressed()
            })
                .setNegativeButton(R.string.quit, DialogInterface.OnClickListener { _, _ ->
                    viewModel.onQuitPressed()
                })

            builder.setView(binding.root)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}