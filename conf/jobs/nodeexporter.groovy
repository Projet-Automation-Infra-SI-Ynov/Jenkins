#!groovy
println('------------------------------------------------------------------Import Job CaC/node_exporter')
def pipelineScript = new File('/var/jenkins_config/jobs/nodeexporter-pipeline.groovy').getText("UTF-8")

pipelineJob('Cac/node_exporter') {
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
            name('REGISTRY_IP')
            defaultValue('192.168.10.X')
            description('Enter the Registry IP address')
            trim(false)
        }
        stringParam {
            name('MASTER_IP')
            defaultValue('192.168.10.X')
            description('Enter the K3S Master IP address')
            trim(false)
        }
            stringParam {
            name('WORKER_IP')
            defaultValue('192.168.10.X')
            description('Enter the K3S Worker IP address')
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