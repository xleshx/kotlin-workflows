package company.challenge.workflows.config

import company.challenge.workflows.Contractor

val contractorSourcer: () -> Contractor = Contractor("", "", "")

fun getContractorsConfig() : Map<String, (String, Contractor) -> Unit> {
    val setContractorName: (String?, Contractor) -> Unit = { value, e -> e.contractorName = value!! }
    val setFullName: (String?, Contractor) -> Unit = { value, e -> e.fullName = value!! }
    val setEmail: (String?, Contractor) -> Unit = { value, e -> e.email = value!! }

    return mapOf("contractorName" to setContractorName, "fullName" to setFullName, "email" to setEmail)

}
