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
                git branch: "${params.BRANCH}", url: 'https://github.com/Projet-Automation-Infra-SI-Ynov/Saves'
            }
        }
        stage('Replace params to inventory file') {
            steps {
                sh "sed -i 's/IP_GRAFANA/${params.GRAFANA_IP}/g' ./ansible/dockerTools/tools.ini"
                sh "sed -i 's/PASSWORD/${params.PASSWORD}/g' ./ansible/dockerTools/tools.yml"
                sh "sed -i 's/USER/${params.USERNAME}/g' ./ansible/dockerTools/tools.yml"
                sh "sed -i 's/IP_BACKUP_SERVER/${params.BACKUP_SERVER_IP}/g' ./ansible/dockerTools/tools.yml"
            }
        }
        stage('Add registry address IP to inventory file') {
            steps {
                sh "sed -i 's/IP_REGISTRY/${params.REGISTRY_IP}/g' ./ansible/dockerRegistry/registry.ini"
                sh "sed -i 's/PASSWORD/${params.PASSWORD}/g' ./ansible/dockerRegistry/registry.yml"
                sh "sed -i 's/USER/${params.USERNAME}/g' ./ansible/dockerRegistry/registry.yml"
                sh "sed -i 's/IP_BACKUP_SERVER/${params.BACKUP_SERVER_IP}/g' ./ansible/dockerRegistry/registry.yml"
            }
        }
        stage('Execute docker tools playbook') {
            steps {
                sh "ansible-playbook -i ./ansible/dockerTools/tools.ini ./ansible/dockerTools/tools.yml"
            }
        }
        stage('Execute docker registry playbook') {
            steps {
                sh "ansible-playbook -i ./ansible/dockerRegistry/registry.ini ./ansible/dockerRegistry/registry.yml"
            }
        }
    }
}