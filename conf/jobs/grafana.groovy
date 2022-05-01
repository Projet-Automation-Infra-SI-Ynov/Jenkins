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
            name('GRAFANA_IP')
            defaultValue('192.168.10.X')
            description('Enter the Grafana IP address')
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
            name('BACKUP_SERVER_IP')
            defaultValue('192.168.10.X')
            description('Enter the backup server IP address')
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