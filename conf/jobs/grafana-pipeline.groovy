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
                git branch: "${params.BRANCH}", url: 'https://github.com/Projet-Automation-Infra-SI-Ynov/Grafana'
            }
        }
        stage('Add Grafana address IP') {
            steps {
                sh "sed -i 's/IP_GRAFANA/${params.GRAFANA_IP}/g' ./Ansible/grafana.ini"
            }
        }
        stage('Execute playbook') {
            steps {
                sh "ansible-playbook -i ./Ansible/grafana.ini ./Ansible/grafana.yml"
            }
        }
    }
}