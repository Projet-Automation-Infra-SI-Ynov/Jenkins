#!groovy
println('------------------------------------------------------------------Import Job Saves/saveImages')
def pipelineScript = new File('/var/jenkins_config/jobs/saveImages-pipeline.groovy').getText("UTF-8")

pipelineJob('Saves/saveImages') {
    description("Ansible")
    parameters {
        stringParam {
            name('BRANCH')
            defaultValue('master')
            description('Branch to retrieve from Git')
            trim(false)
        }
        stringParam {
            name('GRAFANA_IP')
            defaultValue('192.168.10.X')
            description('Enter the Grafana IP address')
            trim(false)
        }
        stringParam {
            name('LOG_IP')
            defaultValue('192.168.10.X')
            description('Enter the Graylog IP address')
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