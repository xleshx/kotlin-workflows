package company.challenge.workflows

import java.io.File
import kotlin.coroutines.experimental.buildSequence

class CorruptedFileException(message: String?) : Exception(message)

fun <R> getParsedObjectsSequence(fileName: String, conf: Map<String, (String, R) -> Unit>,
                                 factory: () -> R): Sequence<R> {
    return buildSequence {
        var hasStarted = false
        var entity: R = factory()
        File(fileName).bufferedReader().use { r ->
            r.lineSequence().forEach { line ->
                when {
                    line.startsWith("start") -> {
                        if (hasStarted) throw CorruptedFileException("Second start before end, filename: $fileName, line: $line")
                        hasStarted = true
                        entity = factory()
                    }
                    line.startsWith("end") -> {
                        if (!hasStarted) throw CorruptedFileException("End before start, filename: $fileName, line: $line")
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

