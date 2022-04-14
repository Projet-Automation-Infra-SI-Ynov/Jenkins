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
        // stage('Remove useless files and folder') {
        //     steps {
        //         sh "rm -rf terraform/"
        //         sh "rm -rf .terraform.lock.hcl"
        //         sh "rm -rf terraform.tfstate"
        //         sh "rm -rf terraform.tfstate.backup"
        //     }
        // }
        stage('Remove useless files and folder') {
            steps {
                sh "sed -i 's/NAME/${params.SERVER_NAME}/g' ./main.tf"
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