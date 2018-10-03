package company.challenge.workflows

import company.challenge.workflows.config.*
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BaseCasesTest {

    @Test
    fun `should return Workflows with instances`() {

        val workflows = getParsedObjectsSequence("./data/workflows.data", getWorkflowConfig(), ::workflowFactory).toList()
        val workflowInstances = getParsedObjectsSequence("./data/workflowInstances.data",
                getWorkflowInstanceConfig(), ::workflowInstanceFactory).toList()

        val result = getWorkflowWithInstances(workflowInstances, workflows)
        assertThat(result).hasSize(3)
        assertThat(result).extracting("workflow").extracting("id").contains("1", "2", "3")
        assertThat(result).allMatch { it.instances.isNotEmpty() }
        assertThat(result.first {it.workflow.id == "1"}.instances)
                .hasSize(6)
                .extracting("id").containsExactly("1524667587190714616", "8109038857709595886", "4784917772368473437",
                        "268212283749527254", "7867291045058546588", "5664173453017357474")
    }

    @Test
    fun `should return Workflows with running instance counts`() {

        val workflows = getParsedObjectsSequence("./data/workflows.data", getWorkflowConfig(), ::workflowFactory).toList()
        val workflowInstances = getParsedObjectsSequence("./data/workflowInstances.data",
                getWorkflowInstanceConfig(), ::workflowInstanceFactory).toList()

        val result = getWorkflowWithCounts(workflows, workflowInstances)
        assertThat(result).hasSize(3)
        assertThat(result).extracting("workflow").extracting("id").contains("1", "2", "3")
        assertThat(result).allMatch { it!!.count > 0 }
        assertThat(result.first { it!!.workflow.id == "1"}!!.count).isEqualTo(3)
        assertThat(result.first { it!!.workflow.id == "2"}!!.count).isEqualTo(3)
    }

    @Test
    fun `should return Contractors with running instance counts`() {

        val contractors = getParsedObjectsSequence("./data/contractors.data", getContractorsConfig(),
                ::contractorFactory).toList()
        val workflowInstances = getParsedObjectsSequence("./data/workflowInstances.data",
                getWorkflowInstanceConfig(), ::workflowInstanceFactory).toList()

        val result = getContractorsHavingRunningWorkflows(contractors, workflowInstances)

        assertThat(result).hasSize(2)
        assertThat(result).extracting("contractorName").containsExactly("con99", "con07")
    }
}