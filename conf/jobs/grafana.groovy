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
            name('GF_USER')
            defaultValue('admin')
            description('Define grafana user')
            trim(false)
        }
        stringParam {
            name('GF_PASSWORD')
            defaultValue('X')
            description('Define password Grafana user')
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
        stringParam {
            name('MASTER_IP')
            defaultValue('192.168.10.X')
            description('Enter the backup server IP address')
            trim(false)
        }
        stringParam {
            name('WORKER_IP')
            defaultValue('192.168.10.X')
            description('Enter the backup server IP address')
            trim(false)
        }
        stringParam {
            name('LOG_IP')
            defaultValue('192.168.10.X')
            description('Enter the Graylog IP address')
            trim(false)
        }
        stringParam {
            name('DNS_IP')
            defaultValue('192.168.10.253')
            description('Enter the DNS IP address')
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