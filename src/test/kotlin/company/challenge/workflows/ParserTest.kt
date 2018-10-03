package company.challenge.workflows

import company.challenge.workflows.config.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParserTest {

    @Test
    fun `successfully parse workflows from the file`() {
        val parsed = getParsedObjectsSequence("./workflows.data", getWorkflowConfig(), ::workflowFactory).toList()
        assertThat(parsed).hasSize(3)
        assertThat(parsed).extracting("id").contains("1", "2", "3")
        assertThat(parsed).extracting("name")
                .contains("Purchase Request Approval Sub-Workflow", "Invoice Approval Sub-Workflow", "Procurement Workflow")
    }

    @Test
    fun `successfully parse workflow instances from the file`() {
        val parsed = getParsedObjectsSequence("./workflowInstances.data",
                getWorkflowInstanceConfig(), ::workflowInstanceFactory).toList()

        assertThat(parsed).hasSize(19)
        assertThat(parsed).extracting("id")
                .contains("1524667587190714616", "535828436669789118", "8563780828914013865",
                "3683228499925040330", "8109038857709595886", "4784917772368473437", "268212283749527254",
                "2737875753375411154", "5709038654199052856", "5709038654199052856", "1354720373442029191",
                "7030155089190540600", "7867291045058546588", "5664173453017357474", "3675438571717748310",
                "5354061769058793437", "1144354076396245253", "8188195007108246993", "5214148519671346594")
        assertThat(parsed).extracting("workflowId").contains("1", "2", "3")
    }

    @Test
    fun `successfully parse employees from the file`() {
        val parsed = getParsedObjectsSequence("./employees.data", getEmployeeConfig(), ::employeeFactory).toList()
        assertThat(parsed).hasSize(6)
        assertThat(parsed).extracting("employeeId").contains("0009", "0199", "0003", "0112", "0200", "0198")
        assertThat(parsed).extracting("email")
                .contains("john.doe@company.local", "liam.mills@company.local", "jean.dupont@company.local",
                        "sally.housecoat@company.local", "joshua.knox@company.local", "h.doster@company.local")
    }

    @Test
    fun `successfully parse contractors from the file`() {
        val parsed = getParsedObjectsSequence("./contractors.data", getContractorsConfig(), ::contractorFactory).toList()
        assertThat(parsed).hasSize(3)
        assertThat(parsed).extracting("contractorName").contains("con24", "con99", "con07")
        assertThat(parsed).extracting("email")
                .contains("conny.contractor@example.com", "erica.external@example.com", "f.consultant@example.org")
    }

}
