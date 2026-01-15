package org.coffeebreak.data.ai

import android.content.Context
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.FileUtil


class CoffeeModelRunner(context: Context) {

    private val interpreter: Interpreter = Interpreter(
        FileUtil.loadMappedFile(context, "tensor.tflite")
    )

    fun run(prompt: String): String {
        val input = arrayOf(prompt)
        val output = Array(1) { ByteArray(1024) }

        interpreter.run(input, output)

        return String(output[0]).trim()
    }
}
