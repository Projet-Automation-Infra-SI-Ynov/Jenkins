#!groovy
println('------------------------------------------------------------------Import Job Saves/saveFolder')
def pipelineScript = new File('/var/jenkins_config/jobs/saveFolder-pipeline.groovy').getText("UTF-8")

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
            name('REGISTRY_IP')
            defaultValue('192.168.10.X')
            description('Enter the registry IP address')
            trim(false)
        }
        stringParam {
            name('BACKUP_SERVER_IP')
            defaultValue('192.168.10.X')
            description('Enter the backup server IP address')
            trim(false)
        }
        stringParam {
            name('USERNAME')
            defaultValue('administrateur')
            description('Enter user of the backup server')
            trim(false)
        }
        stringParam {
            name('PASSWORD')
            defaultValue('X')
            description('Enter password of user')
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