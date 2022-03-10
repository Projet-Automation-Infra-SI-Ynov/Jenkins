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
        stage('Add Grafana address IP') {
            steps {
                sh "sed -i 's/IP_GRAFANA/${params.GRAFANA_IP}/g' /home/ansible/inventory.ini"
            }
        }
        stage('Execute playbook') {
            steps {
                sh "ansible-playbook -i /home/ansible/inventory.ini /home/ansible/grafana.yml"
            }
        }
    }
}