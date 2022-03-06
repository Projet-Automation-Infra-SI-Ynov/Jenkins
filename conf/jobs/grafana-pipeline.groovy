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
        stage('Execute playbook') {
            steps {
                sh "ansible-playbook -i /var/jenkins_config/inventory.ini -u centos -kK /var/jenkins_config/grafana.yml"
            }
        }
    }
}