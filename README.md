### Case Description ###
There is a company that is using a self written workflow management system for collaboration both
internally and with its contractors. That system has a rough-and-ready export job that writes out the
internal state every night into the following files using an arbitrary format:
* workflows.data - the definitions of the workflows
* workflowInstances.data - instances of the workflows, each with its current status and its assignee
* employees.data - persons within the company eligible to be assigned to a workflow instance
* contractors.data - contractors from outside the company that may be involved and thus are
potential assignees
These files have to be analysed in an automated way. 

Application does the following:
* Reading the data files programmatically.
* Showing all workflows with their according instances.
* Showing all workflows having running instances and the number of those instances.
* Showing all contractors that are assignees to running instances. 

### Run ###
Project requires at least JDK 8. 
From the root directory:
````
./gradlew clean build && java -jar build/libs/workflows-fat-0.0.1.jar
````
Report will be shown in the console

### Details ###
* have never worked with Kotlin/Gradle Kotlin DSL before, used it here as an extra challenge  
* workflowInstances.data file contains data in broken format, to overcome this simple in-place parsing exception added, file fixed manually
* filenames hardcoded in app, expected to be in ./data folder

### Contact ###

alexey.lesh@gmail.com
