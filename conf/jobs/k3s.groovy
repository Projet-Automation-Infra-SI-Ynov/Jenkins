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
        stringParam {
            name('REGISTRY_IP')
            defaultValue('192.168.10.X')
            description('Enter the registry IP address')
            trim(false)
        }
        stringParam {
            name('MASTER_IP')
            defaultValue('192.168.10.X')
            description('Enter the master IP address')
            trim(false)
        }
        stringParam {
            name('WORKER_IP')
            defaultValue('192.168.10.X')
            description('Enter the worker IP address')
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