#!groovy
println('------------------------------------------------------------------Import Job CaC/k3s')
def pipelineScript = new File('/var/jenkins_config/jobs/k3s-pipeline.groovy').getText("UTF-8")

pipelineJob('Cac/k3s') {
    description("Ansible")
    parameters {
        stringParam {
            name('BRANCH')
            defaultValue('master')
            description('Branch to retrieve from Git')
            trim(false)
        }
    }
    definition {
        cps {
            script(pipelineScript)
            sandbox()
        }
    }
}