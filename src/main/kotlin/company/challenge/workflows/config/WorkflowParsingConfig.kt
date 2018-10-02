package company.challenge.workflows.config

import company.challenge.workflows.Workflow

val workflowSourcer: () -> Workflow = Workflow("", "", "", "")

fun getWorkflowConfig() : Map<String, (String, Workflow) -> Unit> {

    val setId: (String?, Workflow) -> Unit = { value, w -> w.id = value!! }
    val setName: (String?, Workflow) -> Unit = { value, w -> w.name = value!! }
    val setAuthor: (String?, Workflow) -> Unit = { value, w -> w.author = value!! }
    val setVersion: (String?, Workflow) -> Unit = { value, w -> w.version = value!! }

    return mapOf("id" to setId, "name" to setName, "author" to setAuthor, "version" to setVersion)
}

