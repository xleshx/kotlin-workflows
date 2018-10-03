package company.challenge.workflows.config

import company.challenge.workflows.Employee

val employeeFactory: Employee get() = Employee("", "", "")

fun getEmployeeConfig() : Map<String, (String, Employee) -> Unit> {
    val setEmployeeId: (String?, Employee) -> Unit = { value, e -> e.employeeId = value!! }
    val setFullName: (String?, Employee) -> Unit = { value, e -> e.fullName = value!! }
    val setEmail: (String?, Employee) -> Unit = { value, e -> e.email = value!! }

    return mapOf("employeeId" to setEmployeeId, "fullName" to setFullName, "email" to setEmail)

}
