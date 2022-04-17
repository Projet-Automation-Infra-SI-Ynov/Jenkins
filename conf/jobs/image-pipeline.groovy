#!groovy

pipeline {
    agent any
    environment {
        ANSIBLE = 'ANSIBLE'
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '50'))
        ansiColor('xterm')
    }
    stages {
        stage('Git checkout') {
            steps {
                git branch: "${params.BRANCH}", url: 'https://github.com/Projet-Automation-Infra-SI-Ynov/Images'
            }
        }
        stage('Add user for login to registry') {
            steps {
                sh "sed -i 's/USER/${params.USERNAME}/g' ./image.yml"
            }
        }
        stage('Add password for login to registry') {
            steps {
                sh "sed -i 's/PASSWORD/${params.PASSWORD}/g' ./image.yml"
            }
        }
        stage('Add Registry address IP to inventory file') {
            steps {
                sh "sed -i 's/IP_REGISTRY/${params.REGISTRY_IP}/g' ./image.ini"
            }
        }
        stage('Add Registry address IP to playbook file') {
            steps {
                sh "sed -i 's/IP_REGISTRY/${params.REGISTRY_IP}/g' ./image.yml"
            }
        }
        stage('Select image to playbook file') {
            steps {
                sh "sed -i 's%IMAGES_PULL%${params.IMAGES_PULL}%g' ./image.yml"
                // On met des '%' car il peut y avoir un '/' dans l'image. Exemple : grafana/grafana:8.4.4
            }
        }
        stage('Add image push to playbook file') {
            steps {
                sh "sed -i 's/IMAGEREGISTRY/${params.IMAGESTOREGISTRY}/g' ./image.yml"
            }
        }
        stage('Execute playbook') {
            steps {
                sh "ansible-playbook -i ./image.ini ./image.yml"
            }
        }
    }
}