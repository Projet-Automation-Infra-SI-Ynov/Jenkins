#!groovy
println('------------------------------------------------------------------Import Job CaC/image-log')
def pipelineScript = new File('/var/jenkins_config/jobs/imageLog-pipeline.groovy').getText("UTF-8")

pipelineJob('CaC/image-log') {
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
            name('LOG_IP')
            defaultValue('192.168.10.X')
            description('Enter the Graylog IP address')
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
            choices(['mongo:4.2', 'docker.elastic.co/elasticsearch/elasticsearch-oss:7.10.2', 'graylog/graylog:4.2', 'nginx:1.21.6'])
            description('Select the image in the server')
        }
        choice {
            name('IMAGESTOREGISTRY')
            choices(['mongo:4.2', 'elasticsearch:7.10.2', 'graylog:4.2', 'nginx:1.21.6'])
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