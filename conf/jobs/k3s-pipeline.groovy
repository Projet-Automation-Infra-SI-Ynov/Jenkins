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
                git branch: "${params.BRANCH}", url: 'https://github.com/Projet-Automation-Infra-SI-Ynov/Deploy_k3s'
            }
        }
        stage('')
            steps {
                sh "sed -i 's/###IP_MASTER###/${params.MASTER_IP}/g' ./k3s-worker/tasks/main.yml "
            }
        stage('Execute playbook') {
            steps {
                sh "ansible-playbook -i ./inventory.ini ./playbook.yml"
            }
        }
    }
}