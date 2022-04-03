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
                git branch: "${params.BRANCH}", url: 'https://github.com/Projet-Automation-Infra-SI-Ynov/Registry'
            }
        }
        stage('Add Grafana address IP') {
            steps {
                sh "sed -i 's/IP_REGISTRY/${params.REGISTRY_IP}/g' ./registry.ini"
            }
        }
        stage('Execute playbook') {
            steps {
                sh "ansible-playbook -i ./registry.ini ./registry.yml"
            }
        }
    }
}