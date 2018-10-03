package company.challenge.workflows

import java.io.File
import kotlin.coroutines.experimental.buildSequence

fun <R> getParsedObjectsSequence(fileName: String, conf: Map<String, (String, R) -> Unit>,
                                 factory: () -> R): Sequence<R> {
    return buildSequence {
        var hasStarted = false
        var entity: R = factory()
        File(fileName).bufferedReader().use { r ->
            r.lineSequence().forEach { line ->
                when {
                    line.startsWith("start") -> {
                        hasStarted = true
                        entity = factory()
                    }
                    line.startsWith("end") -> {
                        hasStarted = false
                        yield(entity)
                    }
                    hasStarted -> {
                        val keyVal = line.split(":")
                        val lambda = conf[keyVal.first().trim()]
                        lambda?.let { it(keyVal.last().trim(), entity) }
                    }
                }
            }
        }
    }
}

