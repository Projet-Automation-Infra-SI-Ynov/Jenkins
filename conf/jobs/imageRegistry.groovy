#!groovy
println('------------------------------------------------------------------Import Job CaC/image-registry')
def pipelineScript = new File('/var/jenkins_config/jobs/imageRegistry-pipeline.groovy').getText("UTF-8")

pipelineJob('CaC/image-registry') {
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
            name('REGISTRY_IP')
            defaultValue('192.168.10.X')
            description('Enter the Registry IP address')
            trim(false)
        }
        choice {
            name('IMAGES_PULL')
            choices(['registry:2.8.1', 'konradkleine/docker-registry-frontend:v2', 'vaultwarden/server:1.24.0'])
            description('Select the image in the server')
        }
        choice {
            name('IMAGESTOREGISTRY')
            choices(['registry:2.8.1', 'registry-ui:2.0', 'vaulwarden:1.24.0'])
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