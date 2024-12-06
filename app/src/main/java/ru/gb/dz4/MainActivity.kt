package ru.gb.dz4

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.google.android.material.checkbox.MaterialCheckBox.STATE_UNCHECKED
import com.google.android.material.snackbar.Snackbar
import ru.gb.dz4.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.photo.scaleType = ImageView.ScaleType.CENTER_INSIDE
        binding.textName.counterMaxLength = 40
        binding.progress.progress = Random.nextInt(101)
        binding.scoresDigit.text = binding.progress.progress.toString() + "/100"
        binding.textButton.isEnabled = false

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        radioGroup.setOnCheckedChangeListener { _, buttonId ->
            checkAll(binding)
        }
        binding.switchMaterial.setOnCheckedChangeListener() { _, _ ->
            checkState(binding)
            checkAll(binding)
        }
        binding.textButton.setOnClickListener {
            showSnackbar("Изменения сохранены")
        }
        binding.editText1.doOnTextChanged { text, start, before, count ->
            checkAll(binding)
        }
        binding.editText2.doOnTextChanged { text, start, before, count ->
            checkAll(binding)
        }
        binding.checkbox1.setOnClickListener {
            checkAll(binding)
        }
        binding.checkbox2.setOnClickListener {
            checkAll(binding)
        }
    }

    private fun checkAll(binding: ActivityMainBinding) {
        var switchAdd: Boolean = false
        if (((binding.switchMaterial.isChecked) &&
            ((binding.checkbox1.isChecked) || (binding.checkbox2.isChecked))) ||
            (!binding.switchMaterial.isChecked)
        ) {
            switchAdd = true
        }
        if ((binding.editText1.length() in 1..40) &&
            (binding.editText2.length() in 1..17) &&
            ((binding.radioButtonOne.isChecked) || (binding.radioButtonTwo.isChecked)) &&
            (switchAdd)
        ) {
            binding.textButton.isEnabled = true
        } else binding.textButton.isEnabled = false
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
    }

    private fun checkState(binding: ActivityMainBinding) {
        if (binding.switchMaterial.isChecked) {
            binding.switchMaterial.isChecked = true
            binding.checkbox1.isEnabled = true
            binding.checkbox2.isEnabled = true
        } else {
            binding.switchMaterial.isChecked = false
            binding.checkbox1.isEnabled = false
            binding.checkbox2.isEnabled = false
            binding.checkbox1.checkedState = STATE_UNCHECKED
            binding.checkbox2.checkedState = STATE_UNCHECKED
        }
    }
}