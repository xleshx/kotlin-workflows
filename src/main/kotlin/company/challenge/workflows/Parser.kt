package company.challenge.workflows

import java.io.File
import kotlin.coroutines.experimental.buildSequence

fun <R> getParsedObjectsSequence(fileName: String, conf: Map<String, (String, R) -> Unit>, sourcer: () -> R): Sequence<R> {
    return buildSequence {
        var hasStarted = false
        var workflow = sourcer()
        File(fileName).bufferedReader().use { r ->
            r.lineSequence().forEach { line ->
                when {
                    line.startsWith("start") -> {
                        hasStarted = true
                        workflow = sourcer()
                    }
                    line.startsWith("end") -> {
                        hasStarted = false
                        yield(workflow)
                    }
                    hasStarted -> {
                        val keyVal = line.split(":")
                        val lambda = conf[keyVal.first().trim()]
                        lambda?.let { it(keyVal.last().trim(), workflow) }
                    }
                }
            }
        }
    }
}

