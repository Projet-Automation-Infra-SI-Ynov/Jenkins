#!groovy
println('------------------------------------------------------------------Import Job CaC/grafana')
def pipelineScript = new File('/var/jenkins_config/jobs/grafana-pipeline.groovy').getText("UTF-8")

pipelineJob('Cac/grafana') {
    description("Ansible")
    definition {
        cps {
            script(pipelineScript)
            sandbox()
        }
    }
}