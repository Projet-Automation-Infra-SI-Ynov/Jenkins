#!groovy
println('------------------------------------------------------------------Import Job CaC/graylog')
def pipelineScript = new File('/var/jenkins_config/jobs/graylog-pipeline.groovy').getText("UTF-8")

pipelineJob('CaC/graylog') {
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
            description('Enter the registry IP address')
            trim(false)
        }
        stringParam {
            name('LOG_IP')
            defaultValue('192.168.10.X')
            description('Enter the graylog IP address')
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