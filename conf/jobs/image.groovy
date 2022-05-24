#!groovy
println('------------------------------------------------------------------Import Job CaC/image-tools')
def pipelineScript = new File('/var/jenkins_config/jobs/image-pipeline.groovy').getText("UTF-8")

pipelineJob('CaC/image-tools') {
    description("Terraform")
    parameters {
        stringParam {
            name('BRANCH')
            defaultValue('master')
            description('Branch to retrieve from Git')
            trim(false)
        }
        stringParam {
            name('USERNAME')
            defaultValue('administrateur')
            description('Enter user to push images')
            trim(false)
        }
        stringParam {
            name('PASSWORD')
            defaultValue('X')
            description('Enter the password of user')
            trim(false)
        }
        stringParam {
            name('GRAFANA_IP')
            defaultValue('192.168.10.X')
            description('Enter the Registry IP address')
            trim(false)
        }
        stringParam {
            name('REGISTRY_IP')
            defaultValue('192.168.10.X')
            description('Enter the Registry IP address')
            trim(false)
        }
        choice {
            name('IMAGES_PULL')
            choices(['jenkins', 'grafana/grafana:8.4.4', 'prom/prometheus:v2.34.0', 'quay.io/prometheus/node-exporter'])
            description('Select the image in the server')
        }
        choice {
            name('IMAGESTOREGISTRY')
            choices(['jenkins:1.0', 'grafana:8.4.4', 'prometheus:2.34.0', 'node_exporter:1.0'])
            description('Select the image to push in the registry')
        }
    }
    definition {
        cps {
            script(pipelineScript)
            sandbox()
        }
    }
}