#!groovy
println('------------------------------------------------------------------Import Job CaC/node_exporter')
def pipelineScript = new File('/var/jenkins_config/jobs/nodeexporter-pipeline.groovy').getText("UTF-8")

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
            name('MASTER_IP')
            defaultValue('192.168.10.X')
            description('Enter the master IP address')
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