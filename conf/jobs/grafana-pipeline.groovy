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
        stage('git checkout') {
            steps {
                dir("/home/centos/docker/grafana"){
                    git branch: "${params.BRANCH}", url: 'https://github.com/Projet-Automation-Infra-SI-Ynov/Grafana'
                }
            }
        }
        stage('Modify hosts folder') {
            steps {
                sh "sed -i 's/IP_JENKINS/${params.PUBLIC_IP}/g' /home/centos/docker/grafana/prometheus.yml "
            }
        }
        stage('Execute playbook') {
            steps {
                dir("/home/centos/ansible/"){
                    sh "ansible-playbook -i inventory/hosts --user centos grafana.yml"
                }
            }
        }
    }
}