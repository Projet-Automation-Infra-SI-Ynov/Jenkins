#!groovy
println('------------------------------------------------------------------Import Job CaC/grafana')
def pipelineScript = new File('/var/jenkins_config/jobs/grafana-pipeline.groovy').getText("UTF-8")

pipelineJob('Cac/grafana') {
    description("Ansible")
    parameters {
        stringParam {
            name('GRAFANA_IP')
            defaultValue('172.16.100.X')
            description('Enter the Grafana IP address')
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