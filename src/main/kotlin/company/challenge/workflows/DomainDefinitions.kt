package company.challenge.workflows

data class Workflow(var id: String,
                    var name: String,
                    var author: String,
                    var version: String)

data class WorkflowInstance(var id: String,
                            var workflowId: String,
                            var assignee: String,
                            var step: String,
                            var status: String)

data class Employee(var employeeId: String,
                    var fullName: String,
                    var email: String)

data class Contractor(var contractorName: String,
                      var fullName: String,
                      var email: String)
