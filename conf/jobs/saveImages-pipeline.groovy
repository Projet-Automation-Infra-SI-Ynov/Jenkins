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
        stage('Add Grafana address IP to inventory file') {
            steps {
                sh "sed -i 's/IP_GRAFANA/${params.GRAFANA_IP}/g' ./ansible/save.ini"
            }
        }
        stage('Execute playbook') {
            steps {
                sh "ansible-playbook -i ./ansible/save.ini ./ansible/save.yml"
            }
        }
        stage('Add Graylog address IP to inventory file') {
            steps {
                sh "sed -i 's/IP_LOG/${params.LOG_IP}/g' ./ansible/save-log.ini"
            }
        }
        stage('Execute playbook') {
            steps {
                sh "ansible-playbook -i ./ansible/save-log.ini ./ansible/save-log.yml"
            }
        }
    }
}