#!groovy
println('------------------------------------------------------------------Import Job IaC/terraform')
def pipelineScript = new File('/var/jenkins_config/jobs/terraform-pipeline.groovy').getText("UTF-8")

pipelineJob('IaC/terraform') {
    description("Terraform")
    parameters {
        stringParam {
            name('BRANCH')
            defaultValue('master')
            description('Branch to retrieve from Git')
            trim(false)
        }
        stringParam {
            name('SERVER_NAME')
            defaultValue('Debian')
            description('Enter the name of the new server')
            trim(false)
        }
        choice {
            name('ACTION')
            choices(['apply', 'destroy'])
            description('Select the action')
        }
    }
    definition {
        cps {
            script(pipelineScript)
            sandbox()
        }
    }
}