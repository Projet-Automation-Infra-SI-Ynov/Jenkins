#!groovy
println('------------------------------------------------------------------Import Job CaC/grafana')
def pipelineScript = new File('/var/jenkins_config/jobs/grafana-pipeline.groovy').getText("UTF-8")

pipelineJob('Cac/grafana') {
    description("Ansible")
    parameters {
        stringParam {
            name('BRANCH')
            defaultValue('master')
            description('Branch to retrieve from Git')
            trim(false)
        }
        stringParam {
            name('IP_JENKINS')
            defaultValue('192.168.1.1')
            description('Enter the IP address of the Jenkins app')
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