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
        stage('Execute script to install k3s') {
            steps {
                sh "sh ./k3s_launch.sh"
            }
        }
    }
}