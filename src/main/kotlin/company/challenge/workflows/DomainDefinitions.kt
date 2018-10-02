package company.challenge.workflows

data class Workflow(var id: String, var name: String, var author: String, var version: String) : () -> Workflow {
    override fun invoke(): Workflow {
        return Workflow("", "", "", "")
    }
}

data class WorkflowInstance(var id: String, var workflowId: String, var assignee: String,
                            var step: String, var status: String) : () -> WorkflowInstance {
    override fun invoke(): WorkflowInstance {
        return WorkflowInstance("", "", "", "", "")
    }
}

data class Employee(var employeeId: String, var fullName: String, var email: String) : () -> Employee {
    override fun invoke(): Employee{
        return Employee("", "", "")
    }
}

data class Contractor(var contractorName: String, var fullName: String, var email: String) : () -> Contractor {
    override fun invoke(): Contractor{
        return Contractor("", "", "")
    }
}
