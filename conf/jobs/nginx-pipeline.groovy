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
                git branch: "${params.BRANCH}", url: 'https://github.com/Projet-Automation-Infra-SI-Ynov/Reverse-proxy'
            }
        }
        // ----------------- Prometheus -----------------
        stage('Add Jenkins address IP') {
            steps {
                sh "sed -i 's/JENKINS2/${params.JENKINS_IP}/g' ./Ansible/Nginx/tasks/main.yml"
            }
        }
        stage('Add Registry address IP') {
            steps {
                sh "sed -i 's/REGISTRY2/${params.REGISTRY_IP}/g' ./ansible/Nginx/tasks/main.yml"
            }
        }
        stage('Add backup address IP') {
            steps {
                sh "sed -i 's/GRAYLOG/${params.LOG_IP}/g' ./ansible/Nginx/tasks/main.yml"
            }
        }
        // ----------------- Grafana -----------------
        stage('Add Nginx address IP') {
            steps {
                sh "sed -i 's/IP_LOG/${params.LOG_IP}/g' ./ansible/inventory.ini"
            }
        }
        stage('Execute playbook') {
            steps {
                sh "ansible-playbook -i ./ansible/inventory.ini ./ansible/playbook.yml"
            }
        }
    }
}