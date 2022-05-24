#!groovy

pipeline {
    agent any
    environment {
        TERRAFORM = 'TERRAFORM'
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '50'))
        ansiColor('xterm')
    }
    stages {
        stage('Git checkout') {
            steps {
                git branch: "${params.BRANCH}", url: 'https://github.com/Projet-Automation-Infra-SI-Ynov/Terraform'
            }
        }
        stage('Remove useless files and folder') {
            steps {
                sh "sed -i 's/NAME/${params.SERVER_NAME}/g' ./main.tf"
            }
        }
        stage('Remove files & folders') {
            steps {
                sh "rm -rf ./*"
            }
        }
        stage('Terraform init') {
            steps {
                sh "terraform init"
            }
        }
        stage('Terraform apply') {
            steps {
                sh "terraform ${params.ACTION} --auto-approve"
            }
        }
    }
}