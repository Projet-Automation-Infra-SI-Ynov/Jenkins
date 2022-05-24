#!groovy
println('------------------------------------------------------------------Import Job CaC/nginx')
def pipelineScript = new File('/var/jenkins_config/jobs/nginx-pipeline.groovy').getText("UTF-8")

pipelineJob('Cac/nginx') {
    description("Ansible")
    parameters {
        stringParam {
            name('BRANCH')
            defaultValue('master')
            description('Branch to retrieve from Git')
            trim(false)
        }
        stringParam {
            name('JENKINS_IP')
            defaultValue('192.168.10.X')
            description('Enter the Jenkins IP address')
            trim(false)
        }
        stringParam {
            name('REGISTRY_IP')
            defaultValue('192.168.10.X')
            description('Enter the Registry IP address')
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