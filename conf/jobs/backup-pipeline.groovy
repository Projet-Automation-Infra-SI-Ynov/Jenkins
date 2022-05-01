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
                git branch: "${params.BRANCH}", url: 'https://github.com/Projet-Automation-Infra-SI-Ynov/Backup'
            }
        }
        stage('Add backup server address IP in inventory file') {
            steps {
                sh "sed -i 's/IP_BACKUP_SERVER/${params.BACKUP_SERVER_IP}/g' ./backup.ini"
            }
        }
        stage('Execute playbook') {
            steps {
                sh "ansible-playbook -i ./backup.ini ./backup.yml"
            }
        }
    }
}