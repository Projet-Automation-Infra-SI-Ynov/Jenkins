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
        // ----------------- Prometheus -----------------
        stage('Add Jenkins address IP') {
            steps {
                sh "sed -i 's/JENKINS2/${params.JENKINS_IP}/g' ./Ansible/Grafana/tasks/main.yml"
            }
        }
        stage('Add Registry address IP') {
            steps {
                sh "sed -i 's/REGISTRY2/${params.REGISTRY_IP}/g' ./Ansible/Grafana/tasks/main.yml"
            }
        }
        stage('Add backup address IP') {
            steps {
                sh "sed -i 's/BACKUP2/${params.BACKUP_SERVER_IP}/g' ./Ansible/Grafana/tasks/main.yml"
            }
        }
        // ----------------- Grafana -----------------
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