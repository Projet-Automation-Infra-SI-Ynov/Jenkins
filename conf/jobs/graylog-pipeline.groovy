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
                git branch: "${params.BRANCH}", url: 'https://github.com/Projet-Automation-Infra-SI-Ynov/Graylog'
            }
        }
        stage('Add registry address IP in ansible role') {
            steps {
                sh "sed -i 's/REGISTRY2/${params.REGISTRY_IP}/g' ./ansible/Graylog/tasks/main.yml"
            }
        }
        stage('Add Graylog address IP in inventory file') {
            steps {
                sh "sed -i 's/IP_LOG/${params.LOG_IP}/g' ./ansible/graylog.ini"
            }
        }
        stage('Execute playbook') {
            steps {
                sh "ansible-playbook -i ./ansible/graylog.ini ./ansible/graylog.yml"
            }
        }
    }
}