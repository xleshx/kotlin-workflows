package company.challenge.workflows

import company.challenge.workflows.config.getWorkflowConfig
import company.challenge.workflows.config.getWorkflowInstanceConfig
import company.challenge.workflows.config.workflowInstanceSourcer
import company.challenge.workflows.config.workflowSourcer
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.assertj.core.api.Condition
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class BaseCasesTest {

    @Test
    fun `should return Workflows with instances`() {

        val workflows = getParsedObjectsSequence("./workflows.data", getWorkflowConfig(), workflowSourcer).toList()
        val workflowInstances = getParsedObjectsSequence("./workflowInstances.data",
                getWorkflowInstanceConfig(), workflowInstanceSourcer).toList()

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

        val workflows = getParsedObjectsSequence("./workflows.data", getWorkflowConfig(), workflowSourcer).toList()
        val workflowInstances = getParsedObjectsSequence("./workflowInstances.data",
                getWorkflowInstanceConfig(), workflowInstanceSourcer).toList()

        val result = getWorkflowWithCounts(workflows, workflowInstances)
        assertThat(result).hasSize(3)
        assertThat(result).extracting("workflow").extracting("id").contains("1", "2", "3")
        assertThat(result).allMatch { it!!.count > 0 }
        assertThat(result.first { it!!.workflow.id == "1"}!!.count).isEqualTo(3)
        assertThat(result.first { it!!.workflow.id == "2"}!!.count).isEqualTo(2)
    }
}