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
                git branch: "${params.BRANCH}", url: 'https://github.com/Projet-Automation-Infra-SI-Ynov/Bitwarden'
            }
        }
        stage('Add Graylog address IP') {
            steps {
                sh "sed -i 's/GRAYLOG/${params.LOG_IP}/g' ./ansible/Bitwarden/tasks/main.yml"
            }
        }
        stage('Add Registry address IP in inventory file') {
            steps {
                sh "sed -i 's/IP_REGISTRY/${params.REGISTRY_IP}/g' ./ansible/bitwarden.ini"
            }
        }
        stage('Execute playbook') {
            steps {
                sh "ansible-playbook -i ./ansible/bitwarden.ini ./ansible/bitwarden.yml"
            }
        }
    }
}