#!groovy
println('------------------------------------------------------------------Import Job CaC/backup')
def pipelineScript = new File('/var/jenkins_config/jobs/backup-pipeline.groovy').getText("UTF-8")

pipelineJob('CaC/backup') {
    description("Ansible")
    parameters {
        stringParam {
            name('BRANCH')
            defaultValue('master')
            description('Branch to retrieve from Git')
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