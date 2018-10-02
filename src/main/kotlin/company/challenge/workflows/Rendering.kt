package company.challenge.workflows

fun renderWorkflowsWithInstances(workflowWithInstances: List<WorkflowWithInstances>) {
    wrapHeader("WORKFLOW WITH INSTANCES: ")
    fun renderInstances(instances: List<WorkflowInstance>) {
        instances.forEach { wi -> println("-- Instance ${wi.id}: $wi") }
    }

    workflowWithInstances.forEach { w ->
        val workflow = w.workflow
        println("Workflow ${workflow.name}: $workflow ")
        renderInstances(w.instances)
        println()
    }
}

fun renderWorkflowWithCounts(workflowsWithCount: List<WorkflowWithCount?>) {
    wrapHeader("WORKFLOW HAVING NUMBER OF RUNNING INSTANCES: ")

    workflowsWithCount.forEach {
        println("- Workflow ${it!!.workflow.name} has ${it.count} running instances - ${it.workflow} ")
    }
}

fun renderContractors(contractors: Set<Contractor>) {
    wrapHeader("CONTRACTORS HAVING RUNNING WORKFLOW INSTANCES: ")

    contractors.forEach { println(it) }
}

fun wrapHeader(header: String){
    println()
    println(header)
    println()
}