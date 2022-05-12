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
        stage('Git checkout ') {
            steps {
                git branch: "${params.BRANCH}", url: 'https://github.com/Projet-Automation-Infra-SI-Ynov/Deploy_k3s'
            }
        }
        stage('Add k3s-master IP address to the token to join worker with his') {
            steps {
                sh "sed -i 's/###IP_MASTER###/${params.MASTER_IP}/g' ./k3s-worker/tasks/main.yml "
            }
        }
        stage('Add k3s-master IP address into inventory file') {
            steps {
                sh "sed -i 's/IP_MASTER/${params.MASTER_IP}/g' ./inventory.ini "
            }
        }
        stage('Add k3s-worker IP address into inventory file') {
            steps {
                sh "sed -i 's/IP_WORKER/${params.WORKER_IP}/g' ./inventory.ini "
            }
        }
        stage('Execute playbook') {
            steps {
                sh "ansible-playbook -i ./inventory.ini ./playbook.yml"
            }
        }
    }
}