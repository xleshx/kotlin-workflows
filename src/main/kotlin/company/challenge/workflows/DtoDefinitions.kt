package company.challenge.workflows

data class WorkflowWithInstances(val workflow: Workflow, val instances: List<WorkflowInstance> = emptyList())
data class WorkflowWithCount(val workflow: Workflow, val count: Int = 0)