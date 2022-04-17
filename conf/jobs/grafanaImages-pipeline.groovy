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
        stage('Add Registry address IP to inventory file') {
            steps {
                sh "sed -i 's/IP_REGISTRY/${params.REGISTRY_IP}/g' ./Grafana/grafana.ini"
            }
        }
        stage('Add Registry address IP to playbook file') {
            steps {
                sh "sed -i 's/IP_REGISTRY/${params.REGISTRY_IP}/g' ./Grafana/grafana.yml"
            }
        }
        stage('Execute playbook') {
            steps {
                sh "ansible-playbook -i ./Grafana/grafana.ini ./Grafana/grafana.yml"
            }
        }
    }
}