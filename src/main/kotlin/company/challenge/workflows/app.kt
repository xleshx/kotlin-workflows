package company.challenge.workflows

import company.challenge.workflows.config.*

fun main(args: Array<String>) {
    val (workflows, workflowInstances, contractors) = readFilesProgrammatically()
    showReport(workflowInstances, workflows, contractors)
}

fun showReport(workflowInstances: List<WorkflowInstance>, workflows: List<Workflow>, contractors: List<Contractor>) {
    showWorkflowsWithInstances(workflowInstances, workflows)
    showWorkflowHavingRunningInstancesWithCount(workflowInstances, workflows)
    showContractorsHavingRunningWorkflows(contractors, workflowInstances)
}

fun readFilesProgrammatically(): Triple<List<Workflow>, List<WorkflowInstance>, List<Contractor>> {
    //in order to cover use case `Reading the data files programmatically`
    getParsedObjectsSequence("./employees.data", getEmployeeConfig(),
        ::employeeFactory).toList()

    val workflows = getParsedObjectsSequence("./workflows.data", getWorkflowConfig(), ::workflowFactory).toList()

    val workflowInstances = getParsedObjectsSequence("./workflowInstances.data", getWorkflowInstanceConfig(),
        ::workflowInstanceFactory).toList()

    val contractors = getParsedObjectsSequence("./contractors.data", getContractorsConfig(),
        ::contractorFactory).toList()
    return Triple(workflows, workflowInstances, contractors)
}

fun showWorkflowsWithInstances(workflowInstances: List<WorkflowInstance>, workflows: List<Workflow>) {
    val toRender = getWorkflowWithInstances(workflowInstances, workflows)
    renderWorkflowsWithInstances(toRender)
}

fun getWorkflowWithInstances(workflowInstances: List<WorkflowInstance>, workflows: List<Workflow>): List<WorkflowWithInstances> {
    val instanceByWorkflowId = workflowInstances.groupBy { f -> f.workflowId }
    return workflows.asSequence()
        .map { WorkflowWithInstances(it, instanceByWorkflowId[it.id].orEmpty()) }
        .toList()
}

fun showWorkflowHavingRunningInstancesWithCount(workflowInstances: List<WorkflowInstance>, workflows: List<Workflow>) {
    val workflowsWithCount = getWorkflowWithCounts(workflows, workflowInstances)
    renderWorkflowWithCounts(workflowsWithCount)
}

fun getWorkflowWithCounts(workflows: List<Workflow>, workflowInstances: List<WorkflowInstance>): List<WorkflowWithCount?> {
    val workflowsById = workflows.associateBy { it.id }

    val runningInstanceByWorkflowId = workflowInstances
        .asSequence()
        .filter { it.status == "RUNNING" }
        .groupBy { it.workflowId }
        .filter { (_, v) -> v.isNotEmpty() }

    val workflowsWithCount = runningInstanceByWorkflowId.asSequence()
        .map { workflowsById[it.key]?.let { it1 -> WorkflowWithCount(it1, it.value.size) } }
        .toList()
    return workflowsWithCount
}

fun showContractorsHavingRunningWorkflows(contractors: List<Contractor>, workflowInstances: List<WorkflowInstance>) {
    val countractorsWithWorkflows = getContractorsHavingRunningWorkflows(contractors, workflowInstances)
    renderContractors(countractorsWithWorkflows)
}

fun getContractorsHavingRunningWorkflows(contractors: List<Contractor>,
                                         workflowInstances: List<WorkflowInstance>): Set<Contractor> {
    val allAssigneesWithRunningInstances = workflowInstances
        .asSequence()
        .filter { it.status == "RUNNING" }
        .map { it.assignee }
        .toSet()

    return contractors.asSequence()
        .filter { allAssigneesWithRunningInstances.contains(it.email) }
        .toSet()
}

