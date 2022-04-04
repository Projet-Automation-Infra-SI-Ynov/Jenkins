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
                git branch: "${params.BRANCH}", url: 'https://github.com/Projet-Automation-Infra-SI-Ynov/Node_exporter'
            }
        }
        stage('Execute playbook') {
            steps {
                sh "ansible-playbook -i ./inventory.ini ./node_exporter.yml"
            }
        }
    }
}