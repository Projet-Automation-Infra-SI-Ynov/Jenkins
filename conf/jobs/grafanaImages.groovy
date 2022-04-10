#!groovy
println('------------------------------------------------------------------Import Job CaC/grafanaImages')
def pipelineScript = new File('/var/jenkins_config/jobs/grafanaImages-pipeline.groovy').getText("UTF-8")

pipelineJob('Cac/grafanaImages') {
    description("Ansible")
    parameters {
        stringParam {
            name('BRANCH')
            defaultValue('master')
            description('Branch to retrieve from Git')
            trim(false)
        }
        stringParam {
            name('REGISTRY_IP')
            defaultValue('192.168.10.X')
            description('Enter the Jenkins IP address')
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