package company.challenge.workflows.config

import company.challenge.workflows.WorkflowInstance

val workflowInstanceSourcer: () -> WorkflowInstance = WorkflowInstance("", "", "", "", "")

fun getWorkflowInstanceConfig() : Map<String, (String, WorkflowInstance) -> Unit> {

    val setWorkflowInstanceId: (String?, WorkflowInstance) -> Unit = { value, w -> w.id = value!! }
    val setWorkflowId: (String?, WorkflowInstance) -> Unit = { value, w -> w.workflowId = value!! }
    val setAssignee: (String?, WorkflowInstance) -> Unit = { value, w -> w.assignee = value!! }
    val setStep: (String?, WorkflowInstance) -> Unit = { value, w -> w.step = value!! }
    val setStatus: (String?, WorkflowInstance) -> Unit = { value, w -> w.status = value!! }

    return mapOf("id" to setWorkflowInstanceId, "workflowId" to setWorkflowId,
            "assignee" to setAssignee, "step" to setStep, "status" to setStatus)

}

